package com.tianyi.drs.duty.service;

import java.util.List;

import com.tianyi.drs.duty.model.Vehicle;
import com.tianyi.util.PaginationData;

public interface VehicleService {

	
	public int findCount(Vehicle vehicle);
	
	public List<Vehicle> findPageList(Vehicle query,PaginationData page);
}
