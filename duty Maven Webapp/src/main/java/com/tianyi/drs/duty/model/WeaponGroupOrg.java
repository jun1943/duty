package com.tianyi.drs.duty.model;
/**
 * 武器分组组织机构关系实体类
 */
public class WeaponGroupOrg {
	/**
	 * 主键id
	 */
	private Integer id;
	/**
	 * 武器分组id
	 */
    private Integer weaponGroupId;
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

    public Integer getWeaponGroupId() {
        return weaponGroupId;
    }

    public void setWeaponGroupId(Integer weaponGroupId) {
        this.weaponGroupId = weaponGroupId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }
}