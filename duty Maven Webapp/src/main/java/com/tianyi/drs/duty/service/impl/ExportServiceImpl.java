package com.tianyi.drs.duty.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tianyi.drs.basedata.dao.PoliceMapper;
import com.tianyi.drs.basedata.model.Gps;
import com.tianyi.drs.basedata.model.Police;
import com.tianyi.drs.basedata.model.Vehicle;
import com.tianyi.drs.basedata.model.Weapon;
import com.tianyi.drs.duty.dao.ExportMapper;
import com.tianyi.drs.duty.exportmodel.ExtDbResult;
import com.tianyi.drs.duty.exportmodel.ExtItem;
import com.tianyi.drs.duty.exportmodel.ExtShiftInfo;
import com.tianyi.drs.duty.service.ExportService;

@Service("exportService")
public class ExportServiceImpl implements ExportService {

	@Resource(name = "exportMapper")
	private ExportMapper exportMapper;
	
	@Resource(name="policeMapper")
	private PoliceMapper policeMapper;

	public List<ExtItem<Police>> loadPoliceDutyInfo(Integer orgId, Integer ymd){
		Map<Integer,ExtItem<?>> cache=new HashMap<Integer,ExtItem<?>>();//dutyItemId局部缓存，避免大量低效率的循环。
		Map<Integer,Object> cache2=new HashMap<Integer,Object>();//ItemId 局部缓存，避免大量低效率的循环。Object无意义，都为null
		
		List<ExtItem<Police>> eps=new ArrayList<ExtItem<Police>>(); 
		
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("orgId", orgId);
		map.put("ymd", ymd);
		
		List<ExtDbResult> rs=exportMapper.loadDutyItemInfo(map);
		
		for(ExtDbResult r : rs){
			if(r.getItemTypeId()==2){
				@SuppressWarnings("unchecked")
				ExtItem<Police> ep=(ExtItem<Police>)this.createItemInfo(r);
				ep.setShiftInfo(this.createShiftInfo(r));  //只有第一层需要写班次信息
				
				cache.put(ep.getDutyItemId(), ep);//添加到缓存
				cache2.put(ep.getData().getId(), null);  
				eps.add(ep);//添加到list
				
			}else{
				if(cache.containsKey(r.getParentId())){
					@SuppressWarnings("unchecked")
					ExtItem<Police> pp=(ExtItem<Police>)cache.get(r.getParentId());
					if(pp.getItems()==null){
						pp.setItems(new ArrayList<ExtItem<?>>());
					}
					ExtItem<?> cp= (ExtItem<?>)createItemInfo(r);
					pp.getItems().add(cp);
					cache.put(r.getDutyItemId(), cp);
					
				}
			}
		}
		
		List<Police> mps=policeMapper.loadListByOrgId(map);
		
		for(Police mp : mps){
			if(!cache2.containsKey(mp.getId())){
				ExtItem<Police> ep2=new ExtItem<Police>();
				ep2.setData(mp);
				eps.add(ep2);
			}
		}
		
		return eps;
		
	}

	private  Object createItemInfo(ExtDbResult result){
		ExtItem<Object> item=new ExtItem<Object>();
		Object data=null;
		switch(result.getItemTypeId()){
		case 1:
			data=this.createVehicle(result);
			break;
		case 2:
			data=this.createPolice(result);
			break;
		case 3:
			data=this.createWeapon(result);
			break;
		case 4:
			data=this.createGsp(result);
			break;
		}
		item.setDutyItemId(result.getDutyItemId());
		item.setData(data);
		item.setItemTypeId(result.getItemTypeId());
		item.setLevel(result.getLevel());
		
		return item;
	}

	private ExtItem<Vehicle> createVehicleInfo(ExtDbResult result){
		ExtItem<Vehicle> item=new ExtItem<Vehicle>();
		item.setDutyItemId(result.getDutyItemId());
		item.setData(this.createVehicle(result));
		item.setItemTypeId(result.getItemTypeId());
		item.setLevel(result.getLevel());
		
		return item;
	}
	
	private ExtItem<Police> createPoliceInfo(ExtDbResult result){
		ExtItem<Police> item=new ExtItem<Police>();
		item.setDutyItemId(result.getDutyItemId());
		item.setData(this.createPolice(result));
		item.setItemTypeId(result.getItemTypeId());
		item.setLevel(result.getLevel());
		
		return item;
	}
	
	
	
	private ExtShiftInfo createShiftInfo(ExtDbResult result){
		ExtShiftInfo info=new ExtShiftInfo();
		info.setBeginTime(result.getBeginTime());
		info.setDutyTypeId(result.getDutyTypeId());
		info.setDutyTypeName(result.getDutyTypeName());
		info.setEndTime(result.getEndTime());
		info.setShiftId(result.getDutyItemId());
		info.setShiftName(result.getName());
		
		return info;
	}
	
	private Vehicle createVehicle(ExtDbResult result){
		Vehicle v=new Vehicle();
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
	
	private Police createPolice(ExtDbResult result){
		Police p=new Police();
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
	
	private Weapon createWeapon(ExtDbResult result){
		Weapon w=new Weapon();
		w.setId(result.getWeaponId());
		w.setNumber(result.getWeaponNumber());
		w.setOrgId(result.getWeaponOrgId());
		w.setStandard(result.getWeaponStandard());
		w.setTypeId(result.getWeaponTypeId());
		return w;
	}
	
	private Gps createGsp(ExtDbResult result){
		Gps g=new Gps();
		g.setGpsName(result.getGpsName());
		g.setIconUrl(result.getGpsIconUrl());
		g.setId(result.getGpsId());
		g.setNumber(result.getGpsNumber());
		g.setOrgId(result.getGpsOrgId());
		g.setTypeId(result.getGpsTypeId());
		return g;
	}
	
	private Object createItem(ExtDbResult result){
		switch(result.getItemTypeId()){
		case 1:
			return this.createVehicle(result);
		case 2:
			return this.createPolice(result);
		case 3:
			return this.createWeapon(result);
		case 4:
			return this.createGsp(result);
		default:
				return null;
		}
		
	}
	
	public List<ExtDbResult> loadDutyItemInfo(Integer orgId, Integer ymd,Integer itemTypeId) {
		

		Map<Integer,ExtDbResult> cache=new HashMap<Integer,ExtDbResult>();//局部缓存，避免大量低效率的循环。
		
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("orgId", orgId);
		map.put("ymd", ymd);
		
		List<ExtDbResult> rs=exportMapper.loadDutyItemInfo(map);
		
		for(ExtDbResult r : rs){
			
		}
		
		return null;
	}

}
