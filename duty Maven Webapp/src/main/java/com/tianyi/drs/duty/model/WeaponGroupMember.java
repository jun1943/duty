package com.tianyi.drs.duty.model;
/**
 * 武器分组成员实体类
 */
public class WeaponGroupMember {
	/**
	 * 主键id
	 */
	private Integer id;
	/**
	 * 武器分组id
	 */
    private Integer groupId;
    /**
	 * 武器id
	 */
    private Integer weaponId;
    /**
	 * 平台标识
	 */
    private Boolean syncState;
    /**
	 * 平台id
	 */
    private Integer platformId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getWeaponId() {
        return weaponId;
    }

    public void setWeaponId(Integer weaponId) {
        this.weaponId = weaponId;
    }

    public Boolean getSyncState() {
        return syncState;
    }

    public void setSyncState(Boolean syncState) {
        this.syncState = syncState;
    }

    public Integer getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }
}