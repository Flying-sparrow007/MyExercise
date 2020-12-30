package com.gk.spring05;
/**
 * 动态代理:
 * 掌握两个类:
 * 1.Proxy:代理
 * 2.InvocationHandler:调用处理程序invoke()方法
 * 
 * 代理:
 * 	代理模式的好处:
 * 		可以使真实角色的操作更加纯粹,不用去关注一些公共的业务,公共业务也就是交给代理角色,
 * 		实现的业务分工,公共业务发生扩展的时候,方便集中管理.
 * 	静态代理缺点:一个真实角色有一个代理角色,代码量会翻倍,开发效率会降低
 * 
 * 动态代理:
 * 	动态代理和静态代理一样,动态代理的代理类是动态生成的,不是字节写的,
 * 	动态代理分两大类,基于接口的动态代理,基于类的动态代理基于接口
 * 	接口: JDK 动态代理	基于类cglib	Java字节码实现:Javasist
 * @author LENOVO
 *
 */
public interface UserService {
	void add();
	void delete();
	void update();
	void select();
}
