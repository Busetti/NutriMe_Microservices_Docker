package com.app.nutritionist.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.app.nutritionist.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	Optional<User> findByEmailId(String emailId);

}
