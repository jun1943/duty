package com.tianyi.drs.duty.viewmodel;

import java.util.List;

import com.tianyi.drs.duty.model.Duty;

public class DutyVM extends Duty{
	
	private String typeName;
	private List<DutyShiftVM> shifts;
	
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public List<DutyShiftVM> getShifts() {
		return shifts;
	}

	public void setShifts(List<DutyShiftVM> shifts) {
		this.shifts = shifts;
	}
}
