package com.routeplanner.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.routeplanner.shopping.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUsername(String name);
	
	
}
