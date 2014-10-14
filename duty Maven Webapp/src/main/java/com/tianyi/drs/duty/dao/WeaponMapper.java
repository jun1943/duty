package com.tianyi.drs.duty.dao;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.Weapon;
@MyBatisRepository
public interface WeaponMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Weapon record);

    int insertSelective(Weapon record);

    Weapon selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Weapon record);

    int updateByPrimaryKey(Weapon record);
}