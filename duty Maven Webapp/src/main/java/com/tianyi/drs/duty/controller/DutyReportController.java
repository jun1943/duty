package com.tianyi.drs.duty.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianyi.drs.duty.model.PoliceTarget;
import com.tianyi.drs.duty.service.DutyReportService;
import com.tianyi.drs.duty.viewmodel.DutyItemVM;
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
			@RequestParam(value = "criteria", required = false) String criteriaJson,
			HttpServletRequest request
			){
		
		JSONObject jobj=JSONObject.fromObject(criteriaJson);
		
		JSONUtils.getMorpherRegistry().registerMorpher(  new DateMorpher(new String[] { "yyyy-MM-dd HH:mm" }));
		
		Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();

		classMap.put("orgIds", Integer.class);
		classMap.put("taskPropertyIds", Integer.class);
		classMap.put("attireTypeIds", Integer.class);
		classMap.put("policeTypeIds", Integer.class);
		classMap.put("armamentTypeIds", Integer.class);
		classMap.put("dutyTypeIds", Integer.class);
		
		DutyReportCriteria drc=(DutyReportCriteria)JSONObject.toBean(jobj,DutyReportCriteria.class,classMap);

		List<DutyReportVM> ls=dutyReportService.loadDutyReport(drc);
		
		ListResult<DutyReportVM> rs=new ListResult<DutyReportVM>(ls.size(),ls,true);
		
		return rs.toJson();
	}
		
}
