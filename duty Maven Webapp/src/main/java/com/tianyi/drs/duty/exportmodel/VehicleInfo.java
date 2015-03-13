package com.tianyi.drs.duty.exportmodel;

import java.util.List;
 
import com.tianyi.drs.basedata.model.Vehicle;

public class VehicleInfo   extends ItemInfo<Vehicle> {
	private  List<PoliceEInfo> policeItems;
	private  List<GpsInfo> gpsItems;
	
	
	public List<GpsInfo> getGpsItems() {
		return gpsItems;
	}

	public void setGpsItems(List<GpsInfo> gpsItems) {
		this.gpsItems = gpsItems;
	}

	public List<PoliceEInfo> getPoliceItems() {
		return policeItems;
	}

	public void setPoliceItems(List<PoliceEInfo> policeItems) {
		this.policeItems = policeItems;
	} 
}
