package com.tianyi.drs.duty.model;

public class GPSDevice {
    private Integer id;

    private Integer gpsTypeId;

    private Integer orgId;

    private String number;

    private Integer gpsId;

    private String gpsName;

    private byte[] gpsIcon;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGpsTypeId() {
        return gpsTypeId;
    }

    public void setGpsTypeId(Integer gpsTypeId) {
        this.gpsTypeId = gpsTypeId;
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

    public byte[] getGpsIcon() {
        return gpsIcon;
    }

    public void setGpsIcon(byte[] gpsIcon) {
        this.gpsIcon = gpsIcon;
    }
}