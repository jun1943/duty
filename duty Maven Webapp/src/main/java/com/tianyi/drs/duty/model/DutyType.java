package com.tianyi.drs.duty.model;

public class DutyType {


	private Integer id=0;

    private String name;

    private Integer parentId;

    private Integer level =0;

    private Boolean isLeaf;

    private String fullpath;

    private Boolean isShowname;

    private Integer maxPolice;

    private Integer attireType;

    private Integer dutyPropertyId;

    private Integer assoTaskType;

    private Boolean syncState;

    private Integer platformId;

    private Integer armamentType;
    
    private boolean isUsed;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Boolean getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public String getFullpath() {
        return fullpath;
    }

    public void setFullpath(String fullpath) {
        this.fullpath = fullpath;
    }

    public Boolean getIsShowname() {
        return isShowname;
    }

    public void setIsShowname(Boolean isShowname) {
        this.isShowname = isShowname;
    }

    public Integer getMaxPolice() {
        return maxPolice;
    }

    public void setMaxPolice(Integer maxPolice) {
        this.maxPolice = maxPolice;
    }

    public Integer getAttireType() {
        return attireType;
    }

    public void setAttireType(Integer attireType) {
        this.attireType = attireType;
    }

    public Integer getDutyPropertyId() {
        return dutyPropertyId;
    }

    public void setDutyPropertyId(Integer dutyPropertyId) {
        this.dutyPropertyId = dutyPropertyId;
    }

    public Integer getAssoTaskType() {
        return assoTaskType;
    }

    public void setAssoTaskType(Integer assoTaskType) {
        this.assoTaskType = assoTaskType;
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
    
    public Integer getArmamentType() {
		return armamentType;
	}

	public void setArmamentType(Integer armamentType) {
		this.armamentType = armamentType;
	}

	public boolean getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}
}