package com.gmy.vo;

import java.util.Date;


/**
 * Emp entity. @author MyEclipse Persistence Tools
 */

public class Emp implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer sal;
	private Date birthday;
	private Integer age;
	private String name;

	// Constructors

	/** default constructor */
	public Emp() {
	}

	/** minimal constructor */
	public Emp(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Emp(Integer id, Integer sal, Date birthday, Integer age, String name) {
		this.id = id;
		this.sal = sal;
		this.birthday = birthday;
		this.age = age;
		this.name = name;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSal() {
		return this.sal;
	}

	public void setSal(Integer sal) {
		this.sal = sal;
	}
	public Date getBirthday() {
		return this.birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Emp [id=" + id + ", sal=" + sal + ", birthday=" + birthday
				+ ", age=" + age + ", name=" + name + "]";
	}
	
}