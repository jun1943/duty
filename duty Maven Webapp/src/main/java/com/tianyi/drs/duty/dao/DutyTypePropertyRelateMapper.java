package com.tianyi.drs.duty.dao;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.DutyTypePropertyRelate;

/**
 * 勤务类型属性关联表映射类
 * @author lq
 *
 */
@MyBatisRepository
public interface DutyTypePropertyRelateMapper {
	
	/**
	 * 根据id删除对象
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入新的属性关系对象
     * @param record
     * @return
     */
    int insert(DutyTypePropertyRelate record);

    /**
     * 插入新的属性关系对象
     * @param record
     * @return
     */
    int insertSelective(DutyTypePropertyRelate record);

    /**
     * 根据id获取对象
     * @param id
     * @return
     */
    DutyTypePropertyRelate selectByPrimaryKey(Integer id);

    /**
     * 更新对象
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(DutyTypePropertyRelate record);

    /**
     * 更新对象
     * @param record
     * @return
     */
    int updateByPrimaryKey(DutyTypePropertyRelate record);
    
    /**
     * 根据勤务类型id删除对象
     * @param dutyTypeId
     */
    void deleteByDutyTypeId(Integer dutyTypeId);
}