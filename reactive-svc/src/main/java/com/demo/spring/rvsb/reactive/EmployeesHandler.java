package com.demo.spring.rvsb.reactive;

import com.demo.spring.rvsb.reactive.model.Employee;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
public class EmployeesHandler {

	private EmployeesClient eClient;
	public EmployeesHandler(EmployeesClient emplClient) {
		this.eClient = emplClient;
	}

	public Mono<ServerResponse> getEmployees(ServerRequest request) {
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromProducer(eClient.getEmployees(), Employee.class))
				.log();
	}
}
