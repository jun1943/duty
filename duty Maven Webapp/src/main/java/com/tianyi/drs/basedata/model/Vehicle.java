package com.tianyi.drs.basedata.model;

public class Vehicle {
    private int id;

    private int vehicleTypeId;

    private int orgId;

    private String number;

    private String intercomGroup;

    private int gpsId;

    private String gpsName;

    private String purpose;

    private String brand;

    private String siteQty;

    private Boolean syncState;

    private int platformId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(int vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getIntercomGroup() {
        return intercomGroup;
    }

    public void setIntercomGroup(String intercomGroup) {
        this.intercomGroup = intercomGroup;
    }

    public int getGpsId() {
        return gpsId;
    }

    public void setGpsId(int gpsId) {
        this.gpsId = gpsId;
    }

    public String getGpsName() {
        return gpsName;
    }

    public void setGpsName(String gpsName) {
        this.gpsName = gpsName;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSiteQty() {
        return siteQty;
    }

    public void setSiteQty(String siteQty) {
        this.siteQty = siteQty;
    }

    public Boolean getSyncState() {
        return syncState;
    }

    public void setSyncState(Boolean syncState) {
        this.syncState = syncState;
    }

    public int getPlatformId() {
        return platformId;
    }

    public void setPlatformId(int platformId) {
        this.platformId = platformId;
    }
}