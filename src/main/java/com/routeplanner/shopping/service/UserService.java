package com.routeplanner.shopping.service;
import java.util.Optional;
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

	@Autowired
	private UserRepository userRepository;
	
	public UserService() {
		
	}
	
	public void save(User user) {
		userRepository.save(user);
	}
	
	// TODO SPRING SECURITY add: 	return optionalUser.map(CustomUserDetails::new).get();
	public User findByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optUser = userRepository.findByUsername(username);
		optUser.orElseThrow(()-> new UsernameNotFoundException("Username not found"));
		return optUser.get();
	}
	
}
