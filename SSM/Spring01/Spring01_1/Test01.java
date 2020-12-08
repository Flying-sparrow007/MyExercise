package com.gk.spring;
/**
 * 客户端
 * @author LENOVO
 *
 */
public class Test01 {
	public static void main(String[] args) {
		/*
		 * 在我们之前的业务中,用户需求可能会影响我们原来的代码,我们需要根据用户需求去修改代码,
		 * 这样一来程序员代码量非常庞大,修改一次的成本代价非常昂贵
		 * 我们可以使用一个set方法,来注入相关的对象,这么一来发生了革命性的变化
		 * 
		 * 之前程序员是主动创建对象,控制权在程序员手里,
		 * 使用了set注入之后,程序员不再具有主动权,而是变成了被动接收的对象,
		 * 这就类似于Spring框架中的IOC(控制反转)设计思想
		 */
		Man man = new Man();
		//电锯
		//Saw saw = new Saw();
		
		//斧子
		Aex aex = new Aex();
		//使用set方法注入信息
		man.setTool(aex);
		man.work();
	}
}
