package com.tianyi.drs.duty.model;
/**
 * 警员任务实体类
 */
public class PoliceTarget {
	/**
	 * 主键值id
	 */
    private Integer id;
    /**
	 * 报备id
	 */
    private Integer dutyId;
    /**
	 * 报备明细id
	 */
    private Integer dutyItemId;
    /**
	 * 警员id
	 */
    private Integer policeId;
    /**
	 * 任务类型id
	 */
    private Integer taskTypeId;
    /**
	 * 区域id
	 */
    private Integer targetId;
    /**
	 * 经过次数
	 */
	private Integer count;
	/**
	 * 停留时间
	 */
	private Integer stayTime;
	/**
	 * 是否经过
	 */
    private Boolean isPass;
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

    public Integer getDutyId() {
        return dutyId;
    }

    public void setDutyId(Integer dutyId) {
        this.dutyId = dutyId;
    }

    public Integer getDutyItemId() {
        return dutyItemId;
    }

    public void setDutyItemId(Integer dutyItemId) {
        this.dutyItemId = dutyItemId;
    }
    
    public Integer getPoliceId() {
        return policeId;
    }

    public void setPoliceId(Integer policeId) {
        this.policeId = policeId;
    }

    public Integer getTaskTypeId() {
        return taskTypeId;
    }

    public void setTaskTypeId(Integer taskTypeId) {
        this.taskTypeId = taskTypeId;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public Boolean getIsPass() {
        return isPass;
    }

    public void setIsPass(Boolean isPass) {
        this.isPass = isPass;
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

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getStayTime() {
		return stayTime;
	}

	public void setStayTime(Integer stayTime) {
		this.stayTime = stayTime;
	}
}