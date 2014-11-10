package com.tianyi.drs.basedata.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
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

	@RequestMapping(value = "IconsUpload.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	void IconsUpload(@RequestParam("myicons") CommonsMultipartFile mFile,
			@RequestParam MultipartFile mf, Icons icons,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {

			int i = 100;

			int x = i;

		} catch (Exception ex) {
			String s = ex.getMessage();
		}
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

			map.put("pageStart", (page - 1) * rows);
			map.put("pageSize", rows);
			map.put("orgId", orgId);
			map.put("orgCode", orgCode);
			map.put("orgPath", orgPath);
			map.put("name", name);
			map.put("typeid", typeid);

			int total = iconsService.loadVMCount(map);
			list = iconsService.loadVMList(map);

			ListResult<Icons> rs = new ListResult<Icons>(total, list);

			String result = JSONObject.fromObject(rs).toString();

			return result;
		} catch (Exception ex) {
			return "{\"total\":0,\"rows\":[]}";
		}
	}

	@SuppressWarnings("resource")
	@RequestMapping(value = "saveIcons.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String saveIcons(Icons icons) throws Exception {
		try {
			String path = icons.getName();
			// String[] plist = path.split("\\");
			String name = "uploadify-cancel.png";
			FileInputStream fis = null;
			fis = new FileInputStream(path);
			icons.setName(name);

			// icons.setIcon(fis);
			icons.setPlatformId(1);
			icons.setSyncState(true);
			int result = 0;
			if (icons.getId() > 0) {
				int pid = icons.getId();
				icons.setId(pid);
				result = iconsService.updateByPrimaryKey(icons);
			} else {
				result = iconsService.insert(icons);
			}
			return "{\"success\":true,\"Message\":\"保存成功,result is " + result
					+ "\"}";
		} catch (Exception ex) {
			return "{\"success\":false,\"Message\":\"保存失败，原因："
					+ ex.getMessage() + "\"}";
		}
	}

	@RequestMapping(value = "deleteIcons.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String deleteIcons(int id) throws Exception {
		try {
			int result = 0;
			if (id > 0) {
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
	String uploadIcon(@RequestParam("file") CommonsMultipartFile cmFile, // 请求参数一定要与form中的参数名对应
			Icons icon, HttpServletRequest request, HttpServletResponse response) {

		try {
			File tmpDir = null;//初始化上传文件的临时存放目录
			File saveDir = null;//初始化上传文件后的保存目录
			
			if (!cmFile.isEmpty()) { 
				 
				int size = (int) cmFile.getFileItem().getSize();
				String name = cmFile.getFileItem().getName();
				byte[] img = new byte[(int) size];

				InputStream ops = cmFile.getFileItem().getInputStream();

				int s = ops.read(img);

			}
			byte[] s = new byte[1024];
			Map parametermap = new HashMap();
			parametermap.put("file", s);
			String name = icon.getName();

			ObjResult<Icons> rs = new ObjResult<Icons>();

			return rs.toJson();
		} catch (Exception ex) {
			return null;
		}

	}
}
