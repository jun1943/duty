package com.tianyi.drs.duty.dao;

import com.tianyi.drs.duty.model.Police;

public interface PoliceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Police record);

    int insertSelective(Police record);

    Police selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Police record);

    int updateByPrimaryKey(Police record);
}