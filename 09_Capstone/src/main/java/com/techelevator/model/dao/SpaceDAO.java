package com.techelevator.model.dao;

import java.time.LocalDate;
import java.util.List;

import com.techelevator.model.*;

public interface SpaceDAO {
	
	public List<Space> retrieveSpacesByVenueID(long userSelectedVenue);

	public List<Space> retrieveAvailableSpaces(LocalDate startingDate, int numberOfDays, int expectedAttendance, long userSelectedVenue);

}

