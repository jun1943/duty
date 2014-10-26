package com.tianyi.drs.basedata.dao;

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
}