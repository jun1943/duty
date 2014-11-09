package com.tianyi.drs.duty.dao;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.GpsGroup;
import com.tianyi.drs.duty.viewmodel.GpsGroupVM;
@MyBatisRepository
public interface GpsGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GpsGroup record);

    int insertSelective(GpsGroup record);

    GpsGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GpsGroup record);

    int updateByPrimaryKey(GpsGroup record);

	int countByOrgId(Map<String, Object> map);

	List<GpsGroupVM> loadVMListByOrgId(Map<String, Object> map);

	List<GpsGroupVM> loadVMListByOrgIdShared(Map<String, Object> map);
}