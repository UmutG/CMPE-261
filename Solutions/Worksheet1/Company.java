// Umut Geyik - 120200145 - CMPE 261.01/0102
package worksheet01;

import java.util.Random;
import java.util.Scanner;

// Let's create a company class
// We need at least 3 values. Here, we have name, area, worker and stockValue variables.
public class Company {

	public String name, area;
	public int worker;
	public double stockValue;
	
	// Default constructor will have no data but worker = 1
	public Company() {
		this.name = null;
		this.area = null;
		this.worker = 1;
	}
	
	// Class constructor is here will get and set values
	public Company(String name, String area, int worker, double stockValue) {
		this.name = name;
		this.area = area;
		this.worker = worker;
		this.stockValue = stockValue;
	}

	// Getters, setters and toString() methods are down below:
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getWorker() {
		return worker;
	}

	public void setWorker(int worker) {
		this.worker = worker;
	}

	public double getStockValue() {
		return stockValue;
	}

	public void setStockValue(double stockValue) {
		this.stockValue = stockValue;
	}
	
	@Override
	public String toString() {
		return "Company name = " + name + ", area = " + area + ", employee number = " 
	+ worker + ", stock value = " + stockValue;
	}

	// Profit method is basically calculate annually profit of given company
	// It takes 2 parameters; company itself and its capital
	public double profit(Company comp, double capital) {
		double total = 0;
		try {
			// If the company's area is unknown, we cannot calculate the profit
			if (comp.area == "" || comp.area == " " || (comp.area.isBlank()))
				throw new Exception("Area cannot be empty!");
			
			/* If it is a tech company, base total profit is 80k
			 * If it is a health company, base total profit is 120k
			 * If it is a mechanic company, base total profit is 150k
			 * If it is a food company, base total profit is 200k
			*/
			if (comp.area == "Tech")
				total = 80000;
			else if (comp.area == "Health")
				total = 120000;
			else if (comp.area == "Mechanic")
				total = 150000;
			else if (comp.area == "Food")
				total = 200000;
			// Here is the calculation of annually profit
			total = total - (comp.worker * 500) + capital - (capital * 0.18);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return total;
	}
	
	// If a company wants to do shipping, calculate the cost based
	// Logistic method has 2 parameters
	// Distance's unit is kilometer, and cost's unit is USD
	public double logistic(double distance, boolean membership) {
		double cost = 20;
		try {
			// World's diameter is 12743 km. So, distance cannot exceed 12743 and
			// cannot be negative number
			if (distance < 0 || distance > 12743)
				throw new Exception("You are living in the World!");
			else if (membership == true)
				cost += (distance * 0.5) + 50;
			else if (membership == false)
				cost += (distance * 0.70) + 50;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return cost;
	}
	
	// Let's check if the company's name is already taken.
	// Compare 2 companies' names and return true if it is not taken
	// If it is taken, return false
	public boolean nameCheck(Company comp1, Company comp2) {
		try {
			// If one of the companies' name is empty, give an error
			if (comp1.name.isBlank() || comp2.name.isBlank()) {
				throw new Exception("One of the companies' name is missing!");
			}
				
			if (comp1.name == comp2.name) 
				return false;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return true;
	}
	
	// Set a CEO to stated company. It has 5 parameters
	// If magic number is lower than 20, the secret game will be initialized
	public int magic = 0;
	public String CEOofComp(Company comp, String name, String surName, int age, int exp) {
		magic = age - exp;
		
		try {
			// CEO's age cannot be lower than 15 or higher than 87.
			// Currently Hillary Yip is the youngest CEO in the world and
			// Warren Buffett is the oldest CEO in the world
			if (age < 15 || age > 87) 
				throw new Exception("Youngest CEO in the World is 15. Oldest CEO in the World is 87."
						+ "\nLet's be real!");
			// If any of the parameters' value is empty or out of bounds, give an error
			if (comp.name.isEmpty() || name.isEmpty() || surName.isEmpty() || exp < 0) {
				throw new Exception("One of the components is empty or invalid!");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		if (magic < 20 && magic >= 0)
			stockHack(comp);
		
		return comp.name + "'s new CEO is: " + name + " " + surName.toUpperCase() + ". S/he is " + age
				+ " and has " + exp + " years of experience.";
		
	}
	// This method will run if and only magic number in CEOofComp hit lower than 20 and bigger than 0
	public void stockHack(Company comp) {
		
		System.out.print("=================================\n"
				+ "Welcome To The Stock Hack Game!\n"
				+ "If you find the magic number, you will control the given company's stock value.\n"
				+ "Shall we begin? (Y/N): ");
		Scanner scan = new Scanner(System.in);
		char answer = scan.next().charAt(0);
			try {
				// If the user didn't give Y or N answer, exit the game
				if (answer == 'Y' || answer == 'y' || answer == 'n' || answer == 'N') {
					// If the answer was Y, start the game. Guess the number between 0-5
					// If user's prediction is correct, set the given value to target comp's stock value
					// Else, terminate the game
					if (answer == 'Y' || answer =='y') {
						Random rand = new Random();
						int num = rand.nextInt(6);
						System.out.print("I picked a number between 0-5. What is your guess? = ");
						int guess = scan.nextInt();
						
						if (num != guess) 
							System.out.println("Game has been terminated.");
							
						else {
							System.out.println("Congratulations! Your prediction was correct.\n"
									+ "So, what is the new stock value?");
							double stockVal = scan.nextDouble();
							scan.close();
							
							if (stockVal < 0) {
								throw new Exception("Cannot set it to negative decimal/number.\n"
										+ "Terminating the game...");
							}
								
							else {
								comp.setStockValue(stockVal);
								System.out.println(comp.name + "'s stock value is now " + stockVal);
							}	
						}
					}
					if (answer == 'N' || answer == 'n') 
						System.out.println("Terminating the game...");
					
				}
				else {
					throw new Exception("You miss the boat! Goodbye.");
				}
					
					
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
	}
	
}
