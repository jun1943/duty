package com.tianyi.drs.duty.dao;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.Gps;
import com.tianyi.drs.duty.viewmodel.GpsVM;
import com.tianyi.util.PaginationData;
@MyBatisRepository
public interface GpsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Gps record);

    int insertSelective(Gps record);

    Gps selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Gps record);

    int updateByPrimaryKey(Gps record);

	int findCount(Gps gps);

	List<Gps> findPageList(Gps query, PaginationData page);

	int loadVMCount(Map<String, Object> map);

	List<GpsVM> loadVMList(Map<String, Object> map);
}