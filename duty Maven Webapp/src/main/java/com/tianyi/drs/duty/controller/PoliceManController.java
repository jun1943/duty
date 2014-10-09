package com.tianyi.drs.duty.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tianyi.drs.duty.model.PoliceMan;
import com.tianyi.drs.duty.service.PoliceManService;
import com.tianyi.drs.duty.utils.ServiceFactory;

@Controller
@RequestMapping("/PoliceMan")
public class PoliceManController {
	
	private PoliceManService policeService = ServiceFactory.getPoliceServiceInit();

	@RequestMapping("index")
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "savePolice.do", produces = "application/json;charset=UTF-8")
	public void savePolice() throws Exception {
		try {
			PoliceMan policeman = new PoliceMan();
			policeman.setName("王五");
			policeman.setGpsId(5);
			policeman.setGpsName("一号手机定位");
			policeman.setMobile("13568865179");
			policeman.setIntercomGroup("ssss");
			policeman.setIntercomPerson("22222");
			policeman.setIdcardno("512301198506234875");
			policeman.setOrgId(2);
			policeman.setMobileShort("6179");
			policeman.setNumber("51007816");
			policeman.setPoliceTypeId(3);
			policeman.setTitle("科长");
			System.out.println(policeService.insert(policeman));
		} catch (Exception ex) {
			System.out.println("insert failed");
		}
	}
}