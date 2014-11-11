package com.tianyi.drs.basedata.service;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.basedata.model.IntercomGroup;
import com.tianyi.drs.basedata.model.Vehicle;
import com.tianyi.drs.basedata.model.VehicleType;
import com.tianyi.drs.basedata.viewmodel.VehicleVM;
import com.tianyi.util.PaginationData;

public interface VehicleService {

	
	public int findCount(Vehicle vehicle);
	
	public List<Vehicle> findPageList(Vehicle query,PaginationData page);
	
	
	public int loadVMCount(Map<String,Object> query);
	
	public List<VehicleVM> loadVMList(Map<String,Object> query);

	public int updateByPrimaryKey(Vehicle vehicle);

	public int insert(Vehicle vehicle);

	public int deleteByPrimaryKey(int id);

	public List<VehicleType> selectVehicleType();

	public List<IntercomGroup> selectIntercomGroup();

	public List<VehicleVM> loadVMListWithGroup(Map<String, Object> map);
}
