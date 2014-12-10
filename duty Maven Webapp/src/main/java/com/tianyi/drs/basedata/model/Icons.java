package com.tianyi.drs.basedata.model;
/*
 * 系统图标实体类
 */
public class Icons {
	/*
	 * 主键值id
	 */
    private Integer id;
    /*
	 * 图标类型id
	 */
    private Integer typeId;
    /*
	 * 图标名称
	 */
    private String name;
    /*
	 * 图标地址
	 */
    private String iconUrl;
    /*
	 *凭条标识 
	 */
    private Boolean syncState;
    /*
	 * 平台id
	 */
    private Integer platformId;

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

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
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