package com.tianyi.drs.duty.model;
/**
 * GPS分组、组织机构关联中间表
 */
public class GpsGroupOrg {
	/**
	 * 主键id
	 */
    private Integer id;
    /**
	 * GPS分组id
	 */
    private Integer gpsGroupId;
    /**
	 * 组织机构id
	 */
    private Integer orgId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGpsGroupId() {
        return gpsGroupId;
    }

    public void setGpsGroupId(Integer gpsGroupId) {
        this.gpsGroupId = gpsGroupId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }
}