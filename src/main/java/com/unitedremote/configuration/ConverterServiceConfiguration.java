package com.unitedremote.configuration;

import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

@Configuration
public class ConverterServiceConfiguration {

	@Bean
	@Primary
	public ConversionService getConversionService(Set<Converter<?, ?>> converters) {
		ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
		bean.setConverters(converters);
		bean.afterPropertiesSet();
		return bean.getObject();
	}

}
