package com.tianyi.drs.duty.model;
/**
 * 警员分组、组织机构关联实体类
 */
public class PoliceGroupOrg {
	/**
	 * 主键值id
	 */
    private Integer id;
    /**
	 * 警员分组id
	 */
    private Integer policeGroupId;
    /**
	 * 组织结构id
	 */
    private Integer orgId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPoliceGroupId() {
        return policeGroupId;
    }

    public void setPoliceGroupId(Integer policeGroupId) {
        this.policeGroupId = policeGroupId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }
}