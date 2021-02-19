package com.techelevator.model.dao;

import java.time.LocalDate;
import java.util.List;

import com.techelevator.model.*;

public interface ReservationDAO {
	
		public Reservation addReservation(int spaceID, int expectedAttendance, LocalDate startingDate, LocalDate endingDate, String reservedFor);
	
		public List<Reservation> retrieveUpcomingReservations(int venueId);
}