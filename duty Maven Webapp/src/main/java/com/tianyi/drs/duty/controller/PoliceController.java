package com.tianyi.drs.duty.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianyi.drs.duty.model.Police;
import com.tianyi.drs.duty.service.PoliceService;
import com.tianyi.drs.duty.viewmodel.PoliceVM;

@Scope("prototype")
@Controller
@RequestMapping("/police")
public class PoliceController {
	@Resource(name = "policeService")
	protected PoliceService policeService;// =
											// ServiceFactory.getPoliceServiceInit();

	@RequestMapping("index")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "getPoliceList.do", produces = "application/json;charset=UTF-8")
	public @ResponseBody  String getPoliceList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int page = 1;
		int pageSize = 10;
		int _page = Integer.parseInt(request.getParameter("page"));
		int _pageSize = Integer.parseInt(request.getParameter("rows"));
		if(_page>0){
			page= _page;
		}
		if(_pageSize>0){
			pageSize= _pageSize;
		}
		
		List<PoliceVM> list = new ArrayList<PoliceVM>();
		Map<String, Object> map = new HashMap<String, Object>();
		

		map.put("pageStart", page);
		map.put("pageSize", pageSize); 
		map.put("orgId", 2); 
		
		
		int total = policeService.loadVMCount(map);
		list = policeService.loadVMList(map);
		
		JSONArray jsonarray = JSONArray.fromObject(list);  
		
		String result = "{\"total\":"+total+",\"rows\":"+jsonarray.toString()+"}";
		
		return result;
	}

	@RequestMapping(value = "savePolice.do", produces = "application/json;charset=UTF-8")
	public void savePolice() throws Exception {
		try {
			Police police = new Police();
			police.setName("王五");
			police.setGpsId(5);
			police.setGpsName("一号手机定位");
			police.setMobile("13568865179");
			police.setIntercomGroup("ssss");
			police.setIntercomPerson("22222");
			police.setIdcardno("512301198506234875");
			police.setOrgId(100);
			police.setMobileShort("6179");
			police.setNumber("51007816");
			police.setTypeId(3);
			police.setTitle("科长");
			System.out.println(policeService.insert(police));
		} catch (Exception ex) {
			System.out.println("insert failed");
		}
	}

	@RequestMapping(value = "updatePolice.do", produces = "application/json;charset=UTF-8")
	public void updatePolice() throws Exception {
		try {
			Police police = new Police();
			police.setId(1);
			police.setName("张五");
			police.setGpsId(5);
			police.setGpsName("95手机定位");
			police.setMobile("13568865179");
			police.setIntercomGroup("mmmm");
			police.setIntercomPerson("3333");
			police.setIdcardno("512301198506234875");
			police.setOrgId(2);
			police.setMobileShort("6179");
			police.setNumber("51007818");
			police.setTypeId(3);
			police.setTitle("副主任");
			System.out.println(policeService.updateByPrimaryKey(police));
		} catch (Exception ex) {
			System.out.println("update failed");
		}
	}

	@RequestMapping(value = "selectPolice.do", produces = "application/json;charset=UTF-8")
	public void selectPolice() throws Exception {
		try {
			Police police = policeService.selectByPrimaryKey(2);
			String name = "list is empty!";
			if (police != null) {
				name = police.getName();
			}
			System.out
					.println("total count is :" + 1 + " , First is : " + name);
		} catch (Exception ex) {
			System.out.println("select failed");
		}
	}

	@RequestMapping(value = "selectPoliceList.do", produces = "application/json;charset=UTF-8")
	public void selectPoliceList() throws Exception {
		try {
			List<Police> list = policeService.selectAll();
			String name = "list is empty!";
			int count = list.size();
			if (count > 0) {
				name = list.get(0).getName();
			}
			System.out.println("total count is :" + count + " , First is : "
					+ name);
		} catch (Exception ex) {
			System.out.println("select failed");
		}
	}
}
