package com.gk.spring01;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 人
 * @Autowired 是Spring的一个组件,它默认以类型来查找相关的对象
 * @Resource 先找byName,如果没有找到,则找类型
 * @author LENOVO
 * 
 */
@Component
public class Person {
	@Resource
	private Dog dog;
	@Resource
	private Cat cat;
	
	public Person() {
		super();
		System.out.println("Person的无参构造方法!");
	}

	public Dog getDog() {
		return dog;
	}

	@Autowired
	public void setDog(Dog dog) {
		this.dog = dog;
	}

	public Cat getCat() {
		return cat;
	}

	@Autowired
	public void setCat(Cat cat) {
		this.cat = cat;
	}

	@Override
	public String toString() {
		return "Person [dog=" + dog + ", cat=" + cat + "]";
	}

}
