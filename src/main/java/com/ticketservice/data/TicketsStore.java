package com.ticketservice.data;

import java.util.HashMap;
import java.util.Map;

import com.ticketservice.model.SeatHold;
import com.ticketservice.model.SeatingLevel;

/**
* A singleton class to help keep seating levels set up
* 
**/
public class TicketsStore {
	
	public static volatile TicketsStore instance = null;
	private Map<Integer, SeatingLevel> seatingLevels = null;
	private Map<Integer, SeatHold> reservations = null;
	
	private TicketsStore() {   }
	
	public static TicketsStore getInstance() {
	  if(instance == null) {
	     synchronized(TicketsStore.class) {
	       if(instance == null) {
	    	   instance = new TicketsStore();
	    	   instance.setSeatingLevel();
	    	   instance.setSReservation();
	       }
	    }
	  }
	  return instance;
	}
	
	/**
	 * @return map of the seating level configurations
	 */
	public Map<Integer, SeatingLevel> getSeatingLevel() {
		return instance.seatingLevels;
	}
	
	/*Gets configured seating level set up*/
	private void setSeatingLevel() {
		seatingLevels = TheaterSetUp.setUpTheater();
	}
	
	
	/**
	 * @return map of the reservations
	 */
	public Map<Integer, SeatHold> getReservations() {
		return instance.reservations;
	}
	
	/*creates reservations*/
	private void setSReservation() {
		reservations = new HashMap<Integer, SeatHold>();
	}
}
