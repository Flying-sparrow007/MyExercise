package com.gk.spring01;
/**
 * 客户角色
 * @author LENOVO
 *
 */
//客户(要租房子的对象)
public class Client {
	public static void main(String[] args) {
		//客户直接找房东
		/*Host host = new Host();
		host.rent();*/
		
		//客户去找代理
		Host host = new Host();
		//代理
		Proxy proxy = new Proxy();
		//代理房东对象
		proxy.setHost(host);
		//代理租房子
		proxy.rent();
	}
}
