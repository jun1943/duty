package com.tianyi.drs.duty.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianyi.drs.duty.model.DutyType; 
import com.tianyi.drs.duty.service.DutyService;
import com.tianyi.drs.duty.service.DutyTypeService;
import com.tianyi.drs.duty.service.OrgService;
import com.tianyi.drs.duty.viewmodel.DutyItemCountVM; 
import com.tianyi.drs.duty.viewmodel.ListResult;

@Scope("prototype")
@Controller
@RequestMapping("/dutyCalendar")
public class DutyCalendarController {

	@Resource(name = "dutyService")
	protected DutyService dutyService;

	@Resource(name = "dutyTypeService")
	protected DutyTypeService dutyTypeService;

	@Resource(name = "orgService")
	protected OrgService orgService;
	  
	@RequestMapping(value = "getCalender.do")
	public @ResponseBody
	String getCalender(
			@RequestParam(value = "date", required = false) String date,
			@RequestParam(value = "orgId", required = false) Integer orgId,
			HttpServletRequest request) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse(date);
		Calendar c = Calendar.getInstance();

		c.setTime(d);
		int totalDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		String result = "[";
		for (int i = 1; i <= totalDays; i++) {
			c.set(Calendar.DAY_OF_MONTH, i);
			Date dates = c.getTime();
			Calendar cld = Calendar.getInstance();
			cld.setTime(dates);
			int year = cld.get(Calendar.YEAR);
			int month = cld.get(Calendar.MONTH) + 1;
			String week = getWeekOfDate(dates);
			String dt = "";
			if (i < 10) {
				dt = year + "-" + month + "-0" + i;
			} else {
				dt = year + "-" + month + "-" + i;
			}
			result += "{\"y\":\"" + year + "\",\"m\":\"" + month
					+ "\",\"d\":\"" + i + "\",\"week\":\"" + week
					+ "\",\"totalpolice\":\"" + getTotalPolice(dt, orgId)
					+ "\",\"dutyList\":\"" + getDutyList(dt, orgId) + "\"},";
		}
		if (result.endsWith(",")) {
			result = result.substring(0, result.length() - 1) + "]";
		}
		return result;
	}

	/*
	 * 根据日期，组织，获取报备总警力
	 */
	private String getDutyList(String date, Integer orgId) {
		try {
			List<DutyType> list = new ArrayList<DutyType>();
			int dt = 0;
			String dates = date.replace("-", "");
			dt = Integer.parseInt(dates);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ymd", dt);
			map.put("orgId", orgId);
			list = dutyTypeService.loadDutyType(map);
			String result = "";
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					result += "<li>" + list.get(i).getName() + "报备</li>";
				}
			} else {
				result = "<li>无报备</li>";
			}
			return result;
		} catch (Exception ex) {
			return "<li>获取报备类型发生错误</li>";
		}
	}

	/*
	 * 根据日期，组织，获取报备类型
	 */
	private String getTotalPolice(String date, Integer orgId) {
		try {
			int dt = 0;
			String dates = date.replace("-", "");
			dt = Integer.parseInt(dates);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ymd", dt);
			map.put("orgId", orgId);
			List<DutyItemCountVM> list = new ArrayList<DutyItemCountVM>();
			list = dutyTypeService.loadDutyItemCount(map);
			String result = "";
			if (list.size() > 0) {
				//result +="<li>";
				for (int i = 0; i < list.size(); i++) {
					if(list.get(i).getItemTypeName().equals("警员")){
						result += list.get(i).getorgName()+"人";
					}else if(list.get(i).getItemTypeName().equals("车辆")){
						result += list.get(i).getorgName()+"车";
					}else if(list.get(i).getItemTypeName().equals("武器")){
						result += list.get(i).getorgName()+"武器";
					}
				}
				//result +="</li>";
			} else {
				//result = "<li>0</li>";
				result = "0";
			}
			return result;
		} catch (Exception ex) {
			return " 获取报备警力发生错误 ";
		}
	}

	/**
	 * 根据日期获得星期
	 * 
	 * @param date
	 * @return
	 */
	public static String getWeekOfDate(Date date) {
		String[] weekDaysCode = { "0", "1", "2", "3", "4", "5", "6" };
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		return weekDaysCode[intWeek];
	}

	@RequestMapping(value = "getDutyInfoForDay.do")
	public @ResponseBody
	String getDutyInfoForDay(
			@RequestParam(value = "date", required = false) String date,
			@RequestParam(value = "orgId", required = false) Integer orgId,
			HttpServletRequest request) throws Exception {
		try{
			int dt = 0;
			String dates = date.replace("-", "");
			dt = Integer.parseInt(dates);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ymd", dt);
			map.put("orgId", orgId);
			List<DutyItemCountVM> list = new ArrayList<DutyItemCountVM>();
			
			
			String result = "{\"name\":\"randName\",\"position\":\"randPosition\",\"phone\":\"028-0022569\",\"cornet\":\"10086\"}";
			
			return result;
		}
		catch(Exception ex){
			return " 获取报备信息发生错误 ";
		}
	}
	@RequestMapping(value = "getTotalPolice.do")
	public @ResponseBody String getTotalPolice(
			@RequestParam(value = "orgId", required = false) Integer orgId,
			@RequestParam(value = "orgPath", required = false) String orgPath,
			@RequestParam(value = "orgCode", required = false) String orgCode,
			@RequestParam(value = "beginTime", required = false) String beginTime,
			@RequestParam(value = "endTime", required = false) String endTime,
			HttpServletRequest request
			){
		Map<String, Object> map = new HashMap<String, Object>();
		int bTime = 0;
		int eTime = 0;
		if(beginTime != null && !beginTime.equals("")){
			beginTime = beginTime.replace("-", "");
			bTime = Integer.parseInt(beginTime);
		}
		if(endTime != null && !endTime.equals("")){
			endTime = endTime.replace("-", "");
			eTime = Integer.parseInt(endTime);
		}
		map.put("orgId", orgId);
		map.put("orgPath", orgPath);
		map.put("orgCode", orgCode);
		map.put("beginTime", bTime);
		map.put("endTime", eTime);
		List<DutyItemCountVM> dicvms=dutyService.loadTotalPolice(map);
		
		ListResult<DutyItemCountVM> rs=new ListResult<DutyItemCountVM>(dicvms.size(),dicvms,true);
		
		return rs.toJson();
	}
	@RequestMapping(value = "getTotalPolicedetail.do")
	public @ResponseBody String getTotalPolicedetail(
			@RequestParam(value = "orgId", required = false) Integer orgId,
			@RequestParam(value = "orgPath", required = false) String orgPath,
			@RequestParam(value = "orgCode", required = false) String orgCode,
			@RequestParam(value = "beginTime", required = false) String beginTime,
			@RequestParam(value = "endTime", required = false) String endTime,
			HttpServletRequest request
			){
		Map<String, Object> map = new HashMap<String, Object>();

		int bTime = 0;
		int eTime = 0;
		if(beginTime != null && !beginTime.equals("")){
			beginTime = beginTime.replace("-", "");
			bTime = Integer.parseInt(beginTime);
		}
		if(endTime != null && !endTime.equals("")){
			endTime = endTime.replace("-", "");
			eTime = Integer.parseInt(endTime);
		}
		map.put("orgId", orgId);
		map.put("orgPath", orgPath);
		map.put("orgCode", orgCode);
		map.put("beginTime", bTime);
		map.put("endTime", eTime);
		List<DutyItemCountVM> dicvms=dutyService.loadTotalPolicedetail(map);
		
		ListResult<DutyItemCountVM> rs=new ListResult<DutyItemCountVM>(dicvms.size(),dicvms,true);
		
		return rs.toJson();
	}
}
