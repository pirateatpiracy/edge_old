package com.in28minutes.rest.services.restfulwebservices.filter;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	@GetMapping("/filtering")
	public MappingJacksonValue retrieveSomeBean() {
		SomeBean someBean= new SomeBean("Value1", "Value2", "Value3");
		SimpleBeanPropertyFilter bPfilter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
		FilterProvider filter=new SimpleFilterProvider().addFilter("xyz", bPfilter);
		MappingJacksonValue mapping=new MappingJacksonValue(someBean);
		mapping.setFilters(filter);
		return mapping;
	}

	@GetMapping("/filtering-list")
	public MappingJacksonValue retrieveSomeBeanList() {
		List <SomeBean> list=Arrays.asList(new SomeBean("Value1a", "Value2a", "Value3a"),
				new SomeBean("Value1b", "Value2b", "Value3b"), new SomeBean("Value1c", "Value2c", "Value3c"));
		SimpleBeanPropertyFilter bPfilter = SimpleBeanPropertyFilter.filterOutAllExcept("field3","field2");
		FilterProvider filter=new SimpleFilterProvider().addFilter("xyz", bPfilter);
		MappingJacksonValue mapping=new MappingJacksonValue(list);
		mapping.setFilters(filter);
		return mapping;
	}

}
