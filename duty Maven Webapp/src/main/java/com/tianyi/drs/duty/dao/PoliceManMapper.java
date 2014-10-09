package com.tianyi.drs.duty.dao;

import com.tianyi.drs.duty.model.PoliceMan;

public interface PoliceManMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PoliceMan record);

    int insertSelective(PoliceMan record);

    PoliceMan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PoliceMan record);

    int updateByPrimaryKey(PoliceMan record);
}