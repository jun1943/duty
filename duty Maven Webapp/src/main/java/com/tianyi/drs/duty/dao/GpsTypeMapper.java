package com.tianyi.drs.duty.dao;

import com.tianyi.drs.duty.model.GpsType;

public interface GpsTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GpsType record);

    int insertSelective(GpsType record);

    GpsType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GpsType record);

    int updateByPrimaryKey(GpsType record);
}