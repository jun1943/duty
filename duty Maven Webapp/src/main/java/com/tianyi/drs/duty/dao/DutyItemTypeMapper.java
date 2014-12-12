package com.tianyi.drs.duty.dao;

import com.tianyi.drs.duty.model.DutyItemType;
/**
 * 报备节点类型映射类
 * @author lq
 *
 */
public interface DutyItemTypeMapper {
	/**
	 * 根据id删除明细类型
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入新的明细类型数据
     * @param record
     * @return
     */
    int insert(DutyItemType record);

    /**
     * 插入新的明细类型数据
     * @param record
     * @return
     */
    int insertSelective(DutyItemType record);

    /**
     * 根据id获取报备明细类型对象
     * @param id
     * @return
     */
    DutyItemType selectByPrimaryKey(Integer id);

    /**
     * 更行对象
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(DutyItemType record);
    /**
     * 更行对象
     * @param record
     * @return
     */
    int updateByPrimaryKey(DutyItemType record);
}