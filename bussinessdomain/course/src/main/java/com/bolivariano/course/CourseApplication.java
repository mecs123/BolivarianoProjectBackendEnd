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

import java.util.List;

@EnableDiscoveryClient
@SpringBootApplication
public class CourseApplication {
    
    

	public static void main(String[] args) {
		SpringApplication.run(CourseApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadCourseData(CourseRepository repository) {
		return (args) -> {
			Course courso1 = Course.builder()
					.nameCourse("Primero")
					.build();
			Course courso2 = Course.builder()
					.nameCourse("Segundo")
					.build();
			Course courso3 = Course.builder()
					.nameCourse("Tercero")
					.build();
			Course courso4 = Course.builder()
					.nameCourse("Cuarto")
					.build();
			Course courso5 = Course.builder()
					.nameCourse("Quinto")
					.build();
			Course courso6 = Course.builder()
					.nameCourse("Sexto")
					.build();
			Course courso7 = Course.builder()
					.nameCourse("Séptimo")
					.build();
			Course courso8 = Course.builder()
					.nameCourse("Octavo")
					.build();
			Course courso9 = Course.builder()
					.nameCourse("Noveno")
					.build();
			Course courso10 = Course.builder()
					.nameCourse("Décimo")
					.build();
			Course courso11 = Course.builder()
					.nameCourse("Once")
					.build();

			// Guarda todos los cursos en la base de datos
			repository.saveAll(List.of(courso1, courso2, courso3, courso4, courso5, courso6, courso7, courso8, courso9, courso10, courso11));
		};
	}


	@Bean
	@LoadBalanced
	public WebClient.Builder loadBalancedWebClientBuilder() {
		return WebClient.builder();
	}

       
           
}
