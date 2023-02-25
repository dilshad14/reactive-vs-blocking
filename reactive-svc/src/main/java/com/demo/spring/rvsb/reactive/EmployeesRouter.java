package com.demo.spring.rvsb.reactive;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class EmployeesRouter {

	@Bean
	public RouterFunction<ServerResponse> route(EmployeesHandler employeesHandler) {

		return RouterFunctions
			.route(GET("/reactive/employees").and(accept(MediaType.APPLICATION_JSON)), employeesHandler::getEmployees);
	}
}
