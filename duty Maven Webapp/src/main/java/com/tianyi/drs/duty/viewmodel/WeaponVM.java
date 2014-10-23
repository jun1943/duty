package com.tianyi.drs.duty.viewmodel;

import com.tianyi.drs.duty.model.Weapon;

public class WeaponVM extends Weapon {
	
	 private String typeName;
	  
	    private String orgName;
	    
		 private String orgCode;
		  
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
