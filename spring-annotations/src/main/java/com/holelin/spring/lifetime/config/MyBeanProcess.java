package com.holelin.spring.lifetime.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.Aware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * ClassName: MyBeanProcess
 *
 * @author HoleLin
 * @version 1.0
 * @date 2019/8/17
 */

@Component
public class MyBeanProcess implements BeanPostProcessor {
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("postProcessBeforeInitialization" + beanName + "-->" + bean);
		return bean;
	}
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("postProcessAfterInitialization" + beanName + "-->" + bean);
		return bean;
	}
}
