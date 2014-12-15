package com.tianyi.drs.duty.service;

import java.util.List;

import com.tianyi.drs.duty.viewmodel.DutyReportCriteria;
import com.tianyi.drs.duty.viewmodel.DutyReportVM;

/**
 * 警务综合查询接口层
 * @author lq
 *
 */
public interface DutyReportService {

	/**
	 * 查询警务综合查询统计数据
	 * @param crideria
	 * 包括；时间、衣着、武装、属性、类型，报备
	 * @return
	 */
	List<DutyReportVM> loadDutyReport(DutyReportCriteria crideria);
	
}
