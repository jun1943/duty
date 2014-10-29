package com.tianyi.drs.duty.dao;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.PoliceGroupOrg;
import com.tianyi.drs.duty.viewmodel.PoliceGroupVM;

@MyBatisRepository
public interface PoliceGroupOrgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PoliceGroupOrg record);

    int insertSelective(PoliceGroupOrg record);

    PoliceGroupOrg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PoliceGroupOrg record);

    int updateByPrimaryKey(PoliceGroupOrg record);
    
    List<PoliceGroupOrg> loadPoliceGroupOrgByPGId(int pgid);
    
    void deleteByPGId(int PGId);

	List<PoliceGroupVM> loadVMListByOrgIdShared(Map<String, Object> map);
}