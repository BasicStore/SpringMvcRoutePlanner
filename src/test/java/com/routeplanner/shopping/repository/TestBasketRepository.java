package com.routeplanner.shopping.repository;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;
import com.routeplanner.shopping.Basket;
import com.routeplanner.shopping.PassengerType;
import com.routeplanner.shopping.RouteQuery;
import com.routeplanner.shopping.Ticket;
import com.routeplanner.shopping.TicketType;
import com.routeplanner.shopping.User;


@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(
		  classes = { H2TestProfileJPAConfig.class }, 
		  loader = AnnotationConfigContextLoader.class)
@Transactional
public class TestBasketRepository {

	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private BasketRepository<Basket> basketRepository;
	
	@Autowired
	private RouteQueryRepository<RouteQuery> routeQueryRepository;
	
	@Autowired
	private UserRepository<User> userRepository;
	
	
	// persist a basket to the database
	private User adminUser1;
	private RouteQuery routeQuery1;
	private Basket basket1;
	private Ticket ticket1;
	
	
	public TestBasketRepository() {
		
		
		
	}
	
	private void createBasket1() {
		basket1 = new Basket();
		createTickets();
		
		Set<Ticket> tickets = new HashSet<Ticket>();
		tickets.add(ticket1);
		basket1.setTickets(tickets);
		basket1.setUser(adminUser1);
		basket1.setOpen(true);
		basketRepository.saveAndFlush(basket1);
	}
	
	
	private RouteQuery createRouteQuery1() {
		routeQuery1 = new RouteQuery();
		routeQuery1.setCurrRouteStart("Oxford Circus");
		routeQuery1.setCurrRouteDest("East Putney");
		routeQuery1.setSuccessfulLastSearch(true);
		routeQuery1.setRouteInfo("blar blar blar");
		routeQueryRepository.saveAndFlush(routeQuery1);
		return routeQuery1;
	}
	
	
	
	
	@Test
	public void testFindOpenBasketForUser() {
		assertEquals(0, (int)basketRepository.count());
		assertEquals(0, (int)routeQueryRepository.count());
		assertEquals(3, (int)userRepository.count()); // includes data.sql
		
		// test that no basket returns an optional null
		Optional<Basket> basket = basketRepository.findOpenBasketForUser(44);
		assertFalse(basket.isPresent());
		
		createRouteQuery1();
		assertEquals(1, (int)routeQueryRepository.count());
		RouteQuery dbRouteQuery = routeQueryRepository.findById(routeQuery1.getId()).get();
		assertEquals(routeQuery1.getCurrRouteStart(), dbRouteQuery.getCurrRouteStart());
		assertEquals(routeQuery1.getCurrRouteDest(), dbRouteQuery.getCurrRouteDest());
		
		int adminUserId = 3;
		User dbUser = userRepository.findById(adminUserId).get();
		assertNotNull(dbUser);
		assertEquals("user3", dbUser.getUsername());
		assertEquals("password", dbUser.getPassword());
		
		// createBasket1();
		
		
		
		
		
	}
	
	
	
	
	private void createTickets() {
		ticket1 = new Ticket();
		ticket1.setPassengerType(PassengerType.STANDARD_PLUS);
		ticket1.setRouteQuery(routeQuery1);
		ticket1.setOpen(true);
		ticket1.setNumUnits(3);
		ticket1.setTravelDate(LocalDate.now());
		ticket1.setTicketType(TicketType.PEAK);
	}
	
	
	
	
	
	/*
	BASKET
	
	@OneToMany
	private Set<Ticket> tickets;
	
	
	
	
	// TODO should this be explicit but have duplication????? or leave  program to checlk whether there are any open items
	private boolean open = true;
	
	---------------------------------------------------------------------
	
	TICKET
	
	
	public Ticket(boolean open, int numUnits, PassengerType passengerType, LocalDate travelDate, 
			RouteQuery routeQuery, Rule rule) {
	
	
	private PassengerType passengerType;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate travelDate;
	
	private TicketType ticketType;
		
	@OneToOne
	private RouteQuery routeQuery;
	
	private int numUnits;
	
	// an item is always open until it is explicitly closed
	private boolean open = true;
	
	
	---------------------------------------------------------------------
	PASSENGER TYPE
	
	PassengerType.STANDARD_PLUS
	
	
	
	
	---------------------------------------------------------------------
	ROUTE QUERY
	
	currRouteStart;
	
	private String currRouteDest;
	
	private boolean successfulLastSearch = true;
	
	private String routeInfo;
	
	---------------------------------------------------------------------
	
	
	
	
	
	 */
	
	
	
	
	
	

}




/*




// test how to expect exception
	@Test(expected = MyException.class)
	public void givenNull_whenThrowsErrorOnCreate_thenCorrect() {
	    try
	    {
	    	throwException();
	    	fail("MyException was not thrown!!!!");
	    }
	    catch(MyException re)
	    {
	       String message = "Employee ID is null";
	       assertEquals(message, re.getMessage());
	       System.out.println("My exception was caught");
	    }
	}

	
	
	private void throwException() throws MyException {
		throw new MyException("My excpetion msg goes here");
	}
	
	
	
	private class MyException extends Exception {
		public MyException(String msg) {
			super(msg);
		}
	}

*/