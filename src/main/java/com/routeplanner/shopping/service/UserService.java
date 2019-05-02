package com.routeplanner.shopping.service;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.routeplanner.shopping.User;
import com.routeplanner.shopping.ex.UsernameNotFoundException;
import com.routeplanner.shopping.repository.UserRepository;

@Transactional(isolation = Isolation.DEFAULT, propagation=Propagation.REQUIRED) 
@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository<User> userRepository;
	
	public UserService() {
		
	}
	
	public void save(User user) {
		userRepository.save(user);
		logger.debug("User saved with id: " + user.getId());
	}
	
	// TODO SPRING SECURITY add: 	return optionalUser.map(CustomUserDetails::new).get();
	public User findByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optUser = userRepository.findByUsername(username);
		optUser.orElseThrow(()-> new UsernameNotFoundException("Username not found"));
		return optUser.get();
	}
	
}
