package org.kosta.pawPadSmell;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@ComponentScan
public class pawPadSmellApplication {

	public static void main(String[] args) {
		SpringApplication.run(pawPadSmellApplication.class, args);
	}

}
