package com.tianyi.drs.duty.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tianyi.drs.duty.dao.DutyReportMapper;
import com.tianyi.drs.duty.service.DutyReportService;
import com.tianyi.drs.duty.viewmodel.DutyReportCriteria;
import com.tianyi.drs.duty.viewmodel.DutyReportVM;

@Service("dutyReportService")
public class DutyReportServiceImpl implements DutyReportService{

	@Resource(name = "dutyReportMapper")
	private DutyReportMapper dutyReportMapper;
	
	public List<DutyReportVM> loadDutyReport(DutyReportCriteria criteria) {
		Map<String,Object> map=new HashMap<String,Object>();
		if(criteria.getOrgIds().size()>0){
			map.put("orgIds", criteria.getOrgIds());
		}
		map.put("ymd", criteria.getYmd());
		map.put("beginTime", criteria.getBeginTime());
		map.put("endTime", criteria.getEndTime());
		if(criteria.getTaskPropertyIds().size()>0){
			map.put("taskPropertyIds", criteria.getTaskPropertyIds());
		}
		if(criteria.getAttireTypeIds().size()>0){
			map.put("attireTypeIds", criteria.getAttireTypeIds());
		}
		if(criteria.getPoliceTypeIds().size()>0){
			map.put("policeTypeIds", criteria.getPoliceTypeIds());
		}
		if(criteria.getArmamentTypeIds().size()>0){
			map.put("armamentTypeIds", criteria.getArmamentTypeIds());
		}
		if(criteria.getDutyTypeIds().size()>0){
			map.put("dutyTypeIds", criteria.getDutyTypeIds());
		}
		
		List<DutyReportVM> ls=dutyReportMapper.loadDutyReport(map);
		
		return ls;
	}

}
