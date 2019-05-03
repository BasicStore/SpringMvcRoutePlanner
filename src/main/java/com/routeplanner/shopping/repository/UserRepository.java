package com.routeplanner.shopping.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.routeplanner.shopping.User;

public interface UserRepository<T> extends JpaRepository<User, Integer> {

	Optional<User> findByUsername(String username);
	
	@Query("SELECT u FROM User u WHERE u.username = :username and u.password = :password")
	Optional<User> fetchUserFromLoginCredentials(String username, String password);
	
}
