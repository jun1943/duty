package com.tianyi.drs.duty.dao;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.WeaponGroup;
import com.tianyi.drs.duty.viewmodel.WeaponGroupVM;
@MyBatisRepository
public interface WeaponGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WeaponGroup record);

    int insertSelective(WeaponGroup record);

    WeaponGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WeaponGroup record);

    int updateByPrimaryKey(WeaponGroup record);

	int countByOrgId(Map<String, Object> map);

	List<WeaponGroupVM> loadVMListByOrgIdShared(Map<String, Object> map);

	List<WeaponGroupVM> loadVMListByOrgId(Map<String, Object> map);
}