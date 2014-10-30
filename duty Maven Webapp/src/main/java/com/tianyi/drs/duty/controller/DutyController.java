package com.tianyi.drs.duty.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianyi.drs.duty.service.DutyDescService;
import com.tianyi.drs.duty.viewmodel.DutyDescVM;
import com.tianyi.drs.duty.viewmodel.ObjResult;

@Scope("prototype")
@Controller
@RequestMapping("/duty")
public class DutyController {
	@Resource(name = "dutyDescService")
	protected DutyDescService dutyDescService;
	
	@RequestMapping(value = "loadDutyDesc.do")
	public @ResponseBody String loadDutyDesc(
			@RequestParam(value = "id", required = false) Integer id,
			HttpServletRequest request
			){
		
		DutyDescVM vm= dutyDescService.loadDutyDescVM(id);
		
		ObjResult<DutyDescVM> rs=new ObjResult<DutyDescVM>(true,null,id,vm);
		
		return rs.toJson();
	}
	
}
