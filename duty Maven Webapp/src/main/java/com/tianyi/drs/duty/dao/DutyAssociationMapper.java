package com.tianyi.drs.duty.dao;

import com.tianyi.drs.duty.model.DutyAssociation;

public interface DutyAssociationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DutyAssociation record);

    int insertSelective(DutyAssociation record);

    DutyAssociation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DutyAssociation record);

    int updateByPrimaryKey(DutyAssociation record);
}