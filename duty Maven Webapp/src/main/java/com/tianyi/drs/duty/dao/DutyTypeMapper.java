package com.tianyi.drs.duty.dao;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.DutyType;
import com.tianyi.drs.duty.viewmodel.DutyItemCountVM;
import com.tianyi.drs.duty.viewmodel.DutyTypePropertyVM;
import com.tianyi.drs.duty.viewmodel.DutyTypeVM;

@MyBatisRepository
public interface DutyTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DutyType record);

    int insertSelective(DutyType record);

    DutyType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DutyType record);

    int updateByPrimaryKey(DutyType record);
    
    //自定义
    List<DutyTypePropertyVM> loadProperties();
    
    List<DutyTypeVM> loadDutyTypeVM(Map<String,Object> map);

    void updateUseStateByFullPath(Map<String,Object> map);
    
    int checkUsed(String fullPath);
 
	List<DutyType> loadDutyType(Map<String, Object> map);

	List<DutyItemCountVM> loadDutyItemCount(Map<String, Object> map);
 
}