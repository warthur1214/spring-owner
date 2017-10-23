package com.warthur.demo.test.domain;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.security.PrivateKey;

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

	private User(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.age = builder.age;
	}

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

	public static class Builder {
		private long id;
		private String name;
		private Integer age;

		public User builer() {
			return new User(this);
		}

		public Builder setId(long id) {
			this.id = id;
			return this;
		}

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setAge(Integer age) {
			this.age = age;
			return this;
		}
	}

	public static void main(String[] args) {
		User user = new User.Builder().setId(1212).setAge(27).setName("warthur").builer();
	}
}
