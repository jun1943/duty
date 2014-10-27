package com.tianyi.drs.duty.viewmodel;

import java.util.List;

import net.sf.json.JSONObject;

/**
 * 分页结果集
 * @author Owen
 *
 * @param <T>
 */
public class ListResult<T> {
	
	private boolean isSuccess=true;
	private String msg;
	private int total;
	private List<T> rows;
	
	public ListResult()
	{
		
	}
	
	
	
	public ListResult(int total,List<T> rows){
		this.total=total;
		this.rows=rows;
	}
	
	public String toJson(){
		JSONObject rs=JSONObject.fromObject(this);
		return rs.toString();
	}
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
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
}
