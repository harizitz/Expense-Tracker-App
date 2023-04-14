package com.expensetracker.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {

	@Value("${secret_key}")
	private String key;


	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		String token = Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 3600000)) // Expiry time - 1 hour
				.signWith(SignatureAlgorithm.HS512, key).compact();
		return token;
	}

	public String getUsernameFromToken(String token) {
		return getClaimsFromToken(token, Claims::getSubject);
	}

	public boolean isExpired(String token) {
		Date date = getClaimsFromToken(token, Claims::getExpiration);
		return date.before(new Date());
	}

	private <T> T getClaimsFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
		return claimsResolver.apply(claims);
	}

}
