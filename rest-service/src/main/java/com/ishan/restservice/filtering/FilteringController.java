package com.ishan.restservice.filtering;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	@GetMapping(path="/filtering")
	public TestBean retrieveTestBean(){
		TestBean bean = new TestBean("value1","value2","value3");
		MappingJacksonValue value = new MappingJacksonValue(bean);
		FilterProvider filters = new SimpleFilterProvider();
		
		value.setFilters(filters);
		return bean;
	}
}
