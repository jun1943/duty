package com.tianyi.drs.duty.service;

import java.util.List;

import com.tianyi.drs.duty.model.Org;
import com.tianyi.drs.duty.viewmodel.TaskTargetVM;

/**
 * 勤务报备，关联任务列表获取
 * @author lq
 *
 */
public interface DutyTaskService {
	/**
	 * 根据任务类型，组织机构，获取报备任务列表
	 * @param taskType
	 * @param org
	 * @return
	 */
	List<TaskTargetVM>  loadTaskTargetVMList(Integer taskType,Org org);
}
