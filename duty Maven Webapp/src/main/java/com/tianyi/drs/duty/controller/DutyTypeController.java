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

import com.tianyi.drs.basedata.service.PoliceService;
import com.tianyi.drs.basedata.viewmodel.PoliceVM;
import com.tianyi.drs.duty.model.Org;
import com.tianyi.drs.duty.service.DutyTypeService;
import com.tianyi.drs.duty.service.OrgService;
import com.tianyi.drs.duty.service.PoliceGroupService;
import com.tianyi.drs.duty.viewmodel.DutyTypePropertyVM;
import com.tianyi.drs.duty.viewmodel.DutyTypeVM;
import com.tianyi.drs.duty.viewmodel.ListResult;
import com.tianyi.drs.duty.viewmodel.ObjResult;
import com.tianyi.drs.duty.viewmodel.PoliceGroupVM;

@Scope("prototype")
@Controller
@RequestMapping("/dutyType")
public class DutyTypeController {

	@Resource(name = "dutyTypeService")
	protected DutyTypeService dutyTypeService;

	@Resource(name = "policeService")
	protected PoliceService policeService;

	@Resource(name = "policeGroupService")
	protected PoliceGroupService policeGroupService;

	@RequestMapping(value = "list.do")
	public @ResponseBody
	String List(
			@RequestParam(value = "isUsed", required = false) Boolean isUsed,
			HttpServletRequest request) {

		List<DutyTypeVM> ls = dutyTypeService.loadDutyTypeVM(isUsed);

		ListResult<DutyTypeVM> rs = new ListResult<DutyTypeVM>(ls.size(), ls);

		String result = rs.toJson();
		return result;
	}

	@RequestMapping(value = "loadProperties.do")
	public @ResponseBody
	String loadProperties(HttpServletRequest request) {

		List<DutyTypePropertyVM> ls = dutyTypeService.loadProperties();

		ListResult<DutyTypePropertyVM> rs = new ListResult<DutyTypePropertyVM>(
				ls.size(), ls);

		String result = rs.toJson();
		return result;
	}

	@RequestMapping(value = "saveDutyType.do")
	public @ResponseBody
	String saveDutyType(
			@RequestParam(value = "dutyType", required = false) String dutyType,
			HttpServletRequest request) {

		JSONObject jsObj = JSONObject.fromObject(dutyType);
		Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();

		classMap.put("properties", DutyTypePropertyVM.class);
		DutyTypeVM dtvm = (DutyTypeVM) JSONObject.toBean(jsObj,
				DutyTypeVM.class, classMap);

		dutyTypeService.save(dtvm);

		ObjResult<DutyTypeVM> rs = new ObjResult<DutyTypeVM>(true, null,
				dtvm.getId(), null);

		String result = rs.toJson();
		return result;
	}

	@RequestMapping(value = "changeDutyTypeUseState.do")
	public @ResponseBody
	String changeDutyTypeUseState(
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "isUsed", required = false) String isUsed,
			HttpServletRequest request) {

		Integer did = Integer.parseInt(id);
		Boolean dIsUsed = Boolean.parseBoolean(isUsed);

		dutyTypeService.updateUseStateByFullPath(did, dIsUsed);

		ObjResult<DutyTypeVM> rs = new ObjResult<DutyTypeVM>(true, null, 0,
				null);

		String result = rs.toJson();
		return result;
	}

	@RequestMapping(value = "deleteDutyType.do")
	public @ResponseBody
	String deleteDutyType(
			@RequestParam(value = "id", required = false) String id,
			HttpServletRequest request) {

		Integer did = Integer.getInteger(id);

		String msg = "";
		Boolean isSuccess = dutyTypeService.deleteNode(did, msg);

		ObjResult<DutyTypeVM> rs = new ObjResult<DutyTypeVM>(isSuccess, msg, 0,
				null);

		String result = rs.toJson();
		return result;
	}


	@RequestMapping(value = "getPoliceGrouplist.do")
	public @ResponseBody
	String getPoliceGrouplist(@RequestParam(value = "orgId", required = false) Integer orgId,
			HttpServletRequest request) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);

		List<PoliceGroupVM> pgvms = policeGroupService.loadVMListByOrgId(map);
		int total = pgvms.size();
		ListResult<PoliceGroupVM> rs = new ListResult<PoliceGroupVM>(total,
				pgvms);

		String ss = JSONObject.fromObject(rs).toString();

		return ss;
	}
}
