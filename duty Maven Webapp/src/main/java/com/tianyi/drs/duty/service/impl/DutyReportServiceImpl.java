package com.tianyi.drs.duty.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.tianyi.drs.duty.dao.DutyReportMapper;
import com.tianyi.drs.duty.service.DutyReportService;
import com.tianyi.drs.duty.viewmodel.DutyReportCriteria;
import com.tianyi.drs.duty.viewmodel.DutyReportVM;

public class DutyReportServiceImpl implements DutyReportService{

	@Resource(name = "dutyReportMapper")
	private DutyReportMapper dutyReportMapper;
	
	public List<DutyReportVM> loadDutyReport(DutyReportCriteria crideria) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("orgIds", crideria.getOrgIds());
		map.put("ymd", crideria.getYmd());
		map.put("beginTime", crideria.getBeginTime());
		map.put("endTime", crideria.getEndTime());
		map.put("taskProperyIds", crideria.getTaskPropertyIds());
		map.put("attireTypeIds", crideria.getAttireTypeIds());
		map.put("policeTypeIds", crideria.getPoliceTypeIds());
		map.put("armamentTypeIds", crideria.getArmamentTypeIds());
		map.put("dutyTypeIds", crideria.getDutyTypeIds());
		
		List<DutyReportVM> ls=dutyReportMapper.loadDutyReport(map);
		
		return ls;
	}

}
