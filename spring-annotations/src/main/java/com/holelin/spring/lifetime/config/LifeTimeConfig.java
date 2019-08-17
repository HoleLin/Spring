package com.holelin.spring.lifetime.config;

import com.holelin.spring.lifetime.entity.Car;
import com.holelin.spring.lifetime.entity.Cat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: LifeTimeConfig
 *
 * @author HoleLin
 * @version 1.0
 * @date 2019/8/17
 */

@Configuration
@ComponentScan(basePackages = "com.holelin.spring.lifetime")
public class LifeTimeConfig {

	@Bean(initMethod = "init", destroyMethod = "destroy")
	public Car car() {
		return new Car();
	}
}
