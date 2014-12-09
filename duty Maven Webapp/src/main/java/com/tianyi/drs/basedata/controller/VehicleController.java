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

/*
 * 车辆管理逻辑控制器；
 */
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

	/*
	 * 获取车辆列表信息
	 * 
	 * vehicle_Query：查询条件包
	 * sort：排序列
	 * order：排序方式
	 * page：当前页
	 * rows：每页条数
	 */
	@RequestMapping(value = "getVehicleList.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getVehicleList(
			@RequestParam(value = "vehicle_Query", required = false) String query,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
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
			page = page == 0 ? 1 : page;
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
			if (order != null) {
				if (!order.equals("")) {
					map.put("order", order);
				}
			} else {
				map.put("order", "asc");
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

	/*
	 * 
	 * 保存车辆信息；
	 * 
	 */
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

	/*
	 * 
	 * 删除车辆，
	 * 
	 * id：传入选择车辆的id；
	 */
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

	
	/*
	 * 
	 * 获取车辆类型列表，以下拉框的形式展现；
	 */
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

	
	/*
	 * 
	 * 获取车辆类型列表数据，以数据列表的形式展现
	 * 
	 * 用于报备中车辆资源的过滤条件筛选；
	 */
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

	
	/*
	 * 
	 * 获取车辆对应的组呼号；
	 */
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

	/*
	 * 获取车辆资源类表
	 * 
	 * 用于报备页面资源元素的展示
	 */
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
