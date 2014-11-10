package com.tianyi.drs.duty.service;

import java.util.List;

import com.tianyi.drs.duty.model.Org;
import com.tianyi.drs.duty.viewmodel.TaskTargetVM;

public interface DutyTaskService {
	List<TaskTargetVM>  loadTaskTargetVMList(Integer taskType,Org org);
}
