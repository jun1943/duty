package com.tianyi.drs.duty.viewmodel;

public class PoliceViewModel {
	private Integer id;

    private String typeName;

    private String name;

    private Integer typeId;
    
    private Integer orgId;
    
    private String orgName;

    private String idcardno;

    private String number;

    private String title;

    private String mobile;

    private String mobileShort;

    private String intercomGroup;

    private String intercomPerson;

    private Integer gpsId;

    private String gpsName;

    private Boolean syncState;

    private Integer platformId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getIdcardno() {
        return idcardno;
    }

    public void setIdcardno(String idcardno) {
        this.idcardno = idcardno;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobileShort() {
        return mobileShort;
    }

    public void setMobileShort(String mobileShort) {
        this.mobileShort = mobileShort;
    }

    public String getIntercomGroup() {
        return intercomGroup;
    }

    public void setIntercomGroup(String intercomGroup) {
        this.intercomGroup = intercomGroup;
    }

    public String getIntercomPerson() {
        return intercomPerson;
    }

    public void setIntercomPerson(String intercomPerson) {
        this.intercomPerson = intercomPerson;
    }

    public Integer getGpsId() {
        return gpsId;
    }

    public void setGpsId(Integer gpsId) {
        this.gpsId = gpsId;
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
}
