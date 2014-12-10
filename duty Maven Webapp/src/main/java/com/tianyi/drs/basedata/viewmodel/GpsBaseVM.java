package com.tianyi.drs.basedata.viewmodel;
/**
 * GPS模拟类，用于前台显示
 * @author lq
 *
 */
public class GpsBaseVM {
	/**
	 * id，用户前台显示标识
	 */
	private int id;
	/**
	 * gps名称
	 */
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
