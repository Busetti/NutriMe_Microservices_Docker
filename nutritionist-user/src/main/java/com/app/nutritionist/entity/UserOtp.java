package com.app.nutritionist.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "user_otp")
public class UserOtp {
	
	@Id
	private String emailId;
	private String otp;
	private LocalDateTime createdDate;

}
