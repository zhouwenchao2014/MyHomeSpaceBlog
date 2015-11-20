package com.zhou.blog.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhou.blog.model.SystemConfigDo;
import com.zhou.blog.service.SystemConfigService;
import com.zhou.blog.utils.Utils;

public class SystemConfigTest{
	
	private SystemConfigService systemConfigService;
	
	@Before
    public void before(){                                                                   
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:conf/spring.xml"
                ,"classpath:conf/spring-mybatis.xml"});
        systemConfigService = (SystemConfigService) context.getBean("systemConfigServiceImpl");
    }
     
	
	@Test
	public void add(){
		SystemConfigDo systemConfigDo=new SystemConfigDo(Utils.newUUID(),"ES端口","ESPort","9300","ES端口号");
		systemConfigService.add(systemConfigDo);
		
	}
}
