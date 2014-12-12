package com.tianyi.drs.duty.dao;

import java.util.List;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.GpsGroupOrg;

/**
 * 定位设备——组织机构映射类
 * @author lq
 *
 */
@MyBatisRepository
public interface GpsGroupOrgMapper {
	
	/**
	 * 根据id删除对象
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入新的组织所属定位设备分组
     * @param record
     * @return
     */
    int insert(GpsGroupOrg record);

    /**
     * 插入新的组织所属定位设备分组
     * @param record
     * @return
     */
    int insertSelective(GpsGroupOrg record);

    /**
     * 根据id获取组织所属分组对象
     * @param id
     * @return
     */
    GpsGroupOrg selectByPrimaryKey(Integer id);

    /**
     * 更新对象
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(GpsGroupOrg record);

    /**
     * 更新对象
     * @param record
     * @return
     */
    int updateByPrimaryKey(GpsGroupOrg record);

    /**
     * 根据分组id获取所属组织分组列表
     * @param pgid
     * @return
     */
	List<GpsGroupOrg> loadGpsGroupOrgByPGId(int pgid);

	/**
	 * 根据id删除分组对象
	 * @param id
	 */
	void deleteByPGId(Integer id);
}