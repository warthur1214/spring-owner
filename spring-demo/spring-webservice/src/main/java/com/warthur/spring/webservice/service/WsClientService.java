package com.warthur.spring.webservice.service;

import com.warthur.spring.webservice.domain.GetCountryRequest;
import com.warthur.spring.webservice.domain.GetCountryResponse;
import com.warthur.spring.webservice.domain.SetCountryRequest;
import com.warthur.spring.webservice.domain.SetCountryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.annotation.PostConstruct;

@Service
public class WsClientService extends WebServiceGatewaySupport {

	private static final String SOAP_URL = "http://localhost:8080/ws";

	@Autowired
	private Jaxb2Marshaller jaxb2Marshaller;

	@PostConstruct
	public void init() {
		setDefaultUri(SOAP_URL + "/countries.wsdl");
		setMarshaller(jaxb2Marshaller);
		setUnmarshaller(jaxb2Marshaller);
	}

	public GetCountryResponse getCountry(String name) {
		GetCountryRequest request = new GetCountryRequest();
		request.setName(name);
		return (GetCountryResponse) getWebServiceTemplate().marshalSendAndReceive(
				SOAP_URL + "/countries.xsd", request);
	}

	public SetCountryResponse setCountry(String name, String newName) {
		SetCountryRequest reqeust = new SetCountryRequest();
		reqeust.setName(name);
		reqeust.setNewName(newName);
		return (SetCountryResponse) getWebServiceTemplate().marshalSendAndReceive(SOAP_URL + "/countries.xsd", reqeust);
	}
}
