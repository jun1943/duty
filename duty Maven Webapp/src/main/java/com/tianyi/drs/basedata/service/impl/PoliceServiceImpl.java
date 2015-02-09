package com.tianyi.drs.basedata.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tianyi.drs.basedata.dao.PoliceMapper; 
import com.tianyi.drs.basedata.model.IntercomGroup;
import com.tianyi.drs.basedata.model.Police;
import com.tianyi.drs.basedata.model.PoliceType; 
import com.tianyi.drs.basedata.service.PoliceService;
import com.tianyi.drs.basedata.viewmodel.GpsBaseVM;
import com.tianyi.drs.basedata.viewmodel.PoliceVM;
import com.tianyi.drs.duty.dao.ExportMapper;
import com.tianyi.drs.duty.exportmodel.ExtDbResult;
import com.tianyi.drs.duty.exportmodel.ExtItem;
import com.tianyi.drs.duty.exportmodel.ExtShiftInfo;
import com.tianyi.drs.duty.viewmodel.UserObjectVM;
import com.tianyi.util.PaginationData;

/**
 * 警员接口实现
 * 
 * @author lq
 * 
 */
@Service("policeService")
public class PoliceServiceImpl implements PoliceService {

	@Resource(name = "policeMapper")
	private PoliceMapper policeMapper;

	@Resource(name = "exportMapper")
	private ExportMapper exportMapper;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.PoliceService#
	 */
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return policeMapper.deleteByPrimaryKey(id);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.PoliceService#
	 */
	public int insert(Police record) {
		// TODO Auto-generated method stub
		return policeMapper.insert(record);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.PoliceService#
	 */
	public int insertSelective(Police record) {
		// TODO Auto-generated method stub
		return policeMapper.insertSelective(record);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.PoliceService#
	 */
	public Police selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return policeMapper.selectByPrimaryKey(id);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.PoliceService#
	 */
	public int updateByPrimaryKeySelective(Police record) {
		// TODO Auto-generated method stub
		return policeMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.PoliceService#
	 */
	public int updateByPrimaryKey(Police record) {
		// TODO Auto-generated method stub
		return policeMapper.updateByPrimaryKey(record);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.PoliceService#
	 */
	public List<Police> findBycode(String code) {
		// TODO Auto-generated method stub
		return policeMapper.findBycode(code);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.PoliceService#
	 */
	public Police findByname(String name) {
		// TODO Auto-generated method stub
		return policeMapper.findByname(name);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.PoliceService#
	 */
	public List<Police> selectAll() {
		// TODO Auto-generated method stub
		return policeMapper.selectAll();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.PoliceService#
	 */
	public int updatePolice(Police police) {
		// TODO Auto-generated method stub
		return policeMapper.updatePolice(police);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.PoliceService#
	 */
	public Police login(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return policeMapper.login(params);
	}

	public int findCount(PoliceVM police) {
		int count = policeMapper.countByExample(police);
		return count;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.PoliceService#findPageList(PoliceVM
	 *      police, PaginationData page)
	 */
	public List<PoliceVM> findPageList(PoliceVM police, PaginationData page) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (!(police.getNumber() == null || police.getNumber().length() == 0))
			map.put("number", police.getNumber());

		map.put("pageStart", page.getStartIndex());
		map.put("pageSize", page.getPageSize());
		List<PoliceVM> list = policeMapper.selectWithPage(map);

		return list;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see 
	 *      com.tianyi.drs.basedata.service.PoliceService#loadVMCount(Map<String,
	 *      Object> map)
	 */
	public int loadVMCount(Map<String, Object> map) {
		int count = policeMapper.loadVMCount(map);
		return count;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.PoliceService#loadVMList(Map<String,
	 *      Object> map)
	 */
	public List<PoliceVM> loadVMList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<PoliceVM> list = policeMapper.loadVMList(map);

		return list;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.PoliceService#selectPoliceType()
	 */
	public List<PoliceType> selectPoliceType() {
		// TODO Auto-generated method stub
		return policeMapper.selectPoliceType();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.PoliceService#selectIntercomGroup()
	 */
	public List<IntercomGroup> selectIntercomGroup() {
		// TODO Auto-generated method stub
		return policeMapper.selectIntercomGroup();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.PoliceService#selectGpsId(int orgId)
	 */
	public List<GpsBaseVM> selectGpsId(int orgId) {
		// TODO Auto-generated method stub
		return policeMapper.selectGpsId(orgId);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see 
	 *      com.tianyi.drs.basedata.service.PoliceService#loadVMListWithGroup(Map
	 *      <String, Object> map)
	 */
	public List<PoliceVM> loadVMListWithGroup(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<PoliceVM> list = policeMapper.loadVMListWithGroup(map);
		return list;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.PoliceService#findByidCard(String
	 *      param)
	 */
	public List<Police> findByidCard(String param) {
		// TODO Auto-generated method stub
		return policeMapper.findByidCard(param);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see 
	 *      com.tianyi.drs.basedata.service.PoliceService#loadVMListWithGroupList
	 *      (Map<String, Object> map)
	 */
	public List<PoliceVM> loadVMListWithGroupList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<PoliceVM> list = policeMapper.loadVMListWithGroupList(map);
		return list;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see 
	 *      com.tianyi.drs.basedata.service.PoliceService#deleteByIds(Map<String,
	 *      Object> map)
	 */
	public void deleteByIds(Map<String, Object> map) {
		// TODO Auto-generated method stub
		policeMapper.deleteByIds(map);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see 
	 *      com.tianyi.drs.basedata.service.PoliceService#getUserAuthorization(Map
	 *      <String, Object> map)
	 */
	public UserObjectVM getUserAuthorization(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return policeMapper.getUserAuthorization(map);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.PoliceService#changePoliceStateByIds(Map)
	 */
	public void changePoliceStateByIds(Map<String, Object> map) {
		// TODO Auto-generated method stub
		policeMapper.changePoliceStateByIds(map);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.PoliceService#findByintercomPerson(String)
	 */
	public List<Police> findByintercomPerson(String param) {
		// TODO Auto-generated method stub
		return policeMapper.findByintercomPerson(param);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.PoliceService#loadVMListWithOrgId(Integer)
	 */
	public List<PoliceVM> loadVMListWithOrgId(Integer orgId) {
		// TODO Auto-generated method stub
		return policeMapper.loadVMListWithOrgId(orgId);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.PoliceService#findByIdAndDtyId(String)
	 */
	public List<Police> findByIdAndDtyId(String param) {
		// TODO Auto-generated method stub
		return policeMapper.findByIdAndDtyId(param);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.PoliceService#loadListByOrgId(Map)
	 */
	public List<Police> loadListByOrgId(Integer orgId) {
		// TODO Auto-generated method stub
		return policeMapper.loadListByOrgId(orgId);
	}

	public List<ExtItem<Police>> loadPoliceDutyInfo(Integer orgId, Integer ymd) {
		Map<Integer, ExtItem<?>> cache = new HashMap<Integer, ExtItem<?>>();// dutyItemId局部缓存，避免大量低效率的循环。
		Map<Integer, Object> cache2 = new HashMap<Integer, Object>();// ItemId
																		// 局部缓存，避免大量低效率的循环。Object无意义，都为null

		List<ExtItem<Police>> eps = new ArrayList<ExtItem<Police>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);

		if (ymd == null || ymd == 0) {
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			String date = dateFormat.format(now);
			ymd = Integer.parseInt(date);

		}
		map.put("ymd", ymd);

		List<ExtDbResult> rs = exportMapper.loadDutyItemInfo(map);

		for (ExtDbResult r : rs) {
			if (r.getItemTypeId() == 2) {
				@SuppressWarnings("unchecked")
				ExtItem<Police> ep = (ExtItem<Police>) createItemInfo(r);
				ep.setShiftInfo(createShiftInfo(r)); // 只有第一层需要写班次信息

				cache.put(ep.getDutyItemId(), ep);// 添加到缓存
				cache2.put(ep.getData().getId(), null);
				eps.add(ep);// 添加到list

			} else {
				if (cache.containsKey(r.getParentId())) {
					@SuppressWarnings("unchecked")
					ExtItem<Police> pp = (ExtItem<Police>) cache.get(r
							.getParentId());
					if (pp.getItems() == null) {
						pp.setItems(new ArrayList<ExtItem<?>>());
					}
					ExtItem<?> cp = (ExtItem<?>) createItemInfo(r);
					pp.getItems().add(cp);
					cache.put(r.getDutyItemId(), cp);

				}
			}
		}

		List<Police> mps = policeMapper.loadListByOrgId(orgId);

		for (Police mp : mps) {
			if (!cache2.containsKey(mp.getId())) {
				ExtItem<Police> ep2 = new ExtItem<Police>();
				ep2.setData(mp);
				eps.add(ep2);
			}
		}

		return eps;

	}

	private Object createItemInfo(ExtDbResult result) {
		ExtItem<Object> item = new ExtItem<Object>();
		Object data = null;

		data = this.createPolice(result);

		item.setDutyItemId(result.getDutyItemId());
		item.setData(data);
		item.setItemTypeId(result.getItemTypeId());
		item.setLevel(result.getLevel());

		return item;
	}

	private ExtShiftInfo createShiftInfo(ExtDbResult result) {
		ExtShiftInfo info = new ExtShiftInfo();
		info.setBeginTime(result.getBeginTime());
		info.setDutyTypeId(result.getDutyTypeId());
		info.setDutyTypeName(result.getDutyTypeName());
		info.setEndTime(result.getEndTime());
		info.setShiftId(result.getDutyItemId());
		info.setShiftName(result.getName());

		return info;
	}
 

	private Police createPolice(ExtDbResult result) {
		Police p = new Police();
		p.setGpsId(result.getPoliceGpsId());
		p.setGpsName(result.getPoliceGpsName());
		p.setId(result.getPoliceId());
		p.setIdcardno(result.getPoliceIdcardno());
		p.setIntercomGroup(result.getPoliceIntercomGroup());
		p.setIntercomPerson(result.getPoliceIntercomPerson());
		p.setMobile(result.getPoliceMobile());
		p.setMobileShort(result.getPoliceMobileShort());
		p.setName(result.getPoliceName());
		p.setNumber(result.getPoliceNumber());
		p.setOrgId(result.getOrgId());

		return p;
	}
 

}
