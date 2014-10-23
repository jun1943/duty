package com.tianyi.drs.basedata.dao;

import com.tianyi.drs.basedata.model.IntercomGroup;
import com.tianyi.drs.duty.dao.core.MyBatisRepository;
@MyBatisRepository
public interface IntercomGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IntercomGroup record);

    int insertSelective(IntercomGroup record);

    IntercomGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IntercomGroup record);

    int updateByPrimaryKey(IntercomGroup record);
}