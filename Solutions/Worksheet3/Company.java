// Umut Geyik - 120200145 - CMPE 261.01/0102
package worksheet03;

import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

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
	// It takes 2 parameters; company's area and company's capital
	public double profit(String area, double capital) {
		double total = 0;
		try {
			// If the company's area is unknown, we cannot calculate the profit
			if (area == "" || area == " " || (area.isBlank()))
				JOptionPane.showMessageDialog(null, "Company's area cannot be empty!", "Wrong Input", JOptionPane.ERROR_MESSAGE);
			
			/* If it is a tech company, base total profit is 80k
			 * If it is a health company, base total profit is 120k
			 * If it is a mechanic company, base total profit is 150k
			 * If it is a food company, base total profit is 200k
			 * If it is not any of them, base total profit is 50k
			*/
			if (area == "Tech" || area == "tech")
				total = 80000;
			else if (area == "Health" || area == "health")
				total = 120000;
			else if (area == "Mechanic" || area == "mechanic")
				total = 150000;
			else if (area == "Food" || area == "food")
				total = 200000;
			// Here is the calculation of annually profit
			total -= (total * 0.05) - (capital * 0.18);
		} catch (Exception e) {
			// If any error occurred during the calculation, show it to the user via message dialog
			JOptionPane.showMessageDialog(null, e.getMessage(), "An Error Has Occurred", JOptionPane.ERROR_MESSAGE);
		}
		
		return total;
	}
	
	// If a company wants to do shipping, calculate the cost
	// Logistic method has 2 parameters
	// Distance's unit is kilometer, and cost's unit is USD
	public double logistic(double distance, boolean membership) {
		double cost = 20;
		try {
			// World's diameter is 12743 km. So, distance cannot exceed 12743 and
			// cannot be negative number
			if (distance < 0 || distance > 12743)
				JOptionPane.showMessageDialog(null, "You are living in the World!", "Unrealistic Input", JOptionPane.ERROR_MESSAGE);
			else if (membership == true)
				cost += (distance * 0.5) + 50;
			else if (membership == false)
				cost += (distance * 0.70) + 50;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "An Error Has Occurred", JOptionPane.ERROR_MESSAGE);
		}
		
		return cost;
	}
	
	// Let's check if the company's name is already taken.
	// Compare 2 companies' names and return true if it is not taken
	// If it is taken, return false
	public boolean nameCheck(String comp1, String comp2) {
		try {
			// If one of the companies' name is empty, give an error
			if (comp1.isBlank() || comp2.isBlank()) {
				JOptionPane.showMessageDialog(null, "One of the companies' name is missing!", "Wrong Name Input", JOptionPane.ERROR_MESSAGE);
			}
				
			if (comp1 == comp2) 
				return false;
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "An Error Has Occurred", JOptionPane.ERROR_MESSAGE);
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
				JOptionPane.showMessageDialog(null, "One of the components is empty or invalid!", "Wrong Name Input", JOptionPane.ERROR_MESSAGE);
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "An Error Has Occurred", JOptionPane.ERROR_MESSAGE);
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
		scan.close();
			try {
				// If the user didn't give Y or N answer, exit the game
				if (answer == 'Y' || answer == 'y' || answer == 'n' || answer == 'N') {
					// If the answer was Y, start the game. Guess the number between 0-5
					// If user's prediction is correct, set the given value to target comp's stock value
					// Else, terminate the game
					if (answer == 'Y' || answer =='y') {
						Random rand = new Random();
						int num = rand.nextInt(6);
						int guess = Integer.parseInt(JOptionPane.showInputDialog(null, "I picked a number between 0-5. What is your guess?",
								"????????????????"));
						
						if (num != guess) 
							JOptionPane.showMessageDialog(null, "Game is over!", "Game Abort", JOptionPane.INFORMATION_MESSAGE);
							
						else {
							double stockVal = Double.parseDouble(JOptionPane.showInputDialog(null, "Congratulations! Your prediction was correct.\n"
									+ "So, what is the new stock value?", "Winner winner, chicken dinner!"));

							
							if (stockVal < 0) {
								JOptionPane.showMessageDialog(null, "Cannot set it to negative decimal/number.", 
										"Game Abort", JOptionPane.INFORMATION_MESSAGE);
							}
								
							else {
								comp.setStockValue(stockVal);
								System.out.println(comp.name + "'s stock value is now " + stockVal);
								JOptionPane.showMessageDialog(null, comp.name + "'s stock value is now " + stockVal, 
										"Game Completed", JOptionPane.INFORMATION_MESSAGE);
							}	
						}
					}
					if (answer == 'N' || answer == 'n') 
						JOptionPane.showMessageDialog(null, "Terminating the game...", "Game Abort", JOptionPane.INFORMATION_MESSAGE);
					
				}
				else {
					JOptionPane.showMessageDialog(null, "You miss the boat! Goodbye.", 
							"WHAT THE F#%K? YOU'RE A DUMB.", JOptionPane.WARNING_MESSAGE);
				}
					
					
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "An Error Has Occurred", JOptionPane.ERROR_MESSAGE);
			}
	}
	
}
