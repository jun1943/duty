package com.tianyi.drs.duty.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tianyi.drs.duty.model.Vehicle;
import com.tianyi.drs.duty.service.VehicleService;
import com.tianyi.util.PaginationData;


@Scope("prototype")
@Controller
@RequestMapping("/vehicle")
public class VehicleController {
	@Resource(name="vehicleService")
	protected VehicleService vehicleService;
	
	
	@RequestMapping(value="list.do")
	public ModelAndView list(
			@RequestParam(value = "orgId", required = false) String orgId,
			@RequestParam(value = "number", required = false) String number,
			@RequestParam(value = "pageCount", required = false) Integer pageCount,
			@RequestParam(value = "pageNumber", required = false) Integer pageNo,
			@RequestParam(value = "totalCount", required = false) Integer totalCount,
			HttpServletRequest request){
	
		
		ModelAndView mv = new ModelAndView("/basedata/vehicle");
		
		Vehicle query =new Vehicle();
		
		if(number != null && number.length() > 0){
			query.setNumber(number);
		}
		
		PaginationData page = new PaginationData();
		
		page.setTotal(vehicleService.findCount(query));
		page.setPageNo(pageNo==null?1:pageNo.intValue());
		
		page.setRows(vehicleService.findPageList(query, page));
		mv.addObject("page", page);

		return mv;
	}

}
