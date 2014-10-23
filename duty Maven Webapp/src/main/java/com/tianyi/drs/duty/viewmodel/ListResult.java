package com.tianyi.drs.duty.viewmodel;

import java.util.List;

/**
 * 分页结果集
 * @author Owen
 *
 * @param <T>
 */
public class ListResult<T> {
	
	public int total;
	public List<T> rows;
	
	public ListResult()
	{
		
	}
	
	public ListResult(int total,List<T> rows){
		this.total=total;
		this.rows=rows;
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
}
