package com.tianyi.drs.basedata.dao;

import com.tianyi.drs.basedata.model.WeaponType;
import com.tianyi.drs.duty.dao.core.MyBatisRepository;
@MyBatisRepository
public interface WeaponTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WeaponType record);

    int insertSelective(WeaponType record);

    WeaponType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WeaponType record);

    int updateByPrimaryKey(WeaponType record);
}