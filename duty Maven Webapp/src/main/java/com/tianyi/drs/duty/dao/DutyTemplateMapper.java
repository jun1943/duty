package com.tianyi.drs.duty.dao;

import com.tianyi.drs.duty.model.DutyTemplate;

public interface DutyTemplateMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DutyTemplate record);

    int insertSelective(DutyTemplate record);

    DutyTemplate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DutyTemplate record);

    int updateByPrimaryKey(DutyTemplate record);
}