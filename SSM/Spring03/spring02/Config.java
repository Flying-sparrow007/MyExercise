package com.gk.spring02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * �����ļ���
 * @Configuration ��ע���������ļ����ע��,Ҳ��@Componentͨ�����
 * @Configuration �൱�������ļ��е�<beans></beans>
 * @author LENOVO
 *
 */
@Configuration
//ɨ���ע��
@ComponentScan("com.gk.spring02")
@Import(Config2.class)
public class Config {

	/*
	 * @Bean ���������ļ��е�<bean>��ǩ
	 * bean��id���Ƿ���������
	 * class���Ƿ���ֵ�Ķ���
	 */
	/*@Bean
	public User getUser(){
		return new User();
	}*/
}
