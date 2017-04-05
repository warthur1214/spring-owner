package com.warthur.cloud.ribbon.rest;

import com.warthur.cloud.ribbon.service.ConsumerSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by admin on 2017/4/1.
 */
@RestController
public class ConsumerController {

	@Autowired
	private ConsumerSerivce consumerSerivce;

	@GetMapping("/add")
	public String add() {
		return consumerSerivce.addService();
	}
}
