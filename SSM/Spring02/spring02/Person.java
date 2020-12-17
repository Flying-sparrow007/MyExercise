package com.gk.spring02;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * ��
 * @Autowired ��Spring��һ�����,��Ĭ����������������صĶ���
 * @Resource ����byName,���û���ҵ�,��������
 * @author LENOVO
 *
 */
public class Person {
	@Resource
	private Dog dog;
	@Autowired
	private Cat cat;
	
	public Person() {
		super();
		System.out.println("Person���޲ι��췽��!");
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
