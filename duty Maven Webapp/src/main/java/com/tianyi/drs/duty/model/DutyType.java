package com.tianyi.drs.duty.model;
/**
 * 报备类型实体类
 * @author lq
 *
 */
public class DutyType {

	/**
	 * id值，默认为0
	 */
	private Integer id=0;
	/**
	 * 勤务类型名称
	 */
    private String name;
    /**
	 * 上级节点id
	 */
    private Integer parentId;
    /**
	 * 节点层级，默认为0 
	 */
    private Integer level =0;
    /**
	 * 是否子节点
	 */
    private Boolean isLeaf;
    /**
	 * 节点全路径
	 */
    private String fullpath;
    /**
	 * 是否显示名称
	 */
    private Boolean isShowname;
    /**
	 * 最大警员数目
	 */
    private Integer maxPolice;
    /**
	 * 附加属性
	 */
    private Integer attireType;
    /**
	 * 属性id
	 */
    private Integer dutyPropertyId;
    /**
	 * 着装方式id
	 */
    private Integer assoTaskType;
    /**
	 * 平台标识
	 */
    private Boolean syncState;
    /**
	 * 平台id
	 */
    private Integer platformId;
    /**
	 * 武装方式id
	 */
    private Integer armamentType;
    /**
	 * 是否启用
	 */
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