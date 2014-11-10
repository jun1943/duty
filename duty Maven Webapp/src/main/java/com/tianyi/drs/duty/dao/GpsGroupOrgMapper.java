package com.tianyi.drs.duty.dao;

import java.util.List;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.GpsGroupOrg;
@MyBatisRepository
public interface GpsGroupOrgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GpsGroupOrg record);

    int insertSelective(GpsGroupOrg record);

    GpsGroupOrg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GpsGroupOrg record);

    int updateByPrimaryKey(GpsGroupOrg record);

	List<GpsGroupOrg> loadGpsGroupOrgByPGId(int pgid);

	void deleteByPGId(Integer id);
}