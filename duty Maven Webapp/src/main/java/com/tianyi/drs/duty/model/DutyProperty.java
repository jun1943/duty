package com.tianyi.drs.duty.model;
/**
 * 报备属性实体类
 * @author lq
 *
 */
public class DutyProperty {
	/**
	 * 主键值id
	 */
	private Integer id;
	/**
	 * 属性名称
	 */
    private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 
}
