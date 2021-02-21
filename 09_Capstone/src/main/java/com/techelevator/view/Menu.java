package com.techelevator.view;


import java.text.DateFormatSymbols;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

import com.techelevator.model.*;

public class Menu {
	
	public static final String MAIN_MENU_DISPLAY_VENUES = "1";
	public static final String VENUE_DETAILS_MENU_VIEW_SPACES = "1";
	public static final String RESERVE_A_SPACE_OPTION = "1";
	public static final String MENU_QUIT_OPTION = "Q";

	Scanner in = new Scanner(System.in);

	public String mainMenu() {
		System.out.println("What would you like to do?");
		System.out.println(MAIN_MENU_DISPLAY_VENUES + ") List Venue");
		System.out.println(MENU_QUIT_OPTION + ") Quit");

		return in.nextLine();
	}

	public String allVenuesMenu(List<Venue> venues) {

		System.out.println("Which venue would you like to view?");
		listVenues(venues);
		System.out.println("R) Return to Previous Screen");

		return in.nextLine();
	}

	public String venueDetailsMenu(Venue venue) {
		System.out.println(venue.getName());
		System.out.println("Location: " + venue.getCity_name() + ", " + venue.getState_abbreviation());
		System.out.println("Categories: " + venue.getCategory_name());
		System.out.println();
		System.out.println(venue.getDescription());
		System.out.println();
		System.out.println("What would you like to do next?");
		System.out.println(VENUE_DETAILS_MENU_VIEW_SPACES + ") View Spaces");
		System.out.println("R) Return to Previous Screen");

		return in.nextLine();
	}

	public String spaceDetailsMenu(List<Space> spaces) {
		System.out.println(spaces.get(0).getVenueName() + "Spaces");
		System.out.println();
		System.out.printf("%-5s %-25s %-10s %-10s %-20s %-10s", "", "Name", "Open", "Close", "Daily Rate",
				"Max. Occupancy");
		System.out.println();
		listSpaces(spaces);
		System.out.println();
		System.out.println("What would you like to do next?");
		System.out.println("     1) Reserve a Space");
		System.out.println("     R) Return to Previous Screen");

		return in.nextLine();
	}

	public String reservationStartDate() {
		System.out.println("When do you need the space?");

		return in.nextLine();
	}

	public String numberOfReservationDays() {
		System.out.println("How many days will you need the space?");

		return in.nextLine();
	}

	public String numberOfAttendees() {
		System.out.println("How many people will be in attendance?");

		return in.nextLine();
	}

	public String numberOfSpacesBeingReserved(List<Space> spaces, int numberOfDays) {
		System.out.println("The following spaces are available based on your needs:");
		System.out.println();
		System.out.println();
		System.out.printf("%-10s %-30s %-15s %-15s %-15s %-10s%n", "Space #", "Name", "Daily Rate", "Max Occup.",
				"Accessible?", "Total Cost");
		listSpacesAvailable(spaces, numberOfDays);
		System.out.println();
		System.out.println("Which space would you like to reserve (enter 0 to cancel)?");

		return in.nextLine();
	}

	public String whoIsThisReservationFor() {
		System.out.println("Who is this reservation for?");

		return in.nextLine();
	}

	public void displayConfirmationMenu(Reservation reservation) {
		long daysBetween = ChronoUnit.DAYS.between(reservation.getStartingDate(), reservation.getEndingDate());

		System.out.println("Thanks for submitting your reservation! The details for your event are listed below:");
		System.out.println();

		System.out.println("Confirmation #:  " + reservation.getReservationId());
		System.out.println("Venue:  " + reservation.getVenueName());
		System.out.println("Space:  " + reservation.getSpaceName());
		System.out.println("Reserved For:  " + reservation.getReservedFor());
		System.out.println("Attendees:  " + reservation.getNumberOfAttendees());
		System.out.println("Arrival Date:  " + reservation.getStartingDate());
		System.out.println("Depart Date:  " + reservation.getEndingDate());
		System.out.println("Total Cost:  $" + (reservation.getDailyRate() * daysBetween) );
		System.out.println();
		System.out.println();
		System.out.println();
	}

	public void displayUserMessage(String message) {
		System.out.println(message);
	}

	private void listVenues(List<Venue> venues) {
		System.out.println();
		if (venues.size() > 0) {
			for (Venue ven : venues) {
				System.out.println(ven.getId() + ") " + ven.getName());
			}
		}
	}

	private void listSpaces(List<Space> spaces) {
		for (Space space : spaces) {
			System.out.printf("%-5s %-25s %-10s %-10s %-20s %-10s%n", "#" + space.getId(), space.getName(),
					getMonth(space.getOpenFrom()), getMonth(space.getOpenTo()), "$" + space.getDailyRate(),
					space.getMaxOccupancy());
		}
	}

	private String getMonth(int month) {
		if (month == 0) {
			return "";
		} else
			return new DateFormatSymbols().getMonths()[(month - 1)];
	}

	private void listSpacesAvailable(List<Space> spaces, int numberOfDays) {
		for (Space space : spaces) {
			System.out.printf("%-10s %-30s %-15s %-15s %-15s %-10s%n", space.getId(), space.getName(),
					"$" + space.getDailyRate(), space.getMaxOccupancy(), space.isAccessible(),
					"$" + (space.getDailyRate() * numberOfDays));
		}
	}
	

//	/**
//	 * The Menu class is responsible for printing a simple menu for our application
//	 * and capturing user input. This acts as the User Interface for our app.
//	 * 
//	 * @author Java Green Team 6 is the best!
//	 *
//	 */
//
//	private Scanner scanner;
//
//	public Menu() {
//		scanner = new Scanner(System.in);
//	}
//
//	/**
//	 * This method prints the main menu to the console
//	 * 
//	 * @return user choice
//	 */
//
//	public String printMainMenu() {
//
//		System.out.println("***************************");
//		System.out.println("Excelsior Venues App");
//		System.out.println("***************************\n");
//
//		System.out.println("What would you like to do?\n");
//
//		System.out.println("1) List Venues");
//		System.out.println("Q) Quit\n");
//
//		return scanner.nextLine();
//
//	}
//
//	/**
//	 * This method prints out the Venue Menu (Option 1 from main menu)
//	 * 
//	 * @return user choice
//	 */
//	public String venuesMenu() {
//
//		System.out.println("\nWhich venue would you like to view?\n");
//		System.out.println("Please select your choice:");
//		System.out.println("R) Return to Previous Screen");
//
//		return scanner.nextLine();
//
//	}
//
//	public String venueDetailsMenu() {
//
//		System.out.println("What would you like to do next?\n");
//		System.out.println("1) View Spaces");
//		System.out.println("2) Search for a Reservation");
//		System.out.println("R) Return to Previous Screen");
//
//		return scanner.nextLine();
//	}
//
//	public String venueSpacesMenu() {
//
//		System.out.println("What would you like to do next?");
//		System.out.println("1) Reserve a Space");
//		System.out.println("R) Return to Previous Screen");
//
//		return scanner.nextLine();
//
//	}
//
//	public String reserveASpaceMenu() {
//
//		System.out.println("When do you need the space(mm/dd/yyyy)?");
//		return scanner.nextLine();
//		/*
//		 * //need to figure how to scan in user input for 'How many days will you need
//		 * the space?' and 'How many people will be in attendance?
//		 */
//	}
//
//	public void printAllVenues(List<Venue> venues) {
//		System.out.println();
//		if (venues.size() > 0) {
//			for (Venue venue : venues) {
//				System.out.println(venue.getId() + ") " + venue.getName());
//			}
//		}
//	}
//
//	public String getVenueIdFromUser() {
//
//		System.out.println("\nPlease enter venue id number:");
//		return scanner.nextLine();
//
//	}
//
//	public void printVenue(Venue venue) {
//
//		System.out.println(venue.getName());
//		System.out.println(venue.getCity_name() + ", " + venue.getState_abbreviation());
//		System.out.println(venue.getCategory_name());
//		System.out.println(venue.getDescription());
//
//	}
//	
//
////	/**
////	 * This method prompts the user to enter a valid MLS Numbe and returns the
////	 * user's input
////	 * 
////	 * @return String
////	 */
////	public String getMLSNumberFromUser() {
////
////		System.out.println("\nPlease enter a valid MLS number");
////		return scanner.nextLine();
////
////	}
////	
////	public String promptUserForMLSNumberToDelete() {
////		
////		System.out.println("\nPlease enter a MLS number for the home you wish to delete:");
////		return scanner.nextLine();		
////		
////	}
////	
////
////
////
////    /**
////     * This method prints out a list of homes 
////     * @param List<Home> homesToPrint - An ArrayList containing our list of homes 
////    */
////	public void printListOfHomes(List<Home> homesToPrint) {
////
////		
////		System.out.println("\n*********** Listing Results ************\n");
////		
////		
////		if (homesToPrint.isEmpty()) {
////			System.out.println("No Results Found!");
////			return;
////		}
////
////		for (Home home : homesToPrint) {
////			
////            printHome(home);
////
////		}
////
////	}
////	
////	
////	/**
////	 * This method prints out a single home.  We 'could' have put this up in the for each loop above, but breaking
////	 * it out on it's own keeps the code above cleaner AND we can also reuse when printing out the home from menu option 2
////	 *  
////	 * @param home
////	 */
////	public void printHome(Home home) {
////		
////		if (home == null) {
////			System.out.println("No results found... Pleast try again.");
////			return;
////		}
////		
////		System.out.println("MLS Number: " + home.getMlsNumber());
////
////		if (home.getAddress() != null) {
////			System.out.println("Street Address: " + " "
////					+ home.getAddress().getAddressLine() + " " + home.getAddress().getCity() + " "
////					+ home.getAddress().getState() + " " + home.getAddress().getZipCode() + "\n");
////		}
////		
////		System.out.println(String.format("%-25s %s", "Bedrooms: ", home.getNumberOfBedrooms()));
////		System.out.println(String.format("%-25s %s", "Bathrooms: ", home.getNumberOfBathrooms()));
////		System.out.println(String.format("%-25s %s", "Description: ", home.getShortDescription()));
////		System.out.println(String.format("%-25s $%s", "Price: ", String.format("%.2f", home.getPrice())));
////		
////		System.out.println("\n*********** *** *** ***  ************\n");		
////		
////	}
////	
////	
////	public Home getHomeInfo() {
////		
////		Home home = new Home();
////		
////		System.out.println("Enter the MLS Number this house is being listed under");
////		String mls = scanner.nextLine();
////		home.setMlsNumber(mls);
////		
////		
////		//call private method below to get the address from user
////		Address address = getAddressInfo();
////		home.setAddress(address);
////		
////		
////		System.out.println("Enter the number of Bedrooms: ");
////		String numberOfBedrooms = scanner.nextLine();
////		home.setNumberOfBedrooms(Double.parseDouble(numberOfBedrooms));
////		
////		System.out.println("Enter the number of Bathrooms: ");
////		String numberOfBathrooms = scanner.nextLine();
////		home.setNumberOfBathrooms(Double.parseDouble(numberOfBathrooms));
////		
////		
////		System.out.println("Enter a short description of the property: ");
////		home.setShortDescription(scanner.nextLine());
////		
////		System.out.println("What is the listing price of this property?");
////		String priceAsString = scanner.nextLine();
////		//home.setPrice(Double.parseDouble(priceAsString));
////		home.setPrice(new BigDecimal(priceAsString));
////		
////		
////		
////		return home;
////		
////	}
////	
////	
//	public void printMessage (String message) {
//		System.out.println(message);
//	}
////
////	
////	private Address getAddressInfo() {
////		Address address = new Address();
////		
////		
////		System.out.println("\nEnter address Line 1: ");
////		address.setAddressLine(scanner.nextLine());
////		
////		System.out.println("\nEnter the city name: ");
////		address.setCity(scanner.nextLine());
////		
////		System.out.println("\nEnter the state: ");
////		address.setState(scanner.nextLine());
////		
////		System.out.println("\nEnter the zip code: ");
////		String zip = scanner.nextLine();
////		address.setZipCode(Integer.parseInt(zip));
////		
////		return address;
////	}
	
	

}
