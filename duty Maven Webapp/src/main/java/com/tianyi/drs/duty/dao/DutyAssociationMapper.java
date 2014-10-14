package com.tianyi.drs.duty.dao;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.DutyAssociation;
@MyBatisRepository
public interface DutyAssociationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DutyAssociation record);

    int insertSelective(DutyAssociation record);

    DutyAssociation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DutyAssociation record);

    int updateByPrimaryKey(DutyAssociation record);
}