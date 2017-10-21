package com.warthur.demo.test.domain;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by admin on 2017/3/28.
 */
public class User {

	@JSONField(name = "user_id")
	private long id;
	@JSONField(name = "user_name")
	private String name;
	@JSONField(name = "user_age")
	private Integer age;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}
