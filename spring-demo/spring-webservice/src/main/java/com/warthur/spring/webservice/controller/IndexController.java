package com.warthur.spring.webservice.controller;

import com.warthur.spring.webservice.domain.GetCountryResponse;
import com.warthur.spring.webservice.service.WsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

	@Autowired
	private WsClient wsClient;

	@GetMapping("callws")
	public Object callWs() {
		GetCountryResponse response = wsClient.getCountry("hello");
		return response.getCountry();
	}
}
