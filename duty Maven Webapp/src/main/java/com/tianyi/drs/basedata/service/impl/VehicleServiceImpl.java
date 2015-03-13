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
import com.tianyi.drs.basedata.model.Gps;
import com.tianyi.drs.basedata.model.IntercomGroup;
import com.tianyi.drs.basedata.model.Police;
import com.tianyi.drs.basedata.model.Vehicle;
import com.tianyi.drs.basedata.model.VehicleType;
import com.tianyi.drs.basedata.service.VehicleService;
import com.tianyi.drs.basedata.viewmodel.VehicleVM;
import com.tianyi.drs.duty.dao.ExportMapper;
import com.tianyi.drs.duty.exportmodel.ExtDbResult;
import com.tianyi.drs.duty.exportmodel.ExtShiftInfo;
import com.tianyi.drs.duty.exportmodel.GpsInfo;
import com.tianyi.drs.duty.exportmodel.ItemInfo;
import com.tianyi.drs.duty.exportmodel.PoliceEInfo;
import com.tianyi.drs.duty.exportmodel.VehicleExtItem;
import com.tianyi.drs.duty.exportmodel.VehicleInfo;
import com.tianyi.util.PaginationData;

/**
 * 车辆接口实现
 * 
 * @author lq
 * 
 */
@Service("vehicleService")
public class VehicleServiceImpl implements VehicleService {

	@Resource(name = "vehicleMapper")
	private VehicleMapper vehicleMapper;

	@Resource(name = "exportMapper")
	private ExportMapper exportMapper;

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.VehicleService#findByidCard(Vehicle
	 *      vehicle)
	 */
	public int findCount(Vehicle vehicle) {
		int count = vehicleMapper.countByExample(vehicle);
		return count;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.VehicleService#findPageList(Vehicle
	 *      query,PaginationData page)
	 */
	public List<Vehicle> findPageList(Vehicle query, PaginationData page) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (!(query.getNumber() == null || query.getNumber().length() == 0))
			map.put("number", query.getNumber());

		map.put("pageStart", page.getStartIndex());
		map.put("pageSize", page.getPageSize());
		List<Vehicle> list = vehicleMapper.selectByExample(map);

		return list;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see 
	 *      com.tianyi.drs.basedata.service.VehicleService#loadVMList(Map<String,
	 *      Object> query)
	 */
	public List<VehicleVM> loadVMList(Map<String, Object> query) {
		// Map<String, Object> map = new HashMap<String, Object>();
		// if(!(query.getNumber() == null || query.getNumber().length() ==0) )
		// map.put("number", query.getNumber());
		//
		// if(!(query.getOrgPath() == null || query.getOrgPath().length() ==0) )
		// map.put("orgPath", query.getOrgPath());
		//
		// if(inSubOrg)
		// {
		// map.put("inSubOrg", true);
		// }
		//
		// map.put("pageStart", page.getStartIndex());
		// map.put("pageSize", page.getPageSize());

		List<VehicleVM> list = vehicleMapper.loadVMList(query);

		return list;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see 
	 *      com.tianyi.drs.basedata.service.VehicleService#loadVMCount(Map<String
	 *      ,Object> query)
	 */
	public int loadVMCount(Map<String, Object> query) {
		int count = vehicleMapper.loadVMCount(query);
		return count;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.VehicleService#updateByPrimaryKey(Vehicle
	 *      vehicle)
	 */
	public int updateByPrimaryKey(Vehicle vehicle) {
		// TODO Auto-generated method stub
		return vehicleMapper.updateByPrimaryKey(vehicle);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.VehicleService#insert(Vehicle
	 *      vehicle)
	 */
	public int insert(Vehicle vehicle) {
		// TODO Auto-generated method stub
		return vehicleMapper.insert(vehicle);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.VehicleService#deleteByPrimaryKey(int
	 *      id)
	 */
	public int deleteByPrimaryKey(int id) {
		// TODO Auto-generated method stub
		return vehicleMapper.deleteByPrimaryKey(id);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.VehicleService#selectVehicleType()
	 */
	public List<VehicleType> selectVehicleType() {
		// TODO Auto-generated method stub
		return vehicleMapper.selectVehicleType();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.VehicleService#selectIntercomGroup()
	 */
	public List<IntercomGroup> selectIntercomGroup() {
		// TODO Auto-generated method stub
		return vehicleMapper.selectIntercomGroup();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see 
	 *      com.tianyi.drs.basedata.service.VehicleService#loadVMListWithGroup(Map
	 *      <String, Object> map)
	 */
	public List<VehicleVM> loadVMListWithGroup(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return vehicleMapper.loadVMListWithGroup(map);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see 
	 *      com.tianyi.drs.basedata.service.VehicleService#deleteByIds(Map<String
	 *      , Object> map)
	 */
	public void deleteByIds(Map<String, Object> map) {
		// TODO Auto-generated method stub
		vehicleMapper.deleteByIds(map);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.VehicleService#findByNumber(String)
	 */
	public List<Vehicle> findByNumber(String param) {
		// TODO Auto-generated method stub
		return vehicleMapper.findByNumber(param);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.VehicleService#findByIdAndDtyId(String)
	 */
	public List<Vehicle> findByIdAndDtyId(String param) {
		// TODO Auto-generated method stub
		return vehicleMapper.findByIdAndDtyId(param);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.tianyi.drs.basedata.service.VehicleService#loadListByOrgId(Map)
	 */
	public List<Vehicle> getVehicleInfo(Integer orgId) {
		// TODO Auto-generated method stub
		return vehicleMapper.getVehicleInfo(orgId);
	}

	public List<VehicleExtItem> getVehicleDutyInfo(Integer orgId, Integer ymd) {
		Map<Integer, ItemInfo<?>> cache = new HashMap<Integer, ItemInfo<?>>();// dutyItemId局部缓存，避免大量低效率的循环。
		Map<Integer, Object> cache2 = new HashMap<Integer, Object>();// ItemId
																		// //
		List<VehicleExtItem> vlst = new ArrayList<VehicleExtItem>();
		// 局部缓存，避免大量低效率的循环。Object无意义，都为null
		List<VehicleInfo> vs = new ArrayList<VehicleInfo>();

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

				VehicleInfo v = this.createVehicleInfo(r);
				v.setShiftInfo(this.createShiftInfo(r)); // 只有第一层需要写班次信息

				cache.put(v.getDutyItemId(), v);// 添加到缓存
				cache2.put(v.getData().getId(), null);
				vs.add(v);// 添加到list

			} else {
				if (cache.containsKey(r.getParentId())) {
					VehicleInfo pp = (VehicleInfo) cache.get(r.getParentId());

					switch (r.getItemTypeId()) {
					case 2:// police
						if (pp.getPoliceItems() == null) {
							pp.setPoliceItems(new ArrayList<PoliceEInfo>());
						}
						PoliceEInfo p = createPoliceEInfo(r);
						pp.getPoliceItems().add(p);
						cache.put(r.getDutyItemId(), p);
						break;
					case 4: // gps
						if (pp.getGpsItems() == null) {
							pp.setGpsItems(new ArrayList<GpsInfo>());
						}
						GpsInfo g = createGpsInfo(r);
						pp.getGpsItems().add(g);
						cache.put(r.getDutyItemId(), g);
						break;
					}
				}
			}
		}

		List<Vehicle> mps = vehicleMapper.getVehicleInfo(orgId);

		for (Vehicle mp : mps) {
			if (!cache2.containsKey(mp.getId())) {
				VehicleInfo ep2 = new VehicleInfo();
				ep2.setData(mp);
				vs.add(ep2);
			}
		}
		vlst = CreateVehicleExtItem(vs);
		return vlst;
	}

	private List<VehicleExtItem> CreateVehicleExtItem(List<VehicleInfo> eps) {
		// TODO Auto-generated method stub
		List<VehicleExtItem> pets = new ArrayList<VehicleExtItem>();
		for (VehicleInfo rs : eps) {
			VehicleExtItem pei = new VehicleExtItem();

			pei.setData(rs.getData());
			pei.setDutyItemId(rs.getDutyItemId());
			pei.setItemTypeId(rs.getItemTypeId());
			pei.setShiftInfo(rs.getShiftInfo());
			if (rs.getGpsItems() != null) {
				if (rs.getGpsItems().size() > 0) {
					for (int i = 0; i < rs.getGpsItems().size(); i++) {
						GpsInfo ep = rs.getGpsItems().get(i);
						List<Integer> gpsid = new ArrayList<Integer>();
						if (ep.getData() != null) {
							Gps g = (Gps) ep.getData();
							gpsid.add(g.getId());
							if (pei.getGpsItems() == null) {
								pei.setGpsItems(gpsid);
							} else {
								pei.getGpsItems().add(g.getId());
							}
						}

					}
				}
			}
			if (rs.getPoliceItems() != null) {
				if (rs.getPoliceItems().size() > 0) {
					for (int i = 0; i < rs.getPoliceItems().size(); i++) {
						PoliceEInfo ep = rs.getPoliceItems().get(i);
						List<Integer> pid = new ArrayList<Integer>();
						if (ep.getData() != null) {
							Police g = (Police) ep.getData();
							pid.add(g.getId());
							if (pei.getPoliceItems() == null) {
								pei.setPoliceItems(pid);
							} else {
								pei.getGpsItems().add(g.getId());
							}
						}

					}
				}
			}
			pets.add(pei);
		}
		return pets;
	}

	private VehicleInfo createVehicleInfo(ExtDbResult result) {
		VehicleInfo item = new VehicleInfo();
		Vehicle data = this.createVehicle(result);
		item.setDutyItemId(result.getDutyItemId());
		item.setData(data);
		item.setItemTypeId(result.getItemTypeId());
		// item.setLevel(result.getLevel());

		return item;
	}

	private GpsInfo createGpsInfo(ExtDbResult result) {
		GpsInfo item = new GpsInfo();
		Gps data = this.createGsp(result);
		item.setDutyItemId(result.getDutyItemId());
		item.setData(data);
		item.setItemTypeId(result.getItemTypeId());
		// item.setLevel(result.getLevel());

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

	private Police createPolice(ExtDbResult result) {
		Police p = new Police();
		p.setGpsId(result.getPoliceGpsId());
		p.setGpsName(result.getPoliceGpsName());
		p.setId(result.getPoliceId());
		p.setIdcardno(result.getPoliceIdcardno());
		p.setIntercomGroup(result.getPoliceIntercomGroup());
		p.setIntercomPerson(result.getPoliceIntercomPerson());
		p.setMobile(result.getPoliceMobile());
		p.setMobileShort(result.getPoliceMobileShort());
		p.setName(result.getPoliceName());
		p.setNumber(result.getPoliceNumber());
		p.setOrgId(result.getOrgId());

		return p;
	}

	private PoliceEInfo createPoliceEInfo(ExtDbResult result) {
		PoliceEInfo item = new PoliceEInfo();
		Police data = this.createPolice(result);
		item.setDutyItemId(result.getDutyItemId());
		item.setData(data);
		item.setItemTypeId(result.getItemTypeId());
		// item.setLevel(result.getLevel());

		return item;
	}

	private Gps createGsp(ExtDbResult result) {
		Gps g = new Gps();
		g.setGpsName(result.getGpsName());
		g.setIconUrl(result.getGpsIconUrl());
		g.setId(result.getGpsId());
		g.setNumber(result.getGpsNumber());
		g.setOrgId(result.getGpsOrgId());
		g.setTypeId(result.getGpsTypeId());
		return g;
	}
}
