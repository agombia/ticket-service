package com.ticketservice.util;

public enum Status {
	AVAILABLE("A"),
	HOLD("H"),
    RESERVED("R");
    
	private String status;
    public String getStatus() {
		return status;
	}
    
    Status(String status){
    	this.status = status;
    }
}
