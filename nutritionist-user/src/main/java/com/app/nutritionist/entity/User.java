package com.app.nutritionist.entity;

import java.time.LocalDateTime;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "user_info")
public class User {
	
	private String firstName;
	private String lastName;
	@Id
	private String emailId;
	private String password;
	private int height;
	private int weight;
	private int age;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;

}
