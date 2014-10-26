package com.tianyi.drs.duty.viewmodel;

import net.sf.json.JSONObject;

public class ObjResult<T> {

	private boolean isSuccess;
	private String msg;
	private T obj;
	private int id;
	
	public ObjResult(){
		
	}
	
	public ObjResult(boolean isSuccess,String msg,int id,T obj){
		this.isSuccess=isSuccess;
		this.msg=msg;
		this.id=id;
		this.obj=obj;
	}
	
	public String toJson(){
		JSONObject rs=JSONObject.fromObject(this);
		
		return rs.toString();
	}
	
	public boolean getIsSuccess() {
		return isSuccess;
	}


	public void setIsSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getObj() {
		return obj;
	}


	public void setObj(T obj) {
		this.obj = obj;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
