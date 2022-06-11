package com.app.nutritionist.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.nutritionist.entity.UserOtp;

@Repository
public interface UserOtpRepository extends MongoRepository<UserOtp, String> {

	Optional<UserOtp> findByEmailId(String emailId);

	@Modifying
	@Query("update user_otp set otp = :otp and createdDate = :createdDate where emailId = :emailId")
	void updateOTP(@Param(value = "otp") String otp, @Param(value = "createdDate") LocalDateTime createdDate,
			@Param(value = "emailId") String emailId);

}
