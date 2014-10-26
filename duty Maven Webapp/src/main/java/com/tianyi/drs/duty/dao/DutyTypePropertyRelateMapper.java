package com.tianyi.drs.duty.dao;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.DutyTypePropertyRelate;
@MyBatisRepository
public interface DutyTypePropertyRelateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DutyTypePropertyRelate record);

    int insertSelective(DutyTypePropertyRelate record);

    DutyTypePropertyRelate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DutyTypePropertyRelate record);

    int updateByPrimaryKey(DutyTypePropertyRelate record);
    
    void deleteByDutyTypeId(Integer dutyTypeId);
}