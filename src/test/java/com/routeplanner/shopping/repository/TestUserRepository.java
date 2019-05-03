package com.routeplanner.shopping.repository;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;
import com.routeplanner.shopping.User;


@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(
		  classes = { H2TestProfileJPAConfig.class }, 
		  loader = AnnotationConfigContextLoader.class)
@Transactional
public class TestUserRepository {
	
	@Autowired
	private UserRepository<User> userRepository;
	
	public TestUserRepository() {
		
	}

	@Test
	public void testFindByUsername() {
		
		User newUser = new User();
		newUser.setUsername("MyUser");
		newUser.setPassword("MyPass123");
		newUser.setEmail("sdfdsf@sdfsd.com");
		newUser.setLastName("Smith");
		userRepository.saveAndFlush(newUser);
		
		assertTrue(userRepository.findByUsername(newUser.getUsername()).isPresent());
		assertEquals(newUser.getUsername(), userRepository.findByUsername(newUser.getUsername()).get().getUsername());
		assertEquals(newUser.getPassword(), userRepository.findByUsername(newUser.getUsername()).get().getPassword());
		
		assertTrue(userRepository.fetchUserFromLoginCredentials(newUser.getUsername(), newUser.getPassword()).isPresent());
		assertEquals(newUser.getUsername(), userRepository.fetchUserFromLoginCredentials(newUser.getUsername(), newUser.getPassword()).get().getUsername());
		assertEquals(newUser.getPassword(), userRepository.fetchUserFromLoginCredentials(newUser.getUsername(), newUser.getPassword()).get().getPassword());
	}
	
	
	
}
