package com.tianyi.drs.duty.dao;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.Vehicle;

@MyBatisRepository
public interface VehicleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Vehicle record);

    int insertSelective(Vehicle record);

    Vehicle selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Vehicle record);

    int updateByPrimaryKey(Vehicle record);
    
    int countByExample(Vehicle vehicle);
    
    List<Vehicle> selectByExample(Map<String, Object> map);
}