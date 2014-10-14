package com.tianyi.drs.duty.dao;

import com.tianyi.drs.duty.model.WeaponType;

public interface WeaponTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WeaponType record);

    int insertSelective(WeaponType record);

    WeaponType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WeaponType record);

    int updateByPrimaryKey(WeaponType record);
}