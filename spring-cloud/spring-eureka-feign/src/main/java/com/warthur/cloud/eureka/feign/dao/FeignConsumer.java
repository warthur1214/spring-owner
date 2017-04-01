package com.warthur.cloud.eureka.feign.dao;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by admin on 2017/4/1.
 */
@FeignClient("eureka-client")
public interface FeignConsumer {

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	Integer add(@RequestParam("a") Integer a, @RequestParam("b") Integer b);
}
