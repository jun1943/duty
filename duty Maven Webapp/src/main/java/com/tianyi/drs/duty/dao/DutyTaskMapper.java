package com.tianyi.drs.duty.dao;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.DutyTask;
import com.tianyi.drs.duty.viewmodel.TaskTargetVM;
@MyBatisRepository
public interface DutyTaskMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DutyTask record);

    int insertSelective(DutyTask record);

    DutyTask selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DutyTask record);

    int updateByPrimaryKey(DutyTask record);
    
    List<TaskTargetVM> loadCommunityByOrg(Map<String,Object> map);
    
    List<TaskTargetVM> loadPatrolAreaByOrg(Map<String,Object> map);
    
    List<TaskTargetVM> loadBayonetByOrg(Map<String,Object> map);
}