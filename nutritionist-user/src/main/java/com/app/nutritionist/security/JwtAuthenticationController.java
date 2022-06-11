package com.app.nutritionist.security;

import java.security.InvalidParameterException;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.nutritionist.dto.LoginDto;

@RestController
@CrossOrigin
@RequestMapping("/app/v1/user")
public class JwtAuthenticationController {

	
	private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@PostMapping(value = "/authenticate")
	public ResponseEntity<JwtResponse> generateAuthenticationToken(@RequestBody LoginDto loginDto) throws Exception {

		// Read the HTTP Header from API
		String username = loginDto.getEmailId();
		String password = loginDto.getPassword();

		log.info("UserController getUserdetails {} : ", username);

		if (null == username || username.isEmpty()) {
			throw new InvalidParameterException("Not found the userid, Please contact the support team");
		}
		
		if(null == password || password.isEmpty()) {
			throw new UsernameNotFoundException("User not found, Please contact the support team");
		}
		
		authenticate(username, password);

		final JwtUserPrinciple userDetails = userDetailsService.loadUserByUsername(username);
		
		

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token, userDetails.getUsers()));
	}

	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
