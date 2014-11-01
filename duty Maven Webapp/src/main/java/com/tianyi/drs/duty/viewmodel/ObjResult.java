package com.tianyi.drs.duty.viewmodel;

import com.tianyi.drs.duty.util.DateJsonValueProcessor;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class ObjResult<T> {

	private  JsonConfig jsonConfig;
	
	private boolean isSuccess;
	private String msg;
	private T obj;
	private int id;
	
	public ObjResult(){
		jsonConfig=new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor());
	}
	
	public ObjResult(boolean isSuccess,String msg,int id,T obj){
		this();
		this.isSuccess=isSuccess;
		this.msg=msg;
		this.id=id;
		this.obj=obj;
	}
	
	public String toJson(){
		JSONObject rs=JSONObject.fromObject(this,jsonConfig);
		
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
