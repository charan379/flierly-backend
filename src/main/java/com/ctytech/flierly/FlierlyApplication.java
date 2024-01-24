package com.ctytech.flierly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration(exclude = {
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
		org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class
})
@SpringBootApplication
public class FlierlyApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlierlyApplication.class, args);
	}

}
