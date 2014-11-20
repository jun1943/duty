package com.tianyi.drs.duty.dao;

import java.util.List;
import java.util.Map;

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

	void deleteByDutyId(int dutyId);

	List<DutyItem> loadlistByDutyId(Integer id);

	void deleteByDutyIdlist(Map<String, Object> map);
}