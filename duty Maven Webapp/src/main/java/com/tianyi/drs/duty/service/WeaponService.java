package com.tianyi.drs.duty.service;

import java.util.List; 

import com.tianyi.drs.duty.model.Weapon;
 

public interface WeaponService {

	int deleteByPrimaryKey(Integer id);

    int insert(Weapon record);

    int insertSelective(Weapon record);

    Weapon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Weapon record);

    int updateByPrimaryKey(Weapon record);

    Weapon findBycode(String code);
    
    Weapon findByname(String name);
    
     List<Weapon> selectAll();
    
     int updatePolice(Weapon weapon);
   
}
