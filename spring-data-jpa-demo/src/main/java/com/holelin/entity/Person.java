package com.holelin.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * ClassName: Person
 *
 * @author HoleLin
 * @version 1.0
 * @date 2019/8/10
 */
@Data
@Entity
@Table(name = "tab_person")
public class Person {
	@Id
	@GeneratedValue
	private Integer id;
	private String lastName;
	private String email;
	private Date birth;
	@ManyToOne
	private Address address;
	private Integer addressId;
}
