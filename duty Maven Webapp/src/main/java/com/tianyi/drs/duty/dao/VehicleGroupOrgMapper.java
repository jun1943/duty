package com.tianyi.drs.duty.dao;

import java.util.List;

import com.tianyi.drs.duty.dao.core.MyBatisRepository; 
import com.tianyi.drs.duty.model.VehicleGroupOrg;
@MyBatisRepository
public interface VehicleGroupOrgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VehicleGroupOrg record);

    int insertSelective(VehicleGroupOrg record);

    VehicleGroupOrg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VehicleGroupOrg record);

    int updateByPrimaryKey(VehicleGroupOrg record);
 

	void deleteByPGId(Integer id);

	List<VehicleGroupOrg> loadVehicleGroupOrgByPGId(int pgid); 
}