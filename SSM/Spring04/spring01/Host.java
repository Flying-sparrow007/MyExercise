package com.gk.spring01;
/**
 * 真实角色
 * @author LENOVO
 *
 */
//房东
public class Host implements Rent {

	@Override
	public void rent() {
		System.out.println("房东要租房子...");
	}
	
}
