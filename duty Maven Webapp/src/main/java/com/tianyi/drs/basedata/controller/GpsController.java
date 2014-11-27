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
/*
 * 定位设备控制器，mark by liqinag
 */
@Scope("prototype")
@Controller
@RequestMapping("/gpsdevice")
public class GpsController {
	@Resource(name = "gpsService")
	protected GpsService gpsService;

	/*
	 * 获取定位设备列表
	 * 参数：
	 * query：前台查询条件打包参数，包括组织机构信息，名称
	 * page：当前页
	 * rows：每页数据量
	 */
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
	/*
	 * 获取定位设备資源列表，用于报备页面显示；
	 * 参数：
	 * orgId、orgCode、orgPath ：前台查询条件打包参数，包括组织机构信息
	 * typeId：类型集
	 * groupId：所属群组集 
	 */
	@RequestMapping(value = "getGpsdeviceSource.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getGpsdeviceSource( 
			@RequestParam(value = "orgId", required = false) Integer orgId,
			@RequestParam(value = "orgCode", required = false) String orgCode,
			@RequestParam(value = "orgPath", required = false) String orgPath,
			@RequestParam(value = "gpsname", required = false) String gpsname, 
			@RequestParam(value = "typeId", required = false) String typeId,  
			@RequestParam(value = "groupId", required = false) String groupId,
			HttpServletRequest request) throws Exception {
		try {
			gpsname= gpsname.replace(",", "");
			List<GpsVM> list = new ArrayList<GpsVM>();
			Map<String, Object> map = new HashMap<String, Object>();
 
			map.put("orgId", orgId);
			map.put("orgPath", orgPath);
			map.put("orgCode", orgCode);
			map.put("gpsname", gpsname);
			//map.put("typeId", typeId);
			if (groupId != null && groupId != "") {
				String[] gs = {};
				gs = groupId.split(",");
				int[] gids = new int[gs.length];
				for (int i = 0; i < gs.length; i++) {
					gids[i] = Integer.parseInt(String.valueOf(gs[i]));
				}
				map.put("gids", gids);
			}
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
			list = gpsService.loadVMListWithGroup(map);
			int total = list.size();
			ListResult<GpsVM> rs = new ListResult<GpsVM>(total, list);

			String result = JSONObject.fromObject(rs).toString();

			return result;
		} catch (Exception ex) {
			return "{\"total\":0,\"rows\":[]}";
		}
	}
	/*
	 * 保存定位设备列表
	 * 参数：gps:传入后台保存对象
	 *  
	 */
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
	
	/*
	 * 删除定位设备
	 * 参数：
	 * id:前台选择的数据id集
	 */
	@RequestMapping(value = "deleteGpsdevice.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String deleteGpsdevice(String id) throws Exception {
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
			 
				gpsService.deleteByIds(map);
			} 
			return "{\"success\":true,\"Message\":\"删除成功,result is " + result + "\"}";
		} catch (Exception ex) {
			return "{\"success\":false,\"Message\":\"删除失败，原因：" + ex.getMessage() + "\"}";
		}
	}
	/*
	 * 获取定位设备类型
	 * 参数：
	 * id:前台选择的数据id集
	 */
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
	/*
	 * 获取定位设备类型，列表形式
	 * 参数：
	 * id:前台选择的数据id集
	 */
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
