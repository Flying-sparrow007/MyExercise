package com.gk.spring01;
/**
 * �����ɫ
 * @author LENOVO
 *
 */
//����
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
	
	//�ͻ�Ҫ������
	private void seeHouse(){
		System.out.println("�ͻ�Ҫ������!");
	}
	
	//�ɽ�
	private void deal(){
		System.out.println("�ɽ�!");
	}
	
}
