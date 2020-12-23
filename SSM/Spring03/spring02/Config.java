package com.gk.spring02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 配置文件类
 * @Configuration 该注释是配置文件类的注解,也是@Component通用组件
 * @Configuration 相当于配置文件中的<beans></beans>
 * @author LENOVO
 *
 */
@Configuration
//扫描包注解
@ComponentScan("com.gk.spring02")
@Import(Config2.class)
public class Config {

	/*
	 * @Bean 代表配置文件中的<bean>标签
	 * bean的id就是方法的名字
	 * class就是返回值的对象
	 */
	/*@Bean
	public User getUser(){
		return new User();
	}*/
}
