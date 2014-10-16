package com.tianyi.drs.duty.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.pool.DruidDataSource;
import com.tianyi.drs.duty.model.Vehicle;
import com.tianyi.drs.duty.service.VehicleService;
import com.tianyi.drs.duty.util.SqlHelper;
import com.tianyi.util.PaginationData;

@Scope("prototype")
@Controller
@RequestMapping("/vehicle")
public class VehicleController {
	@Resource(name = "vehicleService")
	protected VehicleService vehicleService;

	@Resource(name = "dataSource")
	private DruidDataSource ds;

	@RequestMapping(value = "list.do")
	public ModelAndView list(
			@RequestParam(value = "orgId", required = false) String orgId,
			@RequestParam(value = "orgCode", required = false) String orgCode,
			@RequestParam(value = "orgPath", required = false) String orgPath,
			@RequestParam(value = "inSubOrg", required = false) Integer inSubOrg,
			@RequestParam(value = "number", required = false) String number,
			@RequestParam(value = "pageCount", required = false) Integer pageCount,
			@RequestParam(value = "pageNumber", required = false) Integer pageNo,
			@RequestParam(value = "totalCount", required = false) Integer totalCount,
			HttpServletRequest request) {

		ModelAndView mv = new ModelAndView("/basedata/vehicle");

		Map<String, Object> map = new HashMap<String, Object>();

		PaginationData page = new PaginationData();
		
		
		
		map.put("orgCode", orgCode);
		map.put("orgPath", orgPath);
		map.put("inSubOrg", inSubOrg);

		map.put("number", number);

		page.setTotal(vehicleService.loadVMCount(map));
		page.setPageNo(pageNo == null ? 1 : pageNo.intValue());

		map.put("pageStart", page.getStartIndex());
		map.put("pageSize", page.getPageSize());
		
		page.setRows(vehicleService.loadVMList(map));
		mv.addObject("page", page);
		mv.addObject("orgId", orgId);
		mv.addObject("orgCode", orgCode);
		mv.addObject("orgPath", orgPath);
		mv.addObject("inSubOrg", inSubOrg);
		mv.addObject("number", number);
		return mv;
	}

}
