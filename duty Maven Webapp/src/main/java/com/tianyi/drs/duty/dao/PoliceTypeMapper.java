package com.tianyi.drs.duty.dao;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.PoliceType;
@MyBatisRepository
public interface PoliceTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PoliceType record);

    int insertSelective(PoliceType record);

    PoliceType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PoliceType record);

    int updateByPrimaryKey(PoliceType record);
}