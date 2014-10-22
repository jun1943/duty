package com.tianyi.drs.duty.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
 
import org.springframework.stereotype.Service;

import com.tianyi.drs.duty.dao.VehicleMapper;
import com.tianyi.drs.duty.model.Vehicle;
import com.tianyi.drs.duty.model.VehicleType;
import com.tianyi.drs.duty.service.VehicleService;
import com.tianyi.drs.duty.viewmodel.VehicleVM;
import com.tianyi.util.PaginationData;

@Service("vehicleService")
public class VehicleServiceImpl implements VehicleService{

	@Resource(name = "vehicleMapper")
	private VehicleMapper vehicleMapper;
	
	
	public int findCount(Vehicle vehicle) {
		int count = vehicleMapper.countByExample(vehicle);
		return count;
	}


	public List<Vehicle> findPageList(Vehicle query,PaginationData page) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(!(query.getNumber() == null || query.getNumber().length() ==0) )
			map.put("number", query.getNumber());
		
		map.put("pageStart", page.getStartIndex());
		map.put("pageSize", page.getPageSize());
		List<Vehicle> list = vehicleMapper.selectByExample(map);
		
		return list;
	}


	public List<VehicleVM> loadVMList(Map<String,Object> query) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		if(!(query.getNumber() == null || query.getNumber().length() ==0) )
//			map.put("number", query.getNumber());
//		
//		if(!(query.getOrgPath() == null || query.getOrgPath().length() ==0) )
//			map.put("orgPath", query.getOrgPath());
//		
//		if(inSubOrg)
//		{
//			map.put("inSubOrg", true);
//		}
//		
//		map.put("pageStart", page.getStartIndex());
//		map.put("pageSize", page.getPageSize());

		
		List<VehicleVM> list = vehicleMapper.loadVMList(query);
		
		return list;
	}


	public int loadVMCount(Map<String,Object> query) {
		int count= vehicleMapper.loadVMCount(query);
		return count;
	}


	public int updateByPrimaryKey(Vehicle vehicle) {
		// TODO Auto-generated method stub
		return vehicleMapper.updateByPrimaryKey(vehicle);
	}


	public int insert(Vehicle vehicle) {
		// TODO Auto-generated method stub
		return vehicleMapper.insert(vehicle);
	}


	public int deleteByPrimaryKey(int id) {
		// TODO Auto-generated method stub
		return vehicleMapper.deleteByPrimaryKey(id);
	}


	public List<VehicleType> selectVehicleType() {
		// TODO Auto-generated method stub
		return vehicleMapper.selectVehicleType();
	}
	
}
