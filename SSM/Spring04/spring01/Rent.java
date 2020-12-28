package com.gk.spring01;
/**
 * 代理模式:它就是SpringAOP的底层代码
 * 代理模式分为两类:
 * 	1.静态代理
 * 		抽象角色:一般会使用接口或者抽象类来解决
 * 		真是角色:被代理的角色
 * 		代理角色:代理真实角色
 * 		客户角色:访问代理对象的人
 * 	2.动态代理
 * 		和静态代理角色一样,但是它的优缺点不一样
 * @author LENOVO
 *
 */
//抽象角色(租房)
public interface Rent {
	void rent();//租房
}
