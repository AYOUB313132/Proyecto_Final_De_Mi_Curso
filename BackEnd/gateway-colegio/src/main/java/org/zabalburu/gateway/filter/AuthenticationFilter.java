package org.zabalburu.gateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.zabalburu.gateway.util.JwtUtil;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

	@Autowired
	private RouteValidator routeValidator;

	// @Autowired
	// private RestTemplate template;

	@Autowired
	private JwtUtil util;

	public AuthenticationFilter() {
		super(Config.class);
	}

	public static class Config {

	}

	@Override
	public GatewayFilter apply(Config config) {
		// TODO Auto-generated method stub
		return ((exchange, chain) -> {
			if (routeValidator.isSecured.test(exchange.getRequest())) {

				// Check header Token
				if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					throw new RuntimeException("Mising Authorization header");
				}

				String authHeaders = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
				if (authHeaders != null && authHeaders.startsWith("Bearer ")) {
					authHeaders = authHeaders.substring(7);
				}

				try {
					// Rest call to auth service
					// template.getForObject("http//spring-security-JWT/validate?token" +
					// authHeaders, String.class);
					util.isTokenValid(authHeaders);

				} catch (Exception e) {
					System.out.println("Invalid Access");
					throw new RuntimeException("Un authorization access to application");
				}
			}

			return chain.filter(exchange);
		});

	}
}
