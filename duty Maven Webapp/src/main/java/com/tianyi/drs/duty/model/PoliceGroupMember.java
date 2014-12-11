package com.tianyi.drs.duty.model;
/**
 * 警员分组成员实体类
 */
public class PoliceGroupMember {
	/**
	 * 主键值id
	 */
    private Integer id;
    /**
	 * 警员分组id
	 */
    private Integer groupId;
    /**
	 * 警员id
	 */
    private Integer policeId;
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

    public Integer getPoliceId() {
        return policeId;
    }

    public void setPoliceId(Integer policeId) {
        this.policeId = policeId;
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