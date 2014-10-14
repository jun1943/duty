package com.tianyi.drs.duty.dao;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.PoliceGroup;
@MyBatisRepository
public interface PoliceGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PoliceGroup record);

    int insertSelective(PoliceGroup record);

    PoliceGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PoliceGroup record);

    int updateByPrimaryKey(PoliceGroup record);
}