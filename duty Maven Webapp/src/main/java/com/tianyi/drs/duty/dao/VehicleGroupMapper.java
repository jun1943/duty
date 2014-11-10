package com.tianyi.drs.duty.dao;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.VehicleGroup;
import com.tianyi.drs.duty.viewmodel.VehicleGroupVM;
@MyBatisRepository
public interface VehicleGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VehicleGroup record);

    int insertSelective(VehicleGroup record);

    VehicleGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VehicleGroup record);

    int updateByPrimaryKey(VehicleGroup record);

	int countByOrgId(Map<String, Object> map);

	List<VehicleGroupVM> loadVMListByOrgId(Map<String, Object> map);

	List<VehicleGroupVM> loadVMListByOrgIdShared(Map<String, Object> map);
}