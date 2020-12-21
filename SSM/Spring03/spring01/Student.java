package com.gk.spring01;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @Component(value="xx")	xx���޸�bean��id Ч����ͬ��@Component("xx")
 * @Component ��Spring����������bean��id���Ըö���������дСд��ʼ,
 * ����: ������:Student ��Spring���������bean��id��student
 * @author LENOVO
 *
 */
@Component("stu")
//�ӳټ���(������)
//@Lazy(true)
//������
@Scope("prototype")
public class Student {
	private String name;
	
	public Student() {
		System.out.println("Student���޲ι��췽��!");
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
