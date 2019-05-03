package com.routeplanner.shopping.service;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.routeplanner.shopping.RegistrationDetails;
import com.routeplanner.shopping.Role;
import com.routeplanner.shopping.User;
import com.routeplanner.shopping.ex.UsernameNotFoundException;
import com.routeplanner.shopping.repository.RegistrationDetailsRepository;
import com.routeplanner.shopping.repository.RoleRepository;
import com.routeplanner.shopping.repository.UserRepository;


@Transactional(isolation = Isolation.DEFAULT, propagation=Propagation.REQUIRED) 
@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository<User> userRepository;
	
	@Autowired
	private RegistrationDetailsRepository<RegistrationDetails> regDetailsRepository;
	
	public UserService() {
		
	}
	
	public void save(User user) {
		userRepository.save(user);
		logger.debug("User saved with id: " + user.getId());
	}
	
	// TODO SPRING SECURITY add: 	return optionalUser.map(CustomUserDetails::new).get();
	public User findByUsername(String username, String password) throws UsernameNotFoundException {
		Optional<User> optUser = userRepository.fetchUserFromLoginCredentials(username, password);
		optUser.orElseThrow(()-> new UsernameNotFoundException("Username not found"));
		return optUser.get();
	}
	
	
	public Role getMemberRole() {
		Optional<Role> role = roleRepository.getMembershipRole();
		role.orElseThrow(()->new NullPointerException("could not locate membership role"));
		return role.get();
	}
	
	
	@Transactional(isolation = Isolation.DEFAULT, propagation=Propagation.REQUIRES_NEW)
	public void register(RegistrationDetails regDetails) {
		// persist the user, and the reg details within the same session
		if (regDetails == null || regDetails.getUser() == null) {
			throw new NullPointerException("could not register person with missing attributes");
		}
		
		
		// TODO ISSUE DOING THIS>...............
		// handle member level role
		Set<Role> roles = new HashSet<Role>();
		roles.add(getMemberRole());	
		regDetails.getUser().setRoles(roles);
		
		
		// Set<Role> roleSet = roleRepository.findAll().stream().collect(Collections.toSet());
		
		
		
		
		// persist registration
		userRepository.save(regDetails.getUser());
		regDetailsRepository.saveAndFlush(regDetails);
		logger.info("new user successfully registered - registration details: " + regDetails.getId());
	}
	
	
}
