package com.tianyi.drs.duty.dao;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.PoliceGroupMember;
import com.tianyi.drs.duty.viewmodel.PoliceGroupMemberVM;

/**
 * 警员分组成员映射类
 * @author lq
 *
 */
@MyBatisRepository
public interface PoliceGroupMemberMapper {
	/**
	 * 根据id删除警员分组成员
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入新的警员分组成员对象
     * @param record
     * @return
     */
    int insert(PoliceGroupMember record);

    /**
     * 插入新的警员分组成员对象
     * @param record
     * @return
     */
    int insertSelective(PoliceGroupMember record);

    /**
     * 根据id获取警员分组成员对象
     * @param id
     * @return
     */
    PoliceGroupMember selectByPrimaryKey(Integer id);

    /**
     * 更新警员分组成员对象
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(PoliceGroupMember record);

    /**
     * 更新警员分组成员对象
     * @param record
     * @return
     */
    int updateByPrimaryKey(PoliceGroupMember record);
    
    /**
     * 根据警员分组id，获取成员总数
     * @param groupId
     * @return
     */
    int countMemberByGroupId(Integer groupId);
    
    /**
     * 根据警员分组id，获取成员列表
     * @param map
     * @return
     */
    List<PoliceGroupMemberVM> loadMemberByGroupId(Map<String, Object> map);
    
    /**
     * 根据成员id，判断该成员是否已经存在
     * @param map
     * @return
     */
    int existsByMemberId(Map<String, Object> map);
    
    /**
     * 根据id删除警员分组成员
     * @param groupId
     */
    void deleteByGroupId(Integer groupId);
}