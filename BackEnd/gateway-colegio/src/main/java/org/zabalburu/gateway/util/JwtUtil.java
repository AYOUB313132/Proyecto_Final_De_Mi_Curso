package org.zabalburu.gateway.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;

@Component
public class JwtUtil {


	private SecretKey key;
	
	
	public JwtUtil() {
		String secretKey = "c34a407057dbad499e4a8a23f955b473bb4b1651c85aee112024f63173e88240";
		byte [] keyBytes = Base64.getDecoder().decode(secretKey.getBytes(StandardCharsets.UTF_8));
		this.key = new SecretKeySpec(keyBytes, "hmacSHA256");
	}
	
	
	public boolean isTokenValid(String token) {
		
		return Jwts
				.parserBuilder()
				.setSigningKey(key)
				.build()
				.isSigned(token);
	}
	

}
