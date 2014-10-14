package com.tianyi.drs.duty.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tianyi.drs.duty.dao.PoliceMapper;
import com.tianyi.drs.duty.dao.WeaponMapper;
import com.tianyi.drs.duty.model.Weapon;
import com.tianyi.drs.duty.service.WeaponService;
 
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
		return 0;
	}

	public int insertSelective(Weapon record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Weapon selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateByPrimaryKeySelective(Weapon record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKey(Weapon record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Weapon findBycode(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	public Weapon findByname(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Weapon> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public int updatePolice(Weapon weapon) {
		// TODO Auto-generated method stub
		return 0;
	}

}
