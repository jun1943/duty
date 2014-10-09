package com.tianyi.drs.duty.dao;

import com.tianyi.drs.duty.model.PoliceCar;

public interface PoliceCarMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PoliceCar record);

    int insertSelective(PoliceCar record);

    PoliceCar selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PoliceCar record);

    int updateByPrimaryKey(PoliceCar record);
}