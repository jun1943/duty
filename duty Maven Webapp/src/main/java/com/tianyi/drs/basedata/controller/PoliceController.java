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
import com.tianyi.drs.basedata.model.Police;
import com.tianyi.drs.basedata.model.PoliceType;
import com.tianyi.drs.basedata.service.PoliceService;
import com.tianyi.drs.basedata.viewmodel.GpsBaseVM;
import com.tianyi.drs.basedata.viewmodel.PoliceVM;
import com.tianyi.drs.duty.viewmodel.ListResult;

@Scope("prototype")
@Controller
@RequestMapping("/police")
public class PoliceController {
	@Resource(name = "policeService")
	protected PoliceService policeService;// =
											// ServiceFactory.getPoliceServiceInit();

	@RequestMapping("index")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "getPoliceList.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getPoliceList(
			@RequestParam(value = "police_Query", required = false) String query,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			HttpServletRequest request) throws Exception {
		try {  
			JSONObject joQuery = JSONObject.fromObject(query);
			int orgId = Integer.parseInt(joQuery.getString("orgId"));
			int isSubOrg = Integer.parseInt(joQuery.getString("isSubOrg"));
			String name = joQuery.getString("name");
			int typeid = Integer.parseInt(joQuery.getString("typeid"));

			String orgCode = joQuery.getString("orgCode");
			String orgPath = joQuery.getString("orgPath");

			List<PoliceVM> list = new ArrayList<PoliceVM>();
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("pageStart", (page - 1) * rows);
			map.put("pageSize", rows);
			map.put("orgId", orgId);
			map.put("isSubOrg", isSubOrg);
			map.put("orgCode", orgCode);
			map.put("orgPath", orgPath);
			map.put("name", name);
			map.put("typeid", typeid);

			int total = policeService.loadVMCount(map);
			list = policeService.loadVMList(map);

			ListResult<PoliceVM> rs = new ListResult<PoliceVM>(total, list);

			String result = JSONObject.fromObject(rs).toString();

			return result;
		} catch (Exception ex) {
			return "{\"total\":0,\"rows\":[]}";
		}
	}

	@RequestMapping(value = "getPoliceSource.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getPoliceSource(
			@RequestParam(value = "orgId", required = false) Integer orgId,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "typeId", required = false) String typeId,
			@RequestParam(value = "groupId", required = false) String groupId
			)
			throws Exception { 
		try{
			List<PoliceVM> list = new ArrayList<PoliceVM>();
			Map<String, Object> map = new HashMap<String, Object>();
			name = name.replace(",", "");
			map.put("orgId", orgId); 
			map.put("name", name); 
			map.put("typeId", typeId); 
			map.put("groupId", groupId); 
			list = policeService.loadVMListWithGroup(map);
			 
			int total = list.size();
			ListResult<PoliceVM> rs = new ListResult<PoliceVM>(total, list);
	
			String result = JSONObject.fromObject(rs).toString();
	
			return result;
		}
		catch(Exception ex){
			return "{\"total\":0,\"rows\":[]}";
		}
	}
	@RequestMapping(value = "savePolice.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String savePolice(Police police) throws Exception {
		try {
			police.setPlatformId(1);
			police.setSyncState(true);
			int result = 0;
			if (police.getId() > 0) {
				int pid = police.getId();
				police.setId(pid);
				result = policeService.updateByPrimaryKey(police);
			} else {
				result = policeService.insert(police);
			}
			return "{\"success\":true,\"Message\":\"保存成功,result is " + result
					+ "\"}";
		} catch (Exception ex) {
			return "{\"success\":false,\"Message\":\"保存失败，原因："
					+ ex.getMessage() + "\"}";
		}
	}
	

	@RequestMapping(value = "deletePolice.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String deletePolice(int id) throws Exception {
		try {
			int result =0;
			if(id>0){
				result = policeService.deleteByPrimaryKey(id);
			}
			return "{\"success\":true,\"Message\":\"删除成功,result is " + result + "\"}";
		} catch (Exception ex) {
			return "{\"success\":false,\"Message\":\"删除失败，原因：" + ex.getMessage() + "\"}";
		}
	}

	@RequestMapping(value="getPoliceType.do",produces="application/json;charset=UTF-8")
	public @ResponseBody String getPoliceType() throws Exception {
		try
		{ 
			List<PoliceType> list = policeService.selectPoliceType();
			JSONArray result = JSONArray.fromObject(list);
			return result.toString();
		}
		catch(Exception ex){
			return "";
		}
	}


	@RequestMapping(value="getPoliceTypeList.do",produces="application/json;charset=UTF-8")
	public @ResponseBody String getPoliceTypeList() throws Exception {
		try
		{ 
			List<PoliceType> list = policeService.selectPoliceType();
			int total = list.size();
			ListResult<PoliceType> rs = new ListResult<PoliceType>(total, list);

			String result = JSONObject.fromObject(rs).toString();

			return result;
		}
		catch(Exception ex){
			return "{\"total\":0,\"rows\":[]}";
		}
	}
	@RequestMapping(value="getintercomGroup.do",produces="application/json;charset=UTF-8")
	public @ResponseBody String getintercomGroup() throws Exception {
		try
		{ 
			List<IntercomGroup> list = policeService.selectIntercomGroup();
			JSONArray result = JSONArray.fromObject(list);
			return result.toString();
		}
		catch(Exception ex){
			return "";
		}
	}
	


	@RequestMapping(value="getGpsId.do",produces="application/json;charset=UTF-8")
	public @ResponseBody String getGpsId(int orgId) throws Exception {
		try
		{ 
			List<GpsBaseVM> list = policeService.selectGpsId(orgId);
			JSONArray result = JSONArray.fromObject(list);
			return result.toString();
		}
		catch(Exception ex){
			return "";
		}
	}
	
	@RequestMapping(value = "updatePolice.do", produces = "application/json;charset=UTF-8")
	public void updatePolice() throws Exception {
		try {
			Police police = new Police();
			police.setId(1);
			police.setName("张五");
			police.setGpsId(5);
			police.setGpsName("95手机定位");
			police.setMobile("13568865179");
			police.setIntercomGroup("mmmm");
			police.setIntercomPerson("3333");
			police.setIdcardno("512301198506234875");
			police.setOrgId(2);
			police.setMobileShort("6179");
			police.setNumber("51007818");
			police.setTypeId(3);
			police.setTitle("副主任");
			System.out.println(policeService.updateByPrimaryKey(police));
		} catch (Exception ex) {
			System.out.println("update failed");
		}
	}

	@RequestMapping(value = "selectPolice.do", produces = "application/json;charset=UTF-8")
	public void selectPolice() throws Exception {
		try {
			Police police = policeService.selectByPrimaryKey(2);
			String name = "list is empty!";
			if (police != null) {
				name = police.getName();
			}
			System.out
					.println("total count is :" + 1 + " , First is : " + name);
		} catch (Exception ex) {
			System.out.println("select failed");
		}
	}

	@RequestMapping(value = "selectPoliceList.do", produces = "application/json;charset=UTF-8")
	public void selectPoliceList() throws Exception {
		try {
			List<Police> list = policeService.selectAll();
			String name = "list is empty!";
			int count = list.size();
			if (count > 0) {
				name = list.get(0).getName();
			}
			System.out.println("total count is :" + count + " , First is : "
					+ name);
		} catch (Exception ex) {
			System.out.println("select failed");
		}
	}
}
