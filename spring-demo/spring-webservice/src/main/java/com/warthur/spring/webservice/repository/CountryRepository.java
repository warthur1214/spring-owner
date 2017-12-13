package com.warthur.spring.webservice.repository;

import com.warthur.spring.webservice.domain.Country;
import com.warthur.spring.webservice.domain.Currency;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Repository
public class CountryRepository {

	private static final Map<String, Country> countries = new HashMap<>();

	@PostConstruct
	public void initData() {
		Country spain = new Country();
		spain.setName("Spain");
		spain.setCapital("Madrid");
		spain.setCurrency(Currency.EUR);
		spain.setPopulation(46704314);

		countries.put(spain.getName(), spain);

		Country poland = new Country();
		poland.setName("Poland");
		poland.setCapital("Warsaw");
		poland.setCurrency(Currency.PLN);
		poland.setPopulation(38186860);

		countries.put(poland.getName(), poland);

		Country uk = new Country();
		uk.setName("United Kingdom");
		uk.setCapital("London");
		uk.setCurrency(Currency.GBP);
		uk.setPopulation(63705000);

		countries.put(uk.getName(), uk);
	}

	public Country findCountry(String name) {
		Assert.notNull(name, "The country's name must not be null");
		return countries.get(name);
	}

	public Country updateCountry(String name, String newName) {
		Assert.notNull(name, "The country's name must not be null");

		Country country = countries.get(name);
		Assert.notNull(country, "The country must not be null");

		country.setName(newName);
		countries.remove(name);
		countries.put(newName, country);
		return country;
	}
}
