package com.tianyi.drs.duty.service;

import java.util.List;

import com.tianyi.drs.duty.viewmodel.DutyReportCriteria;
import com.tianyi.drs.duty.viewmodel.DutyReportVM;

public interface DutyReportService {

	List<DutyReportVM> loadDutyReport(DutyReportCriteria crideria);
	
}
