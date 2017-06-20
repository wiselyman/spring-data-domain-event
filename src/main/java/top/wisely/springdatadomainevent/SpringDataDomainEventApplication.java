package top.wisely.springdatadomainevent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringDataDomainEventApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataDomainEventApplication.class, args);
	}
}
