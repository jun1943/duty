package com.tianyi.drs.duty.viewmodel;

/**
 * 警员分组model类，用于前台操作
 * @author lq
 */
public class PGVM {
	/**
	 * 分组名称
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
