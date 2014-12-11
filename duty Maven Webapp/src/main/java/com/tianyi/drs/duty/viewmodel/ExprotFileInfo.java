package com.tianyi.drs.duty.viewmodel;
/**
 * 导出文件类
 * @author lq
 */
public class ExprotFileInfo {

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	/**
	 * 导出文件路径
	 */
	private String path;
	/**
	 * 导出文件名称
	 */
	private String name;
	/**
	 * 导出文件大小
	 */
	private Integer size;
	
}
