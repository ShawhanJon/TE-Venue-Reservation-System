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
	public List<Space> retrieveSpacesByVenueID(long venueID) {
		
		List<Space> spaces = new ArrayList<Space>();
		
		String sql = "SELECT space.id AS space_id, space.venue_id AS venue_id, space.name AS space_name, space.is_accessible AS is_accessible, space.open_from AS open_from, space.open_to AS open_to,"
				+ "CAST (space.daily_rate AS numeric) AS daily_rate, space.max_occupancy AS max_occupancy, venue.name AS venue_name"
				+ "FROM space"
				+ "JOIN venue ON space.venue_id = venue.id"
				+ "WHERE venue.id = ?";
		
		SqlRowSet spaceResults = jdbcTemplate.queryForRowSet(sql, venueID);
		
		//loop through the results
		while(spaceResults.next()) {
			Space space = mapRowToSpace(spaceResults);
			spaces.add(space);
		}
		
		return spaces;
		
	}

	@Override
	public List<Space> retrieveAvailableSpaces(LocalDate startingDate, int numberOfDays, int expectedAttendance,
			long venueID) {
	
	LocalDate endingDate = startingDate.plusDays(numberOfDays);
	List<Space> spaceRequest = new ArrayList<Space>();
		
	String sqlRetrieveAvailableSpaces = "SELECT space.id, space.venue_id, space.name, space.is_accessible, space.open_from, space.open_to, CAST(space.daily_rate AS decimal), space.max_occupancy FROM space " +
            "JOIN venue ON venue.id = space.venue_id " +
            "WHERE venue_id = ? " +
            "AND max_occupancy >= ? " +
            "AND NOT EXISTS (SELECT * FROM reservation " +
            "WHERE (CAST(? AS DATE) BETWEEN reservation.start_date AND reservation.end_date " +
            "OR CAST(? AS DATE) BETWEEN reservation.start_date AND reservation.end_date) " +
            "AND reservation.space_id = space.id) " +
            "AND ((EXTRACT(MONTH from CAST(? AS DATE)) BETWEEN space.open_from AND space.open_to) OR space.open_from IS NULL AND space.open_to IS NULL) " +
            "AND ((EXTRACT(MONTH from CAST(? AS DATE)) BETWEEN space.open_from AND space.open_to) OR space.open_from IS NULL AND space.open_to IS NULL) " +
            "GROUP BY space.id " +
            "ORDER BY space.daily_rate ASC " +
            "LIMIT 5";
    SqlRowSet spaceResults = jdbcTemplate.queryForRowSet(sqlRetrieveAvailableSpaces, venueID, expectedAttendance, startingDate, endingDate, startingDate, endingDate);
	
    while(spaceResults.next()) {
		Space space = mapRowToSpace(spaceResults);
		spaceRequest.add(space);
    }
    
    return spaceRequest;
    
    
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
