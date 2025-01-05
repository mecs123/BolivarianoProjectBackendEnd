package com.bolivariano.subject;

import com.bolivariano.subject.entities.SubjectCollege;
import com.bolivariano.subject.repository.SubjectRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SubjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubjectApplication.class, args);
	}
	@Bean
	public CommandLineRunner loadData(SubjectRepository repository) {
		return (args) -> {
			// Inserta datos predeterminados al arrancar la aplicación
			repository.save(new SubjectCollege(null, "Matemáticas"));
			repository.save(new SubjectCollege(null, "Español"));
			repository.save(new SubjectCollege(null, "Ciencias Naturales"));
			repository.save(new SubjectCollege(null, "Ciencias Sociales"));
			repository.save(new SubjectCollege(null, "Inglés"));
			repository.save(new SubjectCollege(null, "Ética"));
			repository.save(new SubjectCollege(null, "Educación Física"));
			repository.save(new SubjectCollege(null, "Arte"));
			repository.save(new SubjectCollege(null, "Tecnología e Informática"));

		};
	}

	@Bean
	@LoadBalanced
	public WebClient.Builder loadBalancedWebClientBuilder() {
		return WebClient.builder();
	}
           

}
