package com.tianyi.drs.duty.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianyi.drs.duty.service.DutyService;
import com.tianyi.drs.duty.service.DutyTypeService;
import com.tianyi.drs.duty.service.OrgService;
import com.tianyi.drs.duty.util.ExcelPortUtil;
import com.tianyi.drs.duty.viewmodel.DutyExportVM;
import com.tianyi.drs.duty.viewmodel.DutyItemCountVM;
import com.tianyi.drs.duty.viewmodel.DutyItemVM;
import com.tianyi.drs.duty.viewmodel.DutyVM;
import com.tianyi.drs.duty.viewmodel.ExprotFileInfo;
import com.tianyi.drs.duty.viewmodel.ListResult;
import com.tianyi.drs.duty.viewmodel.ObjResult;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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

	@RequestMapping(value = "getCalender.do", produces = "application/json;charset=UTF-8")
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
					+ "\"},";
			// + "\",\"dutyList\":\"" + getDutyList(dt, orgId) + "\"},";
		}
		if (result.endsWith(",")) {
			result = result.substring(0, result.length() - 1) + "]";
		}
		return result;
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
				for (int i = 0; i < list.size(); i++) {
					result += "<li>";
					if (list.get(i).getItemTypeName().equals("警员")) {
						result += list.get(i).getorgName() + "人";
					} else if (list.get(i).getItemTypeName().equals("车辆")) {
						result += list.get(i).getorgName() + "车";
					} else if (list.get(i).getItemTypeName().equals("武器")) {
						result += list.get(i).getorgName() + "武器";
					}
					result += "</li>";
				}
			} else {
				result = "<li class='nobaobei' style='display: list-item;'>无报备</li>"  ;
//						+ "<li class='baoBeiBtn'><div class='pasteBtnBox' onclick='selectPasteBox(this,'"+date+"')' style='display: none;'><a href='javascript:void(0);'>粘贴</a></div>"
//						+ "<a href='javascript:void(0);'>粘贴</a>"
//						+ "</div></li>";
				//result = "无报备";
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

	@RequestMapping(value = "getTotalPolice.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getTotalPolice(
			@RequestParam(value = "orgId", required = false) Integer orgId,
			@RequestParam(value = "orgPath", required = false) String orgPath,
			@RequestParam(value = "orgCode", required = false) String orgCode,
			@RequestParam(value = "beginTime", required = false) String beginTime,
			@RequestParam(value = "endTime", required = false) String endTime,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		int bTime = 0;
		int eTime = 0;
		if (beginTime != null && !beginTime.equals("")) {
			beginTime = beginTime.replace("-", "");
			bTime = Integer.parseInt(beginTime);
		}
		if (endTime != null && !endTime.equals("")) {
			endTime = endTime.replace("-", "");
			eTime = Integer.parseInt(endTime);
		}
		map.put("orgId", orgId);
		map.put("orgPath", orgPath);
		map.put("orgCode", orgCode);
		map.put("beginTime", bTime);
		map.put("endTime", eTime);
		List<DutyItemCountVM> dicvms = dutyService.loadTotalPolice(map);

		ListResult<DutyItemCountVM> rs = new ListResult<DutyItemCountVM>(
				dicvms.size(), dicvms, true);

		return rs.toJson();
	}

	@RequestMapping(value = "getTotalPolicedetail.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getTotalPolicedetail(
			@RequestParam(value = "orgId", required = false) Integer orgId,
			@RequestParam(value = "orgPath", required = false) String orgPath,
			@RequestParam(value = "orgCode", required = false) String orgCode,
			@RequestParam(value = "beginTime", required = false) String beginTime,
			@RequestParam(value = "endTime", required = false) String endTime,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();

		int bTime = 0;
		int eTime = 0;
		if (beginTime != null && !beginTime.equals("")) {
			beginTime = beginTime.replace("-", "");
			bTime = Integer.parseInt(beginTime);
		}
		if (endTime != null && !endTime.equals("")) {
			endTime = endTime.replace("-", "");
			eTime = Integer.parseInt(endTime);
		}
		map.put("orgId", orgId);
		map.put("orgPath", orgPath);
		map.put("orgCode", orgCode);
		map.put("beginTime", bTime);
		map.put("endTime", eTime);
		List<DutyItemCountVM> dicvms = dutyService.loadTotalPolicedetail(map);

		ListResult<DutyItemCountVM> rs = new ListResult<DutyItemCountVM>(
				dicvms.size(), dicvms, true);

		return rs.toJson();
	}

	@RequestMapping(value = "exportDataToExcle.do")
	public @ResponseBody
	String exportDataToExcle(
			@RequestParam(value = "orgId", required = false) Integer orgId,
			@RequestParam(value = "ymd", required = false) Integer ymd,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException {
		try {
			DutyVM dvm = null;
			boolean isSuccess = false;
			// 取服务器地址，默认指向bin目录
			String realPath = getClass().getResource("/").getFile().toString();
			// 将地址截取，指向resouse目录
			realPath = realPath.substring(0, (realPath.length() - 16));
			realPath += "resource";
			String exlPath = "/resource";
			File sF = new File(realPath);
			if (!sF.exists()) {
				sF.mkdir();
			}
			realPath += "/" + "excels";
			exlPath += "/" + "excels";
			File iconF = new File(realPath);
			if (!iconF.exists()) {
				iconF.mkdir();
			}
			String s = UUID.randomUUID().toString();
			s = s.replace("-", "");
			realPath += "/" + s + ".xls";
			exlPath += "/" + s + ".xls";

			dvm = dutyService.loadVMByOrgIdAndYmd(orgId, ymd);
			List<DutyItemVM> list = dvm.getItems();
			if (list.size() > 0) {
				List<DutyExportVM> sublist = new ArrayList<DutyExportVM>();
				sublist = getSubList(list);
				isSuccess = initExcelData(sublist, realPath);
				if (isSuccess) {
					FileInputStream fis = null;
					OutputStream os = null;
					try {
						String fileName = "警力报备明细数据表" + ymd;
						fis = new FileInputStream(realPath);
						os = response.getOutputStream();// 取得输出流
						response.reset();// 清空输出流
						response.setHeader("Content-disposition",
								"attachment; filename=" + fileName);// 设定输出文件头
						response.setContentType("application/x-download");
						byte[] mybyte = new byte[8192];
						int len = 0;
						while ((len = fis.read(mybyte)) != -1) {
							os.write(mybyte, 0, len);
						}
						os.close();
						ExprotFileInfo finfo=new ExprotFileInfo();
						finfo.setPath(exlPath);
						ObjResult<ExprotFileInfo> rs=new ObjResult<ExprotFileInfo>(true,null,0,finfo);
						
						String json=rs.toJson();
						
						return json;
						
					} catch (IOException e) {
						return "{\"isSuccess\":false,\"Message\":\""
								+ e.getMessage() + "\",\"Data\":\"\"}";
					}
				} else {
					return "{\"isSuccess\":false,\"Message\":\"创建Excel表格失败\",\"Data\":\"\"}";
				}
			} else {
				return "{\"isSuccess\":false,\"Message\":\"数据查询失败\",\"Data\":\"\"}";
			}
		} catch (Exception e) {
			return "{\"isSuccess\":false,\"Message\":\"" + e.getMessage()
					+ "\",\"Data\":\"\"}";
		}
	}

	private List<DutyExportVM> getSubList(List<DutyItemVM> list) {
		List<DutyExportVM> sList = new ArrayList<DutyExportVM>();
		for (int i = 0; i < list.size(); i++) {
			DutyExportVM dwm = new DutyExportVM();
			DutyItemVM dim = list.get(i);
			dwm.setDutyName(dim.getDisplayName());
			dwm.setTypeName(dim.getItemInnerTypeName());
			String date = "";
			if (dim.getBeginTime() != null) {
				date = dim.getBeginTime().toString();
			}
			if (dim.getEndTime() != null) {
				date += "至" + dim.getEndTime().toString();
			}
			dwm.setTimeArea(date);
			if (dim.getChildren() != null && dim.getChildren().size() > 0) {

			}
			dwm.setVehicleCount("1");
			dwm.setPoliceCount("1");
			dwm.setWeaponCount("1");
			dwm.setGpsdeviceCount("1");
			sList.add(dwm);
		}
		return sList;
	}

	private Boolean initExcelData(List<DutyExportVM> sublist, String filepath) {
		File file = new File(filepath);
		if (!file.exists()) {
			file.delete();
		}
		boolean isCreateSuccess = false;
		Workbook workbook = null;
		try {
			// XSSFWork used for .xslx (>= 2007), HSSWorkbook for 03 .xsl
			workbook = new XSSFWorkbook();// HSSFWorkbook();//WorkbookFactory.create(inputStream);
			if (workbook != null) {
				Sheet sheet = workbook.createSheet("dutyData");
				Row row0 = sheet.createRow(0);

				Cell cell_1 = row0.createCell(1, Cell.CELL_TYPE_STRING);
				CellStyle style = ExcelPortUtil.getStyle(workbook);
				cell_1.setCellStyle(style);
				cell_1.setCellValue("名称");
				sheet.autoSizeColumn(1);

				Cell cell_2 = row0.createCell(2, Cell.CELL_TYPE_STRING);
				cell_2.setCellStyle(style);
				cell_2.setCellValue("资源类型");
				sheet.autoSizeColumn(2);

				Cell cell_3 = row0.createCell(3, Cell.CELL_TYPE_STRING);
				cell_3.setCellStyle(style);
				cell_3.setCellValue("时间区间");
				sheet.autoSizeColumn(2);

				Cell cell_4 = row0.createCell(4, Cell.CELL_TYPE_STRING);
				cell_4.setCellStyle(style);
				cell_4.setCellValue("车辆数量");
				sheet.autoSizeColumn(4);

				Cell cell_5 = row0.createCell(5, Cell.CELL_TYPE_STRING);
				cell_5.setCellStyle(style);
				cell_5.setCellValue("警员数量");
				sheet.autoSizeColumn(5);

				Cell cell_6 = row0.createCell(6, Cell.CELL_TYPE_STRING);
				cell_6.setCellStyle(style);
				cell_6.setCellValue("武器数量");
				sheet.autoSizeColumn(6);

				Cell cell_7 = row0.createCell(7, Cell.CELL_TYPE_STRING);
				cell_7.setCellStyle(style);
				cell_7.setCellValue("定位设备");
				sheet.autoSizeColumn(7);
				for (int rowNum = 1; rowNum <= sublist.size(); rowNum++) {
					Row row = sheet.createRow(rowNum);
					DutyExportVM devm = new DutyExportVM();
					devm = sublist.get(rowNum - 1);
					Cell cella = row.createCell(1, Cell.CELL_TYPE_STRING);
					cella.setCellValue(devm.getDutyName());
					Cell cellb = row.createCell(2, Cell.CELL_TYPE_STRING);
					cellb.setCellValue(devm.getTypeName());
					Cell cellc = row.createCell(3, Cell.CELL_TYPE_STRING);
					cellc.setCellValue(devm.getTimeArea());
					Cell celld = row.createCell(4, Cell.CELL_TYPE_STRING);
					celld.setCellValue(devm.getPoliceCount());
					Cell celle = row.createCell(5, Cell.CELL_TYPE_STRING);
					celle.setCellValue(devm.getVehicleCount());
					Cell cellf = row.createCell(6, Cell.CELL_TYPE_STRING);
					cellf.setCellValue(devm.getWeaponCount());
					Cell cellg = row.createCell(7, Cell.CELL_TYPE_STRING);
					cellg.setCellValue(devm.getGpsdeviceCount());
				}
				try {
					FileOutputStream outputStream = new FileOutputStream(
							filepath);
					workbook.write(outputStream);
					outputStream.flush();
					outputStream.close();
					isCreateSuccess = true;
				} catch (Exception e) {

				}
			}
			return isCreateSuccess;
		} catch (Exception e) {
			return isCreateSuccess;
		}
	}
}
