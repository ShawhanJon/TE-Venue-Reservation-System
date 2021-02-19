package com.techelevator.model.dao.jdbc;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.Reservation;
import com.techelevator.model.dao.ReservationDAO;

public class JDBCReservationDAO implements ReservationDAO{
	
	private JdbcTemplate jdbcTemplate;

	public JDBCReservationDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Reservation addReservation(int spaceID, int expectedAttendance, LocalDate startingDate, LocalDate endingDate,
			String reservedFor) {
		String sql = "INSERT INTO reservation (reservation_id, space_id, number_of_attendees, start_date, end_date, "
				+ "reserved_for) VALUES (DEFAULT, ?, ?, ?, ?, ?) RETURNING reservation_id";

		SqlRowSet row = jdbcTemplate.queryForRowSet(sql, spaceID, expectedAttendance, startingDate, endingDate, reservedFor);
		row.next();
		int reservationId = row.getInt("reservation_id");

		sql = "SELECT reservation_id, space.name AS space_name, venue.name AS venue_name, "
				+ "space.daily_rate AS daily_rate, number_of_attendees, start_date, end_date, reserved_for "
				+ "FROM reservation JOIN space ON reservation.space_id = space.id "
				+ "JOIN venue ON space.venue_id = venue.id WHERE reservation_id = ?";

		SqlRowSet reservationResults = jdbcTemplate.queryForRowSet(sql, reservationId);
		reservationResults.next();
		Reservation reservation = mapRowToReservation(reservationResults);
		return reservation;
		
	}

	@Override
	public List<Reservation> retrieveUpcomingReservations(int venueId) {

		List<Reservation> reservation = new LinkedList<Reservation>();

		String sql = "SELECT reservation_id, space.name AS space_name, venue.name AS venue_name, "
				+ "space.daily_rate AS daily_rate, number_of_attendees, start_date, end_date, "
				+ "reserved_for FROM reservation JOIN space on reservation.space_id = space.id JOIN "
				+ "venue on space.venue_id = venue.id WHERE venue_id = ? AND start_date BETWEEN "
				+ "CURRENT_DATE AND (CURRENT_DATE + 30) ORDER BY start_date";
		SqlRowSet reservationResults = jdbcTemplate.queryForRowSet(sql, venueId);

		while (reservationResults.next()) {
			reservation.add(mapRowToReservation(reservationResults));
		}

		return reservation;
	}

	private Reservation mapRowToReservation(SqlRowSet result) {
		Reservation reservation = new Reservation();

		LocalDate startingDate = result.getDate("start_date").toLocalDate();
		LocalDate endingDate = result.getDate("end_date").toLocalDate();
		double totalCost = result.getDouble("daily_rate") * (ChronoUnit.DAYS.between(startingDate, endingDate));

		reservation.setId(result.getInt("reservation_id"));
		reservation.setVenue(result.getString("venue_name"));
		reservation.setSpace(result.getString("space_name"));
		reservation.setTotalCost(totalCost);
		reservation.setStartingDate(startingDate);
		reservation.setEndingDate(endingDate);
		reservation.setNumberOfAttendees(result.getInt("number_of_attendees"));
		reservation.setReservedFor(result.getString("reserved_for"));

		return reservation;
	}
	
}
