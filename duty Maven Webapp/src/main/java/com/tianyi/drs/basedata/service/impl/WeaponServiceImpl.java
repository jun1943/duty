package com.tianyi.drs.basedata.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
 
import com.tianyi.drs.basedata.dao.WeaponMapper;
import com.tianyi.drs.basedata.model.Weapon;
import com.tianyi.drs.basedata.model.WeaponType;
import com.tianyi.drs.basedata.service.WeaponService; 
import com.tianyi.drs.basedata.viewmodel.WeaponVM;
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
	
}
