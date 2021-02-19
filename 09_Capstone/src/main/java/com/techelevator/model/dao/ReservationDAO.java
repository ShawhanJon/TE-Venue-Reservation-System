package com.techelevator.model.dao;


import com.techelevator.model.*;

public interface ReservationDAO {
	
	public Reservation addReservation(Reservation reservation);

	public Reservation getReservationById(int id);

}