package com.techelevator.model.dao;

import java.util.List;

import com.techelevator.model.*;
import com.techelevator.model.dao.jdbc.*;

public interface VenueDAO {
	
	public List<Venue> getVenues(JDBCSpaceDAO spaceDAO, JDBCCategoryDAO categoryDAO);

}
