package com.app.nutritionist.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.app.nutritionist.entity.User;
import com.app.nutritionist.entity.UserOtp;
import com.app.nutritionist.exceptions.EmailAddressNotValidException;
import com.app.nutritionist.exceptions.UserAlreadyExistException;
import com.app.nutritionist.exceptions.UserNotFoundException;
import com.app.nutritionist.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
	private UserService userService;
	
	@InjectMocks
	private UserController userController;
	private User user;
	private UserOtp userOtp;
	private Optional<User> user1;
	
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        user = new User();
        user.setFirstName("abcd");
        user.setLastName("efgh");
        user.setEmailId("abcd@gmail.com");
        user.setPassword("12345678");
        user.setAge(40);
        user.setHeight(170);
        user.setWeight(77);
        
        userOtp = new UserOtp();
        userOtp.setEmailId("abcd@gmail.com");
        userOtp.setOtp("123456");
        userOtp.setCreatedDate(LocalDateTime.now());
        
        user1 = Optional.of(user);
	}
	
	@AfterEach
    public void tearDown() {
		user = null;
    }
	
	@Test
	public void saveUser() throws Exception {
		when(userService.saveUser(any())).thenReturn(user);
		mockMvc.perform(
				post("/app/v1/user/signup").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
		verify(userService).saveUser(any());
	}
	
	@Test
	public void saveUser1() throws Exception {
		when(userService.saveUser(any())).thenThrow(UserAlreadyExistException.class);
		mockMvc.perform(
				post("/app/v1/user/signup").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
		verify(userService).saveUser(any());
	}
	
	@Test
	public void saveUser2() throws Exception {
		when(userService.saveUser(any())).thenThrow(EmailAddressNotValidException.class);
		mockMvc.perform(
				post("/app/v1/user/signup").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
		verify(userService).saveUser(any());
	}
	
	@Test
	void getUserInfo() throws Exception {
		when(userService.getUser(user.getEmailId())).thenReturn(user);
		mockMvc.perform(get("/app/v1/user/userinfo/abcd@gmail.com")).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
		verify(userService).getUser(any());
	}
	
	@Test
	void getUserInfo1() throws Exception {
		when(userService.getUser(user.getEmailId())).thenThrow(UserNotFoundException.class);
		mockMvc.perform(get("/app/v1/user/userinfo/abcd@gmail.com")).andExpect(MockMvcResultMatchers.status().isNotFound())
				.andDo(MockMvcResultHandlers.print());
		verify(userService).getUser(any());
	}
	
	@Test
	void getSendOtp() throws Exception {
		when(userService.generateOTP(user.getEmailId())).thenReturn("Success");
		mockMvc.perform(get("/app/v1/user/sendotp/abcd@gmail.com")).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
		verify(userService).generateOTP(any());
	}
	
	@Test
	void getSendOtp1() throws Exception {
		when(userService.generateOTP(user.getEmailId())).thenThrow(UserNotFoundException.class);
		mockMvc.perform(get("/app/v1/user/sendotp/abcd@gmail.com")).andExpect(MockMvcResultMatchers.status().isNotFound())
				.andDo(MockMvcResultHandlers.print());
		verify(userService).generateOTP(any());
	}
	
	@Test
	void getSendOtp2() throws Exception {
		when(userService.generateOTP(user.getEmailId())).thenThrow(Exception.class);
		mockMvc.perform(get("/app/v1/user/sendotp/abcd@gmail.com")).andExpect(MockMvcResultMatchers.status().isNotFound())
				.andDo(MockMvcResultHandlers.print());
		verify(userService).generateOTP(any());
	}
	
	@Test
	void getValidateOtp() throws Exception {
		when(userService.validateOTP(user.getEmailId(),userOtp.getOtp())).thenReturn("Success");
		mockMvc.perform(get("/app/v1/user/validateotp/?email=abcd@gmail.com&otp=123456")).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
		verify(userService).validateOTP(any(),any());
	}
	
	@Test
	void getValidateOtp1() throws Exception {
		when(userService.validateOTP(user.getEmailId(),userOtp.getOtp())).thenThrow(UserNotFoundException.class);
		mockMvc.perform(get("/app/v1/user/validateotp/?email=abcd@gmail.com&otp=123456")).andExpect(MockMvcResultMatchers.status().isNotFound())
				.andDo(MockMvcResultHandlers.print());
		verify(userService).validateOTP(any(),any());
	}
	 
	@Test
	public void updateUserInfo() throws Exception {
		when(userService.updateUserInfo(any())).thenReturn(user);
		mockMvc.perform(
				post("/app/v1/user/updateuserinfo").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
		verify(userService).updateUserInfo(any());
	}
	
	@Test
	public void updateUserInfo1() throws Exception {
		when(userService.updateUserInfo(any())).thenThrow(Exception.class);
		mockMvc.perform(
				post("/app/v1/user/updateuserinfo").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
		verify(userService).updateUserInfo(any());
	}
	
	@Test
	public void updateUserPassword() throws Exception {
		when(userService.updateUserPassword(any())).thenReturn(user);
		mockMvc.perform(
				post("/app/v1/user/updateuserpassword").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
		verify(userService).updateUserPassword(any());
	}
	
	@Test
	public void updateUserPassword1() throws Exception {
		when(userService.updateUserPassword(any())).thenThrow(UserNotFoundException.class);
		mockMvc.perform(
				post("/app/v1/user/updateuserpassword").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());
		verify(userService).updateUserPassword(any());
	}
	
	@Test
	public void updateUserPassword2() throws Exception {
		when(userService.updateUserPassword(any())).thenThrow(Exception.class);
		mockMvc.perform(
				post("/app/v1/user/updateuserpassword").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
				.andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
		verify(userService).updateUserPassword(any());
	}
	
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
