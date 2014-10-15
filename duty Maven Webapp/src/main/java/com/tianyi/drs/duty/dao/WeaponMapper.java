package com.tianyi.drs.duty.dao;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.Weapon;
@MyBatisRepository
public interface WeaponMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Weapon record);

    int insertSelective(Weapon record);

    Weapon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Weapon record);

    int updateByPrimaryKey(Weapon record);

	List<Weapon> selectAll();

	int countByExample(Weapon weapon);

	List<Weapon> selectByExample(Map<String, Object> map);
}