package com.ticketservice.service;

import java.util.Optional;

import com.ticketservice.model.SeatHold;

public class TicketServiceImp implements TicketService{
	
	/**
	 * This method gets the number of seats available per seating level or the total seats in the theater
	 * **/
	public int numSeatsAvailable(Optional<Integer> venueLevel) {
		return TicketsUtils.numberSeatAvailable(venueLevel);
	}

	/**
	* Find and hold the best available seats for a customer
	*/
	public SeatHold findAndHoldSeats(int numSeats, Optional<Integer> minLevel, Optional<Integer> maxLevel, String customerEmail) {
		
		return TicketsUtils.findAndHoldSeats(numSeats, minLevel, maxLevel, customerEmail);
	}

	/**
	* Commit seats held for a specific customer
	* */
	public String reserveSeats(int seatHoldId, String customerEmail) {
		SeatHold reserveSeat = TicketsUtils.reserveSeats(seatHoldId, customerEmail);
		
		return ""+reserveSeat.getSeatHoldId();
	}
}
