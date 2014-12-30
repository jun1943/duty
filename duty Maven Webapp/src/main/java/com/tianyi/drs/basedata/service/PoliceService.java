package com.tianyi.drs.basedata.service;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.basedata.model.IntercomGroup;
import com.tianyi.drs.basedata.model.Police;
import com.tianyi.drs.basedata.model.PoliceType;
import com.tianyi.drs.basedata.viewmodel.GpsBaseVM;
import com.tianyi.drs.basedata.viewmodel.PoliceVM;
import com.tianyi.drs.duty.viewmodel.UserObjectVM;
import com.tianyi.util.PaginationData;
/**
 * 警员后台逻辑服务层
 * @author lq
 *
 */
public interface PoliceService {
	/**
	 * 删除对象
	 * @param id
	 * @return
	 */
    int deleteByPrimaryKey(Integer id);
    /**
     * 插入新的 警员对象
     * @param record
     * @return
     */
    int insert(Police record);
    /**
     * 插入查询对象集
     * @param record
     * @return
     */
    int insertSelective(Police record);
    /**
     * 根据id查询对象
     * @param id
     * @return
     */
    Police selectByPrimaryKey(Integer id);
    /**
     * 更新对象
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Police record);
    /**
     * 跟新对象
     * @param record
     * @return
     */
    int updateByPrimaryKey(Police record);
    /**
     * 根据编码获取警员对象
     * @param code
     * @return
     */
    List<Police> findBycode(String code);
    /**
     * 根据姓名查询警员对象
     * @param name
     * @return
     */
    Police findByname(String name);
    /**
     * 查询所有对象列表
     * @return
     */
     List<Police> selectAll();
    /**
     * 更新对象
     * @param policeman
     * @return
     */
     int updatePolice(Police policeman);
     /**
      * 传入参数，验证登陆
      * @param params
      * @return
      */
	 Police login(Map<String, Object> params);
	 /**
	  * 查询记录总数
	  * @param query
	  * @return
	  */
	 int findCount(PoliceVM query);
	 /**
	  * 查询分页记录
	  * @param query
	  * @param page
	  * @return
	  */
	 List<PoliceVM> findPageList(PoliceVM query, PaginationData page);
	 /**
	  * 查询分页记录总数
	  * @param map
	  * @return
	  */
	 int loadVMCount(Map<String, Object> map);
	 /**
	  * 查询列表记录，分页
	  * @param map
	  * @return
	  */
	 List<PoliceVM> loadVMList(Map<String, Object> map);
	 /**
	  * 查询警员类型列表
	  * @return
	  */
	 List<PoliceType> selectPoliceType();
	 /**
	  * 查询对讲机组呼号列表
	  * @return
	  */
	 List<IntercomGroup> selectIntercomGroup();
	 /**
	  * 查询gpsId对象列表
	  * @param orgId
	  * @return
	  */
	 List<GpsBaseVM> selectGpsId(int orgId);
	 /**
	  * 查询记录分页显示列表
	  * @param map
	  * @return
	  */
	 List<PoliceVM> loadVMListWithGroup(Map<String, Object> map);
	 /**
	  * 根据身份证号码查询警员对象
	  * @param param
	  * @return
	  */
	 List<Police> findByidCard(String param);
	 /**
	  * 查询分组警员对象列表
	  * @param map
	  * @return
	  */
	 List<PoliceVM> loadVMListWithGroupList(Map<String, Object> map);
	 /**
	  * 批量删除警员对象
	  * @param map
	  */
	 void deleteByIds(Map<String, Object> map);
	 /**
	  * 验证用户的有效性
	  * @param map
	  * @return
	  */
	 UserObjectVM getUserAuthorization(Map<String, Object> map);
	 /**
	  * 批量更改警员的状态
	  * 启用或者锁定
	  * @param map
	  */
	void changePoliceStateByIds(Map<String, Object> map);
	/**
	 * 根据个呼号，查询是否已存在警员
	 * @param param
	 * @return
	 */
	List<Police> findByintercomPerson(String param);
	/**
	 * 根据组织机构，查询所有数据
	 * @param orgId
	 * @return
	 */
	List<PoliceVM> loadVMListWithOrgId(Integer orgId);
}
