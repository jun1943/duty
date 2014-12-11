package com.tianyi.drs.duty.model;
/**
 * 报备属性id
 */
public class DutyTypePropertyRelate {
	/**
	 * 主键值id
	 */
    private Integer id;
    /**
	 * 勤务类型id
	 */
    private Integer dutyTypeId;
    /**
	 * 报备属性id
	 */
    private Integer propertyId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDutyTypeId() {
        return dutyTypeId;
    }

    public void setDutyTypeId(Integer dutyTypeId) {
        this.dutyTypeId = dutyTypeId;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }
}