package com.tianyi.drs.duty.viewmodel;
/**
 * 武器分组model，用作显示
 */
public class WGVM {
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 是否共享到下级
	 */
	private String shareTypeDesc;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShareTypeDesc() {
		return shareTypeDesc;
	}
	public void setShareTypeDesc(String shareTypeDesc) {
		this.shareTypeDesc = shareTypeDesc;
	}
}
