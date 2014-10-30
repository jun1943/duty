package com.tianyi.drs.duty.model;

public class DutyItem {
    private Integer id;

    private Integer dutyShiftId;

    private Integer itemTypeId;

    private String itemId;

    private String udName;

    private Integer parentId;

    private Boolean isLeaf;

    private Integer level;

    private String fullIdPath;

    private Boolean syncState;

    private Integer platformId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDutyShiftId() {
        return dutyShiftId;
    }

    public void setDutyShiftId(Integer dutyShiftId) {
        this.dutyShiftId = dutyShiftId;
    }

    public Integer getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(Integer itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getUdName() {
        return udName;
    }

    public void setUdName(String udName) {
        this.udName = udName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Boolean getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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
}