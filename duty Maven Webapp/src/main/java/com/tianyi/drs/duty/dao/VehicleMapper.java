package com.tianyi.drs.duty.dao;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.Vehicle;
import com.tianyi.drs.duty.viewmodel.VehicleVM;

@MyBatisRepository
public interface VehicleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Vehicle record);

    int insertSelective(Vehicle record);

    Vehicle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Vehicle record);

    int updateByPrimaryKey(Vehicle record);
    
    int countByExample(Vehicle vehicle);
    
    List<Vehicle> selectByExample(Map<String, Object> map);
    
    int countByVM(Map<String, Object> map);
    
    List<VehicleVM> loadVMList(Map<String, Object> map);
    
}