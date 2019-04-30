package com.routeplanner.shopping.service;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.routeplanner.shopping.Basket;
import com.routeplanner.shopping.PassengerType;
import com.routeplanner.shopping.RouteQuery;
import com.routeplanner.shopping.Ticket;
import com.routeplanner.shopping.TicketType;
import com.routeplanner.shopping.User;
import com.routeplanner.shopping.repository.BasketRepository;


@RunWith(MockitoJUnitRunner.Silent.class)
public class TestBasketService {

	@Mock
	private BasketRepository mockBasketRepository;
	
	@InjectMocks    
	private BasketService basketService;
	
	// create a basket and its contents
	private User adminUser1;
	private RouteQuery routeQuery1;
	private Basket basket1;
	private Ticket ticket1;
	
	
	private void createBasket1() {
		basket1 = new Basket();
		createTickets();
		Set<Ticket> tickets = new HashSet<Ticket>();
		tickets.add(ticket1);
		basket1.setTickets(tickets);
		adminUser1 = new User();
		adminUser1.setUsername("MyUsername");
		adminUser1.setPassword("MyPass123");
		adminUser1.setLastName("LastnamePPP");
		adminUser1.setEmail("sdf@sdf.com");
		basket1.setUser(adminUser1);
		basket1.setOpen(true);
		basket1.setId(66);
	}
	
	
	private void createTickets() {
		ticket1 = new Ticket();
		ticket1.setPassengerType(PassengerType.STANDARD_PLUS);
		ticket1.setRouteQuery(routeQuery1);
		ticket1.setOpen(true);
		ticket1.setNumUnits(3);
		ticket1.setTravelDate(LocalDate.now());
		ticket1.setTicketType(TicketType.PEAK);
		ticket1.setRouteQuery(createRouteQuery1());
	}
	
	
	private RouteQuery createRouteQuery1() {
		routeQuery1 = new RouteQuery();
		routeQuery1.setCurrRouteStart("Oxford Circus");
		routeQuery1.setCurrRouteDest("East Putney");
		routeQuery1.setSuccessfulLastSearch(true);
		routeQuery1.setRouteInfo("blar blar blar");
		return routeQuery1;
	}

	
	public TestBasketService() {
		
		
	}
		
	@Test
	public void testFindOpenBasketForUser() {
		createBasket1();
		
		Mockito.when(mockBasketRepository.findOpenBasketForUser(basket1.getId())).thenReturn(Optional.ofNullable(basket1));
		Basket servBasket = basketService.findOpenBasketForUser(basket1.getId());
		assertNotNull(servBasket);
		assertEquals(basket1.getId(), servBasket.getId());
	}
	
	@Test
	public void testFindOpenBasketForUserWithInvalidParam() {
		createBasket1();
		Mockito.when(mockBasketRepository.findOpenBasketForUser(-1)).thenReturn(Optional.ofNullable(null));
		Basket basket = basketService.findOpenBasketForUser(999);
		assertNull(basket);
	}

	
	@Before   
	public void before() {
		// System.out.println("before each test");
	}

	@After   
	public void after() {
		// System.out.println("after each test");
	}

	@BeforeClass     
	public static void beforeClass() {
		// System.out.println("only once before any tests in class are run");
	}

	@AfterClass     
	public static void afterClass() {
		// System.out.println("only once after any tests in class are run");
	}
		
	
}
