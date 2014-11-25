package com.tianyi.drs.basedata.controller;

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

import com.tianyi.drs.basedata.model.IntercomGroup;
import com.tianyi.drs.basedata.model.Vehicle;
import com.tianyi.drs.basedata.model.VehicleType;
import com.tianyi.drs.basedata.service.VehicleService;
import com.tianyi.drs.basedata.viewmodel.VehicleVM;
import com.tianyi.drs.duty.viewmodel.ListResult;

@Scope("prototype")
@Controller
@RequestMapping("/vehicle")
public class VehicleController {

	@RequestMapping("index")
	public String index() {
		return "index";
	}

	@Resource(name = "vehicleService")
	protected VehicleService vehicleService;

	@RequestMapping(value = "getVehicleList.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getVehicleList(
			@RequestParam(value = "vehicle_Query", required = false) String query,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "sort", required = false) String sort,
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
			if (sort != null) {
				if (!sort.equals("")) {
					map.put("sort", "v." + sort);
				}
			} else {
				map.put("sort", "v.id");
			}
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
	String deleteVehicle(String id) throws Exception {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			int result = 0;
			if (id != null && id != "") {
				String[] s = {};
				s = id.split(",");
				int[] ids = new int[s.length];
				for (int i = 0; i < s.length; i++) {
					ids[i] = Integer.parseInt(String.valueOf(s[i]));
				}
				map.put("ids", ids);

				vehicleService.deleteByIds(map);
			}
			return "{\"success\":true,\"Message\":\"删除成功,result is " + result
					+ "\"}";
		} catch (Exception ex) {
			return "{\"success\":false,\"Message\":\"删除失败，原因："
					+ ex.getMessage() + "\"}";
		}
	}

	@RequestMapping(value = "getVehicleType.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getVehicleType() throws Exception {
		try {
			List<VehicleType> list = vehicleService.selectVehicleType();
			JSONArray result = JSONArray.fromObject(list);
			return result.toString();
		} catch (Exception ex) {
			return "";
		}
	}

	@RequestMapping(value = "getvehicleTypelist.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getvehicleTypelist() throws Exception {
		try {
			List<VehicleType> list = vehicleService.selectVehicleType();
			int total = list.size();
			ListResult<VehicleType> rs = new ListResult<VehicleType>(total,
					list);

			String result = JSONObject.fromObject(rs).toString();

			return result;
		} catch (Exception ex) {
			return "{\"total\":0,\"rows\":[]}";
		}
	}

	@RequestMapping(value = "getintercomGroup.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getintercomGroup() throws Exception {
		try {
			List<IntercomGroup> list = vehicleService.selectIntercomGroup();
			JSONArray result = JSONArray.fromObject(list);
			return result.toString();
		} catch (Exception ex) {
			return "";
		}
	}

	@RequestMapping(value = "getVehicleSource.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getVehicleSource(
			@RequestParam(value = "orgId", required = false) Integer orgId,
			@RequestParam(value = "orgCode", required = false) String orgCode,
			@RequestParam(value = "orgPath", required = false) String orgPath,
			@RequestParam(value = "number", required = false) String number,
			@RequestParam(value = "typeId", required = false) String typeId,
			@RequestParam(value = "groupId", required = false) String groupId,
			HttpServletRequest request) throws Exception {
		try {

			number = number.replace(",", "");
			List<VehicleVM> list = new ArrayList<VehicleVM>();
			Map<String, Object> map = new HashMap<String, Object>();
			if (groupId != null && groupId != "") {
				String[] gs = {};
				gs = groupId.split(",");
				int[] gids = new int[gs.length];
				for (int i = 0; i < gs.length; i++) {
					gids[i] = Integer.parseInt(String.valueOf(gs[i]));
				}
				map.put("gids", gids);
			}
			if (typeId != null && typeId != "") {
				String[] s = {};
				s = typeId.split(",");
				int[] ids = new int[s.length];
				for (int i = 0; i < s.length; i++) {
					ids[i] = Integer.parseInt(String.valueOf(s[i]));
				}
				map.put("ids", ids);
			}
			map.put("orgId", orgId);
			map.put("orgPath", orgPath);
			map.put("orgCode", orgCode);
			map.put("number", number);

			list = vehicleService.loadVMListWithGroup(map);
			int total = list.size();
			ListResult<VehicleVM> rs = new ListResult<VehicleVM>(total, list);

			String result = JSONObject.fromObject(rs).toString();

			return result;
		} catch (Exception ex) {
			return "{\"total\":0,\"rows\":[]}";
		}
	}
}
