package com.tianyi.drs.duty.dao;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.viewmodel.DutyReportVM;

@MyBatisRepository
public interface DutyReportMapper {
	List<DutyReportVM> loadDutyReport(Map<String,Object> map);
}
