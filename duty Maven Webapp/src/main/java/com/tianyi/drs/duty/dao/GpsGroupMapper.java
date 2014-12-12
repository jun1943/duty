package com.tianyi.drs.duty.dao;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.GpsGroup;
import com.tianyi.drs.duty.viewmodel.GpsGroupVM;

/**
 * 定位设备分组映射类
 * @author lq
 *
 */
@MyBatisRepository
public interface GpsGroupMapper {
	
	/**
	 * 根据id删除对象
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入新的定位设备分组对象
     * @param record
     * @return
     */
    int insert(GpsGroup record);

    /**
     * 插入新的定位设备分组对象
     * @param record
     * @return
     */
    int insertSelective(GpsGroup record);

    /**
     * 根据id获取定位设备分组对象
     * @param record
     * @return
     */
    GpsGroup selectByPrimaryKey(Integer id);

    /**
     * 更新定位设备对象
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(GpsGroup record);

    /**
     * 更新定位设备对象
     * @param record
     * @return
     */
    int updateByPrimaryKey(GpsGroup record);

    /**
     * 更新定位设备对象
     * @param map
     * @return
     */
	int countByOrgId(Map<String, Object> map);

	/**
	 * 根据组织机构id，获取定位设备分组列表
	 * @param map
	 * @return
	 */
	List<GpsGroupVM> loadVMListByOrgId(Map<String, Object> map);

	/**
	 * 根据组织机构id，获取定位设备分组列表及共享组
	 * @param map
	 * @return
	 */
	List<GpsGroupVM> loadVMListByOrgIdShared(Map<String, Object> map);
}