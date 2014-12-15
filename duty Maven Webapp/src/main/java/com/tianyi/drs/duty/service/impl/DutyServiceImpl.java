package com.tianyi.drs.duty.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianyi.drs.duty.dao.DutyItemMapper;
import com.tianyi.drs.duty.dao.DutyMapper;
import com.tianyi.drs.duty.dao.PoliceTargetMapper;
import com.tianyi.drs.duty.model.Duty;
import com.tianyi.drs.duty.model.DutyItem;
import com.tianyi.drs.duty.model.DutyProperty;
import com.tianyi.drs.duty.model.PoliceTarget;
import com.tianyi.drs.duty.service.DutyService;
import com.tianyi.drs.duty.viewmodel.DutyItemCountVM;
import com.tianyi.drs.duty.viewmodel.DutyItemVM;
import com.tianyi.drs.duty.viewmodel.DutyVM;

/**
 * 勤务报备服务接口实现
 * @author lq
 *
 */
@Service("dutyService")
public class DutyServiceImpl implements DutyService {

	@Resource(name = "dutyMapper")
	private DutyMapper dutyMapper;

	@Resource(name = "dutyItemMapper")
	private DutyItemMapper dutyItemMapper;

	@Resource(name = "policeTargetMapper")
	private PoliceTargetMapper policeTargetMapper;

	/**
	 * 获取勤务报备数据列表，
	 */
	public List<DutyVM> loadVMList(Map<String, Object> map) {
		List<DutyVM> dvms = dutyMapper.loadDutyVMList(map);
		itemToTreeOfList(dvms);
		return dvms;
	}

	/**
	 * 根据组织机构id，日期，获取勤务报备数据以及明细
	 */
	public DutyVM loadVMByOrgIdAndYmd(Integer orgId, Integer ymd) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("ymd", ymd);
		map.put("isTemplate", false);
		List<DutyVM> dvms = loadVMList(map);

		DutyVM dvm = null;

		if (dvms.size() > 0) {
			dvm = dvms.get(0);
		}
		return dvm;
	}

	/**
	 * 根据id，获取勤务报备信息
	 */
	public DutyVM loadById(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		List<DutyVM> dvms = loadVMList(map);

		DutyVM dvm = null;

		if (dvms.size() > 0) {
			dvm = dvms.get(0);
		}
		return dvm;
	}

	/**
	 * 将报备数据明细构建为树状结构列表
	 * @param dvms
	 */
	private void itemToTreeOfList(List<DutyVM> dvms) {
		for (DutyVM dvm : dvms) {
			Map<Integer, DutyItemVM> map = new HashMap<Integer, DutyItemVM>();
			List<DutyItemVM> ls = dvm.getItems();
			dvm.setItems(new ArrayList<DutyItemVM>());
			for (DutyItemVM ivm : ls) {
				if (ivm.getParentId() != null && ivm.getParentId() != 0) {
					DutyItemVM pivm = map.get(ivm.getParentId());
					if (pivm.getChildren() == null)
						pivm.setChildren(new ArrayList<DutyItemVM>());
					map.get(ivm.getParentId()).getChildren().add(ivm);
				} else {
					dvm.getItems().add(ivm);
				}
				map.put(ivm.getId(), ivm);
			}
		}

	}

	/**
	 * 获取报备模板，用于报备模板选择
	 */
	public List<Duty> loadTemplatesWithOutItem(Integer orgId) {
		List<Duty> vms = dutyMapper.loadTemplatesWithOutItem(orgId);
		return vms;
	}

	/**
	 * 保存勤务报备数据信息
	 */
	@Transactional
	public void save(DutyVM vm) {
		vm.setCreateTime(new Date());

		if (vm.getId() == 0) {
			dutyMapper.insert(vm);
		} else {
			dutyMapper.updateByPrimaryKey(vm);
		}

		dutyItemMapper.deleteByDutyId(vm.getId());
		policeTargetMapper.deleteByDutyId(vm.getId());

		for (DutyItemVM ivm : vm.getItems()) {
			saveItem(ivm, null, vm);
		}
	}

	/**
	 * 保存勤务报备数据明细
	 * @param ivm
	 * @param pivm
	 * @param vm
	 */
	private void saveItem(DutyItemVM ivm, DutyItemVM pivm, DutyVM vm) {

		ivm.setId(0);
		ivm.setDutyId(vm.getId());

		if (ivm.getItemTypeId() == 100) {
			ivm.setDutyTypeId(ivm.getItemId());
			ivm.setBeginTime(null);
			ivm.setEndTime(null);
		} else {
			ivm.setDutyTypeId(pivm.getDutyTypeId());
		}

		if (ivm.getItemTypeId() != 101 && pivm != null) {
			ivm.setBeginTime(pivm.getBeginTime());
			ivm.setEndTime(pivm.getEndTime());
		}

		if (pivm == null) {
			ivm.setLevel(1);
			ivm.setParentId(null);
		} else {
			ivm.setLevel(pivm.getLevel() + 1);
			ivm.setParentId(pivm.getId());
		}

		if (ivm.getChildren() != null && ivm.getChildren().size() > 0) {
			ivm.setIsLeaf(false);
		} else {
			ivm.setIsLeaf(true);
		}

		dutyItemMapper.insert(ivm);
		ivm.setFullIdPath(ivm.getLevel() == 1 ? ivm.getId().toString() : pivm
				.getFullIdPath() + "." + ivm.getId());
		dutyItemMapper.updateByPrimaryKey(ivm);

		if (ivm.getItemTypeId() == 2) {
			if (ivm.getTargets() != null) {
				for (PoliceTarget pt : ivm.getTargets()) {
					pt.setDutyId(vm.getId());
					pt.setDutyItemId(ivm.getId());
					policeTargetMapper.insert(pt);
				}
			}
		}

		if (ivm.getChildren() != null) {

			for (DutyItemVM civm : ivm.getChildren()) {
				saveItem(civm, ivm, vm);
			}
		}
	}

	/**
	 * 获取勤务报备警力统计汇总
	 */
	public List<DutyItemCountVM> loadTotalPolice(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dutyMapper.loadTotalPolice(map);
	}

	/**
	 * 获取勤务报备警力详细数据信息
	 */
	public List<DutyItemCountVM> loadTotalPolicedetail(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dutyMapper.loadTotalPolicedetail(map);
	}

	/**
	 * 查询勤务类型属性列表
	 */
	public List<DutyProperty> selectdutyProperty() {
		// TODO Auto-generated method stub
		return dutyMapper.selectdutyProperty();
	}

	/**
	 * 根据报备id，删除报备数据
	 */
	public void deleteByDutyId(int dutyId) {
		// TODO Auto-generated method stub
		dutyItemMapper.deleteByDutyId(dutyId);
	}

	/**
	 * 根据日期，删除报备数据
	 */
	public void deleteByYMD(Integer targetYmd) {
		// TODO Auto-generated method stub
		dutyMapper.deleteByYMD(targetYmd);
	}

	/**
	 * 插入报备数据
	 */
	public int insert(Duty nduty) {
		// TODO Auto-generated method stub
		return dutyMapper.insert(nduty);
	}

	/**
	 * 根据报备id，获取报备明细数据
	 */
	public List<DutyItem> loadlistByDutyId(Integer id) {
		// TODO Auto-generated method stub
		return dutyItemMapper.loadlistByDutyId(id);
	}

	/**
	 * 插入报备明细数据
	 */
	public int insertDutyItem(DutyItem di) {
		// TODO Auto-generated method stub
		return dutyItemMapper.insert(di);
	}

	/**
	 * 根据组织机构id，日期，获取勤务报备数据，用于报备日历页面显示详细数据
	 */
	public List<Duty> loadVMListByOrgAndYmd(Map<String, Object> maps) {
		// TODO Auto-generated method stub
		return dutyMapper.loadVMListByOrgAndYmd(maps);
	}

	/**
	 * 清除所有报备数据信息
	 */
	public void deleteByDutyIdlist(Map<String, Object> map) {
		// TODO Auto-generated method stub
		dutyItemMapper.deleteByDutyIdlist(map);
	}

	/**
	 * 根据id，删除报备数据对象
	 */
	public void deleteByPrimaryKey(int dId) {
		// TODO Auto-generated method stub
		dutyMapper.deleteByPrimaryKey(dId);
	}
}
