package com.tianyi.drs.duty.dao;

import com.tianyi.drs.duty.model.GPSDevice;

public interface GPSDeviceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GPSDevice record);

    int insertSelective(GPSDevice record);

    GPSDevice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GPSDevice record);

    int updateByPrimaryKeyWithBLOBs(GPSDevice record);

    int updateByPrimaryKey(GPSDevice record);
}