package com.tianyi.drs.duty.model;
/**
 * 警员分组实体类
 * @author lq
 *
 */
public class PoliceGroup {
	/**
	 * 主键值id
	 */
    private Integer id;
    /**
     * 组织机构id
     */
    private Integer orgId;
    /**
     * 分组名称
     */
    private String name;
    /**
     * 是否共享到下级
     */
    private Integer shareType;
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

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getShareType() {
        return shareType;
    }

    public void setShareType(Integer shareType) {
        this.shareType = shareType;
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