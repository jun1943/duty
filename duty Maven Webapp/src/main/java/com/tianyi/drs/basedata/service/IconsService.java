package com.tianyi.drs.basedata.service;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.basedata.model.Icons; 

public interface IconsService {

	int loadCount(Map<String, Object> map);
 
	List<Icons> loadList(Map<String, Object> map);

	int deleteByPrimaryKey(int id);

	int updateByPrimaryKey(Icons icons);

	int insert(Icons icons);

	Icons loadById(int id);

	void deleteByIds(Map<String, Object> map);

}
