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
	
}
