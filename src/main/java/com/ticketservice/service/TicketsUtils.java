package com.ticketservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import com.ticketservice.data.TicketsStore;
import com.ticketservice.model.Seat;
import com.ticketservice.model.SeatHold;
import com.ticketservice.model.SeatingLevel;
import com.ticketservice.util.Status;

/**
 * This is a utility class to get the number of tickets available /on hold/reserved
 * **/
public class TicketsUtils {
	
	private static volatile int reservationId = 1;
	
	private static Map<Integer, SeatingLevel> seats = TicketsStore.getInstance().getSeatingLevel();
	private static Map<Integer, SeatHold> reservations = TicketsStore.getInstance().getReservations();
	/**
	 * Gets the number of seats available by level
	 * availability data for the theater 
	 */
	public static int numberSeatAvailable(Optional<Integer> venueLevel) {
		int seatsAvailable = 0;
		/*Get single level number of seats available*/
		if(venueLevel.isPresent()){
			SeatingLevel level = seats.get(venueLevel.get());
			seatsAvailable = getSeatsAvailable(level.getTickets());
		}
		else{
			/*Get total of all level number of seats available in the theater*/
			for(Entry<Integer, SeatingLevel> entry: seats.entrySet()) {
				SeatingLevel level = entry.getValue();
				seatsAvailable = seatsAvailable + getSeatsAvailable(level.getTickets());
		    }
		}

		return seatsAvailable;
	}
	
	/**
	 * searches array for available seats
	 * **/
	private static int getSeatsAvailable(String[][] tickets){
		int available = 0;
		for (int rows = 0; rows < tickets.length; rows++) { 
			for (int seat = 0; seat < tickets[rows].length; seat++) {
				String status = tickets[rows][seat];
				if (status == null || Status.AVAILABLE.getStatus().equalsIgnoreCase(status)){
					available++;
				}
			}
		}
		
		return available;
	}
	
	/**
	 * Finds or hold seat
	 * **/
	public static SeatHold findAndHoldSeats(int numSeats, Optional<Integer> minLevel, Optional<Integer> maxLevel, String customerEmail){
		boolean foundSeat = false;
		SeatHold seatHold = new SeatHold();
		List<Seat> bookSeat = new ArrayList<Seat>();
		seatHold.setSeats(bookSeat);
		if(!minLevel.isPresent() && !maxLevel.isPresent()){
			/*Returns empty seat hold if values are not present*/
			return seatHold;
		}
		
		if(minLevel.isPresent() || maxLevel.isPresent() ){
			bookSeat = findSeat(minLevel.get(), numSeats);
			/*Only one level is used in the implementation*/
			/*if(bookSeat != null && numSeats != bookSeat.size()){
				bookSeat = findSeat(minLevel.get(), numSeats-bookSeat.size());
			}*/
			
			/*update seatHold details */
			if(foundSeat){
				seatHold.setSeatHoldId(reservationId++);
				seatHold.setCustomerEmail(customerEmail);
				seatHold.setHoldTimer(getTimer(seatHold.getSeatHoldId()));
				reservations.put(seatHold.getSeatHoldId(), seatHold);
			}
		}
		else{
			seatHold.setCustomerEmail("No seats available");
		}
		
		return seatHold;
	}
	
	/**
	 * Timer to expire Seats on Hold after 10sec
	 */
	private static Timer getTimer(final int seatHoldId){
		final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int i = 10;
            public void run() {
                i--;
                if (i< 0){
                	expireSeats(seatHoldId);
                }
            }
        }, 10000);
        
        return timer;
	}
	
	/**
	 * Find availability seat and hold for customer
	 */
	private static List<Seat> findSeat(int level, int numSeats) {
		List<Seat> bookSeat = new ArrayList<Seat>();
		if(level > 0){
			String[][] tickets = seats.get(level).getTickets();
			for (int rows = 0; rows < tickets.length; rows++) {
				for (int seats = 0; seats < tickets[rows].length; seats++) {
					String status = tickets[rows][seats];
					if (Status.AVAILABLE.getStatus().equalsIgnoreCase(status)){
						tickets[rows][seats] = Status.HOLD.getStatus();
						Seat seat = new Seat();
						seat.setLevelId(level);
						seat.setRowId(rows+1);
						seat.setSeatId(seats+1);
						seat.setStatus(Status.HOLD);
						seat.setName("" + level + "_" + rows + "_" + seats);
						bookSeat.add(seat);
						numSeats--;
						break;
					}
				}
				if(numSeats == 0){
					break;
				}
			}
		}
		
		return bookSeat;
	}

	/**
	 * Reserves a seat for a customer with a seatHold Id
	 * **/
	public static SeatHold reserveSeats(int seatHoldId, String customerEmail){
		SeatHold seatHold = reservations.get(seatHoldId);
		if(customerEmail.equalsIgnoreCase(seatHold.getCustomerEmail())){
			List<Seat> seatsHold = seatHold.getSeats();
			for (Seat seat : seatsHold) {
				changeAvailability(seat.getLevelId(), seat.getRowId(), seat.getSeatId(), Status.RESERVED);
				seat.setStatus(Status.RESERVED);
			}
		}
		
		return seatHold;
	}
	
	
	/**
	 * Expires hold seats and make them available again
	 * **/
	public static void expireSeats(int seatHoldId){
		SeatHold seatHold = reservations.get(seatHoldId);
		List<Seat> seatsHold = seatHold.getSeats();
		for (Seat seat : seatsHold) {
			if(seat.getStatus() == Status.HOLD){
				changeAvailability(seat.getLevelId(), seat.getRowId(), seat.getSeatId(), Status.AVAILABLE);
			}
		}
	}
	
	/**
	 * Change availability of the seat 
	 */
	public static void changeAvailability(int levelId, int row, int seat, Status status) {
		SeatingLevel level = seats.get(levelId);
		String[][] seating = level.getTickets();
		seating[row-1][seat-1] = status.getStatus();
	}
	
}
