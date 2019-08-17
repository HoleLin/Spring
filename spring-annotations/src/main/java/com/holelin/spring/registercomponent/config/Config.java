package com.holelin.spring.registercomponent.config;

import com.holelin.spring.registercomponent.condition.MyImportBeanDefinitionRegistrar;
import com.holelin.spring.registercomponent.condition.MyImportSelector;
import com.holelin.spring.registercomponent.condition.WinCondition;
import com.holelin.spring.registercomponent.entity.Person;
import com.holelin.spring.registercomponent.entity.User;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;

/**
 * ClassName: Config
 *
 * @author HoleLin
 * @version 1.0
 * @date 2019/8/17
 */
@Configuration
@ComponentScan(value = "com.holelin.spring",
//		excludeFilters = {@Filter(type = FilterType.ANNOTATION, classes = {Service.class})})
//		includeFilters = {@Filter(type = FilterType.ANNOTATION, classes = {Service.class})},
//		includeFilters = {@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {PersonService.class})},
		includeFilters = {@Filter(type = FilterType.CUSTOM, classes = {MyTypeFilter.class})},
		useDefaultFilters = false)
@Import(value = {User.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class Config {

	@Lazy
	@Bean(value = "person")
	@Conditional(value = {WinCondition.class})
	public Person getPerson() {
		return new Person();
	}

	@Bean(value = "person2")
	public Person getPerson2() {
		return new Person();
	}
}
