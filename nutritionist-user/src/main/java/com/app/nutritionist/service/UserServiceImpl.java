package com.app.nutritionist.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.validator.routines.EmailValidator;
import org.bson.codecs.jsr310.LocalDateTimeCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.app.nutritionist.entity.User;
import com.app.nutritionist.entity.UserOtp;
import com.app.nutritionist.exceptions.EmailAddressNotValidException;
import com.app.nutritionist.exceptions.UserAlreadyExistException;
import com.app.nutritionist.exceptions.UserNotFoundException;
import com.app.nutritionist.repository.UserOtpRepository;
import com.app.nutritionist.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserOtpRepository userOtpRepository;

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public User saveUser(User user) throws UserAlreadyExistException, EmailAddressNotValidException {

		Optional<User> user1 = userRepository.findByEmailId(user.getEmailId());

		if (user1.isPresent()) {
			throw new UserAlreadyExistException("User already exist");
		}

		EmailValidator validator = EmailValidator.getInstance();

		if (!validator.isValid(user.getEmailId())) {
			throw new EmailAddressNotValidException("Email Address not valid");
		}

		user.setCreatedDate(LocalDateTime.now());
		user.setModifiedDate(LocalDateTime.now());

		return userRepository.save(user);
	}
	

	@Override
	public User getUser(String emailId) throws UserNotFoundException {

		Optional<User> user = userRepository.findByEmailId(emailId);

		if (!user.isPresent()) {
			throw new UserNotFoundException("User not found");
		}

		return user.get();
	}

	@Override
	public String generateOTP(String emailId) throws UserNotFoundException, Exception {

		User user = new User();
		String message = "Success";

		try {
			user = getUser(emailId);
		} catch (UserNotFoundException e) {
			message = "Failed";
			throw new UserNotFoundException("User not found");
		}

		int randomPin = (int) (Math.random() * 900000) + 100000;
		String otp = String.valueOf(randomPin);

		Optional<UserOtp> userOtp = userOtpRepository.findByEmailId(emailId);

		try {

			if (userOtp.isPresent()) {
				Query query = new Query();
				query.addCriteria(Criteria.where("emailId").is(emailId));
				Update update = new Update();
				update.set("otp", otp);
				update.set("createdDate", LocalDateTime.now());
				mongoTemplate.updateFirst(query, update, UserOtp.class);
			} else {
				UserOtp userOtp1 = new UserOtp();
				userOtp1.setEmailId(emailId);
				userOtp1.setOtp(otp);
				userOtp1.setCreatedDate(LocalDateTime.now());
				mongoTemplate.save(userOtp1);
			}

			sendMail(emailId, otp);

		} catch (Exception e) {
			message = "Failed";
			throw new Exception("Error in generating otp");
		}

		return message;
	}

	private void sendMail(String emailAddress, String otp) throws MessagingException {

		Properties properties = System.getProperties();

		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("nutritionist.help22@gmail.com", "nutritionist@22");
			}
		});

		try {

			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress("nutritionist.help22@gmail.com"));

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));

			message.setSubject("Password Reset");

			message.setText("Dear user," + System.lineSeparator() + "Please find the OTP " + otp
					+ " for password reset and OTP will expire in 3 minutes." + System.lineSeparator() + "Thank You.");

			Transport.send(message);

		} catch (MessagingException e) {
			throw new MessagingException("Error in sending mail");
		}
	}

	@Override
	public String validateOTP(String emailId, String otp) throws UserNotFoundException {

		User user = new User();
		String message = "Failed";

		try {
			user = getUser(emailId);
		} catch (UserNotFoundException e) {
			throw new UserNotFoundException("User not found");
		}

		Optional<UserOtp> userOtp = userOtpRepository.findByEmailId(emailId);

		if (userOtp.isPresent()) {
			if (otp.equals(userOtp.get().getOtp())) {
				Duration duration = Duration.between(userOtp.get().getCreatedDate(), LocalDateTime.now());
				long durationMinute = duration.toMinutes();
				if (durationMinute < 3) {
					message = "Success";
				}
			}
		}

		return message;
	}

	@Override
	public User updateUserInfo(User user) throws Exception {

		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("emailId").is(user.getEmailId()));
			Update update = new Update();
			update.set("firstName", user.getFirstName());
			update.set("lastName", user.getLastName());
			update.set("height", user.getHeight());
			update.set("weight", user.getWeight());
			update.set("age", user.getAge());
			update.set("modifiedDate", LocalDateTime.now());
			mongoTemplate.updateFirst(query, update, User.class);
		} catch (Exception e) {
			throw new Exception("Error occured while updtaing user info");
		}

		return user;
	}

	@Override
	public User updateUserPassword(User user) throws UserNotFoundException, Exception {

		User user1 = new User();
		try {
			user1 = getUser(user.getEmailId());
		} catch (UserNotFoundException e) {
			throw new UserNotFoundException("User not found");
		}

		try {
			Query query = new Query();
			query.addCriteria(Criteria.where("emailId").is(user.getEmailId()));
			Update update = new Update();
			update.set("password", user.getPassword());
			update.set("modifiedDate", LocalDateTime.now());
			mongoTemplate.updateFirst(query, update, User.class);
		} catch (Exception e) {
			throw new Exception("Error occured while updtaing user password");
		}

		return user1;
	}
	
	
	/*
	 * @Override public User verifyUser(String emailId, String password) throws
	 * UserNotFoundException, Exception {
	 * 
	 * Optional<User> user = userRepository.findByEmailId(emailId); Optional<User>
	 * user1 = Optional.ofNullable(new User());
	 * 
	 * if (!user.isPresent()) { throw new UserNotFoundException("User not found"); }
	 * 
	 * if(password.equals(user.get().getPassword())) { user1= user; } else { throw
	 * new Exception("User Password not matching"); }
	 * 
	 * return user1.get(); }
	 */
}
