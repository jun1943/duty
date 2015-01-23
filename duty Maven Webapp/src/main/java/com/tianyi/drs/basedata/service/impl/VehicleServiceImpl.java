package com.tianyi.drs.basedata.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
 
import org.springframework.stereotype.Service;

import com.tianyi.drs.basedata.dao.VehicleMapper;
import com.tianyi.drs.basedata.model.IntercomGroup;
import com.tianyi.drs.basedata.model.Vehicle;
import com.tianyi.drs.basedata.model.VehicleType;
import com.tianyi.drs.basedata.service.VehicleService;
import com.tianyi.drs.basedata.viewmodel.VehicleVM;
import com.tianyi.util.PaginationData;
/**
 * 车辆接口实现
 * @author lq
 *
 */
@Service("vehicleService")
public class VehicleServiceImpl implements VehicleService{

	@Resource(name = "vehicleMapper")
	private VehicleMapper vehicleMapper;
	
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.VehicleService#findByidCard(Vehicle vehicle)
	 */
	public int findCount(Vehicle vehicle) {
		int count = vehicleMapper.countByExample(vehicle);
		return count;
	}

	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.VehicleService#findPageList(Vehicle query,PaginationData page) 
	 */
	public List<Vehicle> findPageList(Vehicle query,PaginationData page) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(!(query.getNumber() == null || query.getNumber().length() ==0) )
			map.put("number", query.getNumber());
		
		map.put("pageStart", page.getStartIndex());
		map.put("pageSize", page.getPageSize());
		List<Vehicle> list = vehicleMapper.selectByExample(map);
		
		return list;
	}

	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.VehicleService#loadVMList(Map<String,Object> query) 
	 */
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

	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.VehicleService#loadVMCount(Map<String,Object> query) 
	 */
	public int loadVMCount(Map<String,Object> query) {
		int count= vehicleMapper.loadVMCount(query);
		return count;
	}

	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.VehicleService#updateByPrimaryKey(Vehicle vehicle) 
	 */
	public int updateByPrimaryKey(Vehicle vehicle) {
		// TODO Auto-generated method stub
		return vehicleMapper.updateByPrimaryKey(vehicle);
	}

	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.VehicleService#insert(Vehicle vehicle) 
	 */
	public int insert(Vehicle vehicle) {
		// TODO Auto-generated method stub
		return vehicleMapper.insert(vehicle);
	}

	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.VehicleService#deleteByPrimaryKey(int id) 
	 */
	public int deleteByPrimaryKey(int id) {
		// TODO Auto-generated method stub
		return vehicleMapper.deleteByPrimaryKey(id);
	}

	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.VehicleService#selectVehicleType() 
	 */
	public List<VehicleType> selectVehicleType() {
		// TODO Auto-generated method stub
		return vehicleMapper.selectVehicleType();
	}

	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.VehicleService#selectIntercomGroup() 
	 */
	public List<IntercomGroup> selectIntercomGroup() {
		// TODO Auto-generated method stub
		return vehicleMapper.selectIntercomGroup();
	}

	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.VehicleService#loadVMListWithGroup(Map<String, Object> map) 
	 */
	public List<VehicleVM> loadVMListWithGroup(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return vehicleMapper.loadVMListWithGroup(map);
	}

	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.VehicleService#deleteByIds(Map<String, Object> map)
	 */
	public void deleteByIds(Map<String, Object> map) {
		// TODO Auto-generated method stub
		vehicleMapper.deleteByIds(map);
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.VehicleService#findByNumber(String)
	 */
	public List<Vehicle> findByNumber(String param) {
		// TODO Auto-generated method stub
		return vehicleMapper.findByNumber(param);
	}
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.VehicleService#findByIdAndDtyId(String)
	 */
	public List<Vehicle> findByIdAndDtyId(String param) {
		// TODO Auto-generated method stub
		return vehicleMapper.findByIdAndDtyId(param);
	}
	
}
