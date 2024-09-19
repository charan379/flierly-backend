package com.ctytech.flierly;

import com.ctytech.flierly.genric.repository.GenericRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@EnableAutoConfiguration(exclude = {
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
//		org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class
})
@SpringBootApplication
@PropertySources(value = {@PropertySource("classpath:messages.properties")})
@ComponentScan(
		basePackages = "com.ctytech.flierly",
		excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = GenericRepository.class)
)
public class FlierlyApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlierlyApplication.class, args);
	}

}
