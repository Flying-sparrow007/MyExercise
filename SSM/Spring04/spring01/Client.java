package com.gk.spring01;
/**
 * �ͻ���ɫ
 * @author LENOVO
 *
 */
//�ͻ�(Ҫ�ⷿ�ӵĶ���)
public class Client {
	public static void main(String[] args) {
		//�ͻ�ֱ���ҷ���
		/*Host host = new Host();
		host.rent();*/
		
		//�ͻ�ȥ�Ҵ���
		Host host = new Host();
		//����
		Proxy proxy = new Proxy();
		//����������
		proxy.setHost(host);
		//�����ⷿ��
		proxy.rent();
	}
}
