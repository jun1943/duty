package com.tianyi.drs.basedata.service;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.basedata.model.Icons; 

public interface IconsService {

	int loadVMCount(Map<String, Object> map);
 
	List<Icons> loadVMList(Map<String, Object> map);

	int deleteByPrimaryKey(int id);

	int updateByPrimaryKey(Icons icons);

	int insert(Icons icons);

}
