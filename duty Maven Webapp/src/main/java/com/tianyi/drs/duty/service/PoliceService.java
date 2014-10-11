package com.tianyi.drs.duty.service;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.model.Police;

public interface PoliceService {

    int deleteByPrimaryKey(Integer id);

    int insert(Police record);

    int insertSelective(Police record);

    Police selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Police record);

    int updateByPrimaryKey(Police record);

    Police findBycode(String code);
    
    Police findByname(String name);
    
     List<Police> selectAll();
    
     int updatePolice(Police policeman);
  
	 Police login(Map<String, Object> params);
}
