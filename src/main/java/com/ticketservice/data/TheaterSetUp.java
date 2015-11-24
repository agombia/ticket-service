package com.ticketservice.data;

import java.util.HashMap;
import java.util.Map;

import com.ticketservice.model.SeatingLevel;
import com.ticketservice.util.Level;
import com.ticketservice.util.Status;

/**
 * This class sets up the initial Seating capacity for all 
 * levels and it is used only once when the application starts 
 * NB: There could have been a better or a more optimized approach
 * but this is adopted for the sake of solving the problem at hand
 * **/
public class TheaterSetUp {
	
	
	public static Map<Integer, SeatingLevel> setUpTheater(){
		Map<Integer, SeatingLevel> seatings = new HashMap<Integer, SeatingLevel>();
	
		seatings.put(Level.ORCHESTRA.getLevelId(), getOrchestraLevel());
		seatings.put(Level.MAIN.getLevelId(), getMainLevel());
		seatings.put(Level.BALCONY_1.getLevelId(), getBalconyLevel1());
		seatings.put(Level.BALCONY_2.getLevelId(), getBalconyLevel2());
		
		return seatings;
	}
	
	private static SeatingLevel getOrchestraLevel(){
		double amount = 100; 
		int rows = 25;
		int seats = 50;
		
		SeatingLevel seatingLevel = new SeatingLevel();
		seatingLevel.setLevelId(1);
		seatingLevel.setLevelName(Level.getLevelDesc(Level.ORCHESTRA));
		seatingLevel.setPrice(amount);
		seatingLevel.setRows(rows);
		seatingLevel.setSeats(seats);
		seatingLevel.setTickets(createTickets(rows, seats));
		
		return seatingLevel;
	}
	
	private static SeatingLevel getMainLevel(){
		int amount = 75; 
		int rows = 20;
		int seats = 100;
		
		SeatingLevel seatingLevel = new SeatingLevel();
		seatingLevel.setLevelId(2);
		seatingLevel.setLevelName(Level.getLevelDesc(Level.MAIN));
		seatingLevel.setPrice(amount);
		seatingLevel.setRows(rows);
		seatingLevel.setSeats(seats);
		seatingLevel.setTickets(createTickets(rows, seats));	
		
		return seatingLevel;
	}
	
	private static SeatingLevel getBalconyLevel1(){
		int amount = 50; 
		int rows = 15;
		int seats = 100;
		
		SeatingLevel seatingLevel = new SeatingLevel();
		seatingLevel.setLevelId(3);
		seatingLevel.setLevelName(Level.getLevelDesc(Level.BALCONY_1));
		seatingLevel.setPrice(amount);
		seatingLevel.setRows(rows);
		seatingLevel.setSeats(seats);
		seatingLevel.setTickets(createTickets(rows, seats));
		
		return seatingLevel;
	}
	
	private static SeatingLevel getBalconyLevel2(){
		int amount = 40; 
		int rows = 15;
		int seats = 100;

		SeatingLevel seatingLevel = new SeatingLevel();
		seatingLevel.setLevelId(4);
		seatingLevel.setLevelName(Level.getLevelDesc(Level.BALCONY_2));
		seatingLevel.setPrice(amount);
		seatingLevel.setRows(rows);
		seatingLevel.setSeats(seats);
		seatingLevel.setTickets(createTickets(rows, seats));
		
		return seatingLevel;
	}
	

	private static String[][] createTickets(int rows, int seats) {
		String[][] tickets = new String[rows][seats];
		for (rows = 0; rows < tickets.length; rows++) {
			for (seats = 0; seats < tickets[rows].length; seats++) {
				tickets[rows][seats] = Status.AVAILABLE.getStatus();
			}
		}
		
		return tickets;
	}
}
