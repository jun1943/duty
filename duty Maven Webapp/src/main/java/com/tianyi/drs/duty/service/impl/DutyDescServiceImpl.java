package com.tianyi.drs.duty.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianyi.drs.duty.dao.DutyDescMapper;
import com.tianyi.drs.duty.service.DutyDescService;
import com.tianyi.drs.duty.viewmodel.DutyDescVM;
import com.tianyi.drs.duty.viewmodel.DutyItemVM;
import com.tianyi.drs.duty.viewmodel.DutyShiftVM;
import com.tianyi.drs.duty.viewmodel.DutyVM;

@Service("dutyDescService")
public class DutyDescServiceImpl implements DutyDescService{

	@Resource(name = "dutyDescMapper")
	private DutyDescMapper dutyDescMapper;
	
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
		
		
	}

}
