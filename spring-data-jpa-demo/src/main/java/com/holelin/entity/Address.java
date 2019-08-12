package com.holelin.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "tab_address")
@Entity
public class Address {

	@GeneratedValue
	@Id
	private Integer id;
	private String province;
	private String city;


}
