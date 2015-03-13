package com.tianyi.drs.basedata.viewmodel;

import com.tianyi.drs.basedata.model.Weapon;

public class WeaponItemVM extends Weapon {
	
	/**
	 * 武器类型名称
	 */
	 private String typeName;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
