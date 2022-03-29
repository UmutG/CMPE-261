// Umut Geyik - 120200145 - CMPE261.02/0102
package worksheet06;
import java.util.concurrent.locks.ReentrantLock;

// Let's create proper Philosopher class to let them feed their bellies!
// Extend the Philosopher class with Thread to run multiple threading
public class Philosopher extends Thread {

	// Create necessary class attributes
	private int serve;
	private Pasta pasta;
	private ReentrantLock serveLock;
	
	// Default constructor. Also, single serving method.
	public Philosopher(int serve, Pasta pasta) {
		this.serve = serve;
		this.pasta = pasta;
	}
	
	// If they want to have a feast, we have to take orders one-by-one. Cannot feed them if we don't have any pasta :(
	public String servePhs(ReentrantLock serveLock) {
		
		String info = "";
		this.serveLock = serveLock;
		
		// Lock the serve. We have an order!
		serveLock.lock();
		info = getName() + " =>\t" + pasta.getServe(serve);
		
		// Great. We made him/her happy. Let's get another order.
		serveLock.unlock();
		
		// Inform the customer about the order.
		return info;
	}
	
	@Override
	public void run() {
		// Single serving. You'll get what you ordered.
		super.run();
		String info = getName() + " =>\t" + pasta.getServe(serve); 
		System.out.println(info);
	}

	// Gets and sets methods. We may need them.
	public int getServe() {
		return serve;
	}

	public void setServe(int serve) {
		this.serve = serve;
	}

	public Pasta getPasta() {
		return pasta;
	}

	public void setPasta(Pasta pasta) {
		this.pasta = pasta;
	}

	public ReentrantLock getServeLock() {
		return serveLock;
	}

	public void setServeLock(ReentrantLock serveLock) {
		this.serveLock = serveLock;
	}

}
