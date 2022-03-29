
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
	
	// n:         number that'd be queried 
	// div:       divisor  
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

	}
	
	// Check whether the number is divisible by the rest of the integers
	public synchronized void checkFactorRest() {

		// *** Fill this method for PART 2 ***
		
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
}

// Thread for checking divisibility by the rest of the integers
class FactorRest implements Runnable {
	
	// *** Fill this class for PART 4 ***
}


