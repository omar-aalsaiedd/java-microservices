package com.auth.authservice.routes;

import com.auth.authservice.repository.UserRepository;
import com.auth.authservice.security.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class Router {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final LoadBalancerClient loadBalancerClient;

    @Bean
    RouterFunction<ServerResponse> HomepageRoute() {
        return GatewayRouterFunctions.route("homepage")
                .route(RequestPredicates.path("/api/transactions/"), this::handleTransactionRequest)
                .build();
    }

    @Bean
    RouterFunction<ServerResponse> DeleteTransactionRoute() {
        return GatewayRouterFunctions.route("delete_transaction")
                .route(RequestPredicates.DELETE("/api/transactions/{id}"), this::handleDeleteTransactionRequest)
                .build();
    }


    private ServerResponse handleDeleteTransactionRequest(ServerRequest request) throws Exception {
        HttpServletRequest servletRequest = request.servletRequest();

        ServerRequest modifiedRequest = ServerRequest.from(request)
                .header("X-User-Id", extractDataFromAuthorization(servletRequest.getHeader("Authorization")))
                .build();

        return HandlerFunctions.http(resolveServiceUrl("FINTRACKER")).handle(modifiedRequest);
    }

    // handlers

    private ServerResponse handleTransactionRequest(ServerRequest request) throws Exception {
        HttpServletRequest servletRequest = request.servletRequest();


        ServerRequest modifiedRequest = ServerRequest.from(request)
                .header("X-User-Id", extractDataFromAuthorization(servletRequest.getHeader("Authorization")))
                .build();

        // Forward the request to another service
        return HandlerFunctions.http(resolveServiceUrl("FINTRACKER")).handle(modifiedRequest);
    }


    private String extractDataFromAuthorization(String authHeader) {
        if (authHeader.startsWith("Bearer ")) {
            return userRepository.findByUsername(jwtTokenProvider.getUsernameFromToken(authHeader.substring(7))).get().getId().toString();
        }
        return authHeader;
    }

    private String resolveServiceUrl(String serviceName) {
        ServiceInstance instance = loadBalancerClient.choose(serviceName);
        if (instance == null) {
            throw new IllegalStateException("No instances found for service: " + serviceName);
        }
        return instance.getUri().toString();
    }
}
