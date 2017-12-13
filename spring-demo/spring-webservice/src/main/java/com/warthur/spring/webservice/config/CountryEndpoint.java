package com.warthur.spring.webservice.config;

import com.warthur.spring.webservice.domain.GetCountryRequest;
import com.warthur.spring.webservice.domain.GetCountryResponse;
import com.warthur.spring.webservice.domain.SetCountryRequest;
import com.warthur.spring.webservice.domain.SetCountryResponse;
import com.warthur.spring.webservice.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * webservice endpoint class
 */
@Endpoint
public class CountryEndpoint {
	// 与wsdl文件保持一直
	private static final String NAMESPACE_URI = "http://spring.warthur.com/webservice/domain";

	@Autowired
	private CountryRepository countryRepository;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
	@ResponsePayload
	public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
		GetCountryResponse response = new GetCountryResponse();
		response.setCountry(countryRepository.findCountry(request.getName()));

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "setCountryRequest")
	@ResponsePayload
	public SetCountryResponse setCountry(@RequestPayload SetCountryRequest request) {
		SetCountryResponse response = new SetCountryResponse();
		response.setCountry(countryRepository.updateCountry(request.getName(), request.getNewName()));

		return response;
	}
}
