package com.gk.spring;
/**
 * ��
 * @author LENOVO
 *
 */
public class Person {
	private String name;
	private Anm anm;
	
	public Person() {
		super();
		this.name = "����";
	}

	public Anm getAnm() {
		return anm;
	}

	public void setAnm(Anm anm) {
		this.anm = anm;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", anm=" + anm + "]";
	}
	
	public void action(){
		System.out.println(name + "������" + anm.yell() + "������!");
	}
}
