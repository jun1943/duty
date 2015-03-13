package com.tianyi.drs.basedata.model;
/*
 * 定位设备  实体类
 */
public class Gps {
	/*
	 * 主键值id
	 */
    private Integer id;
    /*
	 * 定位设备类型id
	 */
    private Integer typeId;
    /*
	 * 定位设备所属组织机构id
	 */
    private Integer orgId;
    /*
	 * 定位设备编号
	 */
    private String number;
    /*
	 * 定位设备名称
	 */
    private String gpsName;
    /*
	 * 平台标识
	 */
    private Boolean syncState;
    /*
	 *平台id 
	 */
    private Integer platformId;
    /*
	 * 对应图标地址
	 */
    private Integer iconId;

    public Integer getIconId() {
		return iconId;
	}

	public void setIconId(Integer iconId) {
		this.iconId = iconId;
	}

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

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getGpsName() {
        return gpsName;
    }

    public void setGpsName(String gpsName) {
        this.gpsName = gpsName;
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