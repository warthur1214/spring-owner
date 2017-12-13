package com.warthur.spring.webservice.service;

import com.warthur.spring.webservice.domain.GetCountryRequest;
import com.warthur.spring.webservice.domain.GetCountryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.annotation.PostConstruct;

@Service
public class WsClientService extends WebServiceGatewaySupport {

	@Autowired
	private Jaxb2Marshaller jaxb2Marshaller;

	@PostConstruct
	public void init() {
		setDefaultUri("http://localhost:8080/ws/countries.wsdl");
		setMarshaller(jaxb2Marshaller);
		setUnmarshaller(jaxb2Marshaller);
	}

	public GetCountryResponse getCountry(String name) {
		GetCountryRequest request = new GetCountryRequest();
		request.setName(name);
		return (GetCountryResponse) getWebServiceTemplate().marshalSendAndReceive(
				"http://localhost:8080/ws/countries.xsd", request);
	}
}
