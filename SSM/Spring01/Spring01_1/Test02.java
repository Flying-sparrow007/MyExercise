package com.gk.spring;
/**
 * �ͻ���
 * @author LENOVO
 *
 */
public class Test02 {
	public static void main(String[] args) {
		Person p = new Person();
		//è
		//Cat cat = new Cat();
		//��
		Dog dog = new Dog();
		//ʹ��set����ע����Ϣ
		p.setAnm(dog);
		p.action();
	}
}
