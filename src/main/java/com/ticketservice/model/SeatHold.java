package com.ticketservice.model;

import java.util.Date;
import java.util.List;
import java.util.Timer;

public class SeatHold {
	
	private String customerEmail;
	private List<Seat> seats;
	private int seatHoldId;
	private double amount;
	private Timer holdTimer;
	
	/**
	 * @return the seats
	 */
	public List<Seat> getSeats() {
		return seats;
	}
	/**
	 * @param seats the seats to set
	 */
	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}
	/**
	 * @return the seatHoldId
	 */
	public int getSeatHoldId() {
		return seatHoldId;
	}
	/**
	 * @param seatHoldId the seatHoldId to set
	 */
	public void setSeatHoldId(int seatHoldId) {
		this.seatHoldId = seatHoldId;
	}
	
	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	/**
	 * @return the customerEmail
	 */
	public String getCustomerEmail() {
		return customerEmail;
	}
	/**
	 * @param customerEmail the customerEmail to set
	 */
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	/**
	 * @return the holdTimer
	 */
	public Timer getHoldTimer() {
		return holdTimer;
	}
	/**
	 * @param holdTimer the holdTimer to set
	 */
	public void setHoldTimer(Timer holdTimer) {
		this.holdTimer = holdTimer;
	}
	
	public void printReservation() {
        Date timenow = new Date();
        System.out.println();
        System.out.println("Date: " + timenow.toString());
        System.out.println("Reservation Id: " + seatHoldId);
        System.out.println("Enjoy your Movie.");
        System.out.println();
    }
}
