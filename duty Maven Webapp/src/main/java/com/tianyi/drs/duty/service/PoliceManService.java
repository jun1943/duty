package com.tianyi.drs.duty.service;
  
import com.tianyi.drs.duty.model.PoliceMan;

public interface PoliceManService {
	
	public int deleteByPrimaryKey(Integer id);

	public int insert(PoliceMan record);

	public int insertSelective(PoliceMan record);

	public PoliceMan selectByPrimaryKey(Integer id);

	public int updateByPrimaryKeySelective(PoliceMan record);

	public int updateByPrimaryKey(PoliceMan record);
}