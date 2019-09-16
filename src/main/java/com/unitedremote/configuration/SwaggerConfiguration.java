package com.unitedremote.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

	@Bean
	public Docket releasApi() {
		return getBaseDocket();
	}

	private Docket getBaseDocket() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(getSwaggerPathsPredicate()).build();
	}

	
	@SuppressWarnings("unchecked")
	private Predicate<String> getSwaggerPathsPredicate() {
		return Predicates.or(PathSelectors.regex("/api/.*"));
	}

}
