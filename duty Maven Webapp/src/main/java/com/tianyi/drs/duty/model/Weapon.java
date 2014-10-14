package com.tianyi.drs.duty.model;

public class Weapon {
    private Long id;

    private String standard;

    private String number;

    private Long typeId;

    private Long orgId;

    private Boolean syncState;

    private Integer platformId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
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