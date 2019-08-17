package com.holelin.spring.registercomponent.condition;

import com.holelin.spring.registercomponent.entity.BadMan;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * ClassName: MyImportBeanDefinitionRegistrar
 *
 * @author HoleLin
 * @version 1.0
 * @date 2019/8/17
 */

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
	/**
	 * @param importingClassMetadata 当前类的注解信息
	 * @param registry               BeanDefinition注册类
	 *                               把所有需要添加到容器中的Bean,
	 *                               调用BeanDefinitionRegistry#registerBeanDefinition手工注册进来
	 */
	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
	                                    BeanDefinitionRegistry registry) {
		boolean result = registry.containsBeanDefinition("com.holelin.spring.registercomponent.entity.Man");
		if (result) {
			RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(BadMan.class);
			registry.registerBeanDefinition("badMan", rootBeanDefinition);
		}
	}
}
