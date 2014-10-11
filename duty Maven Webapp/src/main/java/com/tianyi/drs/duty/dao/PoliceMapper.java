package com.tianyi.drs.duty.dao;

import java.util.List;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.Police;
import java.util.Map;

@MyBatisRepository
public interface PoliceMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Police record);

    int insertSelective(Police record);

    Police selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Police record);

    int updateByPrimaryKey(Police record);
 
	Police findBycode(String code); 
	
	Police findByname(String name); 
	
	List<Police> selectAll();
	 
	int updatePolice(Police police);
	  
	Police login(Map<String, Object> params);
}