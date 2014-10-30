package com.tianyi.drs.duty.service;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.viewmodel.DutyDescVM;

public interface DutyDescService {

	DutyDescVM loadDutyDescVM(Integer id);
	
	void save(DutyDescVM ddvm);
}
