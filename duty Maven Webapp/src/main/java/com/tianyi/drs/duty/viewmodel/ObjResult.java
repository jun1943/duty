package com.tianyi.drs.duty.viewmodel;

import net.sf.json.JSONObject;

public class ObjResult<T> {

	private boolean isSuccess;
	private String msg;
	private T obj;
	
	public ObjResult(){
		
	}
	
	public ObjResult(boolean isSuccess,String msg,T obj){
		this.isSuccess=isSuccess;
		this.msg=msg;
		this.obj=obj;
	}
	
	public String toJson(){
		JSONObject rs=JSONObject.fromObject(this);
		
		return rs.toString();
	}
	
	public boolean isSuccess() {
		return isSuccess;
	}


	public void setSuccess(boolean isSuccess) {
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
	
	
}
