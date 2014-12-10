package com.tianyi.drs.basedata.viewmodel;

import com.tianyi.drs.basedata.model.Weapon;

/**
 * 武器虚拟类，继承武器实体类，用于前台显示
 * @author lq
 *
 */
public class WeaponVM extends Weapon {
	
	/**
	 * 武器类型名称
	 */
	 private String typeName;
	  /**
	   * 组织机构名称
	   */
	    private String orgName;
	    /**
	     * 组织机构编码
	     */
		 private String orgCode;
		  /**
		   * 组织机构路径
		   */
		    private String orgPath;
			 

		public String getTypeName() {
			return typeName;
		}

		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}

		public String getOrgName() {
			return orgName;
		}

		public void setOrgName(String orgName) {
			this.orgName = orgName;
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
 
}
