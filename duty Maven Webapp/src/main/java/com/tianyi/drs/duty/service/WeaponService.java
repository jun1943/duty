package com.tianyi.drs.duty.service;

import java.util.List; 

import com.tianyi.drs.duty.model.Weapon;
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

	List<?> findPageList(Weapon query, PaginationData page); 
}
