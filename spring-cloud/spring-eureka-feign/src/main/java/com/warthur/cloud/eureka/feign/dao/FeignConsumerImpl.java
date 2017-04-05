package com.warthur.cloud.eureka.feign.dao;

import org.springframework.stereotype.Component;

/**
 * Created by admin on 2017/4/5.
 */
@Component
public class FeignConsumerImpl implements FeignConsumer {
	@Override
	public Integer add(Integer a, Integer b) {
		return -9999;
	}
}
