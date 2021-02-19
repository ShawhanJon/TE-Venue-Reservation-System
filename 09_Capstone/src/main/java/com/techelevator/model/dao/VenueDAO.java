package com.techelevator.model.dao;

import java.util.List;

import com.techelevator.model.*;

public interface VenueDAO {
	
	public List<Venue> getAllVenues();

	public Venue selectVenueById(int id);

}
