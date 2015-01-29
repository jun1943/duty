package com.tianyi.drs.duty.service;

import java.util.List;

import com.tianyi.drs.basedata.model.Police;
import com.tianyi.drs.duty.exportmodel.ExtItem;

public interface ExportService {

	/**
	 * 读取警员备勤信息
	 * @param orgId
	 * @param ymd
	 * @return
	 */
	List<ExtItem<Police>> loadPoliceDutyInfo(Integer orgId, Integer ymd);
	
	
	//List<ExtDbResult>loadDutyItemInfo(Integer orgId,Integer ymd);
	
}
