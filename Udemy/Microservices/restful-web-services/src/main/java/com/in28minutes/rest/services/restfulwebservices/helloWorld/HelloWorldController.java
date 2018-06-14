package com.in28minutes.rest.services.restfulwebservices.helloWorld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(method=RequestMethod.GET, path="/hello-world1")
	public String helloWorld1() {
		return"Hello World !!!";
	}
	@GetMapping( path="/hello-world2")
	public String helloWorld2() {
		return"Hello World !!!";
	}
	@GetMapping( path="/hello-world3")
	public HelloWorldBean helloWorld3() {
		return new HelloWorldBean("Hello World !!!");
	}
	@GetMapping( path="/hello-world4/{name}")
	public HelloWorldBean helloWorld4(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello %s",name));
	}
	
	@GetMapping( path="/hello-world-internationalized")
	public String helloWorld5(@RequestHeader(name="Accept-Language",required=false) Locale locale) {
		return messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocaleContext().getLocale());
	}
	
}
