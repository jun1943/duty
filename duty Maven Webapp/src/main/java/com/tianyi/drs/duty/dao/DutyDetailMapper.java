package com.tianyi.drs.duty.dao;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.DutyDetail;
@MyBatisRepository
public interface DutyDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DutyDetail record);

    int insertSelective(DutyDetail record);

    DutyDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DutyDetail record);

    int updateByPrimaryKey(DutyDetail record);
}