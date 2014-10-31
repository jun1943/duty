package com.tianyi.drs.duty.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianyi.drs.duty.model.DutyDesc;
import com.tianyi.drs.duty.service.DutyDescService;
import com.tianyi.drs.duty.viewmodel.DutyDescVM;
import com.tianyi.drs.duty.viewmodel.ListResult;
import com.tianyi.drs.duty.viewmodel.ObjResult;

@Scope("prototype")
@Controller
@RequestMapping("/duty")
public class DutyController {
	@Resource(name = "dutyDescService")
	protected DutyDescService dutyDescService;
	
	@RequestMapping(value = "loadDutyDesc.do")
	public @ResponseBody String loadDutyDesc(
			@RequestParam(value = "id", required = false) Integer id,
			HttpServletRequest request
			){
		
		DutyDescVM vm= dutyDescService.loadDutyDescVM(id);
		
		ObjResult<DutyDescVM> rs=new ObjResult<DutyDescVM>(true,null,id,vm);
		
		return rs.toJson();
	}

	@RequestMapping(value = "loadDutyDescByOrgIdAndYMD.do")
	public @ResponseBody String loadDutyDescByOrgIdAndYMD(
			@RequestParam(value = "orgId", required = false) Integer orgId,
			@RequestParam(value = "ymd", required = false) Integer ymd,
			HttpServletRequest request
			){
		
		DutyDescVM vm= dutyDescService.loadDutyDescVMByOrgIdAndYMD(orgId, ymd);
		
		if(vm==null){
			vm=new DutyDescVM();
			vm.setId(0);
			vm.setIsTemplate(false);
			vm.setOrgId(orgId);
			vm.setYmd(ymd);
		}
		
		ObjResult<DutyDescVM> rs=new ObjResult<DutyDescVM>(true,null,vm.getId(),vm);
		
		return rs.toJson();
	}
	
	@RequestMapping(value = "save.do")
	public @ResponseBody String save(
			@RequestParam(value = "dutyDesc", required = false) DutyDescVM ddvm,
			HttpServletRequest request
			){
		
		dutyDescService.save(ddvm);
		
		DutyDescVM ddvm2=dutyDescService.loadDutyDescVM(ddvm.getId());
		
		ObjResult<DutyDescVM> rs=new ObjResult<DutyDescVM>(true,null,ddvm2.getId(),ddvm2);
		
		return rs.toJson();
	}
	
	@RequestMapping(value = "loadTemplateByOrgId.do")
	public @ResponseBody String loadTemplateByOrgId(
			@RequestParam(value = "orgId", required = false) Integer orgId,
			HttpServletRequest request
			){
		
		List<DutyDesc> ls= dutyDescService.loadTemplateByOrgId(orgId);

		ListResult<DutyDesc> rs=new ListResult<DutyDesc>(ls.size(),ls,true);
		
		return rs.toJson();
	}
}
