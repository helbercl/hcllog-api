package com.hcllog.api.common;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*Essa anotação define que a classe é um componente spring 
 * que tem o objetivo de definir beanSpring.
 * Com isso a classe se torna um Bean do Spring podendo ser injetado pelo contrustor 
 * gerado pelo spring na classe que esta sendo usada. 
 * */
@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
