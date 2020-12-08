package com.gk.spring;
/**
 * 客户端
 * @author LENOVO
 *
 */
public class Test02 {
	public static void main(String[] args) {
		Person p = new Person();
		//猫
		//Cat cat = new Cat();
		//狗
		Dog dog = new Dog();
		//使用set方法注入信息
		p.setAnm(dog);
		p.action();
	}
}
