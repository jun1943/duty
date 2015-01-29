package com.tianyi.drs.duty.dao;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.basedata.model.Police;
import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.exportmodel.ExtDbResult;
import com.tianyi.drs.duty.exportmodel.ExtItem;

@MyBatisRepository
public interface ExportMapper {

	List<ExtDbResult>loadDutyItemInfo(Map<String, Object> map);
	
	
}
