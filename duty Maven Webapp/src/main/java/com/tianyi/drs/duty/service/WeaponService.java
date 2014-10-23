package com.tianyi.drs.duty.service;

import java.util.List; 
import java.util.Map;

import com.tianyi.drs.duty.model.Weapon;
import com.tianyi.drs.duty.viewmodel.WeaponVM;
import com.tianyi.util.PaginationData;
 

public interface WeaponService {

	int deleteByPrimaryKey(Integer id);

    int insert(Weapon record);

    int insertSelective(Weapon record);

    Weapon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Weapon record);

    int updateByPrimaryKey(Weapon record);
 
     List<Weapon> selectAll();

	int findCount(Weapon query);

	List<Weapon> findPageList(Weapon query, PaginationData page);

	int loadVMCount(Map<String, Object> map);

	List<WeaponVM> loadVMList(Map<String, Object> map); 
}
