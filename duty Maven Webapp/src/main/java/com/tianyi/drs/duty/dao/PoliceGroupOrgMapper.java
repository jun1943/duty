package com.tianyi.drs.duty.dao;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.duty.dao.core.MyBatisRepository;
import com.tianyi.drs.duty.model.PoliceGroupOrg;
import com.tianyi.drs.duty.viewmodel.PoliceGroupVM;

/**
 * 警员组对应组织机构映射类
 * @author lq
 *
 */
@MyBatisRepository
public interface PoliceGroupOrgMapper {
	/**
	 * 根据id删除对象
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入新的警员分组对应组织对象
     * @param record
     * @return
     */
    int insert(PoliceGroupOrg record);

    /**
     * 插入新的对象
     * @param record
     * @return
     */
    int insertSelective(PoliceGroupOrg record);

    /**
     * 查询分组对象信息
     * @param id
     * @return
     */
    PoliceGroupOrg selectByPrimaryKey(Integer id);

    /**
     * 更新对象
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(PoliceGroupOrg record);

    /**
     * 更新对象
     * @param record
     * @return
     */
    int updateByPrimaryKey(PoliceGroupOrg record);
    
    /**
     * 根据分组id，获取警组对应组织机构
     * @param pgid
     * @return
     */
    List<PoliceGroupOrg> loadPoliceGroupOrgByPGId(int pgid);
    
    /**
     * 根据id删除分组
     * @param PGId
     */
    void deleteByPGId(int PGId);

    /**
     * 获取警组对象，以及下级对象
     * @param map
     * @return
     */
	List<PoliceGroupVM> loadVMListByOrgIdShared(Map<String, Object> map);
}