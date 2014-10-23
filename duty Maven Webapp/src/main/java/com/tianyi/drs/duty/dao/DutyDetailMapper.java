package com.tianyi.drs.duty.dao;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.DutyDetail;
@MyBatisRepository
public interface DutyDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DutyDetail record);

    int insertSelective(DutyDetail record);

    DutyDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DutyDetail record);

    int updateByPrimaryKey(DutyDetail record);
}