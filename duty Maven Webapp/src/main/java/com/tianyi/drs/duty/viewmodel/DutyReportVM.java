package com.tianyi.drs.duty.viewmodel;

/**
 * 报备综合统计model类，用作前台显示
 * @author lq
 */
public class DutyReportVM {
	/**
	 * 主键值id
	 */
	private Integer id;
	/**
	 * 父级节点id
	 */
	private Integer parentId;
	/**
	 * 组织机构id
	 */
	private Integer orgId;
	/**
	 * 组织结构名称
	 */
	private String orgName;
	/**
	 * 组织机构名称缩写
	 */
	private String orgShortName;
	/**
	 * 组织机构编码
	 */
	private String orgCode;
	/**
	 * 组织结构路径
	 */
	private String orgPath;
	/**
	 * 领导名称
	 */
	private String leaderNames;
	/**
	 * 主任名称
	 */
	private String directorName;
	/**
	 * 领导报备总数
	 */
	private Integer leaderCount;
	/**
	 * 主任报备总数
	 */
	private Integer directorCount;
	/**
	 * 车辆报备总数
	 */
	private Integer vehicleCount;
	/**
	 * 警员报备总数
	 */
	private Integer policeCount;
	/**
	 * 武器报备总数
	 */
	private Integer weaponCount;
	/**
	 * 定位设备报备总数
	 */
	private Integer gpsCount;
	/**
	 * 社区报备总数
	 */
	private Integer communityCount;
	/**
	 * 巡区报备总数
	 */
	private Integer patrolAreaCount;
	/**
	 * 卡点报备总数
	 */
	private Integer bayonetCount;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgShortName() {
		return orgShortName;
	}
	public void setOrgShortName(String orgShortName) {
		this.orgShortName = orgShortName;
	}
	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgPath() {
		return orgPath;
	}

	public void setOrgPath(String orgPath) {
		this.orgPath = orgPath;
	}

	public String getLeaderNames() {
		return leaderNames;
	}
	public void setLeaderNames(String leaderNames) {
		this.leaderNames = leaderNames;
	}
	public Integer getLeaderCount() {
		return leaderCount;
	}

	public void setLeaderCount(Integer leaderCount) {
		this.leaderCount = leaderCount;
	}

	public Integer getVehicleCount() {
		return vehicleCount;
	}
	public void setVehicleCount(Integer vehicleCount) {
		this.vehicleCount = vehicleCount;
	}
	public Integer getPoliceCount() {
		return policeCount;
	}
	public void setPoliceCount(Integer policeCount) {
		this.policeCount = policeCount;
	}
	public Integer getWeaponCount() {
		return weaponCount;
	}
	public void setWeaponCount(Integer weaponCount) {
		this.weaponCount = weaponCount;
	}
	public Integer getGpsCount() {
		return gpsCount;
	}
	public void setGpsCount(Integer gpsCount) {
		this.gpsCount = gpsCount;
	}
	public Integer getCommunityCount() {
		return communityCount;
	}
	public void setCommunityCount(Integer communityCount) {
		this.communityCount = communityCount;
	}
	public Integer getPatrolAreaCount() {
		return patrolAreaCount;
	}
	public void setPatrolAreaCount(Integer patrolAreaCount) {
		this.patrolAreaCount = patrolAreaCount;
	}
	public Integer getBayonetCount() {
		return bayonetCount;
	}
	public void setBayonetCount(Integer bayonetCount) {
		this.bayonetCount = bayonetCount;
	}
 
	public Integer getDirectorCount() {
		return directorCount;
	}

	public void setDirectorCount(Integer directorCount) {
		this.directorCount = directorCount;
	}

	public String getDirectorName() {
		return directorName;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}


	
}
