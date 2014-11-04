package com.tianyi.drs.duty.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date; 

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianyi.drs.duty.service.DutyService;

@Scope("prototype")
@Controller
@RequestMapping("/dutyCalendar")
public class DutyCalendarController {


	@Resource(name = "dutyService")
	protected DutyService dutyService;
	
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
			result += "{\"y\":\""
					+ year
					+ "\",\"m\":\""
					+ month
					+ "\",\"d\":\""
					+ i
					+ "\",\"week\":\""
					+ week
					+ "\",\"totalpolice\":\""+getTotalPolice(date,orgId)+"\",\"dutyList\":\""+getDutyList(date,orgId)+"\"},";
		}
		if (result.endsWith(",")) {
			result = result.substring(0, result.length() - 1) + "]";
		} 
		return result;
	}
	/*
	 * 根据日期，组织，获取报备总警力
	 * */
	private String getDutyList(String date, Integer orgId) {

		return "<li>领导报备</li><li>处警报备</li><li>巡逻报备</li><li>前置平台报备</li>"; 
	}
	/*
	 * 根据日期，组织，获取报备类型
	 * */
	private String getTotalPolice(String date, Integer orgId) {
		
		return "<li>10人5车6武器</li>";
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
			HttpServletRequest request) throws Exception {

		 
		String	result = "{\"name\":\"randName\",\"position\":\"randPosition\",\"phone\":\"028-0022569\",\"cornet\":\"10086\"}";
		 
		return result;
	}

}
