package com.tianyi.drs.duty.controller; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;  

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody; 
   
import com.tianyi.drs.duty.model.PoliceType;
import com.tianyi.drs.duty.model.Vehicle;
import com.tianyi.drs.duty.model.VehicleType;
import com.tianyi.drs.duty.service.VehicleService;   
import com.tianyi.drs.duty.viewmodel.ListResult; 
import com.tianyi.drs.duty.viewmodel.VehicleVM; 

@Scope("prototype")
@Controller
@RequestMapping("/vehicle")
public class VehicleController {
	@Resource(name = "vehicleService")
	protected VehicleService vehicleService;



	@RequestMapping(value = "getVehicleList.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getVehicleList(
			@RequestParam(value = "vehicle_Query", required = false) String query,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			HttpServletRequest request) throws Exception {
		try {
			JSONObject joQuery = JSONObject.fromObject(query);
			int orgId = Integer.parseInt(joQuery.getString("orgId"));
			int isSubOrg = Integer.parseInt(joQuery.getString("isSubOrg"));
			String number = joQuery.getString("number"); 

			String orgCode = joQuery.getString("orgCode");
			String orgPath = joQuery.getString("orgPath");

			List<VehicleVM> list = new ArrayList<VehicleVM>();
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("pageStart", (page - 1) * rows);
			map.put("pageSize", rows);
			map.put("orgId", orgId);
			map.put("isSubOrg", isSubOrg);
			map.put("orgCode", orgCode);
			map.put("orgPath", orgPath);
			map.put("number", number); 

			int total = vehicleService.loadVMCount(map);
			list = vehicleService.loadVMList(map);

			ListResult<VehicleVM> rs = new ListResult<VehicleVM>(total, list);

			String result = JSONObject.fromObject(rs).toString();

			return result;
		} catch (Exception ex) {
			return "{\"total\":0,\"rows\":[]}";
		}
	}

	@RequestMapping(value = "saveVehicle.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String saveVehicle(Vehicle vehicle) throws Exception {
		try {
			vehicle.setPlatformId(1);
			vehicle.setSyncState(true);
			int result = 0;
			if (vehicle.getId() > 0) {
				int vid = vehicle.getId();
				vehicle.setId(vid);
				result = vehicleService.updateByPrimaryKey(vehicle);
			} else {
				result = vehicleService.insert(vehicle);
			}
			return "{\"success\":true,\"Message\":\"保存成功,result is " + result
					+ "\"}";
		} catch (Exception ex) {
			return "{\"success\":false,\"Message\":\"保存失败，原因："
					+ ex.getMessage() + "\"}";
		}
	}
	

	@RequestMapping(value = "deleteVehicle.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String deleteVehicle(int id) throws Exception {
		try {
			int result =0;
			if(id>0){
				result = vehicleService.deleteByPrimaryKey(id);
			}
			return "{\"success\":true,\"Message\":\"删除成功,result is " + result + "\"}";
		} catch (Exception ex) {
			return "{\"success\":false,\"Message\":\"删除失败，原因：" + ex.getMessage() + "\"}";
		}
	}
	

	@RequestMapping(value="getVehicleType.do",produces="application/json;charset=UTF-8")
	public @ResponseBody String getVehicleType() throws Exception {
		try
		{ 
			List<VehicleType> list = vehicleService.selectVehicleType();
			JSONArray result = JSONArray.fromObject(list);
			return result.toString();
		}
		catch(Exception ex){
			return "";
		}
	} 

}
