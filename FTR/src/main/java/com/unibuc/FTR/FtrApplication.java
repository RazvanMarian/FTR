package com.unibuc.FTR;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Football Team Roster",
				version = "1.0.0",
				description = "Java semester project",
				contact = @Contact(
						name = "Razvan Marian",
						email = "marianrazvan508@gmail.com"
				)
		)
)
public class FtrApplication {

	public static void main(String[] args) {
		SpringApplication.run(FtrApplication.class, args);
	}

}
