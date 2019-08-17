package com.holelin.spring.test;

import com.holelin.spring.lifetime.config.LifeTimeConfig;
import com.holelin.spring.lifetime.entity.Car;
import com.holelin.spring.lifetime.entity.Cat;
import com.holelin.spring.lifetime.entity.Dog;
import com.holelin.spring.registercomponent.config.Config;
import com.holelin.spring.registercomponent.entity.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * ClassName: AnnotationTest
 *
 * @author HoleLin
 * @version 1.0
 * @date 2019/8/17
 */

public class AnnotationTest {
	AnnotationConfigApplicationContext ctx1;
	AnnotationConfigApplicationContext ctx2;

	@Before
	public void load() {
//		ctx1 = new AnnotationConfigApplicationContext(Config.class);
		ctx2 = new AnnotationConfigApplicationContext(LifeTimeConfig.class);
	}

	@Test
	public void testInit1() {
		Car bean = ctx2.getBean(Car.class);
	}

	@Test
	public void testInit2() {
		Cat bean = ctx2.getBean(Cat.class);
	}

	@Test
	public void test2() {
		String[] beanDefinitionNames = ctx1.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			System.out.println(beanDefinitionName);
		}
	}

	@Test
	public void testInit3() {
		Dog bean = ctx2.getBean(Dog.class);

	}

	@Test
	public void test() {
		Person bean = ctx1.getBean(Person.class);
		System.out.println(bean);
	}

	@After
	public void close() {
		ctx2.close();
	}
}
