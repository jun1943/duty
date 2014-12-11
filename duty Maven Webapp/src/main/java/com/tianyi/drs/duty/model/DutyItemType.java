package com.tianyi.drs.duty.model;

/**
 * 节点属性实体类
 * @author lq
 *
 */
public class DutyItemType {
	/**
	 * 主键id
	 */
    private Integer id;
    /**
	 * 属性类型id
	 */
    private Integer typeId;
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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}