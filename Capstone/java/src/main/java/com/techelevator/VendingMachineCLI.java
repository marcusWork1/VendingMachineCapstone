package com.techelevator;
/**************************************************************************************************************************
*  This is your Vending Machine Command Line Interface (CLI) class
*
*  It is the main process for the Vending Machine
*
*  THIS is where most, if not all, of your Vending Machine interactions should be coded
*  
*  It is instantiated and invoked from the VendingMachineApp (main() application)
*  
*  Your code vending machine related code should be placed in here
***************************************************************************************************************************/
import com.techelevator.view.Menu;         // Gain access to Menu class provided for the Capstone
import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachineCLI {

	// Main menu options defined as constants

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String SECOND_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String SECOND_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String SECOND_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] SUB_MENU_OPTIONS = {SECOND_MENU_OPTION_FEED_MONEY, SECOND_MENU_OPTION_SELECT_PRODUCT, SECOND_MENU_OPTION_FINISH_TRANSACTION};
	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS,
			MAIN_MENU_OPTION_PURCHASE,
			MAIN_MENU_OPTION_EXIT
	};

	private Menu vendingMenu;              // Menu object to be used by an instance of this class

	public VendingMachineCLI(Menu menu) {  // Constructor - user will pass a menu for this class to use
		this.vendingMenu = menu;           // Make the Menu the user object passed, our Menu
	}

	/**************************************************************************************************************************
	 *  VendingMachineCLI main processing loop
	 *
	 *  Display the main menu and process option chosen
	 *
	 *  It is invoked from the VendingMachineApp program
	 *
	 *  THIS is where most, if not all, of your Vending Machine objects and interactions
	 *  should be coded
	 *
	 *  Methods should be defined following run() method and invoked from it
	 *
	 ***************************************************************************************************************************/

	public void run() throws IOException {


		boolean shouldProcess = true;         // Loop control variable

		while (shouldProcess) {                // Loop until user indicates they want to exit

			String choice = (String) vendingMenu.getChoiceFromOptions(MAIN_MENU_OPTIONS);  // Display menu and get choice

			switch (choice) {                  // Process based on user menu choice

				case MAIN_MENU_OPTION_DISPLAY_ITEMS:
					displayItems();           // invoke method to display items in Vending Machine
					break;                    // Exit switch statement

				case MAIN_MENU_OPTION_PURCHASE:
					purchaseItems();// invoke method to purchase items from Vending Machine
					break;                    // Exit switch statement

				case MAIN_MENU_OPTION_EXIT:
					endMethodProcessing();    // Invoke method to perform end of method processing
					shouldProcess = false;    // Set variable to end loop
					break;                    // Exit switch statement
			}
		}
		return;                               // End method and return to caller
	}

	/********************************************************************************************************
	 * Methods used to perform processing
	 ********************************************************************************************************/
	public void displayItems() throws IOException {      // static attribute used as method is not associated with specific object instance
		// Code to display items from Vending Machine
		BufferedReader readFile = new BufferedReader(new FileReader("vendingmachine.csv"));
		String line;
		int remainingStock = 5; // come back here to change amount remaining in stock
		while ((line = readFile.readLine()) != null) {
			// for loop to count number of items remaining
			System.out.println(line + "," + remainingStock);
		}
		if (remainingStock == 0) {
			System.out.println("SOLD OUT");
		}
		/* Scanner vendingList = new Scanner("vendingmachine.csv");
		while (vendingList.hasNextLine()) ;

		// create String to hold a line from file
		String snackDetails = vendingList.nextLine();

		// break the line from the file into values
		String[] vendingDetails = snackDetails.split(",");

		// assign vairables to each line item

		String location = vendingDetails[0];
		String itemName = vendingDetails[1];
		Double price = Double.parseDouble(vendingDetails[2]);
		String itemType = vendingDetails[3];
*/

		// if (vendingDetails[3].contentEquals("Chip")) { // for determining snack type
		// }


		// need to come back to this

	} // END OF DISPLAYITEMS

			public void purchaseItems()throws IOException {    // static attribute used as method is not associated with specific object instance

					boolean shouldProcess = true;         // Loop control variable

					while (shouldProcess) {                // Loop until user indicates they want to exit

						String choice = (String) vendingMenu.getChoiceFromOptions(SUB_MENU_OPTIONS);  // Display menu and get choice

						switch (choice) {                  // Process based on user menu choice

							case SECOND_MENU_OPTION_FEED_MONEY:
								moneyEntered();          // invoke method to take in money
								break;                    // Exit switch statement

							case SECOND_MENU_OPTION_SELECT_PRODUCT:
								SelectProduct();// invoke method to purchase items from Vending Machine
								break;                    // Exit switch statement

							case SECOND_MENU_OPTION_FINISH_TRANSACTION:
								endMethodProcessing();    // Invoke method to perform end of method processing
								shouldProcess = false;    // Set variable to end loop
								break;                    // Exit switch statement
						}
					}
					return;                               // End method and return to caller




				// Code to purchase items from Vending Machine
				// System.out.println("(1) Feed Money" + "/n" + "(2) Select Product" + "/n"  + "(3) Finish Transaction");




				// prompt user for how much money they are entering, only accept 1 2 5 10;
				// Menu moneyEntered = new Menu(System.in, System.out);
				// Scanner userInputSecondMenu = new Scanner(System.in);



				// scanner for how much money put in

			} // END OF PURCHASE ITEMS
	public void moneyEntered() throws IOException {
		 // boolean enteredDollars = true;

		// scanner to collect user input
		Scanner moneyGiven = new Scanner(System.in);
		// variable to hold total balance each time
		double totalBalance = 0;
		// boolean to establish if adding more money
		boolean moreMoney = true;
		// while loop if we are adding more money
		while (moreMoney){
		System.out.println("Feed Money. Please use $1, $2, $5, $10.");
		// variable to hold balance collected each time
		double currentBalance = Integer.parseInt(moneyGiven.nextLine());
		// total balance defined
		totalBalance += currentBalance;
		System.out.println("Would you like to enter more money?");

		// more money is false, break the while-loop
		if (moneyGiven.nextLine().toLowerCase().equals("n")) {
			moreMoney = false;
		}
		}
		// display balance along with the menu
		System.out.println("Current Money Provided: " + "$" + totalBalance);

		}

		public void SelectProduct() throws IOException {
			BufferedReader readFile = new BufferedReader(new FileReader("vendingmachine.csv"));
			String line;
			int remainingStock = 5; // come back here to change amount remaining in stock
			while ((line = readFile.readLine()) != null) {
				// for loop to count number of items remaining
				System.out.println(line + "," + remainingStock);
			}

			// if (remainingStock == 0) {
				//System.out.println("SOLD OUT");
			// }
			Scanner vendingList = new Scanner("vendingmachine.csv");
			while (vendingList.hasNextLine()) ;

			// create String to hold a line from file
			String snackDetails = vendingList.nextLine();

			// break the line from the file into values
			String[] vendingDetails = snackDetails.split(",");

			// assign vairables to each line item

			String location = vendingDetails[0];
			String itemName = vendingDetails[1];
			Double price = Double.parseDouble(vendingDetails[2]);
			String itemType = vendingDetails[3];


	}

			public void endMethodProcessing () { // static attribute used as method is not associated with specific object instance
				// Any processing that needs to be done before method ends


			} // END OF METHOD PROCESSING


		} // END OF CODE