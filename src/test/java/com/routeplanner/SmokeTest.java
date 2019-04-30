package com.routeplanner;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import com.routeplanner.ctrl.BasketController;
import com.routeplanner.ctrl.CheckoutController;
import com.routeplanner.ctrl.LoginController;
import com.routeplanner.ctrl.NavigationController;
import com.routeplanner.ctrl.QueryController;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("local")
@PropertySource("classpath:./application.properties")
@TestPropertySource("classpath:./application.properties")
public class SmokeTest {

	@Autowired
	private LoginController loginController;
	
	@Autowired
	private BasketController basketController;
	
	@Autowired
	private CheckoutController checkoutController;
	
	@Autowired
	private NavigationController navigationController;
	
	@Autowired
	private QueryController queryController;
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(loginController).isNotNull();
		assertThat(basketController).isNotNull();
		assertThat(checkoutController).isNotNull();
		assertThat(navigationController).isNotNull();
		assertThat(queryController).isNotNull();
	}
	
	
	// add controller tests:  https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html
	
	
}



