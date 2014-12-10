package com.tianyi.drs.basedata.dao;

import com.tianyi.drs.basedata.model.IntercomGroup;
import com.tianyi.drs.duty.dao.core.MyBatisRepository;
/*
 * 组呼号映射文件类
 */
@MyBatisRepository
public interface IntercomGroupMapper {
	/*
	 * 根据主键值删除组呼号对象
	 */
    int deleteByPrimaryKey(Integer id);
    /*
	 * 插入新的组呼号对象
	 */
    int insert(IntercomGroup record);
    /*
	 * 插入查询组呼号对象
	 */
    int insertSelective(IntercomGroup record);
    /*
	 * 根据主键值查询组呼号对象
	 */
    IntercomGroup selectByPrimaryKey(Integer id);
    /*
   	 * 根据主键值更新组呼号对象
   	 */
    int updateByPrimaryKeySelective(IntercomGroup record);
    /*
   	 * 根据主键值更新组呼号对象
   	 */
    int updateByPrimaryKey(IntercomGroup record);
}