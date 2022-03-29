// Umut Geyik - 120200145 - CMPE261.02/0102
package worksheet05;

import java.util.Random;
import javax.swing.JOptionPane;

// To run different methods at the same time, we have to use 
// multiple threading and parallel execution

public class Company implements Runnable {
	// Here, we have 4 variables for each created company object
	private String comp;
	private String CEO;
	private double logistic;
	private double stockValue;;

	// Given String will set refer to company's information
	public Company(String comp) {
		this.comp = comp;
	}

	// Method will set given CEO to the company
	public String CEO(String ceo) {
		CEO = ceo;
		return ceo;
	}

	// I used previous logistic method with same calculation
	// Just added 2 commands: set cost to the logistic and return the value
	public double logistic(double distance, boolean membership) {
		double cost = 20;
		try {
			// World's diameter is 12743 km. So, distance cannot exceed 12743 and
			// cannot be negative number
			if (distance < 0 || distance > 12743)
				JOptionPane.showMessageDialog(null, "You are not living in the World!", "Unrealistic Input",
						JOptionPane.ERROR_MESSAGE);
			else if (membership == true)
				cost += (distance * 0.5) + 50;
			else if (membership == false)
				cost += (distance * 0.70) + 50;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "An Error Has Occurred", JOptionPane.ERROR_MESSAGE);
		}
		logistic = cost;
		return logistic;
	}

	// Here, I have a random object that will change given stock value
	// with random number from pool of double numbers. Also, user will
	// give an additional pool value to boost stock's value 
	public double stockChanger(double baseValue, double poolValue) {
		Random rand = new Random();
		baseValue += rand.nextDouble() + poolValue;
		stockValue = baseValue;
		return stockValue;
	}

	// I'll use run method with sleep function in order to show outputs with delay
	public void run() {
		while (true) {
			try {
				Thread.currentThread().sleep(100);
				System.out.println(comp + " # " + CEO);
				System.out.println("Logistic Cost: " + logistic);
				System.out.println("New Stock Value: " + stockValue);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
	}

	public static void main(String[] args) {

		// Let's create 2 companies
		Company comp1 = new Company("Company Name: Kleimo" + " | Area: Tech" + " | Num of Employee: 30" + " | Stock Value: 0.75074");
		Company comp2 = new Company("Company Name: Maryland Special" + " | Area: Health" + " | Num of Employee: 120" + " | Stock Value: 1.11213");

		// Let's set CEOs
		comp1.CEO("Nejdat Aydin");
		comp2.CEO("Jack Sparrow");
		
		// Calculate the company's logistic
		comp1.logistic(500, true);
		comp2.logistic(700, false);

		// Change the stock value of the stated company
		comp1.stockChanger(0.75074, 0.05);
		comp2.stockChanger(1.11213, 0.08);

		// Create threads and start their process
		Thread t1 = new Thread(comp1, "#1 Thread");
		Thread t2 = new Thread(comp2, "#2 Thread");

		t1.start();
		t2.start();

	}
}
