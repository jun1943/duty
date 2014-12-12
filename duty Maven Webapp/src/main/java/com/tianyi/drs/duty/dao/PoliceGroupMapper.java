package com.tianyi.drs.duty.dao;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.PoliceGroup;
import com.tianyi.drs.duty.viewmodel.PoliceGroupVM;

/**
 * 警员分组映射类
 * @author lq
 *
 */
@MyBatisRepository
public interface PoliceGroupMapper {
	/**
	 * 根据id删除对象
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入新的警员分组对象
     * @param record
     * @return
     */
    int insert(PoliceGroup record);

    /**
     * 插入新的警员分组对象
     * @param record
     * @return
     */
    int insertSelective(PoliceGroup record);

    /**
     * 根据id获取警员分组对象信息
     * @param id
     * @return
     */
    PoliceGroup selectByPrimaryKey(Integer id);

    /**
     * 更新警员分组对象信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(PoliceGroup record);

    /**
     * 更新警员分组对象信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(PoliceGroup record);
    
    /**
     * 根据组织机构id获取警员分组总数
     * @param map
     * @return
     */
    int countByOrgId(Map<String, Object> map);
    
    /**
     * 根据id获取警员分组列表
     * @param map
     * @return
     */
    List<PoliceGroupVM> loadVMListByOrgId(Map<String, Object> map);

    /**
     * 根据id获取警员分组列表
     * @param map
     * @return
     */
	List<PoliceGroupVM> loadVMListByOrgIdShared(Map<String, Object> map);
    
    
}