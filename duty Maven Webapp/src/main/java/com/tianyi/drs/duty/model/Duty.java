package com.tianyi.drs.duty.model;

import java.util.Date;

/**
 * 报备实体类
 * @author lq
 *
 */
public class Duty {
	/**
	 * 主键值id
	 */
    private Integer id;
    /**
	 * 组织机构id
	 */
    private Integer orgId;
    /**
	 * 是否是模板
	 */
    private Boolean isTemplate;
    /**
	 * 报备名称
	 */
    private String name;
    /**
	 * 报备日期：20140101 
	 */
    private Integer ymd;
    /**
	 * 创建时间
	 */
    private Date createTime;
    /**
	 * 属性id
	 */
    private Integer preparerId;
    /**
	 * 描述
	 */
    private String description;
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

    public Boolean getIsTemplate() {
        return isTemplate;
    }

    public void setIsTemplate(Boolean isTemplate) {
        this.isTemplate = isTemplate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYmd() {
        return ymd;
    }

    public void setYmd(Integer ymd) {
        this.ymd = ymd;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getPreparerId() {
        return preparerId;
    }

    public void setPreparerId(Integer preparerId) {
        this.preparerId = preparerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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