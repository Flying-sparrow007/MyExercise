package com.gk.spring01;

import java.io.Serializable;

public class Bus implements Serializable {

	public Bus() {
		System.out.println("����Bus���޲ι��췽��!");
	}
	
	public void run(){
		System.out.println("Bus����!");
	}
	
	public void destroy(){
		System.out.println("Bus��������!");
	}
}
