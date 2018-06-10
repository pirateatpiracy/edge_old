package com.in28minutes.rest.services.restfulwebservices;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public static final Contact DEFAULT_CONTACT = new Contact("", "", "");
	 public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Awesome Api Documentation", "Awesome Api Documentation", "1.0", "urn:tos",
	          "Contact Email", "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");




	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT_API_INFO);
	}
	
}
