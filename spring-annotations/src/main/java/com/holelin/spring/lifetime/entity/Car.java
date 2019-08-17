package com.holelin.spring.lifetime.entity;

/**
 * ClassName: Car
 *
 * @author HoleLin
 * @version 1.0
 * @date 2019/8/17
 */

public class Car {
	public Car() {
		System.out.println("-----------");
		System.out.println("Car 构造器");
	}

	public void init() {
		System.out.println("Car init");
	}

	public void destroy() {
		System.out.println("Car destroy");
		System.out.println("-----------");
	}

}
