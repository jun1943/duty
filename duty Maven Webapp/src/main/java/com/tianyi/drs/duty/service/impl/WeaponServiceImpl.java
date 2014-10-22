package com.tianyi.drs.duty.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
 
import com.tianyi.drs.duty.dao.WeaponMapper; 
import com.tianyi.drs.duty.model.Weapon;
import com.tianyi.drs.duty.service.WeaponService; 
import com.tianyi.drs.duty.viewmodel.WeaponVM;
import com.tianyi.util.PaginationData;
 
@Service("weaponService")
public class WeaponServiceImpl implements WeaponService {

	@Resource(name="weaponMapper")
	private WeaponMapper weaponMapper;
	
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return weaponMapper.deleteByPrimaryKey(id);
	}

	public int insert(Weapon record) {
		// TODO Auto-generated method stub
		return weaponMapper.insert(record);
	}

	public int insertSelective(Weapon record) {
		// TODO Auto-generated method stub
		return weaponMapper.insertSelective(record);
	}

	public Weapon selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return weaponMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(Weapon record) {
		// TODO Auto-generated method stub
		return weaponMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Weapon record) {
		// TODO Auto-generated method stub
		return weaponMapper.updateByPrimaryKey(record);
	} 

	public List<Weapon> selectAll() {
		// TODO Auto-generated method stub
		return weaponMapper.selectAll();
	}

	public int findCount(Weapon weapon) {
		int count = weaponMapper.countByExample(weapon);
		return count;
	}

	public List<Weapon> findPageList(Weapon query, PaginationData page) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(!(query.getNumber() == null || query.getNumber().length() ==0) )
			map.put("number", query.getNumber());
		
		map.put("pageStart", page.getStartIndex());
		map.put("pageSize", page.getPageSize());
		List<Weapon> list = weaponMapper.selectByExample(map);
		
		return list;
	}
 
	  

	public int loadVMCount(Map<String, Object> map) {
		int count= weaponMapper.loadVMCount(map);
		return count;
	}

	public List<WeaponVM> loadVMList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<WeaponVM> list = weaponMapper.loadVMList(map);
		
		return list;
	} 
	
}
