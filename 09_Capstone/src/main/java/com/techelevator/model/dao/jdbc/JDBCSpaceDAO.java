package com.techelevator.model.dao.jdbc;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.*;

import com.techelevator.model.dao.SpaceDAO;

public class JDBCSpaceDAO implements SpaceDAO {
	
	private JdbcTemplate jdbcTemplate;

	public JDBCSpaceDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	

	@Override
	public List<Space> retrieveSpacesByVenueID(int venueID) {
		
		List<Space> spaces = new ArrayList<Space>();
		
		String sql = "SELECT space.id AS space_id, space.name AS space_name, space.is_accessible AS is_accessible, space.open_from AS open_from, space.open_to AS open_to, space.daily_rate AS daily_rate , space.max_occupancy AS max_occupancy, venue.name AS venue_name "
						+ "FROM space "
						+ "JOIN venue ON space.venue_id = venue.id "
						+ "JOIN categorey_venue ON venue.id = category_venue.venue_id"
						+ "WHERE (venue_id = ?)"
						+ "ORDER BY space_name";
		
		
		SqlRowSet spaceResults = jdbcTemplate.queryForRowSet(sql, venueID);
		
		//loop through the results
		while(spaceResults.next()) {
			Space space = mapRowToSpace(spaceResults);
			spaces.add(space);
		}
		
		return spaces;
		
	}

	@Override
	public List<Space> retrieveAvailableSpaces(LocalDate startingDate, LocalDate endingDate, int expectedAttendance,
			boolean isAccessible, double dailyRate, int category) {
	
	List<Space> spaces = new ArrayList<Space>();
		
		String sql = "SELECT space.id AS space_id, space.name AS space_name, space.is_accessible AS is_accessible, space.open_from AS open_from, space.open_to AS open_to, space.daily_rate AS daily_rate , space.max_occupancy AS max_occupancy, "
						+ "FROM reservationn "
						+ "JOIN space ON reservation.reservation_id = space.id "
						+ "JOIN venue ON space.id = venue.id"
						+ "JOIN category_venue ON venu.id = category_venue.venue_id"
						+ "WHERE start_date =? , end_date = ?, is_accessible = ?, daily_rate = ?, category_id = ? "
						+ "ORDER BY space_name";
		
		
		SqlRowSet spaceResults = jdbcTemplate.queryForRowSet(sql, startingDate, endingDate, expectedAttendance, isAccessible, category);
		
		//loop through the results
		while(spaceResults.next()) {
			Space space = mapRowToSpace(spaceResults);
			spaces.add(space);
		}
		
		return spaces;
		
	}

	

	@Override
	public List<Space> retrieveSpacesForVenue(int venueID, LocalDate startingDate, LocalDate endingDate,
			int expectedAttendance) {
		
		
List<Space> spaces = new ArrayList<Space>();
		
		String sql = "SELECT space.id AS space_id, space.name AS space_name, space.is_accessible AS is_accessible, space.open_from AS open_from, space.open_to AS open_to, space.daily_rate AS daily_rate , space.max_occupancy AS max_occupancy, venue.name AS venue_name "
						+ "FROM reservation"
						+ "JOIN space ON reservation.id = soace.id "
						+ "JOIN venue ON space.id = venue.id"
						+ "WHERE (venue_id = ?, start_date = ?, end_ date = ?)"
						+ "ORDER BY space_name";
		
		
		SqlRowSet spaceResults = jdbcTemplate.queryForRowSet(sql, venueID, startingDate, endingDate, expectedAttendance);
		
		//loop through the results
		while(spaceResults.next()) {
			Space space = mapRowToSpace(spaceResults);
			spaces.add(space);
		}
		
		return spaces;
		
 }
	
	//space.id AS space_id, space.name AS space_name, space.is_accessible AS is_accessible, space.open_from AS open_from, space.open_to AS open_to, space.daily_rate AS daily_rate , space.max_occupancy AS max_occupancy,	
	private Space mapRowToSpace(SqlRowSet spaceResults) {
		Space spaces = new Space();
	
		spaces.setId(spaceResults.getInt("space_id"));
		spaces.setName(spaceResults.getString("space_name"));
		spaces.setAccessible(spaceResults.getBoolean("is_accessible"));
		spaces.setOpenFrom(spaceResults.getInt("open_from"));
		spaces.setOpenTo(spaceResults.getInt("open_to"));
		spaces.setDailyRate(spaceResults.getDouble("daily_rate"));
		spaces.setMaxOccupancy(spaceResults.getInt("max_occupancy"));
		return spaces;
	}
	
	

}
