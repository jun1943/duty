package com.tianyi.drs.basedata.service;

import java.util.List; 
import java.util.Map;

import com.tianyi.drs.basedata.model.Weapon;
import com.tianyi.drs.basedata.model.WeaponType;
import com.tianyi.drs.basedata.viewmodel.WeaponVM;
import com.tianyi.util.PaginationData;
 
/**
 * 武器后台逻辑服务层
 * @author lq
 *
 */
public interface WeaponService {
	/**
	 * 删除武器对象
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(Integer id);
	/**
	 * 
	 * @param record
	 * @return
	 */
    int insert(Weapon record);
    /**
     * 插入查询记录集
     * @param record
     * @return
     */
    int insertSelective(Weapon record);
    /**
     * 根据id查询武器对象
     * @param id
     * @return
     */
    Weapon selectByPrimaryKey(Integer id);
    /**
     * 更新对象
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Weapon record);
    /**
     * 更新对象
     * @param record
     * @return
     */
    int updateByPrimaryKey(Weapon record);
    /**
     * 查询记录列表
     * @return
     */
    List<Weapon> selectAll();
    /**
     * 查询记录总数
     * @param query
     * @return
     */
	int findCount(Weapon query);
	/**
	 * 查询记录分页对象列表
	 * @param query
	 * @param page
	 * @return
	 */
	List<Weapon> findPageList(Weapon query, PaginationData page);
	/**
	 * 查询分页记录总数
	 * @param map
	 * @return
	 */
	int loadVMCount(Map<String, Object> map);
	/**
	 * 查询分页记录列表
	 * @param map
	 * @return
	 */
	List<WeaponVM> loadVMList(Map<String, Object> map);
	/**
	 * 查询武器类型列表
	 * @return
	 */
	List<WeaponType> selectWeaponType();
	/**
	 * 查询武器分组对象列表
	 * @param map
	 * @return
	 */
	List<WeaponVM> loadVMListWithGroup(Map<String, Object> map);
	/**
	 * 批量删除武器对象
	 * @param map
	 */
	void deleteByIds(Map<String, Object> map);
	
	/**
	 * 根据武器编号，查询武器是否存在
	 * @param param
	 * @return
	 */
	List<Weapon> findByNumber(String param);
	/**
	 * 判断是否有武器关联数据
	 * @param string
	 * @return
	 */
	List<Weapon> findByIdAndDtyId(String param); 
}
