package com.app.nutritionist.utils;

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.app.nutritionist.exceptions.JwtTokenMalformedException;
import com.app.nutritionist.exceptions.JwtTokenMissingException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtil {

	private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);

	@Value("${jwt.secret}")
	private String jwtSecret;

	public Claims getClaims(final String token) {
		try {
			// Claims body =
			// Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
			Claims body = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(jwtSecret))
					.parseClaimsJws(token).getBody();
			return body;
		} catch (Exception e) {
			log.error(e.getMessage() + " => " + e);
		}
		return null;
	}

	public void validateToken(final String token) throws JwtTokenMalformedException, JwtTokenMissingException {
		try {
			// Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(jwtSecret)).parseClaimsJws(token);
		} catch (SignatureException ex) {
			throw new JwtTokenMalformedException("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			throw new JwtTokenMalformedException("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			throw new JwtTokenMalformedException("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			throw new JwtTokenMalformedException("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			throw new JwtTokenMissingException("JWT claims string is empty.");
		}
	}

}