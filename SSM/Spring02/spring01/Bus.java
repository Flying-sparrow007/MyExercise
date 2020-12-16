package com.gk.spring01;

import java.io.Serializable;

public class Bus implements Serializable {

	public Bus() {
		System.out.println("这是Bus的无参构造方法!");
	}
	
	public void run(){
		System.out.println("Bus在跑!");
	}
	
	public void destroy(){
		System.out.println("Bus对象被销毁!");
	}
}
