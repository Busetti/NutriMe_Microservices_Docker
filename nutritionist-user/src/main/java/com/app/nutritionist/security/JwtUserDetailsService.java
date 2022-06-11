package com.app.nutritionist.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.nutritionist.entity.User;
import com.app.nutritionist.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;

	/**
	 * @param userRepository
	 */
	@Autowired
	private JwtUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public JwtUserPrinciple loadUserByUsername(String username) {

		Optional<User> user = userRepository.findByEmailId(username);

		return new JwtUserPrinciple(user
				.orElseThrow(() -> new UsernameNotFoundException("Username " + username + " Not found in records")));

	}

}