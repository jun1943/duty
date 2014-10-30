package com.tianyi.drs.duty.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianyi.drs.duty.dao.DutyDescMapper;
import com.tianyi.drs.duty.dao.DutyItemMapper;
import com.tianyi.drs.duty.dao.DutyMapper;
import com.tianyi.drs.duty.dao.DutyShiftMapper;
import com.tianyi.drs.duty.model.DutyDesc;
import com.tianyi.drs.duty.service.DutyDescService;
import com.tianyi.drs.duty.viewmodel.DutyDescVM;
import com.tianyi.drs.duty.viewmodel.DutyItemVM;
import com.tianyi.drs.duty.viewmodel.DutyShiftVM;
import com.tianyi.drs.duty.viewmodel.DutyVM;

@Service("dutyDescService")
public class DutyDescServiceImpl implements DutyDescService{

	@Resource(name = "dutyDescMapper")
	private DutyDescMapper dutyDescMapper;
	
	@Resource(name = "dutyMapper")
	private DutyMapper dutyMapper;
	
	@Resource(name = "dutyShiftMapper")
	private DutyShiftMapper dutyShiftMapper;
	
	@Resource(name = "dutyItemMapper")
	private DutyItemMapper dutyItemMapper;
	
	public DutyDescVM loadDutyDescVM(Integer id) {
		
		DutyDescVM ddvm=dutyDescMapper.loadDutyDescVM(id);
		
			for(DutyVM dvm:ddvm.getDuties()){
				for(DutyShiftVM dsvm:dvm.getShifts()){
					
					List<DutyItemVM> divms=dsvm.getItems();
					//List<DutyItemVM> tls=new ArrayList<DutyItemVM>();
					Map<Integer,DutyItemVM> mls=new LinkedHashMap <Integer,DutyItemVM>();

					for(DutyItemVM divm : divms){
						if(divm.getLevel()>1){
							DutyItemVM divmp =mls.get(divm.getParentId());
							if ( divmp.getChildren()==null)
								divmp.setChildren(new ArrayList<DutyItemVM>());
							divmp.getChildren().add(divm);
						}
						mls.put(divm.getId(), divm);	
					}
					
					dsvm.setItems(new ArrayList<DutyItemVM>());
					
					for(Map.Entry<Integer, DutyItemVM> entry:mls.entrySet()){
						DutyItemVM dvm2=entry.getValue();
						if(dvm2.getLevel()==1){
							dsvm.getItems().add(dvm2);
						}
					}
					
				}
			}
		
		return ddvm;
	}

	@Transactional
	public void save(DutyDescVM ddvm) {
		if(ddvm.getId()==0){
			dutyDescMapper.insert(ddvm);
		}else{
			dutyDescMapper.updateByPrimaryKey(ddvm);
		}
		
		for(DutyVM dvm : ddvm.getDuties()){
			if(dvm.getId()==0)
				dutyMapper.insert(dvm);
			else
				dutyMapper.updateByPrimaryKey(dvm);
			
			for(DutyShiftVM dsvm : dvm.getShifts()){
				if(dsvm.getId()==0)
					dutyShiftMapper.insert(dsvm);
				else
					dutyShiftMapper.updateByPrimaryKey(dsvm);
				
				dutyItemMapper.deleteByShiftId(dsvm.getId()); //构建tree依赖于id的顺序，所以必须先删除所有记录，再添重新添加，否则会混乱。
				
				for(DutyItemVM divm : dsvm.getItems()){
					divm.setId(0);//全部重置为零
					
					dutyItemMapper.insert(divm);
				}
				
			}
			
		}
		
	}

	public DutyDescVM loadDutyDescVMByOrgIdAndYMD(Integer orgId, Integer ymd) {
		Integer id=dutyDescMapper.loadIdByOrgIdAndYMD(orgId, ymd);
		
		DutyDescVM ddvm=dutyDescMapper.loadDutyDescVM(id);
		
		return ddvm;
	}

	public List<DutyDesc> loadTemplateByOrgId(Integer orgId) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("orgId",orgId);
		map.put("isTemplate", true);
		return dutyDescMapper.loadVMList(map);
	}

}
