package com.techelevator.model.dao;

import java.util.List;

public interface CategoryDAO {
	
		public List<String> retrieveCategoriesByVenueId(int venueID);
		
		public List<String> retrieveAllCategories();
	

}