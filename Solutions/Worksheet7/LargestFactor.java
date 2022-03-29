// Umut Geyik - 120200145 - CMPE261.02/0102
package worksheet07;

public class LargestFactor {

	// Do not change this method
	public int testLargestFactor(int n) {
		FactorChecker fc = new FactorChecker(n, 2, 1);

		Thread t1 = new Thread(new Factor2(fc));
		Thread t2 = new Thread(new FactorRest(fc));

		t1.start();
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}

		return fc.getMaxFactor();
	}
}

class FactorChecker {

	// n: number that'd be queried
	// div: divisor
	// maxFactor: maximum factor of the number (except than itself)
	private int n, div;
	private int maxFactor;

	public FactorChecker(int n, int div, int maxFactor) {
		this.n = n;
		this.div = div;
		this.maxFactor = maxFactor;
	}

	// Check whether the number is divisible by the multipliers of two (e.g. 2,4,6,...)
	public synchronized void checkFactor2() {
		
		// *** Fill this method for PART 1 ***
		// We have to check multipliers of two of given number.
		// We can start the loop from 2 and increase i by 2 until the given number. 
		for (int i = 2; i < n; i += 2) {
			if (n % i == 0) {
				// If the given number divisible by i, print it out
				System.out.println(n + " is divisible by " + i + " (even)");
				// Make the current number maxFactor if it is bigger than current maxFactor
				if (i > maxFactor)
					maxFactor = i;
			}

		}
		// Notify threads
		notifyAll();
	}

	// Check whether the number is divisible by the rest of the integers
	public synchronized void checkFactorRest() {

		// *** Fill this method for PART 2 ***
		// We have to check the given number is divisible by the rest of the numbers
		// We can start the loop from 3 and increase it by 2 until the given number. 
		for (int i = 3; i < n; i += 2)
			if (n % i == 0) {
				System.out.println(n + " is divisible by " + i + " (odd)");
				// From checkFactor2, we have the largest factor from there but we may have
				// bigger number than the largest factor from checkFactor2. So, let's check it.
				if (i > maxFactor)
					maxFactor = i;
			}
		// Print out the largest factor.
		System.out.println(maxFactor + " is the largest factor.");
		// Notify threads
		notifyAll();
	}

	public int getN() {
		return n;
	}

	public int getDiv() {
		return div;
	}

	public int getMaxFactor() {
		return this.maxFactor;
	}

	public void setDiv(int div) {
		this.div = div;
	}
}

// Thread for checking divisibility by multipliers of 2
class Factor2 implements Runnable {

	// *** Fill this class for PART 3 ***
	
	// Create a FactorChecker object to use it on run method
	FactorChecker fc;

	// Create Factor2 constructor
	public Factor2(FactorChecker fc) {
		// Get the given FactorChecker
		this.fc = fc;
	}

	public void run() {
		try {
			// Put thread to sleep for 0.45 second
			Thread.currentThread().sleep(450);
			
			// Call the checkFactor2 method.
			fc.checkFactor2();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.err.println("Error has occurred: " + e.getMessage());
		}

	}
}

// Thread for checking divisibility by the rest of the integers
class FactorRest implements Runnable {

	// *** Fill this class for PART 4 ***
	
	// Create a FactorChecker object to use it on run method
	FactorChecker fc;
	
	// Create Factor2 constructor
	public FactorRest(FactorChecker fc) {
		// Get the given FactorChecker
		this.fc = fc;
	}

	public void run() {
		try {
			// Put thread to sleep for 0.45 second
			Thread.currentThread().sleep(450);
			
			// Call the checkFactorRest method.
			fc.checkFactorRest();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.err.println("Error has occurred: " + e.getMessage());
		}
	}
}
