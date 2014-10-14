package com.tianyi.drs.duty.dao;

import com.tianyi.drs.duty.model.DutyTemplateDesc;

public interface DutyTemplateDescMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DutyTemplateDesc record);

    int insertSelective(DutyTemplateDesc record);

    DutyTemplateDesc selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DutyTemplateDesc record);

    int updateByPrimaryKey(DutyTemplateDesc record);
}