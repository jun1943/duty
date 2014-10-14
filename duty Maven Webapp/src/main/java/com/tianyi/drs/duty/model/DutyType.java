package com.tianyi.drs.duty.model;

public class DutyType {
    private Long id;

    private String name;

    private Long parentId;

    private Integer level;

    private Boolean isLeaf;

    private String fullpath;

    private Boolean isShowname;

    private Integer defaultQty;

    private Boolean syncState;

    private Integer platformId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
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

    public Integer getDefaultQty() {
        return defaultQty;
    }

    public void setDefaultQty(Integer defaultQty) {
        this.defaultQty = defaultQty;
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