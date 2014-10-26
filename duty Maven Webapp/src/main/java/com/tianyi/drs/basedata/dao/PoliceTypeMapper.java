package com.tianyi.drs.basedata.dao;

import com.tianyi.drs.basedata.model.PoliceType;
import com.tianyi.drs.duty.dao.core.MyBatisRepository;
@MyBatisRepository
public interface PoliceTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PoliceType record);

    int insertSelective(PoliceType record);

    PoliceType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PoliceType record);

    int updateByPrimaryKey(PoliceType record);
    
    
}