package com.ticketservice;

import java.util.Optional;

import junit.framework.TestCase;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ticketservice.model.SeatHold;
import com.ticketservice.service.TicketService;
import com.ticketservice.service.TicketServiceImp;

/**
 * Test class to test the interface implementations and other methods
 * 
 * **/

public class TicketServiceTest extends TestCase{
		
	private TicketService service;
	
	@BeforeTest
    public void setUp() {
		service = new TicketServiceImp();
    }
	
	@Test
	public void testNumSeatsAvailable() {
		service = new TicketServiceImp();
		Optional<Integer> venueLevel = Optional.of(new Integer(1));
		int seats = service.numSeatsAvailable(venueLevel);
		assertEquals(1250, seats);

		/*Test the to check total of all available seats*/
		venueLevel = Optional.ofNullable(null);
		seats = service.numSeatsAvailable(venueLevel);
		assertEquals(6250, seats);
	}
	
	/*@Test
	public void testFindAndHoldSeats() {
		int numSeats = 5;
		 SeatHold seatHold = service.findAndHoldSeats(numSeats, Optional.of(new Integer(1)), Optional.of(new Integer(1)), "test@gmail.com");
		 assertNotNull(seatHold);
		 assertEquals(1, seatHold.getSeatHoldId());
		 assertNotNull(seatHold.getSeats());
		 assertEquals(numSeats, seatHold.getSeats().size());
	}
	
	@Test
	public void testReserveSeats() {
		int seatHoldId = 1;
		 String reservationCode = service.reserveSeats(seatHoldId, "test@gmail.com");
		 assertNotNull(reservationCode);
	}*/
}
