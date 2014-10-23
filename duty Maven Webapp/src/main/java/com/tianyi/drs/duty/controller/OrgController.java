package com.tianyi.drs.duty.controller;

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

import com.tianyi.drs.duty.model.Org;
import com.tianyi.drs.duty.service.OrgService;
import com.tianyi.drs.duty.viewmodel.OrgWithPoliceVM;

@Scope("prototype")
@Controller
@RequestMapping("/org")
public class OrgController {

	@Resource(name = "orgService")
	protected OrgService orgService;
	
	@RequestMapping(value = "list.do")
	public @ResponseBody String  List(
			@RequestParam(value = "orgCode", required = false) String orgCode,
			@RequestParam(value = "orgPath", required = false) String orgPath,
			HttpServletRequest request
			){
		
			Map<String,Object> map=new HashMap<String,Object>();
			
			map.put("orgCode", orgCode);
			map.put("orgPath", orgPath);
			
			List<Org> ls=orgService.loadSubOrgList(orgCode, orgPath);
			
			String rs=JSONArray.fromObject(ls).toString();
			return rs;
		
	}
	/**
	 * 返回组织机构和下属警员信息
	 * @param orgCode
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "listWithPolice.do")
	public @ResponseBody String listWithPolice(
			@RequestParam(value = "rootId", required = false) Integer rootId,
			@RequestParam(value = "id", required = false) String hybrid_id,
			HttpServletRequest request
			){

		Integer qid=null;
		
		if(hybrid_id!=null && hybrid_id!=""){
			if(hybrid_id.indexOf("org")>=0){
				qid=new Integer(hybrid_id.split("_")[1]);
			}
		}else{
			qid=rootId;
		}
		
		if(qid!=null){
			List<OrgWithPoliceVM> ls=orgService.loadOrgWithPoliceVMList(qid);
			JSONArray rs=JSONArray.fromObject(ls);
			
			return rs.toString();
		}else{
			return null;
		}		
	}
	
}
