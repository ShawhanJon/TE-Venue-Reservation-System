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
	public List<Venue> getAllVenues() {

		List<Venue> venues = new ArrayList<Venue>();

		SqlRowSet rows = jdbcTemplate.queryForRowSet(
				"SELECT venue.id AS venue_id, venue.name AS venue_name, venue.city_id AS city_id, venue.description AS venue_description, city.name AS city_name, city.state_abbreviation AS state_abbreviation, STRING_AGG(category.name, ',' ) AS category_name FROM venue"
						+ " JOIN city ON venue.city_id = city.id"
						+ " LEFT JOIN category_venue ON venue.id = category_venue.venue_id"
						+ " LEFT JOIN category ON category_venue.category_id = category.id"
						+ " GROUP BY venue.id, city.name, city.state_abbreviation");

		while (rows.next()) {
			Venue venue = mapRowToVenue(rows);
			venues.add(venue);
		}
		return venues;
	}

	@Override
	public Venue selectVenueById(long id) {
		String selectSql = "SELECT venue.id AS venue_id, venue.name AS venue_name, venue.city_id AS city_id, venue.description AS venue_description, city.name AS city_name, city.state_abbreviation AS state_abbreviation, STRING_AGG(category.name, ',' ) AS category_name FROM venue"
				+ " JOIN city ON venue.city_id = city.id"
				+ " LEFT JOIN category_venue ON venue.id = category_venue.venue_id"
				+ " LEFT JOIN category ON category_venue.category_id = category.id" + " WHERE venue.id = ?"
				+ " GROUP BY venue.id, city.name, city.state_abbreviation";

		SqlRowSet rows = jdbcTemplate.queryForRowSet(selectSql, id);

		Venue venue = null;
		while (rows.next()) {
			venue = mapRowToVenue(rows);
		}

		return venue;
	}

	private Venue mapRowToVenue(SqlRowSet row) {
		Venue venue = new Venue();

		venue.setId(row.getInt("venue_id"));
		venue.setName(row.getString("venue_name"));
		venue.setCity_id(row.getInt("city_id"));
		venue.setDescription(row.getString("venue_description"));
		venue.setCity_name(row.getString("city_name"));
		venue.setState_abbreviation(row.getString("state_abbreviation"));
		venue.setCategory_name(row.getString("category_name"));

		return venue;
	}
	
	
	
		public void save(Venue venue) {
		String insertSql = "INSERT INTO venue (id, name, city_id, description) VALUES (DEFAULT, ?, ?, ?) RETURNING id";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(insertSql, venue.getName(), venue.getCity_id(), venue.getDescription() );
		rows.next();
		int id = rows.getInt("id");
		venue.setId(id);
		}
		
		
		
		
		/*
	 * String sqlInsertVenue = "INSERT INTO venue(id, name, city_id, description " +
	 * "VALUES(?, ?, ?, ?)"; newVenue.setId(getNextVenueId());
	 * jdbcTemplate.update(sqlInsertVenue, newVenue.getId(), newVenue.getName(),
	 * newVenue.getCity_id(), newVenue.getDescription()); }
	 */
	
/*
 * private int getNextVenueId() { SqlRowSet nextIdResult =
 * jdbcTemplate.queryForRowSet("SELECT nextval('venue_id_seq')");
 * if(nextIdResult.next()) { return nextIdResult.getInt(1); } else { throw new
 * RuntimeException("Something went wrong while getting an id for the new venue"
 * ); } } }
 */
}