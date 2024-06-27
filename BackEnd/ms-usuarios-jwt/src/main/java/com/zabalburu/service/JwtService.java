package com.zabalburu.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JwtService {

	private SecretKey key;
	private static final long EXPERATION_TIME = 86400000;  //24 hours
	
	
	public JwtService() {
		String secretKey = "c34a407057dbad499e4a8a23f955b473bb4b1651c85aee112024f63173e88240";
		byte [] keyBytes = Base64.getDecoder().decode(secretKey.getBytes(StandardCharsets.UTF_8));
		this.key = new SecretKeySpec(keyBytes, "hmacSHA256");
	}
	
	
	public String generateToken(UserDetails userDetails) {
		long currentTimeMillis = System.currentTimeMillis();
		return Jwts
				.builder()	
				.subject(userDetails.getUsername())
				.issuedAt(new Date (currentTimeMillis))
				.expiration(new Date(currentTimeMillis + EXPERATION_TIME))
				.signWith(key)
				.compact();		
	}
	
	
	public String generetRefreshToken(Map<String, Object> claims, UserDetails userDetails) {
		return Jwts
				.builder()
				.claims(claims)
				.subject(userDetails.getUsername())
				.issuedAt(new Date (System.currentTimeMillis()))
				.expiration(new Date (System.currentTimeMillis() + EXPERATION_TIME * 7)) // 24H
				.signWith(key)
				.compact();
	}
	
	
	public String extractUsuario(String token) {
		// TODO Auto-generated method stub
		return extractClaims(token, Claims::getSubject);
	}
	
	
	public  <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
		return claimsResolver.apply(Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload());
	}
	
	
	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String usuario = extractUsuario(token);
		return (usuario.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	private boolean isTokenExpired(String token) {
		// TODO Auto-generated method stub
		return extractClaims(token, Claims::getExpiration).before(new Date());
	}
	
	
	
	
}
