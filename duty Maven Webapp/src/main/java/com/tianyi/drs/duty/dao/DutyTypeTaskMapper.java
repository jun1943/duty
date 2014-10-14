package com.tianyi.drs.duty.dao;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.DutyTypeTask;
@MyBatisRepository
public interface DutyTypeTaskMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DutyTypeTask record);

    int insertSelective(DutyTypeTask record);

    DutyTypeTask selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DutyTypeTask record);

    int updateByPrimaryKey(DutyTypeTask record);
}