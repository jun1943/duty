package com.tianyi.drs.basedata.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody; 
       
import com.tianyi.drs.basedata.model.Weapon;
import com.tianyi.drs.basedata.model.WeaponType;
import com.tianyi.drs.basedata.service.WeaponService; 
import com.tianyi.drs.basedata.viewmodel.WeaponVM;
import com.tianyi.drs.duty.util.ExcelPortUtil;
import com.tianyi.drs.duty.viewmodel.ListResult; 

/*
 * 
 * 武器管理逻辑控制器；
 */
@Scope("prototype")
@Controller
@RequestMapping("/weapon")
public class WeaponController {
		
	@Resource(name="weaponService")
	protected WeaponService weaponService;
	/*
	 * 获取武器列表信息
	 * 
	 * weapon_Query：查询条件包 
	 * page：当前页
	 * rows：每页条数
	 */
	@RequestMapping(value = "getWeaponList.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getWeaponList(
			@RequestParam(value = "weapon_Query", required = false) String query,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			HttpServletRequest request) throws Exception {
		try {
			JSONObject joQuery = JSONObject.fromObject(query);
			int orgId = Integer.parseInt(joQuery.getString("orgId"));
			int isSubOrg = Integer.parseInt(joQuery.getString("isSubOrg"));
			String number = joQuery.getString("number");
			//int typeid = Integer.parseInt(joQuery.getString("typeid"));

			String orgCode = joQuery.getString("orgCode");
			String orgPath = joQuery.getString("orgPath");

			List<WeaponVM> list = new ArrayList<WeaponVM>();
			Map<String, Object> map = new HashMap<String, Object>();
			page = page == 0 ? 1 : page;
			map.put("pageStart", (page - 1) * rows);
			map.put("pageSize", rows);
			map.put("orgId", orgId);
			map.put("isSubOrg", isSubOrg);
			map.put("orgCode", orgCode);
			map.put("orgPath", orgPath);
			map.put("number", number);
			//map.put("typeid", typeid);

			int total = weaponService.loadVMCount(map);
			list = weaponService.loadVMList(map);

			ListResult<WeaponVM> rs = new ListResult<WeaponVM>(total, list);

			String result = JSONObject.fromObject(rs).toString();

			return result;
		} catch (Exception ex) {
			return "{\"total\":0,\"rows\":[]}";
		}
	}
	
	/*
	 * 
	 * 获取武器资源列表，
	 * 
	 * 用于报备资源元素展示；
	 */
	@RequestMapping(value = "getweaponSource.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getweaponSource(  
			@RequestParam(value = "orgId", required = false) Integer orgId,
			@RequestParam(value = "orgCode", required = false) String orgCode,
			@RequestParam(value = "orgPath", required = false) String orgPath,
			@RequestParam(value = "number", required = false) String number, 
			@RequestParam(value = "typeId", required = false) String typeId, 
			@RequestParam(value = "groupId", required = false) String groupId,
			HttpServletRequest request) throws Exception {
		try {  
			number = number.replace(",", "");
			List<WeaponVM> list = new ArrayList<WeaponVM>();
			Map<String, Object> map = new HashMap<String, Object>();
 
			map.put("orgId", orgId); 
			map.put("number", number);
			map.put("orgPath", orgPath);
			map.put("orgCode", orgCode);
			//map.put("typeId", typeId);
			if (groupId != null && groupId != "") {
				String[] gs = {};
				gs = groupId.split(",");
				int[] gids = new int[gs.length];
				for (int i = 0; i < gs.length; i++) {
					gids[i] = Integer.parseInt(String.valueOf(gs[i]));
				}
				map.put("gids", gids);
			}
			if (typeId != null&& typeId !="") {
				String[] s = {};
				s = typeId.split(",");
				int[] ids = new int[s.length];
				for (int i = 0; i < s.length; i++) { 
					ids[i] = Integer.parseInt(String.valueOf(s[i]));
				}
				map.put("ids", ids);
			}


			list = weaponService.loadVMListWithGroup(map);
			int total =  list.size();

			ListResult<WeaponVM> rs = new ListResult<WeaponVM>(total, list);

			String result = JSONObject.fromObject(rs).toString();

			return result;
		} catch (Exception ex) {
			return "{\"total\":0,\"rows\":[]}";
		}
	}
	/*
	 * 
	 * 保存武器信息；
	 */
	@RequestMapping(value = "saveWeapon.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String saveWeapon(Weapon weapon) throws Exception {
		try {
			weapon.setPlatformId(1);
			weapon.setSyncState(true);
			int result = 0;
			if (weapon.getId() > 0) {
				int pid = weapon.getId();
				weapon.setId(pid);
				result = weaponService.updateByPrimaryKey(weapon);
			} else {
				result = weaponService.insert(weapon);
			}
			return "{\"success\":true,\"Message\":\"保存成功,result is " + result
					+ "\"}";
		} catch (Exception ex) {
			return "{\"success\":false,\"Message\":\"保存失败，原因："
					+ ex.getMessage() + "\"}";
		}
	}
	
	/*
	 * 删除武器信息
	 * id：页面选择的武器id；
	 */
	@RequestMapping(value = "deleteWeapon.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String deleteWeapon(String id) throws Exception {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			int result = 0;
			if (id != null && id != "") {
				String[] s = {};
				s = id.split(",");
				int[] ids = new int[s.length];
				for (int i = 0; i < s.length; i++) {
					ids[i] = Integer.parseInt(String.valueOf(s[i]));
				}
				map.put("ids", ids);
			 
				weaponService.deleteByIds(map);
			}
//			int result =0;
//			if(id>0){
//				result = weaponService.deleteByPrimaryKey(id);
//			}
			return "{\"success\":true,\"Message\":\"删除成功,result is " + result + "\"}";
		} catch (Exception ex) {
			return "{\"success\":false,\"Message\":\"删除失败，原因：" + ex.getMessage() + "\"}";
		}
	}
	/*
	 * 获取武器类型下拉列表；
	 */
	@RequestMapping(value="getWeaponType.do",produces="application/json;charset=UTF-8")
	public @ResponseBody String getWeaponType() throws Exception {
		try
		{ 
			List<WeaponType> list = weaponService.selectWeaponType();
			JSONArray result = JSONArray.fromObject(list);
			return result.toString();
		}
		catch(Exception ex){
			return "";
		}
	}
	/*
	 * 获取武器类型列表数据，
	 */
	@RequestMapping(value="getWeaponTypelist.do",produces="application/json;charset=UTF-8")
	public @ResponseBody String getWeaponTypelist() throws Exception {
		try
		{ 
			List<WeaponType> list = weaponService.selectWeaponType();
			int total = list.size();
			ListResult<WeaponType> rs = new ListResult<WeaponType>(total, list);

			String result = JSONObject.fromObject(rs).toString();

			return result;
		} catch (Exception ex) {
			return "{\"total\":0,\"rows\":[]}";
		}
	}
	/**
	 * 判断是否有有车辆存在 
	 * 
	 * 判断是否车牌号码重复；
	 */
	@RequestMapping(value = "isExistWeapon.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String isExistWeapon(
			@RequestParam(value = "param", required = false) String param)
			throws Exception {
		try {
			 
				if (!param.equals("")) {
					List<Weapon> weapon = weaponService.findByNumber(param);
					if (weapon.size()>0) {
						return "{\"isSuccess\":false,\"Message\":\"Exits\"}";
					} else {
						return "{\"isSuccess\":true,\"Message\":\"UnExits\"}";
					}
				} else {
					return "{\"isSuccess\":true,\"Message\":\"UnExits\"}";
				}
			 
		} catch (Exception ex) {
			return "{\"isSuccess\":false,\"Message\":\"Exits\"}";
		}
	}
	@RequestMapping(value="exportDataToExcle.do",produces="application/json;charset=UTF-8")
	public @ResponseBody String exportDataToExcle(
			@RequestParam(value = "weapon_Query", required = false) String query,
			HttpServletResponse response, HttpServletRequest request)
	{
		try {
			JSONObject joQuery = JSONObject.fromObject(query);
			int orgId = Integer.parseInt(joQuery.getString("orgId"));
			int isSubOrg = Integer.parseInt(joQuery.getString("isSubOrg"));
			String number = joQuery.getString("number");
			//int typeid = Integer.parseInt(joQuery.getString("typeid"));

			String orgCode = joQuery.getString("orgCode");
			String orgPath = joQuery.getString("orgPath");

			List<WeaponVM> list = new ArrayList<WeaponVM>();
			Map<String, Object> map = new HashMap<String, Object>();
		 
			map.put("pageStart", 1);
			map.put("pageSize", 65530);
			map.put("orgId", orgId);
			map.put("isSubOrg", isSubOrg);
			map.put("orgCode", orgCode);
			map.put("orgPath", orgPath);
			map.put("number", number);
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
			list = weaponService.loadVMList(map);
			if (list.size() > 0) {
				isSuccess = initExcelData(list, realPath);
				if (isSuccess) {
					return "{\"isSuccess\":true,\"Message\":\"" + exlPath
							+ "\",\"Data\":\"" + exlPath + "\"}";
				} else {
					return "{\"isSuccess\":false,\"Message\":\"创建Excel表格失败\",\"Data\":\"\"}";
				}
			} else {
				return "{\"isSuccess\":false,\"Message\":\"获取报备详细信息子失败\",\"Data\":\"\"}";
			}
		} catch (Exception e) {
			return "{\"isSuccess\":false,\"Message\":\"" + e.getMessage()
					+ "\",\"Data\":\"\"}";
		}
	}
 

	private boolean initExcelData(List<WeaponVM> list, String realPath) {
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
				Sheet sheet = workbook.createSheet("武器基础数据");
				Row row0 = sheet.createRow(0);
				String title = "武器基础数据汇总信息";
				if (list.size() > 0) {
					title = list.get(0).getOrgName() + title;
				}
				Cell cell_0 = row0.createCell(0, Cell.CELL_TYPE_STRING);
				CellStyle style = ExcelPortUtil.getStyle(workbook);
				cell_0.setCellStyle(style);

				cell_0.setCellValue(title);
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 0));
			 
				Row row1 = sheet.createRow(1);

				Cell cell_1 = row1.createCell(0, Cell.CELL_TYPE_STRING); 
				cell_1.setCellStyle(style);
				cell_1.setCellValue("武器类型");
				sheet.autoSizeColumn(0);

				Cell cell_2 = row1.createCell(1, Cell.CELL_TYPE_STRING);
				cell_2.setCellStyle(style);
				cell_2.setCellValue("武器编号");
				sheet.autoSizeColumn(1);

				Cell cell_3 = row1.createCell(2, Cell.CELL_TYPE_STRING);
				cell_3.setCellStyle(style);
				cell_3.setCellValue("规格标准");
				sheet.autoSizeColumn(2);
 
 
				
				for (int rowNum = 2; rowNum <= list.size(); rowNum++) {
					Row row = sheet.createRow(rowNum);
					WeaponVM weapon = new WeaponVM();
					weapon = list.get(rowNum - 2);
					Cell cella = row.createCell(0, Cell.CELL_TYPE_STRING);
					cella.setCellValue(weapon.getTypeName() == null ? "" : weapon.getTypeName());
					Cell cellb = row.createCell(1, Cell.CELL_TYPE_STRING);
					cellb.setCellValue(weapon.getNumber() == null ? "" : weapon.getNumber());
					Cell cellc = row.createCell(2, Cell.CELL_TYPE_STRING);
					cellc.setCellValue(weapon.getStandard()== null ? "" : weapon.getStandard());
				 
				}
			}
			try {
				FileOutputStream outputStream = new FileOutputStream(
						realPath);
				workbook.write(outputStream);
				outputStream.flush();
				outputStream.close();
				isCreateSuccess = true;
			} catch (Exception e) {
				isCreateSuccess = false;
			}
			return isCreateSuccess;
		} catch (Exception ex) {
			return isCreateSuccess;
		}
	}
}
