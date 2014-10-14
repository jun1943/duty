package com.tianyi.drs.duty.dao;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.DutyTemplate;
@MyBatisRepository
public interface DutyTemplateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DutyTemplate record);

    int insertSelective(DutyTemplate record);

    DutyTemplate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DutyTemplate record);

    int updateByPrimaryKey(DutyTemplate record);
}