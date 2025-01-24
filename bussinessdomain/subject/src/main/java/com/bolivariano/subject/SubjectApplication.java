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

import java.util.List;

@EnableDiscoveryClient
@SpringBootApplication
public class SubjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubjectApplication.class, args);
	}
	@Bean
	public CommandLineRunner loadData(SubjectRepository repository) {
		return (args) -> {
			repository.saveAll(List.of(
					new SubjectCollege(null, "Matemáticas"),
					new SubjectCollege(null, "Español"),
					new SubjectCollege(null, "Ciencias Naturales"),
					new SubjectCollege(null, "Ciencias Sociales"),
					new SubjectCollege(null, "Inglés"),
					new SubjectCollege(null, "Ética"),
					new SubjectCollege(null, "Educación Física"),
					new SubjectCollege(null, "Arte"),
					new SubjectCollege(null, "Tecnología e Informática")
			));
		};
	}


	@Bean
	@LoadBalanced
	public WebClient.Builder loadBalancedWebClientBuilder() {
		return WebClient.builder();
	}
           

}
