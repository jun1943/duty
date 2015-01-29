package com.tianyi.drs.duty.service;

import java.util.List;
 

import com.tianyi.drs.basedata.model.Gps;
import com.tianyi.drs.basedata.model.Icons;
import com.tianyi.drs.basedata.model.Police;
import com.tianyi.drs.basedata.model.Vehicle;
import com.tianyi.drs.basedata.model.Weapon;
import com.tianyi.drs.duty.exportmodel.ExtItem;

public interface ExportService {

	/**
	 * 读取警员备勤信息及基础信息
	 * @param orgId
	 * @param ymd
	 * @return
	 */
	List<ExtItem<Police>> loadPoliceDutyInfo(Integer orgId, Integer ymd);

	/**
	 * 读取车辆备勤信息及基础信息
	 * @param orgId
	 * @param ymd
	 * @return
	 */
	List<ExtItem<Vehicle>> loadVehicleDutyInfo(Integer orgId, Integer ymd);

	/**
	 * 读取武器备勤信息及基础信息
	 * @param orgId
	 * @param ymd
	 * @return
	 */
	List<ExtItem<Weapon>> loadWeaponDutyInfo(Integer orgId, Integer ymd);

	/**
	 * 读取定位设备备勤信息及基础信息
	 * @param orgId
	 * @param ymd
	 * @return
	 */
	List<ExtItem<Gps>> loadGpsDutyInfo(Integer orgId, Integer ymd);
	
	/**
	 * 读取图标基础信息
	 * @return
	 */
	List<Icons> loadIconsInfo();
	//List<ExtDbResult>loadDutyItemInfo(Integer orgId,Integer ymd);
	
}
