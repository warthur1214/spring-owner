package com.warthur.spring.webservice.controller;

import com.warthur.spring.webservice.domain.GetCountryResponse;
import com.warthur.spring.webservice.service.WsClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

	@Autowired
	private WsClientService wsClientService;

	@GetMapping("/callws/{country}")
	public Object callWs(@PathVariable String country) {
		GetCountryResponse response = wsClientService.getCountry(country);
		return response.getCountry();
	}
}
