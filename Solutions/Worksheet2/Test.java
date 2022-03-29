// Umut Geyik - 120200145 - CMPE 261.01/0102
package worksheet02;

import javax.swing.JOptionPane;
import worksheet02.Company;
import worksheet02.CompanyUI;

public class Test {

	public static void main(String[] args) {
		
		// Create CompanyUI object to show first methods' UI
		CompanyUI compUi = new CompanyUI();
		
		
		// Create Object options array in order to show available operations (methods)
		// Again, 5th method will be only available if the conditions met (if (age - exp < 20))
		Object [] options = {"Create Company", "Calculate Profit",
				"Calculate Logistic Expense ", "Name Checker"};
		
		// Here, we get the index of selected option. Then, we redirect the user
		// with selected options
		int n = JOptionPane.showOptionDialog(null, "Choose Your Operation", "Company v1.0", 
				JOptionPane.CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		
		// If selected options was "Create Company", then show compUi object
		if (n == 0)
			compUi.setVisible(true);
		
		else
			JOptionPane.showMessageDialog(null, "This feature is not ready. \nThank you for using Comp v1.0");
		
		// Initialize 2 companies with given values via JOptionPane
		// and convert them into proper variable type
		try { 
			Company comp1 = new Company(JOptionPane.showInputDialog("Company Name: "), JOptionPane.showInputDialog("Company Area: "), 
					Integer.parseInt(JOptionPane.showInputDialog("Company Employee Number: ")), 
					Double.parseDouble(JOptionPane.showInputDialog("Company Stock Value: ")));
			Company comp2 = new Company(JOptionPane.showInputDialog("Company Name: "), JOptionPane.showInputDialog("Company Area: "), 
					Integer.parseInt(JOptionPane.showInputDialog("Company Employee Number: ")), 
					Double.parseDouble(JOptionPane.showInputDialog("Company Stock Value: ")));
			
			// Print out every companies' attributes
			JOptionPane.showMessageDialog(null, comp1.toString() + "\n" + comp2.toString());
			
			// Now, let's give proper inputs to the methods in order to run them.  
			// Inputs will be taken via JOptionPane
			System.out.println(comp1.logistic(Integer.parseInt(JOptionPane.showInputDialog("Distance please:")), true));
			System.out.println(comp2.logistic(Integer.parseInt(JOptionPane.showInputDialog("Distance please:")), false));
			System.out.println(comp1.CEOofComp(comp2, JOptionPane.showInputDialog("CEO Name: "), 
					JOptionPane.showInputDialog("CEO Surname: "), Integer.parseInt(JOptionPane.showInputDialog("CEO's Age: ")), 
					Integer.parseInt(JOptionPane.showInputDialog("CEO's Experience: "))));
			System.out.println(comp1.profit(comp1, Double.parseDouble(JOptionPane.showInputDialog("Annually Profit: "))));
			System.out.println(comp2.nameCheck(comp2, comp1));
			
			// stockHack method will run if the requirement is met
			System.out.println(comp2.CEOofComp(comp2, "Kadir", "Sultan", 22, 3));
			System.out.println(comp2.getStockValue());
		}
		
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Input Problem", JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
}
