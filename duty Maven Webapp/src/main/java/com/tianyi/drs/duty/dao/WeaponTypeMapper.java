package com.tianyi.drs.duty.dao;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.WeaponType;
@MyBatisRepository
public interface WeaponTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WeaponType record);

    int insertSelective(WeaponType record);

    WeaponType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WeaponType record);

    int updateByPrimaryKey(WeaponType record);
}