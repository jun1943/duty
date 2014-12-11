package com.tianyi.drs.duty.model;
/**
 * 车辆分组、组织机构实体类
 */
public class VehicleGroupOrg {
	/**
	 * 主键id
	 */
	private Integer id;
	/**
	 * 车辆分组id
	 */
    private Integer vehicleGroupId;
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

    public Integer getVehicleGroupId() {
        return vehicleGroupId;
    }

    public void setVehicleGroupId(Integer vehicleGroupId) {
        this.vehicleGroupId = vehicleGroupId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }
}