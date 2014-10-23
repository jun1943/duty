package com.tianyi.drs.duty.dao;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.IntercomGroup;
@MyBatisRepository
public interface IntercomGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IntercomGroup record);

    int insertSelective(IntercomGroup record);

    IntercomGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IntercomGroup record);

    int updateByPrimaryKey(IntercomGroup record);
}