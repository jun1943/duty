package com.tianyi.drs.basedata.dao;

import com.tianyi.drs.basedata.model.GpsType;
import com.tianyi.drs.duty.dao.core.MyBatisRepository;
@MyBatisRepository
public interface GpsTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GpsType record);

    int insertSelective(GpsType record);

    GpsType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GpsType record);

    int updateByPrimaryKey(GpsType record);
}