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

import com.tianyi.drs.basedata.model.Gps;
import com.tianyi.drs.basedata.model.GpsType; 
import com.tianyi.drs.basedata.service.GpsService;
import com.tianyi.drs.basedata.viewmodel.GpsVM;
import com.tianyi.drs.duty.viewmodel.ListResult; 

@Scope("prototype")
@Controller
@RequestMapping("/gpsdevice")
public class GpsController {
	@Resource(name = "gpsService")
	protected GpsService gpsService;


	@RequestMapping(value = "getGpsdeviceList.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getGpsdeviceList(
			@RequestParam(value = "gpsdevice_Query", required = false) String query,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			HttpServletRequest request) throws Exception {
		try {
			JSONObject joQuery = JSONObject.fromObject(query);
			int orgId = Integer.parseInt(joQuery.getString("orgId"));
			int isSubOrg = Integer.parseInt(joQuery.getString("isSubOrg"));
			String gpsname = joQuery.getString("gpsname");  

			String orgCode = joQuery.getString("orgCode");
			String orgPath = joQuery.getString("orgPath");

			List<GpsVM> list = new ArrayList<GpsVM>();
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("pageStart", (page - 1) * rows);
			map.put("pageSize", rows);
			map.put("orgId", orgId);
			map.put("isSubOrg", isSubOrg);
			map.put("orgCode", orgCode);
			map.put("orgPath", orgPath);
			map.put("gpsname", gpsname);  

			int total = gpsService.loadVMCount(map);
			list = gpsService.loadVMList(map);

			ListResult<GpsVM> rs = new ListResult<GpsVM>(total, list);

			String result = JSONObject.fromObject(rs).toString();

			return result;
		} catch (Exception ex) {
			return "{\"total\":0,\"rows\":[]}";
		}
	}

	@RequestMapping(value = "getGpsdeviceSource.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getGpsdeviceSource( 
			@RequestParam(value = "orgId", required = false) Integer orgId,
			@RequestParam(value = "gpsname", required = false) String gpsname, 
			@RequestParam(value = "typeId", required = false) String typeId, 
			HttpServletRequest request) throws Exception {
		try {
			gpsname= gpsname.replace(",", "");
			List<GpsVM> list = new ArrayList<GpsVM>();
			Map<String, Object> map = new HashMap<String, Object>();
 
			map.put("orgId", orgId);
			map.put("gpsname", gpsname);
			//map.put("typeId", typeId);

			if (typeId != null&& typeId !="") {
				String[] s = {};
				s = typeId.split(",");
				int[] ids = new int[s.length];
				for (int i = 0; i < s.length; i++) { 
					ids[i] = Integer.parseInt(String.valueOf(s[i]));
				}
				map.put("ids", ids);
			}

			//int total = gpsService.loadVMCount(map);
			list = gpsService.loadVMList(map);
			int total = list.size();
			ListResult<GpsVM> rs = new ListResult<GpsVM>(total, list);

			String result = JSONObject.fromObject(rs).toString();

			return result;
		} catch (Exception ex) {
			return "{\"total\":0,\"rows\":[]}";
		}
	}
	@RequestMapping(value = "saveGpsdevice.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String saveGpsdevice(Gps gps) throws Exception {
		try {
			gps.setPlatformId(1);
			gps.setSyncState(true);
			int result = 0;
			if (gps.getId() > 0) {
				int vid = gps.getId();
				gps.setId(vid);
				result = gpsService.updateByPrimaryKey(gps);
			} else {
				result = gpsService.insert(gps);
			}
			return "{\"success\":true,\"Message\":\"保存成功,result is " + result
					+ "\"}";
		} catch (Exception ex) {
			return "{\"success\":false,\"Message\":\"保存失败，原因："
					+ ex.getMessage() + "\"}";
		}
	}
	

	@RequestMapping(value = "deleteGpsdevice.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String deleteGpsdevice(int id) throws Exception {
		try {
			int result =0;
			if(id>0){
				result = gpsService.deleteByPrimaryKey(id);
			}
			return "{\"success\":true,\"Message\":\"删除成功,result is " + result + "\"}";
		} catch (Exception ex) {
			return "{\"success\":false,\"Message\":\"删除失败，原因：" + ex.getMessage() + "\"}";
		}
	}

	@RequestMapping(value="getGpsType.do",produces="application/json;charset=UTF-8")
	public @ResponseBody String getGpsType() throws Exception {
		try
		{ 
			List<GpsType> list = gpsService.selectGpsType();
			JSONArray result = JSONArray.fromObject(list);
			return result.toString();
		}
		catch(Exception ex){
			return "";
		}
	}
	@RequestMapping(value="getGpsTypelist.do",produces="application/json;charset=UTF-8")
	public @ResponseBody String getGpsTypelist() throws Exception {
		try
		{ 
			List<GpsType> list = gpsService.selectGpsType();
			int total = list.size();
			ListResult<GpsType> rs = new ListResult<GpsType>(total, list);

			String result = JSONObject.fromObject(rs).toString();

			return result;
		} catch (Exception ex) {
			return "{\"total\":0,\"rows\":[]}";
		}
	}
}
