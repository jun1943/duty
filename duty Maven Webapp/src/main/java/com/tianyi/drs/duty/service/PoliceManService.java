package com.tianyi.drs.duty.service;
  
import com.tianyi.drs.duty.model.PoliceMan;

public interface PoliceManService {
	
	  	int deleteByPrimaryKey(Integer id);

	    int insert(PoliceMan record);

	    int insertSelective(PoliceMan record);

	    PoliceMan selectByPrimaryKey(Integer id);

	    int updateByPrimaryKeySelective(PoliceMan record);

	    int updateByPrimaryKey(PoliceMan record);
}