package com.holelin.spring.lifetime.entity;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * ClassName: Cat
 *
 * @author HoleLin
 * @version 1.0
 * @date 2019/8/17
 */
@Component
public class Cat implements InitializingBean, DisposableBean {
	public Cat() {
		System.out.println("-----------");
		System.out.println("Cat开始构造");
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("Cat开始初始化");
	}
	@Override
	public void destroy() throws Exception {
		System.out.println("Cat开始销毁");
		System.out.println("-----------");
	}
}
