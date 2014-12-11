package com.tianyi.drs.duty.model;
/**
 * 组织机构实体类
 */
public class Org {
	/**
	 * 主键值id
	 */
	private int id;
	/**
	 * 上级机构id
	 */
	private int parentId;
	/**
	 * 组织机构名称
	 */
	private String name;
	/**
	 * 组织机构名称缩写
	 */
	private String shortName;
	/**
	 * 组织机构编码
	 */
	private String code;
	/**
	 * 组织机构编码
	 */
	private String path;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
}
