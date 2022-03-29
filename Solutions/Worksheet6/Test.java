// Umut Geyik - 120200145 - CMPE261.02/0102
package worksheet06;
import java.util.concurrent.locks.ReentrantLock;

// Let's test both of the classes.
public class Test {

	// Kitchen, get ready! We have customers!
	public static void main(String[] args) {
		// With serveLock, we'll take orders one-by-one.
		ReentrantLock serveLock = new ReentrantLock();
		
		// We have 2 kitchen for customers. Let's cook pasta!
		Pasta pasta = new Pasta(5);
		Pasta pasta2 = new Pasta(5);
		
		// Ph. Alice wants one serve of pasta from first kitchen.
		Philosopher p1 = new Philosopher(1, pasta);
		p1.setName("Ph. Alice");
		p1.start();
		
		// We have a group of Philosophers. Servant, go get their order!
		// Ph. Bob wants two serve of pasta from second kitchen. Serve him first.
		Philosopher p2 = new Philosopher(1, pasta2);
		p2.setName("Ph. Bob");
		System.out.println(p2.servePhs(serveLock));
		
		// Ph. Charlie wants two serve of pasta from second kitchen. Serve him second.
		Philosopher p3 = new Philosopher(2, pasta2);
		p3.setName("Ph. Charlie");
		System.out.println(p3.servePhs(serveLock));
		
		// Ph. Duff wants two serve of pasta from second kitchen. Serve him last.
		Philosopher p4 = new Philosopher(3, pasta2);
		p4.setName("Ph. Duff");
		System.out.println(p4.servePhs(serveLock));
	}
}
