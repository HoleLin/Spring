package com.holelin.spring.registercomponent.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * ClassName: MyImportSelector
 *
 * @author HoleLin
 * @version 1.0
 * @date 2019/8/17
 */

public class MyImportSelector implements ImportSelector {
	/**
	 *
	 * @param importingClassMetadata 当前标注了@Import注解的所有注解信息
	 * @return  返回值就是导入到容器中组件的全类名
	 */
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		return new String[]{"com.holelin.spring.registercomponent.entity.Man"};
	}
}
