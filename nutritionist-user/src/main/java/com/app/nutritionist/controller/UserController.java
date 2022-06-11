package com.app.nutritionist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.nutritionist.dto.LoginDto;
import com.app.nutritionist.entity.User;
import com.app.nutritionist.exceptions.EmailAddressNotValidException;
import com.app.nutritionist.exceptions.UserAlreadyExistException;
import com.app.nutritionist.exceptions.UserNotFoundException;
import com.app.nutritionist.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/app/v1/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(path = "/signup",  method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody User user){
		try {
			return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
		} catch (UserAlreadyExistException e) {
			return new ResponseEntity<>("User already exist", HttpStatus.CONFLICT);
		} catch (EmailAddressNotValidException e) {
			return new ResponseEntity<>("Email Address not valid", HttpStatus.CONFLICT);
		}
	}
	
	@RequestMapping(path = "/userinfo/{emailId}",  method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable String emailId){
		try {
			return new ResponseEntity<>(userService.getUser(emailId), HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
		}	
	}
	
	@RequestMapping(path = "/sendotp/{emailId}",  method = RequestMethod.GET)
	public ResponseEntity<?> sendOtp(@PathVariable String emailId){
		try {
			return new ResponseEntity<>(userService.generateOTP(emailId), HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>("Error occured in otp generation", HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(path = "/validateotp",  method = RequestMethod.GET)
	public ResponseEntity<?> validateOtp(@RequestParam(name = "email") String emailId, @RequestParam(name = "otp") String otp) {
		try {
			return new ResponseEntity<>(userService.validateOTP(emailId, otp), HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
		}	
	}
	
	@RequestMapping(path = "/updateuserinfo",  method = RequestMethod.POST)
	public ResponseEntity<?> updateUserInfo(@RequestBody User user){
		try {
			return new ResponseEntity<>(userService.updateUserInfo(user), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Error occured while updtaing user info", HttpStatus.CONFLICT);
		} 
	}
	
	@RequestMapping(path = "/updateuserpassword",  method = RequestMethod.POST)
	public ResponseEntity<?> updateUserPassword(@RequestBody User user){
		try {
			return new ResponseEntity<>(userService.updateUserPassword(user), HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>("Error occured while updtaing user password", HttpStatus.CONFLICT);
		} 
	}

}
