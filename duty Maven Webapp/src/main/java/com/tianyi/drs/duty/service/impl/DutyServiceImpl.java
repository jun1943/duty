package com.tianyi.drs.duty.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tianyi.drs.duty.dao.DutyMapper;
import com.tianyi.drs.duty.service.DutyService;
import com.tianyi.drs.duty.viewmodel.DutyItemVM;
import com.tianyi.drs.duty.viewmodel.DutyVM;

@Service("dutyService")
public class DutyServiceImpl implements DutyService{

	@Resource(name = "dutyMapper")
	private DutyMapper dutyMapper;
	
	public List<DutyVM> loadDutyVMList(Map<String, Object> map) {
		return dutyMapper.loadDutyVMList(map);
	}

	public void itemToTree(){
		
		
	}

}
