package com.tianyi.drs.duty.viewmodel;

import java.util.List;

import com.tianyi.drs.duty.model.DutyShift;

public class DutyShiftVM extends DutyShift{
	private List<DutyItemVM>  items;
	
	public List<DutyItemVM> getItems() {
		return items;
	}

	public void setItems(List<DutyItemVM> items) {
		this.items = items;
	}
}
