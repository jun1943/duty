package com.tianyi.drs.duty.controller;
 
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
 
import net.sf.json.JSONObject; 

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianyi.drs.duty.service.OrgService;
import com.tianyi.drs.duty.service.PoliceGroupService;  
import com.tianyi.drs.duty.viewmodel.PoliceGroupVM;  
import com.tianyi.drs.duty.viewmodel.ListResult;
import com.tianyi.drs.duty.viewmodel.OrgVM; 

@Scope("prototype")
@Controller
@RequestMapping("/policeGroup")
public class PoliceGroupController {

	@Resource(name = "policeGroupService")
	protected PoliceGroupService policeGroupService;
	@Resource(name = "orgService")
	protected OrgService orgService;
	
	
	@RequestMapping(value = "list.do")
	public @ResponseBody String List(
			@RequestParam(value = "policeGroup_Query", required = false) String query,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			HttpServletRequest request) {
	 
		/*
		 * 
		 * @RequestParam(value = "iDisplayStart") int start, 
           @RequestParam(value = "iDisplayLength") int pageSize, 
           @RequestParam(value = "sEcho") int sEcho) throws Exception { 
		 */
		//{"sEcho":1,"iDisplayStart":0,"iDisplayLength":10}

		  
		
		
		

		JSONObject joQuery =JSONObject.fromObject(query);
		
		
		List<OrgVM> os=orgService.loadSubOrgVMList("510106000000", "");
		
		String orgCode=joQuery.getString("orgCode");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageStart", (page-1) * rows);
		map.put("pageSize", rows);
		map.put("orgCode", orgCode);
		map.put("inSubOrg", 0);
		
		int total=policeGroupService.loadVMCount(map);

		List<PoliceGroupVM> pgvms=policeGroupService.loadVMList(map);
		
//		DbTableResult<PoliceGroupVM> rs=new DbTableResult<PoliceGroupVM>();
//		
//		rs.setiTotalDisplayRecords(total);
//		rs.setiTotalRecords(pgvms.size());
//		rs.setAaData(pgvms);
//		rs.setsEcho(dtParObj.getInt("sEcho"));

		ListResult<PoliceGroupVM> rs=new ListResult<PoliceGroupVM>(total,pgvms);
		
		String ss=JSONObject.fromObject(rs).toString();
		
		return ss;
		
	}
}

 
