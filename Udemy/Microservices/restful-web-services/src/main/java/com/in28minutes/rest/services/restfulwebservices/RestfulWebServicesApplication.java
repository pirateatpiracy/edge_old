package com.in28minutes.rest.services.restfulwebservices;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;



import jdk.internal.misc.Unsafe;

@SpringBootApplication
public class RestfulWebServicesApplication { 

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	}
	
	@Bean
	public LocaleResolver  localeResolver() {
		//SessionLocaleResolver localResolver =new SessionLocaleResolver();
		AcceptHeaderLocaleResolver localResolver =new AcceptHeaderLocaleResolver();
		localResolver.setDefaultLocale(Locale.US);
		return localResolver;
		
	}
	public static void disableWarning() {
	    System.err.close();
	   // System.setErr(System.out);
	}
/*	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource=new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}*/
}
