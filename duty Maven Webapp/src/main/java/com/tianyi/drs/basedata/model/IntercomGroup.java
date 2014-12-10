package com.tianyi.drs.basedata.model;
/*
 * 对讲机组呼号实体类
 */
public class IntercomGroup {
	/*
	 * 主键值id
	 */
    private Integer id;
    /*
	 * 组呼号名称
	 */
    private String name;
    /*
	 * 平台标识
	 */
    private Boolean syncState;
    /*
	 * 平台id
	 */
    private Integer platformId;
  
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