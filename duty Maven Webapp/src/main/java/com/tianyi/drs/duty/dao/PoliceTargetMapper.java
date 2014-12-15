package com.tianyi.drs.duty.dao;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.PoliceTarget;
/**
 * 警员关联任务映射类
 * @author lq
 *
 */
@MyBatisRepository
public interface PoliceTargetMapper {
	/**
	 * 根据id删除对象
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入新的关联任务
     * @param record
     * @return
     */
    int insert(PoliceTarget record);

    /**
     * 插入新的关联任务信息
     * @param record
     * @return
     */
    int insertSelective(PoliceTarget record);

    /**
     * 根据id获取警员关联任务对象
     * @param id
     * @return
     */
    PoliceTarget selectByPrimaryKey(Integer id);

    /**
     * 更新对象
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(PoliceTarget record);

    /**
     * 更新对象
     * @param record
     * @return
     */
    int updateByPrimaryKey(PoliceTarget record);
    
    /**
     * 根据节点属性，删除关联任务，
     * @param dutyItemId
     */
    void deleteByDutyItemId(Integer dutyItemId);
    
    /**
     * 根据报备id，删除警员关联任务
     * @param dutyId
     */
    void deleteByDutyId(Integer dutyId);
}