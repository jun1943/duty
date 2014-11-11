package com.tianyi.drs.basedata.dao;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.basedata.model.Icons;
import com.tianyi.drs.duty.dao.core.MyBatisRepository;
@MyBatisRepository
public interface IconsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Icons record);

    int insertSelective(Icons record);

    Icons selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Icons record);

    int updateByPrimaryKeyWithBLOBs(Icons record);

    int updateByPrimaryKey(Icons record);

	List<Icons> loadList(Map<String, Object> map);

	int loadCount(Map<String, Object> map);

	Icons loadById(int id);
}