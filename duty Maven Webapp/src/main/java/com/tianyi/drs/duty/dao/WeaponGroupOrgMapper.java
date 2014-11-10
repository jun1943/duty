package com.tianyi.drs.duty.dao;

import java.util.List;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.WeaponGroupOrg;
@MyBatisRepository
public interface WeaponGroupOrgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WeaponGroupOrg record);

    int insertSelective(WeaponGroupOrg record);

    WeaponGroupOrg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WeaponGroupOrg record);

    int updateByPrimaryKey(WeaponGroupOrg record);

	List<WeaponGroupOrg> loadWeaponGroupOrgByPGId(int pgid);

	void deleteByPGId(Integer id);
}