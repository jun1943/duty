package com.tianyi.drs.duty.dao;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.DutyTypeTask;
@MyBatisRepository
public interface DutyTypeTaskMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DutyTypeTask record);

    int insertSelective(DutyTypeTask record);

    DutyTypeTask selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DutyTypeTask record);

    int updateByPrimaryKey(DutyTypeTask record);
}