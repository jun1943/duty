package com.tianyi.drs.duty.viewmodel;

public class OrgWithPoliceVM {

	private String  id;
	private int rid;
	private String name;
	private int dataType;
	private String code;
	
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDataType() {
		return dataType;
	}
	public void setDataType(int dataType) {
		this.dataType = dataType;
	}
	public String getText() {
		return this.name;
	}
	public void setText(String text) {
		this.name = text;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	
	public String getState(){
		if(dataType==1)
			return "closed";
		else
			return "open";
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
