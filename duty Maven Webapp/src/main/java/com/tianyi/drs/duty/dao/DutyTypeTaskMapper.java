package com.tianyi.drs.duty.dao;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.DutyTypeTask;


/**
 * 勤务类型任务映射类
 * @author lq
 *
 */
@MyBatisRepository
public interface DutyTypeTaskMapper {
	
	/**
	 * 根据id删除对象
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入新的勤务类型任务
     * @param record
     * @return
     */
    int insert(DutyTypeTask record);

    /**
     * 插入新的 勤务类型任务
     * @param record
     * @return
     */
    int insertSelective(DutyTypeTask record);

    /**
     * 根据id查询勤务类型任务对象
     * @param id
     * @return
     */
    DutyTypeTask selectByPrimaryKey(Integer id);

    /**
     * 更新对象
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(DutyTypeTask record);

    /**
     * 更新对象
     * @param record
     * @return
     */
    int updateByPrimaryKey(DutyTypeTask record);
}