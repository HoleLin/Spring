package com.holelin.spring.lifetime.entity;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * ClassName: Dog
 *
 * @author HoleLin
 * @version 1.0
 * @date 2019/8/17
 */
@Component
public class Dog {
	public Dog() {
		System.out.println("-----------");
		System.out.println("Dog Construct");
	}
	@PostConstruct
	public void init() {
		System.out.println("Dog init");
	}
	@PreDestroy
	public void destroy() {
		System.out.println("Dog destroy");
		System.out.println("-----------");
	}
}
