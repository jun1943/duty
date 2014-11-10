package com.tianyi.drs.duty.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tianyi.drs.duty.dao.DutyTaskMapper;
import com.tianyi.drs.duty.model.Org;
import com.tianyi.drs.duty.service.DutyTaskService;
import com.tianyi.drs.duty.viewmodel.TaskTargetVM;


@Service("dutyTaskService")
public class DutyTaskServiceImpl implements DutyTaskService{

	@Resource(name = "dutyTaskMapper")
	private DutyTaskMapper dutyTaskMapper;
	
	public List<TaskTargetVM> loadTaskTargetVMList(Integer taskType, Org org) {
		Map<String,Object>map=new HashMap<String, Object>();
		map.put("orgId", org.getId());
		map.put("orgCode", org.getCode());
		
		List<TaskTargetVM> ls=null;
		
		switch(taskType){
		case 1:
			ls=dutyTaskMapper.loadCommunityByOrg(map);
			break;
		case 2:
			ls=dutyTaskMapper.loadPatrolAreaByOrg(map);
			break;
		case 3:
			ls=dutyTaskMapper.loadBayonetByOrg(map);
			break;
		}
		
		return ls;
	}

}
