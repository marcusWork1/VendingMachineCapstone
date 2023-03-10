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

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

public class VendingMachineCLI  {

/// start of code, our "main"
	// our attributes we defined and will use in various methods throughout

	// Main menu options defined as constants
	 private double totalBalance;
	private double currentBalance;
	private double remainingMoney;
	private String price;
	private String itemName;
	private String itemType;
	private Map<String, List <String>> inventoryLog = new TreeMap<>();
	private List <String> locationElements;
	private List <String> chosenItemInfo;
	private int remainingStock = 5;
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

	private final Menu vendingMenu;              // Menu object to be used by an instance of this class

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
// our main menu made up of switch and case statements to follow the users next selection in the vending machine

	public void run() throws IOException {
		stockInventory();

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
	// stock inventory to hold our Map of inventory information held by the stock list
	public void stockInventory() throws FileNotFoundException {
// creating a file object to hold the file before scanning
		File theFile = new File("vendingmachine.csv");

		// scan inventory file
		try (Scanner fileScanner = new Scanner(theFile)) {



// if line is not empty continue while loop
			while (fileScanner.hasNextLine()) {

				// read line in file
				String line = fileScanner.nextLine();

				// split line up into separate portions
				String[] vendingDetails = line.split(",");

				// assign variables to each line item
				String location = vendingDetails[0];
				String itemName = vendingDetails[1];
				String price = vendingDetails[2];
				String itemType = vendingDetails[3];

// create map that holds a string(Location) and an ArrayList(rest of info)
				List<String> locationElements = new ArrayList();
				locationElements.add(0, itemName);
				locationElements.add(1, price);
				locationElements.add(2, itemType);
				locationElements.add(3, "5");
				// locationElements.set(3, remainingStock);
// add ArrayList locationElements to map value.
				inventoryLog.put(location, locationElements);


			}
		}
	}

		public void displayItems () throws IOException
		{
			// Code to display items from Vending Machine

			// BufferedReader reads file
			BufferedReader readFile = new BufferedReader(new FileReader("vendingmachine.csv"));
			// string called line to hold what is being read
			String line;
			// while loop for when file has next line
			while ((line = readFile.readLine()) != null) {
				// displaying the list line by line
				System.out.println(line);
			}

		} // END OF DISPLAYITEMS

		public void purchaseItems () throws IOException
		{    // purchase menu

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


		} // END OF PURCHASE ITEMS

		public void moneyEntered () throws IOException {

			// scanner to collect user input
			Scanner moneyGiven = new Scanner(System.in);
			// variable to hold total balance each time
			// double totalBalance = 0;
			// boolean to establish if adding more money
			boolean moreMoney = true;
			// while loop if we are adding more money
			while (moreMoney) {
				System.out.println("Feed Money. Please use $1, $2, $5, $10.");
				// variable to hold balance collected each time
				double currentBalance = Integer.parseInt(moneyGiven.nextLine());
				// total balance defined
				totalBalance += currentBalance;
				// making sure the amount of money is stopped at 2 decimals
				String.format("%.2f", totalBalance);
				System.out.println("Would you like to enter more money? (Y/N)");

				// more money is false, break the while-loops
				// ignores case makes case-insensitive
				if (moneyGiven.nextLine().equalsIgnoreCase("n")) {
					moreMoney = false;
				}
			}
			// display balance along with the menu
			System.out.println("Current Money Provided: " + "$" + totalBalance);

		}

		public String SelectProduct ()throws IOException {
		// display the inventory log Map
			System.out.println(inventoryLog);


			// output to user
			System.out.println("Please make a selection, enter a location, or return to main menu (M)");

			// user input
			Scanner locationEntered = new Scanner(System.in);

			// location entered in uppercase
			String snackLocationEntered = locationEntered.nextLine().toUpperCase();

			// if statement for if inventory log contains the location provided via scanner
			if (inventoryLog.containsKey(snackLocationEntered)) {
				// get the value in the inventoryLog Map for the key entered
				inventoryLog.get(snackLocationEntered).get(3);

				// variable for the stock remaining string converted into an int
				int test = Integer.parseInt(inventoryLog.get(snackLocationEntered).get(3));
				test--; //  stock remaining minus what its currently at
				if (test >= 0) { // if stock remaining is greater than or equal to 0

					// turning stock remaining back into a string to insert it back in the List
					String strTest = test + "";
					// inserting stock remaining back into List
					inventoryLog.get(snackLocationEntered).set(3, strTest);

					/// totalBalance minus the price(From list), is the new total balance
					totalBalance -= Double.parseDouble(inventoryLog.get(snackLocationEntered).get(1));

					// if total balance - price is over 0, you can purchase
					if (totalBalance >= 0) {


						// double remainingMoney = totalBalance - price;
						System.out.println("Found it! You selected " + inventoryLog.get(snackLocationEntered).get(0) + " which costs " + inventoryLog.get(snackLocationEntered).get(1) + " and you have " + totalBalance + " money remaining");


					} else {
						System.out.println("Insufficient Funds"); // totalBalance is less than 0 after purchase
					}
					// reduce the stock
					// subtract the price
					// dispense the itemm

				} else { // if product has 0 stock remaining
					test = 0;
					System.out.println("SOLD OUT");// stock remaining is 0, product not available
					System.out.println("Current Money Provided: " + "$" + totalBalance);
					purchaseItems();
				}

			} else {
				System.out.println("Product not Found" + "\n" + "Current Money Provided: " + "$" + totalBalance); // product location entered by customer not found
				purchaseItems();
			}
			if (inventoryLog.get(snackLocationEntered).contains("Chip")) {
				System.out.println(inventoryLog.get(snackLocationEntered).get(2).replace("Chip", "Crunch Crunch, Yum!"));
			}
			if (inventoryLog.get(snackLocationEntered).contains("Candy")) {
				System.out.println(inventoryLog.get(snackLocationEntered).get(2).replace("Candy", "Munch Munch, Yum!"));
			}
			if (inventoryLog.get(snackLocationEntered).contains("Gum")) {
				System.out.println(inventoryLog.get(snackLocationEntered).get(2).replace("Gum", "Chew Chew, Yum!"));
			}
			if (inventoryLog.get(snackLocationEntered).contains("Drink")) {
				System.out.println(inventoryLog.get(snackLocationEntered).get(2).replace("Drink", "Glug Glug, Yum!"));
			}

purchaseItems(); // return to purchase menu

return SelectProduct();
		}

	public void finishTransaction() throws IOException {
		// finish transaction and get change back and return to main menu. Did not complete this part.
		run();



	}



			public void endMethodProcessing () throws IOException { // static attribute used as method is not associated with specific object instance
				// Any processing that needs to be done before method ends
				finishTransaction();


			} // END OF METHOD PROCESSING


		} // END OF CODE