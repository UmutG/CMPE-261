// Umut Geyik - 120200145 - CMPE 261.01/0102

package worksheet06;

// Let's create proper attributes for Pasta class
public class Pasta {
	private int serve;
	
	// Default constructor will have only the amount of serve
	public Pasta(int serve) {
		super();
		this.serve = serve;
	}
	
	// Servant! We have an order, get me that!
	public String getServe(int eaten) {
		String info = "";
		// If we have an adequate amount of serving, let's feed the customer!
		if (serve >= eaten)
			try {
				// Your order is getting ready.
				Thread.currentThread().sleep(250);
				
				// Order is ready. Customer ate it. I guess customer is burping. Decrease the total serving.
				serve -= eaten;
				info = "You have eaten " + eaten + " serve of pasta.";
			} catch (Exception e) {
				// I guess someone cut his/her finger. MEDIC!
				System.err.println(e.getMessage());
			}
		
		else {
			// Oh, no! We don't have enough serving.
			if (serve > 0) 
				// We have less serving than the customer's order.
				info = "We can't serve " + eaten + " serve of pasta. Only " 
						+ serve + " serve left.";
			else
				// We have no pasta left :(
				info = "We can't serve pasta. There is no left";
		}
			
			
		return info;
	}
}
