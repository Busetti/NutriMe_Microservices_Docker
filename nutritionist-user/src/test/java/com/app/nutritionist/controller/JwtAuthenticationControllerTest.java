package com.app.nutritionist.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.app.nutritionist.dto.LoginDto;
import com.app.nutritionist.security.JwtAuthenticationController;
import com.app.nutritionist.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class JwtAuthenticationControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
	private AuthenticationManager authenticationManager;
	
	@InjectMocks
	private JwtAuthenticationController jwtAuthController;
	private LoginDto userLogin;
	
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(jwtAuthController).build();
        userLogin = new LoginDto();
        userLogin.setEmailId("abcd@gmail.com");
        userLogin.setPassword("12345678");
}
	
	@AfterEach
    public void tearDown() {
		userLogin = null;
    }
	
	@Test
	public void generateAuthenticationToken() throws Exception {
		Authentication user = null;
		when(authenticationManager.authenticate(any())).thenReturn(user);
		mockMvc.perform(
				post("/app/v1/user/authenticate").contentType(MediaType.APPLICATION_JSON).content(asJsonString(userLogin)))
				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
	}
	
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
