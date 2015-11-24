package com.ticketservice.model;

/**
 * Class to hold seating level configurations
 * **/
public class SeatingLevel {
	
	private int levelId;
	private String levelName;
	private double price;
	private int rows;
	private int seats;
	private String[][] tickets;	
	
	public SeatingLevel() {	}
	
	/**
	 * @return the levelId
	 */
	public int getLevelId() {
		return levelId;
	}
	/**
	 * @param levelId the levelId to set
	 */
	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}
	/**
	 * @return the levelName
	 */
	public String getLevelName() {
		return levelName;
	}
	/**
	 * @param levelName the levelName to set
	 */
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}
	/**
	 * @param rows the rows to set
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}
	/**
	 * @return the seats
	 */
	public int getSeats() {
		return seats;
	}
	/**
	 * @param seats the seats to set
	 */
	public void setSeats(int seats) {
		this.seats = seats;
	}
	
	/**
	 * @return the tickets
	 */
	public String[][] getTickets() {
		return tickets;
	}


	/**
	 * @param tickets the tickets to set
	 */
	public void setTickets(String[][] tickets) {
		this.tickets = tickets;
	}
}
