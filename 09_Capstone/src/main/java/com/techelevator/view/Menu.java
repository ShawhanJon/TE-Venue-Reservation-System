package com.techelevator.view;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import com.techelevator.model.*;
import com.techelevator.view.*;

public class Menu {


/**
 * The Menu class is responsible for printing a simple menu for our application
 * and capturing user input. This acts as the User Interface for our app.
 * 
 * @author Java Green Team 6 is the best!
 *
 */

	private Scanner scanner;

	public Menu() {
		scanner = new Scanner(System.in);
	}

	/**
	 * This method prints the main menu to the console
	 * 
	 * @return user choice
	 */

	public String printMainMenu() {

		System.out.println("***************************");
		System.out.println("Excelsior Venues");
		System.out.println("***************************\n");

		System.out.println("What would you like to do?\n");
		
		System.out.println("1. List Venues");
		System.out.println("Q. Quit\n");

		return scanner.nextLine();

	}
	
	/**
	 * This method prints out the Venue Menu (Option 1 from main menu)
	 * @return user choice
	 */
	public String venuesMenu() {

		System.out.println("Which venue would you like to view?\n");

		
		

		System.out.println("Please select your choice (number only)");

		return scanner.nextLine();

	}
	
	public void printAllVenues (List<Venue> venues) {
		System.out.println();
		if (venues.size() > 0) {
			for (Venue venue : venues) {
				System.out.println(venue.getId() + ") "+ venue.getName());
			}
		}
	}
	
	

//	/**
//	 * This method prompts the user to enter a valid MLS Numbe and returns the
//	 * user's input
//	 * 
//	 * @return String
//	 */
//	public String getMLSNumberFromUser() {
//
//		System.out.println("\nPlease enter a valid MLS number");
//		return scanner.nextLine();
//
//	}
//	
//	public String promptUserForMLSNumberToDelete() {
//		
//		System.out.println("\nPlease enter a MLS number for the home you wish to delete:");
//		return scanner.nextLine();		
//		
//	}
//	
//
//
//
//    /**
//     * This method prints out a list of homes 
//     * @param List<Home> homesToPrint - An ArrayList containing our list of homes 
//    */
//	public void printListOfHomes(List<Home> homesToPrint) {
//
//		
//		System.out.println("\n*********** Listing Results ************\n");
//		
//		
//		if (homesToPrint.isEmpty()) {
//			System.out.println("No Results Found!");
//			return;
//		}
//
//		for (Home home : homesToPrint) {
//			
//            printHome(home);
//
//		}
//
//	}
//	
//	
//	/**
//	 * This method prints out a single home.  We 'could' have put this up in the for each loop above, but breaking
//	 * it out on it's own keeps the code above cleaner AND we can also reuse when printing out the home from menu option 2
//	 *  
//	 * @param home
//	 */
//	public void printHome(Home home) {
//		
//		if (home == null) {
//			System.out.println("No results found... Pleast try again.");
//			return;
//		}
//		
//		System.out.println("MLS Number: " + home.getMlsNumber());
//
//		if (home.getAddress() != null) {
//			System.out.println("Street Address: " + " "
//					+ home.getAddress().getAddressLine() + " " + home.getAddress().getCity() + " "
//					+ home.getAddress().getState() + " " + home.getAddress().getZipCode() + "\n");
//		}
//		
//		System.out.println(String.format("%-25s %s", "Bedrooms: ", home.getNumberOfBedrooms()));
//		System.out.println(String.format("%-25s %s", "Bathrooms: ", home.getNumberOfBathrooms()));
//		System.out.println(String.format("%-25s %s", "Description: ", home.getShortDescription()));
//		System.out.println(String.format("%-25s $%s", "Price: ", String.format("%.2f", home.getPrice())));
//		
//		System.out.println("\n*********** *** *** ***  ************\n");		
//		
//	}
//	
//	
//	public Home getHomeInfo() {
//		
//		Home home = new Home();
//		
//		System.out.println("Enter the MLS Number this house is being listed under");
//		String mls = scanner.nextLine();
//		home.setMlsNumber(mls);
//		
//		
//		//call private method below to get the address from user
//		Address address = getAddressInfo();
//		home.setAddress(address);
//		
//		
//		System.out.println("Enter the number of Bedrooms: ");
//		String numberOfBedrooms = scanner.nextLine();
//		home.setNumberOfBedrooms(Double.parseDouble(numberOfBedrooms));
//		
//		System.out.println("Enter the number of Bathrooms: ");
//		String numberOfBathrooms = scanner.nextLine();
//		home.setNumberOfBathrooms(Double.parseDouble(numberOfBathrooms));
//		
//		
//		System.out.println("Enter a short description of the property: ");
//		home.setShortDescription(scanner.nextLine());
//		
//		System.out.println("What is the listing price of this property?");
//		String priceAsString = scanner.nextLine();
//		//home.setPrice(Double.parseDouble(priceAsString));
//		home.setPrice(new BigDecimal(priceAsString));
//		
//		
//		
//		return home;
//		
//	}
//	
//	
	public void printMessage (String message) {
		System.out.println(message);
	}
//
//	
//	private Address getAddressInfo() {
//		Address address = new Address();
//		
//		
//		System.out.println("\nEnter address Line 1: ");
//		address.setAddressLine(scanner.nextLine());
//		
//		System.out.println("\nEnter the city name: ");
//		address.setCity(scanner.nextLine());
//		
//		System.out.println("\nEnter the state: ");
//		address.setState(scanner.nextLine());
//		
//		System.out.println("\nEnter the zip code: ");
//		String zip = scanner.nextLine();
//		address.setZipCode(Integer.parseInt(zip));
//		
//		return address;
//	}
	
	

}
