package com.tianyi.drs.duty.dao;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.viewmodel.DutyReportVM;

/**
 * 勤务综合查询映射类
 * @author lq
 *
 */
@MyBatisRepository
public interface DutyReportMapper {
	/**
	 * 获取勤务报备统计数据
	 * @param map
	 * @return
	 */
	List<DutyReportVM> loadDutyReport(Map<String,Object> map);
}
