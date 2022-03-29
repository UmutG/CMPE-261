// Umut Geyik - 120200145 - CMPE 261.01/0102
package worksheet02;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CompanyUI extends JFrame {
	// In order to create an UI, let's create labels, text fields and buttons
	// Name them properly to show them to user
	
	JLabel CompanyName, CompanyArea, CompanyWorker, StockValue;
	JTextField txtName, txtArea, txtWorker, txtStock;
	JButton submit, reset;
	
	public CompanyUI() {
		CompanyName = new JLabel("Company Name");
		CompanyArea = new JLabel("Company Area");
		CompanyWorker = new JLabel("Company Worker");
		StockValue = new JLabel("Company Stock Value");
		
		txtName = new JTextField();
		txtArea = new JTextField();
		txtWorker = new JTextField();
		txtStock = new JTextField();
		
		submit = new JButton("Submit");
		reset = new JButton("Reset");
		
		setLayout(new FlowLayout());
		add(CompanyName);
		txtName.setColumns(10);
		add(txtName);
		
		add(CompanyArea);
		txtArea.setColumns(10);
		add(txtArea);
		
		add(CompanyWorker);
		txtWorker.setColumns(10);
		add(txtWorker);
		
		add(StockValue);
		txtStock.setColumns(10);
		add(txtStock);
		
		add(submit);
		add(reset);
		
		setResizable(false);
		setSize(310, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Create a New Company");
	}
	
}
