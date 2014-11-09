package com.tianyi.drs.duty.util;

public class ResultMsg {

	private boolean isSuccess;
	private String message;
	
	public ResultMsg(){
		
	}
	
	public ResultMsg(boolean isSuccess,String message){
		this.isSuccess=isSuccess;
		this.message=message;
	}
	
	public boolean getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
