* 在Spring配置文件中配置相关信息
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context" xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns:jpa="http://www.springframework.org/schema/data/jpa"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd http://www.springframework.org/schema/data/jpa http://www.springframework.org/schema/data/jpa/spring-jpa.xsd">

    <context:property-placeholder location="classpath:jdbc.properties"/>
    <!-- 1. 配置数据源-->
    <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
        <property name="user" value="${jdbc.user}"/>
        <property name="password" value="${jdbc.password}"/>
        <property name="driverClass" value="${jdbc.driverClass}"/>
        <property name="jdbcUrl" value="${jdbc.jdbcUrl}"/>
    </bean>
    <!-- 2. 配置JPA的EntityManagerFactory -->
    <bean id="entityManagerFactory"
          class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <property name="jpaVendorAdapter">
            <bean class="org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter"/>
        </property>
        <property name="packagesToScan" value="com.holelin.*"/>
        <property name="jpaProperties">
            <props>
                <!-- 二级缓存相关 -->
                <!--
                <prop key="hibernate.cache.region.factory_class">org.hibernate.cache.ehcache.EhCacheRegionFactory</prop>
                <prop key="net.sf.ehcache.configurationResourceName">ehcache-hibernate.xml</prop>
                -->
                <!-- 生成的数据表的列的映射策略 -->
                <prop key="hibernate.ejb.naming_strategy">org.hibernate.cfg.ImprovedNamingStrategy</prop>
                <!-- hibernate 基本属性 -->
                <prop key="hibernate.dialect">org.hibernate.dialect.MySQL5InnoDBDialect</prop>
                <prop key="hibernate.show_sql">true</prop>
                <prop key="hibernate.format_sql">true</prop>
                <prop key="hibernate.hbm2ddl.auto">update</prop>

            </props>
        </property>
    </bean>
    <!-- 3. 配置事务管理器-->
    <bean id="transactionManager" class="org.springframework.orm.jpa.JpaTransactionManager">
        <property name="entityManagerFactory" ref="entityManagerFactory"/>
    </bean>
    <!-- 4. 配置支持注解的事务-->
    <tx:annotation-driven transaction-manager="transactionManager"/>
    <!-- Spring整合JPA结束-->
    <!-- 5. 配置SpringData-->
    <!-- 加入jpa的命名空间-->
    <!-- base-package:扫描Repository Bean所在的package-->
    <jpa:repositories base-package="com.holelin.*"
                      entity-manager-factory-ref="entityManagerFactory"/>
</beans>
```
* 创建IPersonRepository接口继承Repository,并指明泛型实体类为Person,主键类型为Integer
```java
  public interface IPersonRepository extends Repository<Person, Integer> {

  }  
```
* Repository的子接口
    * CrudRepository:提供了常见的增删改查操作
    * PagingAndSortingRepository:提供了分页与排序操作
    * JpaRepository:提供了JPA相关功能
* JpaSpecificationExecutor接口
    * 实现了JPA Criteria查询相关操作
    > Criteria 查询是以元模型的概念为基础的，元模型是为具体持久化单元的受管实体定义的，这些实体可以是实体类，嵌入类或者映射的父类。
    >
    > 
    >
    > CriteriaQuery接口：代表一个specific的顶层查询对象，它包含着查询的各个部分，比如：select 、from、where、group by、order by等注意：CriteriaQuery对象只对实体类型或嵌入式类型的Criteria查询起作用。
    >
    > 
    >
    > Root接口：代表Criteria查询的根对象，Criteria查询的查询根定义了实体类型，能为将来导航获得想要的结果，它与SQL查询中的FROM子句类似。
    >
    >    ​    1：Root实例是类型化的，且定义了查询的FROM子句中能够出现的类型。
    >    ​    2：查询根实例能通过传入一个实体类型给 AbstractQuery.from方法获得。
    >    ​    3：Criteria查询，可以有多个查询根。
    >    ​    4：AbstractQuery是CriteriaQuery 接口的父类，它提供得到查询根的方法。
    >
    > 
    >
    > CriteriaBuilder接口：用来构建CritiaQuery的构建器对象Predicate：一个简单或复杂的谓词类型，其实就相当于条件或者是条件组合
    ```java
    	/**
    	 * 目标: 实现带查询条件的分页. id > 5 的条件
    	 * <p>
    	 * 调用 JpaSpecificationExecutor 的 
    	 * Page<T> findAll(Specification<T> spec, Pageable pageable);
    	 * Specification: 封装了 JPA Criteria 查询的查询条件
    	 * Pageable: 封装了请求分页的信息: 例如 pageNo, pageSize, Sort
    	 */
    	@Test
    	public void testJpaSpecificationExecutor() {
    		int pageNo = 3 - 1;
    		int pageSize = 5;
    		PageRequest pageable = new PageRequest(pageNo, pageSize);
    		//通常使用 Specification 的匿名内部类
    		Specification<Person> specification = new Specification<Person>() {
    			/**
    			 * @param *root: 代表查询的实体类.
    			 * @param query: 可以从中可到 Root 对象, 
    			 * 即告知 JPA Criteria 查询要查询哪一个实体类. 还可以
    			 * 来添加查询条件, 还可以结合 EntityManager 对象得到最终查询的 TypedQuery 对象.
    			 * @param *cb: CriteriaBuilder 对象. 用于创建 Criteria 相关对象的工厂. 
    			 * 当然可以从中获取到 Predicate 对象
    			 * @return: *Predicate 类型, 代表一个查询条件.
    			 */
    			@Override
    			public Predicate toPredicate(Root<Person> root,
    			                             CriteriaQuery<?> query, CriteriaBuilder cb) {
    				Path path = root.get("id");
    				Predicate predicate = cb.gt(path, 5);
    				return predicate;
    			}
    		};
    		Page<Person> page = mJpaRepository.findAll(specification, pageable);
    		System.out.println("总记录数: " + page.getTotalElements());
    		System.out.println("当前第几页: " + (page.getNumber() + 1));
    		System.out.println("总页数: " + page.getTotalPages());
    		System.out.println("当前页面的 List: " + page.getContent());
    		System.out.println("当前页面的记录数: " + page.getNumberOfElements());
    	}
    ```
    