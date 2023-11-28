package tech.noetzold.anPerformaticEcommerce;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import tech.noetzold.anPerformaticEcommerce.security.user.RegisterRequest;
import tech.noetzold.anPerformaticEcommerce.security.service.AuthenticationService;

import static tech.noetzold.anPerformaticEcommerce.security.user.Role.ADMIN;
import static tech.noetzold.anPerformaticEcommerce.security.user.Role.MANAGER;

@SpringBootApplication
@EnableRabbit
@EnableCaching
@EnableFeignClients(basePackages = {"tech.noetzold.anPerformaticEcommerce.client"})
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AnPerformaticEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnPerformaticEcommerceApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.firstname("Admin")
					.lastname("Admin")
					.email("admin@mail.com")
					.password("password")
					.role(ADMIN)
					.build();
			System.out.println("Admin token: " + service.register(admin).getAccessToken());

			var manager = RegisterRequest.builder()
					.firstname("Admin")
					.lastname("Admin")
					.email("manager@mail.com")
					.password("password")
					.role(MANAGER)
					.build();
			System.out.println("Manager token: " + service.register(manager).getAccessToken());

		};
	}

}
