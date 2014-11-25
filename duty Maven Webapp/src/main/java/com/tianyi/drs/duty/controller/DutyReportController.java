package com.tianyi.drs.duty.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianyi.drs.duty.model.PoliceTarget;
import com.tianyi.drs.duty.service.DutyReportService;
import com.tianyi.drs.duty.util.ExcelPortUtil;
import com.tianyi.drs.duty.viewmodel.DutyExportVM;
import com.tianyi.drs.duty.viewmodel.DutyItemVM;
import com.tianyi.drs.duty.viewmodel.DutyReportCriteria;
import com.tianyi.drs.duty.viewmodel.DutyReportVM;
import com.tianyi.drs.duty.viewmodel.ListResult;
import com.tianyi.drs.duty.viewmodel.TaskTargetVM;

@Scope("prototype")
@Controller
@RequestMapping("/dutyReport")
public class DutyReportController {
	@Resource(name = "dutyReportService")
	protected DutyReportService dutyReportService;

	@RequestMapping(value = "loadDutyReport.do")
	public @ResponseBody
	String loadDutyReport(
			@RequestParam(value = "criteria", required = false) String criteriaJson,
			HttpServletRequest request) {
		try {
			JSONObject jobj = JSONObject.fromObject(criteriaJson);

			JSONUtils.getMorpherRegistry().registerMorpher(
					new DateMorpher(new String[] { "yyyy-MM-dd HH:mm" }));

			Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();

			classMap.put("orgIds", Integer.class);
			classMap.put("taskPropertyIds", Integer.class);
			classMap.put("attireTypeIds", Integer.class);
			classMap.put("policeTypeIds", Integer.class);
			classMap.put("armamentTypeIds", Integer.class);
			classMap.put("dutyTypeIds", Integer.class);

			DutyReportCriteria drc = (DutyReportCriteria) JSONObject.toBean(
					jobj, DutyReportCriteria.class, classMap);

			List<DutyReportVM> ls = dutyReportService.loadDutyReport(drc);

			ListResult<DutyReportVM> rs = new ListResult<DutyReportVM>(
					ls.size(), ls, true);

			return rs.toJson();
		} catch (Exception ex) {
			ListResult<DutyReportVM> rs = new ListResult<DutyReportVM>(0, null,
					true);
			return rs.toJson();
		}
	}

	@RequestMapping(value = "exportDutyReport.do")
	public @ResponseBody
	String exportDutyReport(
			@RequestParam(value = "criteria", required = false) String criteriaJson,
			HttpServletRequest request) {
		try {
			JSONObject jobj = JSONObject.fromObject(criteriaJson);

			JSONUtils.getMorpherRegistry().registerMorpher(
					new DateMorpher(new String[] { "yyyy-MM-dd HH:mm" }));

			Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();

			classMap.put("orgIds", Integer.class);
			classMap.put("taskPropertyIds", Integer.class);
			classMap.put("attireTypeIds", Integer.class);
			classMap.put("policeTypeIds", Integer.class);
			classMap.put("armamentTypeIds", Integer.class);
			classMap.put("dutyTypeIds", Integer.class);

			DutyReportCriteria drc = (DutyReportCriteria) JSONObject.toBean(
					jobj, DutyReportCriteria.class, classMap);
			List<DutyReportVM> ls = dutyReportService.loadDutyReport(drc);
			String realPath = getClass().getResource("/").getFile().toString();
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
			boolean isSuccess = false;
			String s = UUID.randomUUID().toString();
			s = s.replace("-", "");
			realPath += "/" + s + ".xls";
			exlPath += "/" + s + ".xls";
			if (ls.size() == 0) {
				return "{\"isSuccess\":false,\"Message\":\"数据查询失败\",\"Data\":\"\"}";
			} else {
				ListResult<DutyReportVM> rs = new ListResult<DutyReportVM>(
						ls.size(), ls, true);
				if (rs.getRows().size() > 0) {
					isSuccess = initExcelData(rs.getRows(), realPath);
					if (isSuccess) {
						return "{\"isSuccess\":true,\"Message\":\"" + exlPath
								+ "\",\"Data\":\"" + exlPath + "\"}";
					} else {
						return "{\"isSuccess\":false,\"Message\":\"创建Excel表格失败\",\"Data\":\"\"}";
					}
				} else {
					return "{\"isSuccess\":false,\"Message\":\"获取报备详细信息子失败\",\"Data\":\"\"}";
				}
			}
		} catch (Exception e) {
			return "{\"isSuccess\":false,\"Message\":\"" + e.getMessage()
					+ "\",\"Data\":\"\"}";
		}
	}

	private boolean initExcelData(List<DutyReportVM> rows, String realPath) {
		File file = new File(realPath);
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
				cell_1.setCellValue("组织机构");
				sheet.autoSizeColumn(1);

				Cell cell_2 = row0.createCell(2, Cell.CELL_TYPE_STRING);
				cell_2.setCellStyle(style);
				cell_2.setCellValue("值班领导");
				sheet.autoSizeColumn(2);

				Cell cell_3 = row0.createCell(3, Cell.CELL_TYPE_STRING);
				cell_3.setCellStyle(style);
				cell_3.setCellValue("值班主任");
				sheet.autoSizeColumn(2);

				Cell cell_4 = row0.createCell(4, Cell.CELL_TYPE_STRING);
				cell_4.setCellStyle(style);
				cell_4.setCellValue("警力数量");
				sheet.autoSizeColumn(4);

				Cell cell_5 = row0.createCell(5, Cell.CELL_TYPE_STRING);
				cell_5.setCellStyle(style);
				cell_5.setCellValue("车辆数量");
				sheet.autoSizeColumn(5);

				Cell cell_6 = row0.createCell(6, Cell.CELL_TYPE_STRING);
				cell_6.setCellStyle(style);
				cell_6.setCellValue("武器数量");
				sheet.autoSizeColumn(6);

				Cell cell_7 = row0.createCell(7, Cell.CELL_TYPE_STRING);
				cell_7.setCellStyle(style);
				cell_7.setCellValue("社区");
				sheet.autoSizeColumn(7);

				Cell cell_8 = row0.createCell(8, Cell.CELL_TYPE_STRING);
				cell_8.setCellStyle(style);
				cell_8.setCellValue("巡区");
				sheet.autoSizeColumn(8);

				Cell cell_9 = row0.createCell(9, Cell.CELL_TYPE_STRING);
				cell_9.setCellStyle(style);
				cell_9.setCellValue("卡点");
				sheet.autoSizeColumn(9);
				for (int rowNum = 1; rowNum <= rows.size(); rowNum++) {
					Row row = sheet.createRow(rowNum);
					DutyReportVM devm = new DutyReportVM();
					devm = rows.get(rowNum - 1);
					Cell cella = row.createCell(1, Cell.CELL_TYPE_STRING);
					cella.setCellValue(devm.getOrgName() == null ? "" : devm
							.getOrgName());
					Cell cellb = row.createCell(2, Cell.CELL_TYPE_STRING);
					cellb.setCellValue(devm.getLeaderNames() == null ? ""
							: devm.getLeaderNames());
					Cell cellc = row.createCell(3, Cell.CELL_TYPE_STRING);
					cellc.setCellValue("");
					Cell celld = row.createCell(4, Cell.CELL_TYPE_STRING);
					celld.setCellValue(devm.getPoliceCount() == null ? 0 : devm
							.getPoliceCount());
					Cell celle = row.createCell(5, Cell.CELL_TYPE_STRING);
					celle.setCellValue(devm.getVehicleCount() == null ? 0
							: devm.getVehicleCount());
					Cell cellf = row.createCell(6, Cell.CELL_TYPE_STRING);
					cellf.setCellValue(devm.getWeaponCount() == null ? 0 : devm
							.getWeaponCount());
					Cell cellg = row.createCell(7, Cell.CELL_TYPE_STRING);
					cellg.setCellValue(devm.getPatrolAreaCount() == null ? 0
							: devm.getPatrolAreaCount());
					Cell cellh = row.createCell(8, Cell.CELL_TYPE_STRING);
					cellh.setCellValue(devm.getCommunityCount() == null ? 0
							: devm.getCommunityCount());
					Cell celli = row.createCell(9, Cell.CELL_TYPE_STRING);
					celli.setCellValue(devm.getBayonetCount() == null ? 0
							: devm.getBayonetCount());
				}
				try {
					FileOutputStream outputStream = new FileOutputStream(
							realPath);
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
