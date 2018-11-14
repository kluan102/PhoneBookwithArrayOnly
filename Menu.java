package phonebook2;

import java.util.Scanner;

public class Menu {
	private static Scanner input=new Scanner(System.in);
	public static int menuChoice() {
		System.out.println("-------------------------------------------------------------------------------------------------");
		System.out.println("Please choose an option  ");
		System.out.println("1) Add new contacts");
		System.out.println("2) Delete contacts");
		System.out.println("3) Search contacts");
		System.out.println("4) Update a record for a given telephone number");
		System.out.println("5) Show all records in ascending order");
		System.out.println("6) Exit this application");
		System.out.print("Enter choice here ");
		return input.nextInt();
	}
	public static int searchChoice() {
		System.out.println("-------------------------------------------------------------------------------------------------");
		System.out.println("Please select search parameter");
		System.out.println("1) Search by first name");
		System.out.println("2) Search by last name");
		System.out.println("3) Search by full name");
		System.out.println("4) Search by phone number");
		System.out.println("5) Search by city");
		System.out.println("6) Search by state");
		System.out.println("7) Search by zipcode");
		System.out.println("Any other number to return to main menu");
		System.out.print("Enter choice here ");
		return input.nextInt();
	}
	
	public static int updateChoice() {
		System.out.println("-------------------------------------------------------------------------------------------------");
		System.out.println("You have a match");
		System.out.println("Please select update parameter");
		System.out.println("1) Update first name");
		System.out.println("2) Update last name");
		System.out.println("3) Update phone number");
		System.out.println("4) Update address (e.g. adress, city, state, zipcode)");
		System.out.println("Any other number to return to main menu");
		System.out.print("Enter choice here ");
		return input.nextInt();
	}

}
