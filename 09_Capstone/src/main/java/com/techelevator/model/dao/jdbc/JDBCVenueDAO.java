package com.techelevator.model.dao.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.Venue;
import com.techelevator.model.dao.VenueDAO;



public class JDBCVenueDAO implements VenueDAO {
	
	private JdbcTemplate jdbcTemplate;

	public JDBCVenueDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Venue> getVenues(JDBCSpaceDAO spaceDAO, JDBCCategoryDAO categoryDAO) {
		
		List<Venue> venues = new ArrayList<Venue>();
		
		String sql = "SELECT venue.id AS venue_id, venue.name AS venue_name, city.name AS city_name, "
						+ "state.name AS state_name, venue.description AS venue_description "
						+ "FROM venue "
						+ "JOIN city ON venue.city_id = city.id "
						+ "JOIN state ON city.state_abbreviation = state.abbreviation "
						+ "ORDER BY venue_name";
		
		
		SqlRowSet venueResults = jdbcTemplate.queryForRowSet(sql);
		
		//loop through the results
		while(venueResults.next()) {
			Venue venue = mapRowToVenue(venueResults);
			venues.add(venue);
		}
		
		return venues;
	}
	
	private Venue mapRowToVenue(SqlRowSet venueResults) {
		Venue venues = new Venue();
	
		venues.setId(venueResults.getInt("venue_id"));
		venues.setName(venueResults.getString("venue_name"));
		venues.setCityName(venueResults.getString("city_name"));
		venues.setStateName(venueResults.getString("state_name"));
		venues.setDescription(venueResults.getString("venue_description"));
		return venues;
	}
}
