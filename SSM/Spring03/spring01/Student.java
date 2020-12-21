package com.gk.spring01;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @Component(value="xx")	xx是修改bean的id 效果等同于@Component("xx")
 * @Component 被Spring容器管理后的bean的id是以该对象名的首写小写开始,
 * 例如: 对象名:Student 被Spring容器管理后bean的id是student
 * @author LENOVO
 *
 */
@Component("stu")
//延迟加载(懒惰性)
//@Lazy(true)
//作用域
@Scope("prototype")
public class Student {
	private String name;
	
	public Student() {
		System.out.println("Student的无参构造方法!");
	}

	public String getName() {
		return name;
	}

	@Value("admin")
	public void setName(String name) {
		System.out.println("setName");
		this.name = name;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + "]";
	}
	
}
