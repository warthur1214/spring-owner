package com.warthur.cloud.eureka.feign.web;

import com.warthur.cloud.eureka.feign.dao.FeignConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by admin on 2017/4/1.
 */
@RestController
public class ConsumerController {

	@Autowired
	private FeignConsumer feignConsumer;

	@GetMapping("/add")
	public Integer add() {
		return feignConsumer.add(20, 20);
	}
}
