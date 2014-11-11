package com.tianyi.drs.basedata.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.tianyi.drs.basedata.model.Icons;
import com.tianyi.drs.basedata.service.IconsService;
import com.tianyi.drs.duty.viewmodel.DutyTypeVM;
import com.tianyi.drs.duty.viewmodel.ListResult;
import com.tianyi.drs.duty.viewmodel.ObjResult;

@Scope("prototype")
@Controller
@RequestMapping("/icons")
public class IconsController extends CommonsMultipartResolver {

	@Resource(name = "iconsService")
	protected IconsService iconsService;

	public IconsController() {
		super();
	}

	@RequestMapping(value = "getIconsList.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String getIconsList(
			@RequestParam(value = "icons_Query", required = false) String query,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			HttpServletRequest request) throws Exception {
		try {
			JSONObject joQuery = JSONObject.fromObject(query);
			int orgId = Integer.parseInt(joQuery.getString("orgId"));
			String name = joQuery.getString("name");

			String orgCode = joQuery.getString("orgCode");
			String orgPath = joQuery.getString("orgPath");
			int typeid = Integer.parseInt(joQuery.getString("typeid"));

			List<Icons> list = new ArrayList<Icons>();
			Map<String, Object> map = new HashMap<String, Object>();
			page = page == 0 ? 1 : page;
			map.put("pageStart", (page - 1) * rows);
			map.put("pageSize", rows);
			map.put("orgId", orgId);
			map.put("orgCode", orgCode);
			map.put("orgPath", orgPath);
			map.put("name", name);
			map.put("typeid", typeid);

			int total = iconsService.loadCount(map);
			list = iconsService.loadList(map);

			ListResult<Icons> rs = new ListResult<Icons>(total, list);

			String result = JSONObject.fromObject(rs).toString();

			return result;
		} catch (Exception ex) {
			return "{\"total\":0,\"rows\":[]}";
		}
	}
 

	@RequestMapping(value = "deleteIcons.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String deleteIcons(int id) throws Exception {
		try {
			int result = 0;
			if (id > 0) {
				Icons icon = new Icons();
				icon = iconsService.loadById(id);
				if(icon!=null){
					String iconUrl = icon.getIconUrl();
					File pc = new File(iconUrl);
					
					if (pc.exists()) {
						pc.delete();
					}
				}
				result = iconsService.deleteByPrimaryKey(id);
			}
			return "{\"success\":true,\"Message\":\"删除成功,result is " + result
					+ "\"}";
		} catch (Exception ex) {
			return "{\"success\":false,\"Message\":\"删除失败，原因："
					+ ex.getMessage() + "\"}";
		}
	}

	@RequestMapping(value = "uploadIcon.do")
	public @ResponseBody
	String uploadIcon(
			@RequestParam("file") CommonsMultipartFile cmFile, // 请求参数一定要与form中的参数名对应
			Icons icons, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			String realPath = getClass().getResource("/").getFile().toString();
			if (realPath.startsWith("/")) {
				realPath = realPath.substring(0);
			}
			if (icons != null) {
				if (icons.getTypeId() == 1) {
					realPath = realPath + "police";
				} else if (icons.getTypeId() == 2) {
					realPath = realPath + "vehicle";
				} else if (icons.getTypeId() == 3) {
					realPath = realPath + "weapon";
				} else if (icons.getTypeId() == 4) {
					realPath = realPath + "gpsdevice";
				} else {
					realPath = realPath + "others";
				}
				File rF = new File(realPath);
				if (!rF.exists()) {
					rF.mkdir();
				}
				realPath = realPath + "/";
			}

			if (!cmFile.isEmpty()) {
				// int size = (int) cmFile.getFileItem().getSize();
				String name = cmFile.getFileItem().getName();

				Icons iconObj = new Icons();
				iconObj.setId(icons.getId());
				iconObj.setTypeId(icons.getTypeId());
				iconObj.setName(icons.getName());
				iconObj.setSyncState(true);
				iconObj.setPlatformId(1);

				int iconId = icons.getId();
				if(iconId==0){
					iconsService.insert(iconObj);
					iconId = iconObj.getId();
					String dirUrl = realPath + iconId;
					File filedir = new File(dirUrl);
					
					if (!filedir.exists()) {
						filedir.mkdir();
					}
					dirUrl = dirUrl + "/";
					String iconUrl = dirUrl + name;
	
					iconObj.setIconUrl(iconUrl);
					iconObj.setId(iconId);
					iconsService.updateByPrimaryKey(iconObj);
	
					InputStream ops = cmFile.getFileItem().getInputStream();
					BufferedImage iconPic = ImageIO.read(ops);
					// String
					// rootPath=getClass().getResource("/").getFile().toString();
	
					File pc = new File(iconUrl);
	
					if (pc.exists()) {
						pc.delete();
					}
	
					ImageIO.write(iconPic, "png", pc);// 不管输出什么格式图片，此处不需改动
				}else{ 
					String dirUrl = realPath + iconId;
					File filedir = new File(dirUrl);
					
					if (!filedir.exists()) {
						filedir.mkdir();
					}
					dirUrl = dirUrl + "/";
					String iconUrl = dirUrl + name;
	
					iconObj.setIconUrl(iconUrl);
					iconObj.setId(iconId);
					iconsService.updateByPrimaryKey(iconObj);
	
					InputStream ops = cmFile.getFileItem().getInputStream();
					BufferedImage iconPic = ImageIO.read(ops);
					// String
					// rootPath=getClass().getResource("/").getFile().toString();
	
					File pc = new File(iconUrl);
	
					if (pc.exists()) {
						pc.delete();
					}
	
					ImageIO.write(iconPic, "png", pc);// 不管输出什么格式图片，此处不需改动
				}
			}
			// ObjResult<IconsVM> rs = new ObjResult<DutyTypeVM>(true, null,
			// dtvm.getId(), null);

			String result = "File upLoad Success !";
			return result;

		} catch (Exception ex) {
			return "File upLoad Failed !";
		}

	}
}
