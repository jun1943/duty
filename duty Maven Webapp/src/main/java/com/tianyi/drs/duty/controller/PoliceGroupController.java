package com.tianyi.drs.duty.controller;
 
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
 
import net.sf.json.JSONArray;
import net.sf.json.JSONObject; 

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianyi.drs.duty.model.PoliceGroup;
import com.tianyi.drs.duty.model.PoliceGroupOrg;
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
	
		JSONObject joQuery =JSONObject.fromObject(query);

		int orgId=joQuery.getInt("orgId");
		String orgCode=joQuery.getString("orgCode");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageStart", (page-1) * rows);
		map.put("pageSize", rows);
		map.put("orgCode", orgCode);
		map.put("orgId", orgId);
		
		map.put("inSubOrg", 0);
		
		int total=policeGroupService.loadVMCountByOrgId(map);

		List<PoliceGroupVM> pgvms=policeGroupService.loadVMListByOrgId(map);
		
		ListResult<PoliceGroupVM> rs=new ListResult<PoliceGroupVM>(total,pgvms);
		
		String ss=JSONObject.fromObject(rs).toString();
		
		return ss;
		
	}
	
	@RequestMapping(value = "getShareOrgs.do")
	public @ResponseBody String getShareOrgs(
			@RequestParam(value = "policeGroupId", required = false) Integer policeGroupId,
			HttpServletRequest request
			){
		
		
		List<PoliceGroupOrg> ls = policeGroupService.loadShareOrgList(policeGroupId);
		
		String rs=JSONArray.fromObject(ls).toString();
		
		return rs;
		
	}
	
	@RequestMapping(value = "loadPoliceGroup.do")
	public  @ResponseBody String loadPoliceGroup(
			@RequestParam(value="policeGroupId",required=false) Integer policeGroupId,
			HttpServletRequest request
			){
		
		PoliceGroup pg=	policeGroupService.loadById(policeGroupId);
		JSONObject joPG=JSONObject.fromObject(pg);
		
		List<PoliceGroupOrg> ls = policeGroupService.loadShareOrgList(policeGroupId);
		
		JSONArray joArray=JSONArray.fromObject(ls);
		
		joPG.accumulate("shareOrgs", joArray.toString());
		
		String rs=joPG.toString();
		return rs;
	}
	
	@RequestMapping(value = "savePoliceGroup.do")
	public @ResponseBody String savePoliceGroup(
			@RequestParam(value = "policeGroup", required = false) String policeGroup,
			HttpServletRequest request
			){
		
		JSONObject joPG =JSONObject.fromObject(policeGroup);
		
		PoliceGroup pg=new PoliceGroup();
		pg.setId(joPG.getInt("id"));
		pg.setName(joPG.getString("name"));
		pg.setShareType(joPG.getInt("shareType"));
		pg.setOrgId(joPG.getInt("orgId"));
		
		JSONArray jaOrgIds=joPG.getJSONArray("shareOrgIds");
		
		Object[] orgIds=jaOrgIds.toArray();
		
		policeGroupService.savePoliceGroup(pg, orgIds);
		
		JSONObject rs=new JSONObject();
		rs.accumulate("isSuccess", true);
		rs.accumulate("msg", "");
		
		return rs.toString();
	}
	/**
	 * 删除警员组
	 * @param policeGroupId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deletePoliceGroup.do")
	 public @ResponseBody String deletePoliceGroup(
			 @RequestParam(value="policeGroupId",required=false) Integer policeGroupId,
				HttpServletRequest request
				){
		 
		 policeGroupService.deleteById(policeGroupId);
		 
		JSONObject rs=new JSONObject();
		rs.accumulate("isSuccess", true);
		rs.accumulate("msg", "");
		 	
		return rs.toString();
	 }
	
}


