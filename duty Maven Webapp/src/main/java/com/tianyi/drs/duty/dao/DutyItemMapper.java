package com.tianyi.drs.duty.dao;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.DutyItem;

/**
 * 报备明细映射文件类
 * @author lq
 *
 */
@MyBatisRepository
public interface DutyItemMapper {
	/**
	 * 根据id删除对象
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入新的报备明细数据
     * @param record
     * @return
     */
    int insert(DutyItem record);

    /**
     * 插入新的报备明细数据
     * @param record
     * @return
     */
    int insertSelective(DutyItem record);

    /**
     * 根据id获取报备明细对象
     * @param id
     * @return
     */
    DutyItem selectByPrimaryKey(Integer id);

    /**
     * 更新报备明细对象
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(DutyItem record);

    /**
     * 更新报备明细对象
     * @param record
     * @return
     */
    int updateByPrimaryKey(DutyItem record);
   
    /**
     * 根据id删除报备
     * @param dutyId
     */
    void deleteByDutyId(Integer dutyId);
    /**
     * 根据id删除报备
     * @param dutyId
     */
	void deleteByDutyId(int dutyId);

	/**
	 * 根据id获取报备明细列表
	 * @param id
	 * @return
	 */
	List<DutyItem> loadlistByDutyId(Integer id);

	/**
	 * 根据id数据集，批量删除报备
	 * @param map
	 */
	void deleteByDutyIdlist(Map<String, Object> map);
}