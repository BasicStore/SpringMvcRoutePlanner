package com.routeplanner.shopping.repository;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
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
	private BasketRepository<Basket> basketRepository;
	
	@Autowired
	private RouteQueryRepository<RouteQuery> routeQueryRepository;
	
	@Autowired
	private UserRepository<User> userRepository;
	
	@Autowired
	private TicketRepository<Ticket> ticketRepository;
	
	
	// persist a basket to the database
	private User memberUser1;
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
		createMemberUser1();
		basket1.setUser(memberUser1);
		basket1.setOpen(true);
		basketRepository.saveAndFlush(basket1);
	}
	
	
	private void createMemberUser1() {
		memberUser1 = new User();
		memberUser1.setUsername("MyUser");
		memberUser1.setPassword("MyPass123");
		memberUser1.setEmail("sdfdsf@sdfsd.com");
		memberUser1.setLastName("Smith");
		userRepository.saveAndFlush(memberUser1);
	}
	
	
	private void createTickets() {
		ticket1 = new Ticket();
		ticket1.setPassengerType(PassengerType.STANDARD_PLUS);
		ticket1.setRouteQuery(routeQuery1);
		ticket1.setOpen(true);
		ticket1.setNumUnits(3);
		ticket1.setTravelDate(LocalDate.now());
		ticket1.setTicketType(TicketType.PEAK);
		ticketRepository.saveAndFlush(ticket1);
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
		assertEquals(0, (int)userRepository.count()); // includes data.sql
		assertEquals(0, (int)basketRepository.count());
		
		// test that no basket returns an optional null
		Optional<Basket> basket = basketRepository.findOpenBasketForUser(44);
		assertFalse(basket.isPresent());
		
		createRouteQuery1();
		assertEquals(1, (int)routeQueryRepository.count());
		RouteQuery dbRouteQuery = routeQueryRepository.findById(routeQuery1.getId()).get();
		assertEquals(routeQuery1.getCurrRouteStart(), dbRouteQuery.getCurrRouteStart());
		assertEquals(routeQuery1.getCurrRouteDest(), dbRouteQuery.getCurrRouteDest());
		 
		createBasket1();
		
		User dbUser = userRepository.findById(memberUser1.getId()).get();
		assertNotNull(dbUser);
		assertEquals(memberUser1.getUsername(), dbUser.getUsername());
		assertEquals(memberUser1.getPassword(), dbUser.getPassword());
		
		assertEquals(1, (int)basketRepository.count());		
		Basket dbBasket = basketRepository.findOpenBasketForUser(memberUser1.getId()).get();
		assertEquals(basket1.getId(), dbBasket.getId());
		assertEquals(basket1.getTickets().size(), dbBasket.getTickets().size());
		basket1.getTickets().remove(ticket1);
		
		Iterator<Ticket> tickIt = dbBasket.getTickets().iterator();
		while (tickIt.hasNext()) {
			Ticket dbTicket = tickIt.next();
			assertEquals(dbTicket.getId(), dbTicket.getId());
			assertEquals(dbTicket.getNumUnits(), dbTicket.getNumUnits());			
			assertEquals(dbTicket.getPassengerType().getId(), dbTicket.getPassengerType().getId());
			assertEquals(dbTicket.getTicketType().getId(), dbTicket.getTicketType().getId());		
			assertEquals(dbTicket.getTravelDate(), dbTicket.getTravelDate());
			assertEquals(dbTicket.getRouteQuery().getCurrRouteStart(), dbTicket.getRouteQuery().getCurrRouteStart());			
			assertEquals(dbTicket.getRouteQuery().getCurrRouteDest(), dbTicket.getRouteQuery().getCurrRouteDest());
		}
		
		assertEquals(basket1.getUser().getId(), dbBasket.getUser().getId());
		assertEquals(basket1.getUser().getUsername(), dbBasket.getUser().getUsername());
		assertEquals(basket1.getUser().getPassword(), dbBasket.getUser().getPassword());
		assertEquals(basket1.isOpen(), dbBasket.isOpen());
	}
	
	
	@Test
	public void testFindOpenBasketForUserWithInvalidValueForId() {
		Optional<Basket> dbBasket = basketRepository.findOpenBasketForUser(9999);
		if (dbBasket.isPresent()) {
			fail("Basket is present, but no basket should exist");
		} 
	}
	
	
	@Before   
	public void before() {
		System.out.println("before each test");
	}

	@After   
	public void after() {
		System.out.println("after each test");
	}

	@BeforeClass     
	public static void beforeClass() {
		System.out.println("only once before any tests in class are run");
	}

	@AfterClass     
	public static void afterClass() {
		System.out.println("only once after any tests in class are run");
	}
	

}




