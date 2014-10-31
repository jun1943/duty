package com.tianyi.drs.duty.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianyi.drs.duty.service.DutyService;
import com.tianyi.drs.duty.viewmodel.ListResult;
import com.tianyi.drs.duty.viewmodel.ObjResult;

@Scope("prototype")
@Controller
@RequestMapping("/duty")
public class DutyController {
	@Resource(name = "dutyService")
	protected DutyService dutyService;
	
	@RequestMapping(value = "loadDutyDesc.do")
	public @ResponseBody String loadDutyDesc(
			@RequestParam(value = "id", required = false) Integer id,
			HttpServletRequest request
			){
		
			return null;
	}

	@RequestMapping(value = "loadDutyDescByOrgIdAndYMD.do")
	public @ResponseBody String loadDutyDescByOrgIdAndYMD(
			@RequestParam(value = "orgId", required = false) Integer orgId,
			@RequestParam(value = "ymd", required = false) Integer ymd,
			HttpServletRequest request
			){
		return null;
	}
	
//	@RequestMapping(value = "save.do")
//	public @ResponseBody String save(
//			@RequestParam(value = "dutyDesc", required = false) DutyDescVM ddvm,
//			HttpServletRequest request
//			){
//		
//		return null;
//	}
	
	@RequestMapping(value = "loadTemplateByOrgId.do")
	public @ResponseBody String loadTemplateByOrgId(
			@RequestParam(value = "orgId", required = false) Integer orgId,
			HttpServletRequest request
			){
		
		return null;
	}
}
