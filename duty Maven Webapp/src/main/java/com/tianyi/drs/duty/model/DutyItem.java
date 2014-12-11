package com.tianyi.drs.duty.model;

import java.util.Date;
/**
 * 报备明细实体类
 * @author lq
 *
 */
public class DutyItem {
	/**
	 * 主键id
	 */
    private Integer id;
    /**
     * 报备id
     */
    private Integer dutyId;
    /**
	 * 勤务类型id
	 */
    private Integer dutyTypeId;
    /**
	 * 班次开始时间
	 */
    private Date beginTime;
    /**
	 * 班次结束时间
	 */
    private Date endTime;
    /**
	 * 报备节点类型id
	 */
    private Integer itemTypeId;
    /**
	 * 报备节点id
	 */
    private Integer itemId;
    /**
	 * 报备节点名称
	 */
    private String itemName;
    /**
	 * 报备节点名称
	 */
    private String name;
    /**
	 * 上级节点id
	 */
    private Integer parentId;
    /**
	 * 节点层级
	 */
    private Integer level;
    /**
	 * 是否子节点
	 */
    private Boolean isLeaf;
    /**
	 * 节点全路径
	 */
    private String fullIdPath;
    /**
	 * 平台标识
	 */
    private Boolean syncState;
    /**
	 * 平台id
	 */
    private Integer platformId;
    /**
	 * 描述
	 */
    private String description;
    /**
	 * 路径地址
	 */
    private String iconUrl;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDutyId() {
        return dutyId;
    }

    public void setDutyId(Integer dutyId) {
        this.dutyId = dutyId;
    }

    public Integer getDutyTypeId() {
        return dutyTypeId;
    }

    public void setDutyTypeId(Integer dutyTypeId) {
        this.dutyTypeId = dutyTypeId;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(Integer itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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

    public String getFullIdPath() {
        return fullIdPath;
    }

    public void setFullIdPath(String fullIdPath) {
        this.fullIdPath = fullIdPath;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
}