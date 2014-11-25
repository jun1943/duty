package com.tianyi.drs.basedata.dao;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.basedata.model.IntercomGroup;
import com.tianyi.drs.basedata.model.Vehicle;
import com.tianyi.drs.basedata.model.VehicleType;
import com.tianyi.drs.basedata.viewmodel.VehicleVM;
import com.tianyi.drs.duty.dao.core.MyBatisRepository;

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

	int loadVMCount(Map<String, Object> query);

	List<VehicleType> selectVehicleType();

	List<IntercomGroup> selectIntercomGroup();

	List<VehicleVM> loadVMListWithGroup(Map<String, Object> map);

	void deleteByIds(Map<String, Object> map);
    
}