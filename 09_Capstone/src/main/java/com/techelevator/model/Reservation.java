package com.techelevator.model;

import java.time.LocalDate;

public class Reservation {
	
	private long reservationId;
	private long spaceID;
	private long numberOfAttendees;
	private LocalDate startingDate;
	private LocalDate endingDate;
	private String reservedFor;
	private String venueName;
	private String spaceName;
	private double dailyRate;
	
	public long getReservationId() {
		return reservationId;
	}
	public void setReservationId(long reservationId) {
		this.reservationId = reservationId;
	}
	public long getSpaceID() {
		return spaceID;
	}
	public void setSpaceID(long spaceID) {
		this.spaceID = spaceID;
	}
	public long getNumberOfAttendees() {
		return numberOfAttendees;
	}
	public void setNumberOfAttendees(long numberOfAttendees) {
		this.numberOfAttendees = numberOfAttendees;
	}
	public LocalDate getStartingDate() {
		return startingDate;
	}
	public void setStartingDate(LocalDate startingDate) {
		this.startingDate = startingDate;
	}
	public LocalDate getEndingDate() {
		return endingDate;
	}
	public void setEndingDate(LocalDate endingDate) {
		this.endingDate = endingDate;
	}
	public String getReservedFor() {
		return reservedFor;
	}
	public void setReservedFor(String reservedFor) {
		this.reservedFor = reservedFor;
	}
	public String getVenueName() {
		return venueName;
	}
	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}
	public String getSpaceName() {
		return spaceName;
	}
	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}
	public double getDailyRate() {
		return dailyRate;
	}
	public void setDailyRate(double dailyRate) {
		this.dailyRate = dailyRate;
	}
	
	
	
	
	
}
