package com.routeplanner.shopping.repository;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
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
	
	private User adminUser1;
	
	public TestUserRepository() {
		
	}

	@Test
	public void testFindByUsername() {
		
		User dbAdminUser1 = userRepository.findById(3).get();
		assertTrue(userRepository.findByUsername(dbAdminUser1.getUsername()).isPresent());
		assertEquals(dbAdminUser1.getUsername(), userRepository.findByUsername(dbAdminUser1.getUsername()).get().getUsername());
		assertEquals(dbAdminUser1.getPassword(), userRepository.findByUsername(dbAdminUser1.getUsername()).get().getPassword());
		
		User newUser = new User();
		newUser.setUsername("MyUser");
		newUser.setPassword("MyPass123");
		newUser.setEmail("sdfdsf@sdfsd.com");
		newUser.setLastName("Smith");
		userRepository.saveAndFlush(newUser);
		
		// TODO script needs to ignore this
//		Optional<User> optUser = userRepository.findByUsername(newUser.getUsername());
//		assertTrue(optUser.isPresent());
//		assertEquals(newUser.getUsername(), optUser.get().getUsername());
//		assertEquals(newUser.getPassword(), optUser.get().getPassword());
//		assertEquals(newUser.getId(), optUser.get().getId());
//		assertEquals(newUser.getEmail(), optUser.get().getEmail());
//		assertEquals(newUser.getLastName(), optUser.get().getLastName());
	}
	
	
	
}
