package com.bolivariano.course;

import com.bolivariano.course.entities.Course;
import com.bolivariano.course.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CourseApplication {
    
    

	public static void main(String[] args) {
		SpringApplication.run(CourseApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadCourseData(CourseRepository repository) {
		return (args) -> {
// Inserta datos predeterminados al arrancar la aplicación
			repository.save(new Course(null, "Primero"));
			repository.save(new Course(null, "Segundo"));
			repository.save(new Course(null, "Tercero"));
			repository.save(new Course(null, "Cuarto"));
			repository.save(new Course(null, "Quinto"));
			repository.save(new Course(null, "Sexto"));
			repository.save(new Course(null, "Séptimo"));
			repository.save(new Course(null, "Octavo"));
			repository.save(new Course(null, "Noveno"));
			repository.save(new Course(null, "Décimo"));
			repository.save(new Course(null, "Once"));

		};
	}

	@Bean
	@LoadBalanced
	public WebClient.Builder loadBalancedWebClientBuilder() {
		return WebClient.builder();
	}

       
           
}
