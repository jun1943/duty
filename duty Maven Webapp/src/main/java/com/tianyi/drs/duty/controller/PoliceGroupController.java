package com.tianyi.drs.duty.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tianyi.drs.duty.service.PoliceGroupService;
import com.tianyi.drs.duty.viewmodel.DbTableResult;
import com.tianyi.drs.duty.viewmodel.PGVM;
import com.tianyi.drs.duty.viewmodel.PoliceGroupVM;
import com.tianyi.util.PaginationData;

@Scope("prototype")
@Controller
@RequestMapping("/policeGroup")
public class PoliceGroupController {

	@Resource(name = "policeGroupService")
	protected PoliceGroupService policeGroupService;
	
	@RequestMapping(value = "list.do")
	public @ResponseBody String List(
			@RequestParam(value = "dtPar", required = false) String dtPar,
			@RequestParam(value = "orgId", required = false) String orgId,
			@RequestParam(value = "orgCode", required = false) String orgCode,
			@RequestParam(value = "orgPath", required = false) String orgPath,
			@RequestParam(value = "inSubOrg", required = false) Integer inSubOrg,
			@RequestParam(value = "groupName", required = false) String groupName,
			@RequestParam(value = "pageIndex", required = false) Integer pageCount,
			@RequestParam(value = "pageNumber", required = false) Integer pageNo,
			@RequestParam(value = "totalCount", required = false) Integer totalCount,
			HttpServletRequest request) {
	
		/*
		 * 
		 * @RequestParam(value = "iDisplayStart") int start, 
           @RequestParam(value = "iDisplayLength") int pageSize, 
           @RequestParam(value = "sEcho") int sEcho) throws Exception { 
		 */
		//{"sEcho":1,"iDisplayStart":0,"iDisplayLength":10}

		JSONObject o =JSONObject.fromObject(orgId);
		
		JSONObject dtParObj=JSONObject.fromObject(dtPar);

		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("orgCode", orgCode);
		map.put("orgPath", orgPath);
		map.put("inSubOrg", inSubOrg);
		map.put("groupName", groupName);
		
		int total=policeGroupService.loadVMCount(map);


		map.put("pageStart", dtParObj.getInt("iDisplayStart"));
		map.put("pageSize", dtParObj.getInt("iDisplayLength"));

		
		List<PoliceGroupVM> pgvms=policeGroupService.loadVMList(map);
		
		DbTableResult<PoliceGroupVM> rs=new DbTableResult<PoliceGroupVM>();
		
		rs.setiTotalDisplayRecords(total);
		rs.setiTotalRecords(pgvms.size());
		rs.setAaData(pgvms);
		rs.setsEcho(dtParObj.getInt("sEcho"));

		String ss=JSONObject.fromObject(rs).toString();
		
		return ss;
		
	}
}

 
