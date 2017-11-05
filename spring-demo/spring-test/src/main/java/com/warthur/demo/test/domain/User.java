package com.warthur.demo.test.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by admin on 2017/3/28.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@JSONField(name = "user_id")
	private Long id;
	@JSONField(name = "user_name")
	private String name;
	@JSONField(name = "user_age")
	private Integer age;
}
