package com.tianyi.drs.basedata.dao;

import java.util.List;
import java.util.Map;

import com.tianyi.drs.basedata.model.Weapon;
import com.tianyi.drs.basedata.model.WeaponType;
import com.tianyi.drs.basedata.viewmodel.WeaponVM;
import com.tianyi.drs.duty.dao.core.MyBatisRepository;
@MyBatisRepository
public interface WeaponMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Weapon record);

    int insertSelective(Weapon record);

    Weapon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Weapon record);

    int updateByPrimaryKey(Weapon record);

	List<Weapon> selectAll();

	int countByExample(Weapon weapon);

	List<Weapon> selectByExample(Map<String, Object> map);

	List<WeaponVM> loadVMList(Map<String, Object> map);

	int countByVM(Map<String, Object> map);

	int loadVMCount(Map<String, Object> map);

	List<WeaponType> selectWeaponType();

	List<WeaponVM> loadVMListWithGroup(Map<String, Object> map);
}