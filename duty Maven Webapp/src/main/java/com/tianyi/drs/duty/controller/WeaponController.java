package com.tianyi.drs.duty.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
   
import com.tianyi.drs.duty.service.WeaponService;
import com.tianyi.util.PaginationData;

@Scope("prototype")
@Controller
@RequestMapping("/weapon")
public class WeaponController {
		
	@Resource(name="weaponService")
	protected WeaponService weaponService;
	

	@RequestMapping(value="list.do")
	public ModelAndView list(
			@RequestParam(value = "orgId", required = false) String orgId,
			@RequestParam(value = "number", required = false) String number,
			@RequestParam(value = "inSubOrg", required = false) Integer inSubOrg,
			@RequestParam(value = "pageCount", required = false) Integer pageCount,
			@RequestParam(value = "pageNumber", required = false) Integer pageNo,
			@RequestParam(value = "totalCount", required = false) Integer totalCount,
			HttpServletRequest request){
	
		
		ModelAndView mv = new ModelAndView("/basedata/weapon"); 
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("orgId", orgId);
		map.put("number", number);
		map.put("inSubOrg", inSubOrg);
		PaginationData page = new PaginationData();
		
		page.setTotal(weaponService.loadVMCount(map));
		page.setPageNo(pageNo == null ? 1 : pageNo.intValue());
		
		map.put("pageStart", page.getStartIndex());
		map.put("pageSize", page.getPageSize());
		page.setRows(weaponService.loadVMList(map));
		mv.addObject("page", page);
		mv.addObject("orgId", orgId); 
		mv.addObject("inSubOrg", inSubOrg);
		mv.addObject("number", number); 
		return mv;
	}
}
