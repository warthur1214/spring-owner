package com.warthur.demo.test.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by admin on 2017/3/28.
 */
@ConfigurationProperties(prefix = "com.warthur.properties")
@Repository
public class MyProperties {

	private String name;
	private int age;
	private String desc;
	private List<String> hands;

	public List<String> getHands() {
		return hands;
	}

	public void setHands(List<String> hands) {
		this.hands = hands;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "MyProperties{" +
				"name='" + name + '\'' +
				", age=" + age +
				", desc='" + desc + '\'' +
				", hands=" + hands +
				'}';
	}
}
