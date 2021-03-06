package com.example.demo.config;

import static com.google.common.collect.Lists.newArrayList;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket api() {
		return new Docket (DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example.demo.rest"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getApiInfo())
				.useDefaultResponseMessages(false)
	            .globalResponseMessage(RequestMethod.GET, newArrayList(new ResponseMessageBuilder().code(500)
	                .message("500 message")
	                .responseModel(new ModelRef("Error"))
	                .build(),
	                new ResponseMessageBuilder().code(403)
	                    .message("Forbidden!!!!!")
	                    .build()));
	}
	
	private ApiInfo getApiInfo() {
		return new ApiInfo(
				"Order Service API",
				"Products API Description",
				"1.0",
				"http://codmind.com/terms",
				new Contact("ImaSoft", "https://ima.com", "ivan_mag@hotmail.com"),
				"LICENSE",
				"LICENSE URL",
				Collections.emptyList()
				);
	}
	
}
