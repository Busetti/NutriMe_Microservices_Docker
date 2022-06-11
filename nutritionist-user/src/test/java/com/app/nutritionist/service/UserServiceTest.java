package com.app.nutritionist.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.mail.Transport;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.app.nutritionist.entity.User;
import com.app.nutritionist.entity.UserOtp;
import com.app.nutritionist.exceptions.EmailAddressNotValidException;
import com.app.nutritionist.exceptions.UserAlreadyExistException;
import com.app.nutritionist.exceptions.UserNotFoundException;
import com.app.nutritionist.repository.UserOtpRepository;
import com.app.nutritionist.repository.UserRepository;


public class UserServiceTest {
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private UserOtpRepository userOtpRepository;
	
	@Mock
	private MongoTemplate mongoTemplate;
	
	@Mock
	private Transport transport;
	
	@InjectMocks
	private UserServiceImpl userService;
	private User user;
	private UserOtp userOtp;
	private Optional<User> user1;
	private List<User> userList;
	
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
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
	public void saveUserDetails() throws UserAlreadyExistException, EmailAddressNotValidException {
		when(userRepository.save(any())).thenReturn(user);
		assertEquals(user, userService.saveUser(user));
		verify(userRepository, times(1)).save(any());
	}
	
	@Test
	public void saveUserDetails1() throws UserAlreadyExistException, EmailAddressNotValidException {
		when(userRepository.findByEmailId(any())).thenReturn(user1);
		try {
			userService.saveUser(user);
		}
		catch(UserAlreadyExistException e) {}
	}
	
	@Test
	public void saveUserDetails2() throws UserAlreadyExistException, EmailAddressNotValidException {
		User user2 = user;
		user2.setEmailId("abcgmail.com");
		when(userRepository.save(any())).thenReturn(user2);
		try {
			userService.saveUser(user2);
		}
		catch(EmailAddressNotValidException e) {}
	}
	
	@Test
	public void getUser() throws UserNotFoundException {
		when(userRepository.findByEmailId("abcd@gmail.com")).thenReturn(Optional.of(user));
		User temp = userService.getUser(user.getEmailId());
        verify(userRepository, times(1)).findByEmailId("abcd@gmail.com");
	}
	
	@Test
	public void getUser1() throws UserNotFoundException {
		when(userRepository.findByEmailId("abcd@gmail.com")).thenReturn(Optional.empty());
		try {
			User temp = userService.getUser(user.getEmailId());
		}
		catch(UserNotFoundException e) { }
        verify(userRepository, times(1)).findByEmailId("abcd@gmail.com");
	}
	
	@Test
	public void generateOTP() throws Exception {
		when(userRepository.findByEmailId("abcd@gmail.com")).thenReturn(Optional.of(user));
		when(userOtpRepository.findByEmailId("abcd@gmail.com")).thenReturn(Optional.of(userOtp));
		Query query = new Query();
		query.addCriteria(Criteria.where("emailId").is("abcd@gmail.com"));
		Update update = new Update();
		update.set("otp", "123456");
		update.set("createdDate", LocalDateTime.now());
		when(mongoTemplate.updateFirst(query, update, UserOtp.class)).thenReturn(null);
		//Mockito.verify(mongoTemplate).updateFirst(query, update, UserOtp.class);
		String temp = userService.generateOTP(user.getEmailId());
        verify(userRepository, times(1)).findByEmailId("abcd@gmail.com");
        verify(userOtpRepository, times(1)).findByEmailId("abcd@gmail.com");
	}
	
	@Test
	public void generateOTP1() throws Exception {
		when(userRepository.findByEmailId("abcd@gmail.com")).thenReturn(Optional.of(user));
		when(userOtpRepository.findByEmailId("abcd@gmail.com")).thenReturn(Optional.empty());
		Query query = new Query();
		query.addCriteria(Criteria.where("emailId").is("abcd@gmail.com"));
		Update update = new Update();
		update.set("otp", "123456");
		update.set("createdDate", LocalDateTime.now());
		when(mongoTemplate.save(userOtp)).thenReturn(null);
		String temp = userService.generateOTP(user.getEmailId());
        verify(userRepository, times(1)).findByEmailId("abcd@gmail.com");
        verify(userOtpRepository, times(1)).findByEmailId("abcd@gmail.com");
	}
	
	@Test
	public void generateOTP3() throws Exception {
		when(userRepository.findByEmailId("abcd@gmail.com")).thenReturn(Optional.empty());
		try {
			String temp = userService.generateOTP(user.getEmailId());
		}
		catch(Exception e) {}
        verify(userRepository, times(1)).findByEmailId("abcd@gmail.com");
	}
	
	@Test
	public void validateOtp() throws Exception {
		when(userRepository.findByEmailId("abcd@gmail.com")).thenReturn(Optional.of(user));
		when(userOtpRepository.findByEmailId("abcd@gmail.com")).thenReturn(Optional.of(userOtp));
		try {
			String temp = userService.validateOTP("abcd@gmail.com", "123456");
		} catch (Exception e) {
		}
		verify(userRepository, times(1)).findByEmailId("abcd@gmail.com");
		verify(userOtpRepository, times(1)).findByEmailId("abcd@gmail.com");
	}
	
	@Test
	public void validateOtp1() throws Exception {
		when(userRepository.findByEmailId("abcd@gmail.com")).thenReturn(Optional.empty());
		try {
			String temp = userService.validateOTP("abcd@gmail.com", "123456");
		}
		catch(Exception e) {}
        verify(userRepository, times(1)).findByEmailId("abcd@gmail.com");
	}
	
	@Test
	public void updateUserInfo() throws Exception {
		Query query = new Query();
		query.addCriteria(Criteria.where("emailId").is(user.getEmailId()));
		Update update = new Update();
		update.set("firstName", user.getFirstName());
		update.set("lastName", user.getLastName());
		update.set("height", user.getHeight());
		update.set("weight", user.getWeight());
		update.set("age", user.getAge());
		update.set("modifiedDate", LocalDateTime.now());
		when(mongoTemplate.updateFirst(query, update, UserOtp.class)).thenReturn(null);
		userService.updateUserInfo(user);
	}
	
	@Test
	public void updateUserPassword() throws Exception {
		when(userRepository.findByEmailId("abcd@gmail.com")).thenReturn(Optional.of(user));
		Query query = new Query();
		query.addCriteria(Criteria.where("emailId").is(user.getEmailId()));
		Update update = new Update();
		update.set("password", user.getPassword());
		update.set("modifiedDate", LocalDateTime.now());
		when(mongoTemplate.updateFirst(query, update, UserOtp.class)).thenReturn(null);
		userService.updateUserPassword(user);
	}
	
	@Test
	public void updateUserPassword1() throws Exception {
		when(userRepository.findByEmailId("abcd@gmail.com")).thenReturn(Optional.empty());
		Query query = new Query();
		query.addCriteria(Criteria.where("emailId").is(user.getEmailId()));
		Update update = new Update();
		update.set("password", user.getPassword());
		update.set("modifiedDate", LocalDateTime.now());
		when(mongoTemplate.updateFirst(query, update, UserOtp.class)).thenReturn(null);
		try {
			userService.updateUserPassword(user);
		}
		catch(Exception e) {}
	}
	


}
