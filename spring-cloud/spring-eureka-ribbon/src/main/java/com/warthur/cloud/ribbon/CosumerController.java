package com.warthur.cloud.ribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by admin on 2017/4/1.
 */
@RestController
public class CosumerController {

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/add")
	public String add() {
		return restTemplate.getForEntity("http://eureka-client-a/add?a=10&b=20", String.class).getBody();
	}
}
