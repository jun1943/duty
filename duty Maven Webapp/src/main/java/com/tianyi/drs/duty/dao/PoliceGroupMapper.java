package com.tianyi.drs.duty.dao;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.PoliceGroup;
import com.tianyi.drs.duty.viewmodel.PoliceGroupVM;
@MyBatisRepository
public interface PoliceGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PoliceGroup record);

    int insertSelective(PoliceGroup record);

    PoliceGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PoliceGroup record);

    int updateByPrimaryKey(PoliceGroup record);
    
    int countByOrgId(Map<String, Object> map);
    
    List<PoliceGroupVM> loadVMListByOrgId(Map<String, Object> map);
    
    
}