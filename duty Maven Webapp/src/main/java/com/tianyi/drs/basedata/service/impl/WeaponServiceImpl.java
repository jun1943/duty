package com.tianyi.drs.basedata.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
 
import com.tianyi.drs.basedata.dao.WeaponMapper; 
import com.tianyi.drs.basedata.model.Weapon;
import com.tianyi.drs.basedata.model.WeaponType;
import com.tianyi.drs.basedata.service.WeaponService; 
import com.tianyi.drs.basedata.viewmodel.WeaponItemVM;
import com.tianyi.drs.basedata.viewmodel.WeaponVM;
import com.tianyi.drs.duty.dao.ExportMapper;
import com.tianyi.drs.duty.exportmodel.ExtDbResult;
import com.tianyi.drs.duty.exportmodel.ExtItem;
import com.tianyi.drs.duty.exportmodel.ExtShiftInfo;
import com.tianyi.util.PaginationData;
/**
 * 武器接口实现
 * @author lq
 *
 */
@Service("weaponService")
public class WeaponServiceImpl implements WeaponService {

	@Resource(name="weaponMapper")
	private WeaponMapper weaponMapper;

	@Resource(name = "exportMapper")
	private ExportMapper exportMapper;
	
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.WeaponService#deleteByPrimaryKey(Integer)
	 */
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return weaponMapper.deleteByPrimaryKey(id);
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.WeaponService#insert(Weapon)
	 */
	public int insert(Weapon record) {
		// TODO Auto-generated method stub
		return weaponMapper.insert(record);
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.WeaponService#insertSelective(Weapon)
	 */
	public int insertSelective(Weapon record) {
		// TODO Auto-generated method stub
		return weaponMapper.insertSelective(record);
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.WeaponService#selectByPrimaryKey(Integer)
	 */
	public Weapon selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return weaponMapper.selectByPrimaryKey(id);
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.WeaponService#updateByPrimaryKeySelective(Weapon)
	 */
	public int updateByPrimaryKeySelective(Weapon record) {
		// TODO Auto-generated method stub
		return weaponMapper.updateByPrimaryKeySelective(record);
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.WeaponService#updateByPrimaryKey(Weapon)
	 */
	public int updateByPrimaryKey(Weapon record) {
		// TODO Auto-generated method stub
		return weaponMapper.updateByPrimaryKey(record);
	} 
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.WeaponService#selectAll()
	 */
	public List<Weapon> selectAll() {
		// TODO Auto-generated method stub
		return weaponMapper.selectAll();
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.WeaponService#findCount(Weapon)
	 */
	public int findCount(Weapon weapon) {
		int count = weaponMapper.countByExample(weapon);
		return count;
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.WeaponService#findPageList(Weapon, PaginationData)
	 */
	public List<Weapon> findPageList(Weapon query, PaginationData page) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(!(query.getNumber() == null || query.getNumber().length() ==0) )
			map.put("number", query.getNumber());
		
		map.put("pageStart", page.getStartIndex());
		map.put("pageSize", page.getPageSize());
		List<Weapon> list = weaponMapper.selectByExample(map);
		
		return list;
	}
 
	  
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.WeaponService#loadVMCount(Map)
	 */
	public int loadVMCount(Map<String, Object> map) {
		int count= weaponMapper.loadVMCount(map);
		return count;
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.WeaponService#loadVMList(Map<String, Object> map)
	 */
	public List<WeaponVM> loadVMList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<WeaponVM> list = weaponMapper.loadVMList(map);
		
		return list;
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.WeaponService#selectWeaponType()
	 */
	public List<WeaponType> selectWeaponType() {
		// TODO Auto-generated method stub
		return weaponMapper.selectWeaponType();
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.WeaponService#loadVMListWithGroup(Map<String, Object> map)
	 */
	public List<WeaponVM> loadVMListWithGroup(Map<String, Object> map) {
		List<WeaponVM> list = weaponMapper.loadVMListWithGroup(map);
		return list;
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.WeaponService#deleteByIds(Map<String, Object> map)
	 */
	public void deleteByIds(Map<String, Object> map) {
		// TODO Auto-generated method stub
		weaponMapper.deleteByIds(map);
	}
	public List<Weapon> findByNumber(String param) {
		// TODO Auto-generated method stub
		return weaponMapper.findByNumber(param);
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.WeaponService#findByIdAndDtyId(String)
	 */
	public List<Weapon> findByIdAndDtyId(String param) {
		// TODO Auto-generated method stub
		return weaponMapper.findByIdAndDtyId(param);
	}
	public List<WeaponItemVM> getWeaponInfo(Integer orgId) {
		// TODO Auto-generated method stub
		return weaponMapper.getWeaponInfo(orgId);
	} 

	public List<ExtItem<Weapon>> getWeaponDutyInfo(Integer orgId, Integer ymd) {
		Map<Integer, ExtItem<?>> cache = new HashMap<Integer, ExtItem<?>>();// dutyItemId局部缓存，避免大量低效率的循环。
		Map<Integer, Object> cache2 = new HashMap<Integer, Object>();// ItemId
																		// 局部缓存，避免大量低效率的循环。Object无意义，都为null

		List<ExtItem<Weapon>> eps = new ArrayList<ExtItem<Weapon>>();

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
			if (r.getItemTypeId() == 3) {
				@SuppressWarnings("unchecked")
				ExtItem<Weapon> ep = (ExtItem<Weapon>) this.createItemInfo(r);
				ep.setShiftInfo(this.createShiftInfo(r)); // 只有第一层需要写班次信息

				cache.put(ep.getDutyItemId(), ep);// 添加到缓存
				cache2.put(ep.getData().getId(), null);
				eps.add(ep);// 添加到list

			} else {
				if (cache.containsKey(r.getParentId())) {
					@SuppressWarnings("unchecked")
					ExtItem<Weapon> pp = (ExtItem<Weapon>) cache.get(r
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

		List<WeaponItemVM> mps = weaponMapper.getWeaponInfo(orgId);

		for (Weapon mp : mps) {
			if (!cache2.containsKey(mp.getId())) {
				ExtItem<Weapon> ep2 = new ExtItem<Weapon>();
				ep2.setData(mp);
				eps.add(ep2);
			}
		}

		return eps;
	}
	


	private Object createItemInfo(ExtDbResult result) {
		ExtItem<Object> item = new ExtItem<Object>();
		Object data = null;

		data = this.createWeapon(result);

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
 


	private Weapon createWeapon(ExtDbResult result) {
		Weapon w = new Weapon();
		w.setId(result.getWeaponId());
		w.setNumber(result.getWeaponNumber());
		w.setOrgId(result.getWeaponOrgId());
		w.setStandard(result.getWeaponStandard());
		w.setTypeId(result.getWeaponTypeId());
		return w;
	}
 
}
