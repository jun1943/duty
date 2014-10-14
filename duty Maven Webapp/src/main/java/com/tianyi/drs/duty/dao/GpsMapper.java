package com.tianyi.drs.duty.dao;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.Gps;
@MyBatisRepository
public interface GpsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Gps record);

    int insertSelective(Gps record);

    Gps selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Gps record);

    int updateByPrimaryKey(Gps record);
}