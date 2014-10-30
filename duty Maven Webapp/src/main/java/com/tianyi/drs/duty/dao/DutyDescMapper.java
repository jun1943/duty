package com.tianyi.drs.duty.dao;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.DutyDesc;
import com.tianyi.drs.duty.viewmodel.DutyDescVM;
@MyBatisRepository
public interface DutyDescMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DutyDesc record);

    int insertSelective(DutyDesc record);

    DutyDesc selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DutyDesc record);

    int updateByPrimaryKey(DutyDesc record);
    
    DutyDescVM loadDutyDescVM(Integer id) ;
}