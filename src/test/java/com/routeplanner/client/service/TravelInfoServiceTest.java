package com.routeplanner.client.service;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.routeplanner.dm.JourneySummary;
import com.routeplanner.engine.IRoutePlanner;
import com.routeplanner.ex.DuplicateStationException;


@RunWith(MockitoJUnitRunner.Silent.class)
public class TravelInfoServiceTest {

	@Mock
	private IRoutePlanner mockPlanner;
	
	@InjectMocks    
	private TravelInfoService travelInfoService;
		
	public TravelInfoServiceTest() {
		
	}
	
	
	@Test
	public void testGetJourneyDetailsValueReturned() {
		String start = "East Putney";
		String dest = "Farringdon";
		JourneySummary journeySummary = travelInfoService.getJourneyDetails(start, dest);
		assertNotNull(journeySummary);
		assertEquals(start, journeySummary.getCurrRouteStart());
		assertEquals(dest, journeySummary.getCurrRouteDest());
		assertNotNull(journeySummary.getRouteInfo());
		assertTrue(journeySummary.isSuccessfulLastSearch());
		assertNull(journeySummary.getFailureException());
	}
	
	
	
	@Test
	public void testGetJourneyDetailsDuplicateStation() {
		String start = "Farringdon";
		String dest = "Farringdon";
		JourneySummary journeySummary = travelInfoService.getJourneyDetails(start, dest);
		String routeInfo = journeySummary.getRouteInfo();
		assertNull(routeInfo);
		assertEquals(start, journeySummary.getCurrRouteStart());
		assertEquals(dest, journeySummary.getCurrRouteDest());
		assertFalse(journeySummary.isSuccessfulLastSearch());
		assertNotNull(journeySummary.getFailureException());
		assertEquals("The start and destination should be different. Please try again.", journeySummary.getFailureException().getMessage());
	}
	
	
	
	@Test
	public void testGetJourneyDetailsInvalidStation() {
		// TODO 
	}

	

}
