package com.tianyi.drs.duty.exportmodel;

import java.util.List;

import com.tianyi.drs.basedata.model.Police;

public class PoliceInfo  extends ItemInfo<Police> {
	
	private  List<GpsInfo> gpsItems;
	
	private  List<WeaponInfo> weaponItems;

	public List<GpsInfo> getGpsItems() {
		return gpsItems;
	}

	public void setGpsItems(List<GpsInfo> gpsItems) {
		this.gpsItems = gpsItems;
	}

	public List<WeaponInfo> getWeaponItems() {
		return weaponItems;
	}

	public void setWeaponItems(List<WeaponInfo> weaponItems) {
		this.weaponItems = weaponItems;
	}
	 
 
}
