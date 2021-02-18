package com.techelevator.model.dao;

import java.time.LocalDate;

import com.techelevator.model.*;

public interface ReservationDAO {
	
		public Reservation addReservation(int spaceID, int expectedAttendance, LocalDate startingDate, LocalDate endingDate, String reservedFor);
	

}