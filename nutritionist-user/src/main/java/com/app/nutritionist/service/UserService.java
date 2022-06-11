package com.app.nutritionist.service;

import com.app.nutritionist.entity.User;
import com.app.nutritionist.exceptions.EmailAddressNotValidException;
import com.app.nutritionist.exceptions.UserAlreadyExistException;
import com.app.nutritionist.exceptions.UserNotFoundException;

public interface UserService {
	
	User saveUser(User user) throws UserAlreadyExistException, EmailAddressNotValidException;
	
	//User verifyUser(String emailId, String password) throws UserNotFoundException, Exception;
	
	User getUser(String emailId) throws UserNotFoundException;
	
	String generateOTP(String emailId) throws UserNotFoundException, Exception;
	
	String validateOTP(String emailId, String otp) throws UserNotFoundException;
	
	User updateUserInfo(User user) throws Exception;
	
	User updateUserPassword(User user) throws UserNotFoundException, Exception;

}
