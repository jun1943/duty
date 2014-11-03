package com.tianyi.drs.duty.dao;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.DutyItem;
@MyBatisRepository
public interface DutyItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DutyItem record);

    int insertSelective(DutyItem record);

    DutyItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DutyItem record);

    int updateByPrimaryKey(DutyItem record);
    
    void deleteByDutyId(Integer dutyId);
}