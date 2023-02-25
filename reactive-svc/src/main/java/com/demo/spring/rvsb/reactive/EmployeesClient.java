package com.demo.spring.rvsb.reactive;

import com.demo.spring.rvsb.reactive.model.Employee;
import org.springframework.beans.factory.annotation.Value;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class EmployeesClient {

	@Value("${backend-svc.url}")
	String backendSvcUrl;
	private final WebClient client;

	// Spring Boot auto-configures a `WebClient.Builder` instance with nice defaults and customizations.
	// We can use it to create a dedicated `WebClient` for our component.
	public EmployeesClient(WebClient.Builder builder) {
		this.client = builder.build();
	}

	public Flux<Employee> getEmployees() {
		return this.client.get().uri(backendSvcUrl).accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(Employee.class);
	}

}
