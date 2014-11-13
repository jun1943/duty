package com.tianyi.drs.duty.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianyi.drs.duty.service.DutyReportService;
import com.tianyi.drs.duty.viewmodel.DutyReportCriteria;
import com.tianyi.drs.duty.viewmodel.DutyReportVM;
import com.tianyi.drs.duty.viewmodel.ListResult;
import com.tianyi.drs.duty.viewmodel.TaskTargetVM;

@Scope("prototype")
@Controller
@RequestMapping("/dutyReport")
public class DutyReportController {
	@Resource(name = "dutyReportService")
	protected DutyReportService dutyReportService;
	
	@RequestMapping(value = "loadDutyReport.do")
	public @ResponseBody String loadDutyReport(
			@RequestParam(value = "criteria", required = false) DutyReportCriteria criteria,
			HttpServletRequest request
			){
		
		List<DutyReportVM> ls=dutyReportService.loadDutyReport(criteria);
		
		ListResult<DutyReportVM> rs=new ListResult<DutyReportVM>(ls.size(),ls,true);
		
		return rs.toJson();
	}
}
