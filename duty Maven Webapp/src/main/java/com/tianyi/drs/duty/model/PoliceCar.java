package com.tianyi.drs.duty.model;

public class PoliceCar {
    private Integer id;

    private Integer carTypeId;

    private Integer orgId;

    private String number;

    private String intercomGroup;

    private Integer gpsId;

    private String gpsName;

    private String carPurpose;

    private String carBrand;

    private Integer carSiteqty;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCarTypeId() {
        return carTypeId;
    }

    public void setCarTypeId(Integer carTypeId) {
        this.carTypeId = carTypeId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
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

    public String getCarPurpose() {
        return carPurpose;
    }

    public void setCarPurpose(String carPurpose) {
        this.carPurpose = carPurpose;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public Integer getCarSiteqty() {
        return carSiteqty;
    }

    public void setCarSiteqty(Integer carSiteqty) {
        this.carSiteqty = carSiteqty;
    }
}