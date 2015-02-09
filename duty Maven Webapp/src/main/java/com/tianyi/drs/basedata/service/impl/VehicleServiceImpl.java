package com.tianyi.drs.basedata.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.tianyi.drs.duty.dao.ExportMapper;
import com.tianyi.drs.duty.exportmodel.ExtDbResult;
import com.tianyi.drs.duty.exportmodel.ExtItem;
import com.tianyi.drs.duty.exportmodel.ExtShiftInfo;
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

	@Resource(name = "exportMapper")
	private ExportMapper exportMapper;

	
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
	/** (non-Javadoc)
	 * @see com.tianyi.drs.basedata.service.VehicleService#loadListByOrgId(Map)
	 */
	public List<Vehicle> loadListByOrgId(Integer orgId) {
		// TODO Auto-generated method stub
		return vehicleMapper.loadListByOrgId(orgId);
	}
	
	

	public List<ExtItem<Vehicle>> loadVehicleDutyInfo(Integer orgId, Integer ymd) {
		Map<Integer, ExtItem<?>> cache = new HashMap<Integer, ExtItem<?>>();// dutyItemId局部缓存，避免大量低效率的循环。
		Map<Integer, Object> cache2 = new HashMap<Integer, Object>();// ItemId
																		// 局部缓存，避免大量低效率的循环。Object无意义，都为null

		List<ExtItem<Vehicle>> eps = new ArrayList<ExtItem<Vehicle>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		
		if (ymd == null || ymd == 0) {
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			String date = dateFormat.format(now);
			ymd = Integer.parseInt(date);

		}
		map.put("ymd", ymd);

		List<ExtDbResult> rs = exportMapper.loadDutyItemInfo(map);

		for (ExtDbResult r : rs) {
			if (r.getItemTypeId() == 1) {
				@SuppressWarnings("unchecked")
				ExtItem<Vehicle> ep = (ExtItem<Vehicle>) this.createItemInfo(r);
				ep.setShiftInfo(this.createShiftInfo(r)); // 只有第一层需要写班次信息

				cache.put(ep.getDutyItemId(), ep);// 添加到缓存
				cache2.put(ep.getData().getId(), null);
				eps.add(ep);// 添加到list

			} else {
				if (cache.containsKey(r.getParentId())) {
					@SuppressWarnings("unchecked")
					ExtItem<Vehicle> pp = (ExtItem<Vehicle>) cache.get(r
							.getParentId());
					if (pp.getItems() == null) {
						pp.setItems(new ArrayList<ExtItem<?>>());
					}
					ExtItem<?> cp = (ExtItem<?>) createItemInfo(r);
					pp.getItems().add(cp);
					cache.put(r.getDutyItemId(), cp);

				}
			}
		}

		List<Vehicle> mps = vehicleMapper.loadListByOrgId(orgId);

		for (Vehicle mp : mps) {
			if (!cache2.containsKey(mp.getId())) {
				ExtItem<Vehicle> ep2 = new ExtItem<Vehicle>();
				ep2.setData(mp);
				eps.add(ep2);
			}
		}

		return eps;
	}
	

	private Object createItemInfo(ExtDbResult result) {
		ExtItem<Object> item = new ExtItem<Object>();
		Object data = null;

		data = this.createVehicle(result);

		item.setDutyItemId(result.getDutyItemId());
		item.setData(data);
		item.setItemTypeId(result.getItemTypeId());
		item.setLevel(result.getLevel());

		return item;
	}

	private ExtShiftInfo createShiftInfo(ExtDbResult result) {
		ExtShiftInfo info = new ExtShiftInfo();
		info.setBeginTime(result.getBeginTime());
		info.setDutyTypeId(result.getDutyTypeId());
		info.setDutyTypeName(result.getDutyTypeName());
		info.setEndTime(result.getEndTime());
		info.setShiftId(result.getDutyItemId());
		info.setShiftName(result.getName());

		return info;
	}
 

	private Vehicle createVehicle(ExtDbResult result) {
		Vehicle v = new Vehicle();
		v.setBrand(result.getVehicleBrand());
		v.setGpsId(result.getVehicleGpsId());
		v.setGpsName(result.getVehicleGpsName());
		v.setId(result.getItemId());
		v.setIntercomGroup(result.getVehicleIntercomGroup());
		v.setIntercomPerson(result.getVehicleIntercomPerson());
		v.setNumber(result.getVehicleNumber());
		v.setOrgId(result.getVehicleOrgId());
		v.setSiteQty(result.getVehicleSiteQty());
		v.setVehicleTypeId(result.getVehicleTypeId());

		return v;

	}
 
}
