package com.gk.spring;
/**
 * �ͻ���
 * @author LENOVO
 *
 */
public class Test01 {
	public static void main(String[] args) {
		/*
		 * ������֮ǰ��ҵ����,�û�������ܻ�Ӱ������ԭ���Ĵ���,������Ҫ�����û�����ȥ�޸Ĵ���,
		 * ����һ������Ա�������ǳ��Ӵ�,�޸�һ�εĳɱ����۷ǳ�����
		 * ���ǿ���ʹ��һ��set����,��ע����صĶ���,��ôһ�������˸����Եı仯
		 * 
		 * ֮ǰ����Ա��������������,����Ȩ�ڳ���Ա����,
		 * ʹ����setע��֮��,����Ա���پ�������Ȩ,���Ǳ���˱������յĶ���,
		 * ���������Spring����е�IOC(���Ʒ�ת)���˼��
		 */
		Man man = new Man();
		//���
		//Saw saw = new Saw();
		
		//����
		Aex aex = new Aex();
		//ʹ��set����ע����Ϣ
		man.setTool(aex);
		man.work();
	}
}