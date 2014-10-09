package com.tianyi.drs.duty.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tianyi.drs.duty.service.PoliceManService;

public class ServiceFactory {
	
	private static PoliceManService policeService;
	
	public static PoliceManService getPoliceServiceInit(){                                                                   
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:properties/spring.xml"
                ,"classpath:properties/spring-mybatis.xml"});
        policeService = (PoliceManService) context.getBean("policemanServiceImpl");
        return policeService;
    }
}
