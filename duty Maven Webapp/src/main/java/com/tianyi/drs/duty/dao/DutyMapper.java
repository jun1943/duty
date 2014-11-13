package com.tianyi.drs.duty.dao;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.Duty;
import com.tianyi.drs.duty.model.DutyProperty;
import com.tianyi.drs.duty.viewmodel.DutyItemCountVM;
import com.tianyi.drs.duty.viewmodel.DutyVM;
@MyBatisRepository
public interface DutyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Duty record);

    int insertSelective(Duty record);

    Duty selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Duty record);

    int updateByPrimaryKey(Duty record);
    
    List<DutyVM> loadDutyVMList(Map<String,Object> map);
    
    List<Duty> loadTemplatesWithOutItem(Integer orgId);

	List<DutyItemCountVM> loadTotalPolice(Map<String, Object> map);

	List<DutyItemCountVM> loadTotalPolicedetail(Map<String, Object> map);

	List<DutyProperty> selectdutyProperty();
}