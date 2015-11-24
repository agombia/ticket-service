package com.ticketservice;



import java.util.Optional;

import com.ticketservice.model.SeatHold;
import com.ticketservice.service.TicketService;
import com.ticketservice.service.TicketServiceImp;

/**
 * This is the main method to launch the Ticket Reservation App.
 * Follow the instruction in the Readme.txt to test the application.
 * **/
public class TicketServiceApp {

	public static void main(String[] args) {
		TicketService service = new TicketServiceImp();
		Optional<Integer> venueLevel = Optional.ofNullable(null);
		int seats = service.numSeatsAvailable(venueLevel);
		System.out.println("Seats available " + seats);
		
		SeatHold hold = service.findAndHoldSeats(5, Optional.of(new Integer(1)), Optional.of(new Integer(3)), "test@gmail.com");
		hold.printReservation();
		
		service.reserveSeats(hold.getSeatHoldId(), "test@gmail.com");
		
	}

}
