package com.tianyi.drs.duty.model;

import java.util.Date;

public class DutyDesc {
    private Integer id;

    private Integer orgId;

    private Boolean asTemplate;

    private String name;

    private Date createTime;

    private Integer preparerId;

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

    public Boolean getAsTemplate() {
        return asTemplate;
    }

    public void setAsTemplate(Boolean asTemplate) {
        this.asTemplate = asTemplate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}