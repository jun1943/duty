package com.tianyi.drs.duty.dao;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.DutyTask;
import com.tianyi.drs.duty.viewmodel.TaskTargetVM;

/**
 * 勤务类型关联任务映射类
 * @author lq
 *
 */
@MyBatisRepository
public interface DutyTaskMapper {
	
	/**
	 * 根据id删除对象
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入新的关联任务对象
     * @param record
     * @return
     */
    int insert(DutyTask record);

    /**
     * 插入新的关联任务对象
     * @param record
     * @return
     */
    int insertSelective(DutyTask record);

    /**
     * 根据id获取关联任务对象
     * @param id
     * @return
     */
    DutyTask selectByPrimaryKey(Integer id);

    /**
     * 更新关联任务对象
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(DutyTask record);

    /**
     * 更新关联任务对象
     * @param record
     * @return
     */
    int updateByPrimaryKey(DutyTask record);
    
    /**
     * 根据组织机构id 获取社区列表
     * @param map
     * @return
     */
    List<TaskTargetVM> loadCommunityByOrg(Map<String,Object> map);
    
    /**
     * 根据组织机构id 获取巡逻区域列表
     * @param map
     * @return
     */
    List<TaskTargetVM> loadPatrolAreaByOrg(Map<String,Object> map);
    
    /**
     * 根据组织机构id 获取卡点列表
     * @param map
     * @return
     */
    List<TaskTargetVM> loadBayonetByOrg(Map<String,Object> map);
}