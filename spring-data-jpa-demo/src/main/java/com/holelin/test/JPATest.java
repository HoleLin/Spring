package com.holelin.test;

import com.holelin.dao.IPersonCrudRepository;
import com.holelin.dao.IPersonJpaRepository;
import com.holelin.dao.IPersonPagingAndSortingRepository;
import com.holelin.dao.IPersonRepository;
import com.holelin.entity.Person;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ClassName: JPATest
 *
 * @author HoleLin
 * @version 1.0
 * @date 2019/8/10
 */

public class JPATest {

	private ApplicationContext ctx;
	private IPersonRepository mPersonRepository;
	private IPersonPagingAndSortingRepository mPagingAndSortingRepository;
	private IPersonCrudRepository mCrudRepository;
	private IPersonJpaRepository mJpaRepository;

	{
		ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		mPersonRepository = ctx.getBean(IPersonRepository.class);
		mCrudRepository = ctx.getBean(IPersonCrudRepository.class);
		mPagingAndSortingRepository = ctx.getBean(IPersonPagingAndSortingRepository.class);
		mJpaRepository = ctx.getBean(IPersonJpaRepository.class);

	}

	@Test
	public void testSpring() throws SQLException {

		DataSource dataSource = ctx.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());
	}

	@Test
	public void testSpringData() {
		IPersonRepository personRepository = ctx.getBean(IPersonRepository.class);
		Person person = personRepository.getByLastName("Lin");
		List<Person> personList = personRepository.getByAddress_IdGreaterThan(1);
		List<Person> personList2 = personRepository.getByEmailEndingWithAndIdLessThan("Lin", 1);
		List<String> emailList = new ArrayList<>();
		emailList.add("Lin@163.com");
		List<Person> byEmailInAndBirthLessThan = personRepository.getByEmailInAndBirthLessThan(emailList, new Date());
		List<Person> admin = personRepository.getByLastNameStartingWithAndIdLessThan("Lin", 1);
		System.out.println(person);
	}

	@Test
	public void testJpa() {

	}

	@Test
	public void testPagingAndSortingRepository() {
		//pageNo 从 0 开始.
		int pageNo = 6 - 1;
		int pageSize = 5;
		//Pageable 接口通常使用的其 PageRequest 实现类. 其中封装了需要分页的信息
		//排序相关的. Sort 封装了排序的信息
		//Order 是具体针对于某一个属性进行升序还是降序.
		Order order1 = new Order(Direction.DESC, "id");
		Order order2 = new Order(Direction.ASC, "email");
		Sort sort = new Sort(order1, order2);

		PageRequest pageable = new PageRequest(pageNo, pageSize, sort);
		Page<Person> page = mPagingAndSortingRepository.findAll(pageable);

		System.out.println("总记录数: " + page.getTotalElements());
		System.out.println("当前第几页: " + (page.getNumber() + 1));
		System.out.println("总页数: " + page.getTotalPages());
		System.out.println("当前页面的 List: " + page.getContent());
		System.out.println("当前页面的记录数: " + page.getNumberOfElements());
	}

	/**
	 * 目标: 实现带查询条件的分页. id > 5 的条件
	 * <p>
	 * 调用 JpaSpecificationExecutor 的 Page<T> findAll(Specification<T> spec, Pageable pageable);
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
			 * @param query: 可以从中可到 Root 对象, 即告知 JPA Criteria 查询要查询哪一个实体类. 还可以
			 * 来添加查询条件, 还可以结合 EntityManager 对象得到最终查询的 TypedQuery 对象.
			 * @param *cb: CriteriaBuilder 对象. 用于创建 Criteria 相关对象的工厂. 当然可以从中获取到 Predicate 对象
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
}
