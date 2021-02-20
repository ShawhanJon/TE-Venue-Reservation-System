package com.techelevator;

import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.*;
import com.techelevator.model.dao.*;
import com.techelevator.model.dao.jdbc.*;
import com.techelevator.model.*;
import com.techelevator.view.*;

public class ExcelsiorCLI {
	
	private static final String List_Venues = "1";
	private static final String Quit = "Q";
	

	private Menu menu;
	private ReservationDAO reservationDAO;
	private SpaceDAO spaceDAO;
	private VenueDAO venueDAO;
	

	public static void main(String[] args) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/excelsior-venues");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");

		ExcelsiorCLI application = new ExcelsiorCLI(dataSource);
		application.run();
	}

	public ExcelsiorCLI(DataSource datasource) {
		
		this.menu = new Menu();
		
//		**MIGHT BREAK **
		
		reservationDAO = new JDBCReservationDAO(datasource);
		spaceDAO = new JDBCSpaceDAO(datasource);
		venueDAO = new JDBCVenueDAO(datasource);
		
	}

	public void run() {
		
		boolean running = true;
		
		while (running) {
			String choice = menu.printMainMenu();
			
			if (choice.equals(List_Venues)) {
				
				List<Venue> venueList = venueDAO.getAllVenues();
				menu.printAllVenues(venueList);
			
					handleSubMenu();
				
			}
			else if (choice.toUpperCase().contentEquals(Quit)) {
				break;
			}
			else {
				menu.printMessage("Invalid Input. Try Again!");
			}
		}
		

	}
	
	
	private void handleSubMenu() {
		
		 boolean isRunning = true;
		 while (isRunning) {
			 
			 String subMenuChoice = menu.venuesMenu();
			 
			 if(subMenuChoice.equals(List_Venues)) {
				 
				 String venueChoice = menu.getVenueIdFromUser();
				/* venueChoice = Integer.parseInt(subMenuChoice); */ 
				/* Venue venue = venueDAO.selectVenueById(venueChoice); */
				/*
				 * menu.printVenue(venue);
				 */
			 }
			 
			 
			/*
			 * List<Venue> venues = venueDAO.getAllVenues(); menu.printAllVenues(venues);
			 */
		 }
		
	}
}
