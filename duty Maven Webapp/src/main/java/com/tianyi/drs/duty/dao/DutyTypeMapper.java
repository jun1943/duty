package com.tianyi.drs.duty.dao;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.DutyType;

@MyBatisRepository
public interface DutyTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DutyType record);

    int insertSelective(DutyType record);

    DutyType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DutyType record);

    int updateByPrimaryKey(DutyType record);
}