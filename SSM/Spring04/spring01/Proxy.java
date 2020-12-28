package com.gk.spring01;
/**
 * 代理角色
 * @author LENOVO
 *
 */
//代理
public class Proxy implements Rent {
	private Host host;

	public void setHost(Host host) {
		this.host = host;
	}

	@Override
	public void rent() {
		host.rent();
		seeHouse();
		deal();
	}
	
	//客户要看房子
	private void seeHouse(){
		System.out.println("客户要看房子!");
	}
	
	//成交
	private void deal(){
		System.out.println("成交!");
	}
	
}
