package com.tianyi.drs.duty.service;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.model.Vehicle;
import com.tianyi.drs.duty.viewmodel.VehicleVM;
import com.tianyi.util.PaginationData;

public interface VehicleService {

	
	public int findCount(Vehicle vehicle);
	
	public List<Vehicle> findPageList(Vehicle query,PaginationData page);
	
	
	public int loadVMCount(Map<String,Object> query);
	
	public List<VehicleVM> loadVMList(Map<String,Object> query);
}
