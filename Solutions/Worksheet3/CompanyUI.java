// Umut Geyik - 120200145 - CMPE 261.01/0102
package worksheet03;

// Import all the necessary libraries we want to work on
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class CompanyUI extends JFrame implements ActionListener, FocusListener {

	// Given 3 methods are: profit, logistic and nameCheck
	// Let's create components of the Company GUI
	// We'll have labels, text fields, buttons, a check box, button groups and radio buttons
	private JLabel lblCompanyArea, lblCapital, lblDistance, lblMembership, lblBlank;
	
	private JTextField txtCompanyArea, txtCapital, txtDistance;
	
	private JButton btnCalculate, btnCalculate2, btnCompare, btnReset;
	private JCheckBox chckMembership;
	private ButtonGroup btnGroup, btnGroup2;
	private JRadioButton rdComp1, rdComp2, rdComp3, rdComp4, rdComp5, rdComp6;
	
	// Create a company class variable to call methods
	private Company company;	
	
	// Initialize the GUI
	public CompanyUI() {
		// Create company object to call methods
		company = new Company();
		
		// Create a grid layout with gaps (margin and padding)
		// and initialize the all components for this GUI
		setLayout(new GridLayout(3, 0, 10, 0));
		lblCompanyArea = new JLabel("Company's Area");
		lblCapital = new JLabel("Company's Capital");
		lblDistance = new JLabel("Distance");
		lblMembership = new JLabel("Membership?");
		lblBlank = new JLabel("");
	
		chckMembership = new JCheckBox();
		
		txtCompanyArea = new JTextField();
		txtCapital = new JTextField();
		txtDistance = new JTextField();
		
		btnCalculate = new JButton("Calculate");
		btnCalculate2 = new JButton("Calculate");
		btnCompare = new JButton("Compare");
		btnReset = new JButton("Reset");
		
		// Add focus listeners on text fields and action listeners on buttons
		txtCompanyArea.addFocusListener(this);
		txtCapital.addFocusListener(this);
		txtDistance.addFocusListener(this);
		
		btnCalculate.addActionListener(this);
		btnCalculate2.addActionListener(this);
		btnCompare.addActionListener(this);
		btnReset.addActionListener(this);
		
		// Set action command will help me to return their values in order to
		// compare two given company names whether they are unique
		rdComp1 = new JRadioButton("Kleimo");
		rdComp1.setActionCommand("Kleimo");
		rdComp2 = new JRadioButton("Eyupsultan Bros. Co.");
		rdComp2.setActionCommand("Eyupsultan Bros. Co.");
		rdComp3 = new JRadioButton("Los Santos Customs");
		rdComp3.setActionCommand("Los Santos Customs");
		rdComp1.setSelected(true);
		
		rdComp4 = new JRadioButton("Kleimo");
		rdComp4.setActionCommand("Kleimo");
		rdComp5 = new JRadioButton("Eyupsultan Bros. Co.");
		rdComp5.setActionCommand("Eyupsultan Bros. Co.");
		rdComp6 = new JRadioButton("Los Santos Customs");
		rdComp6.setActionCommand("Los Santos Customs");
		rdComp4.setSelected(true);
		
		// Create button groups to prevent multiple selection on radio buttons
		btnGroup = new ButtonGroup();
		btnGroup.add(rdComp1);
		btnGroup.add(rdComp2);
		btnGroup.add(rdComp3);
		
		btnGroup2 = new ButtonGroup();
		btnGroup2.add(rdComp4);
		btnGroup2.add(rdComp5);
		btnGroup2.add(rdComp6);
		
		// Here, we have 3 different panels to separate each methods from others
		// We have to add proper components into suitable panels. 
		// Thus, it will look ordered and clean (as much as possible)
		JPanel pnlFirst = new JPanel();
			pnlFirst.setLayout(new GridLayout(0, 2));
			pnlFirst.add(lblCompanyArea);
			pnlFirst.add(txtCompanyArea);
			pnlFirst.add(lblCapital);
			pnlFirst.add(txtCapital);
			pnlFirst.add(btnCalculate);
			pnlFirst.add(btnReset);
			// I decided to use hex codes instead present Java colors
			pnlFirst.setBackground(Color.decode("#fbe9c3"));
		
		JPanel pnlSecond = new JPanel();
			pnlSecond.setLayout(new GridLayout(0, 2));
			pnlSecond.add(lblDistance);
			pnlSecond.add(txtDistance);
			pnlSecond.add(lblMembership);
			pnlSecond.add(chckMembership);
			pnlSecond.add(btnCalculate2);
			pnlSecond.add(btnReset);
			pnlSecond.setBackground(Color.decode("#d4ebbf"));
		
		JPanel pnlThird = new JPanel();
		// This GridLayout has to have 3 rows. Because we have 3 different radio buttons
		// and wanted them to show up as groups
			pnlThird.setLayout(new GridLayout(0, 3));
			pnlThird.add(rdComp1);
			pnlThird.add(rdComp2);
			pnlThird.add(rdComp3);
			pnlThird.add(rdComp4);
			pnlThird.add(rdComp5);
			pnlThird.add(rdComp6);
			// lblBlank is just a blank label to align compare button into middle
			pnlThird.add(lblBlank);
			pnlThird.add(btnCompare);
			pnlThird.setBackground(Color.decode("#64a279"));
		
		// Add all panels to GUI
		add(pnlFirst);
		add(pnlSecond);
		add(pnlThird);
		
		// Set proper attributes to make the GUI visible and ready to go
		setSize(350, 400);
		setVisible(true);
		setResizable(true);
		setTitle("CompanyUI v2.0");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		// Call the GUI to make it appear
		new CompanyUI();
	}

	private void profit() {
		// profit method will help me to get necessary values from user
		// and send them to profit method in company class
		try {
			String area = txtCompanyArea.getText();
			String cap = txtCapital.getText();
			double capital = Double.parseDouble(cap);
			JOptionPane.showMessageDialog(this, "Annually profit is: " + company.profit(area, capital));
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Wrong Input", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	private void logistic() {
		// As same purpose as profit method, we have logistic method
		try {
			double distance = Double.parseDouble(txtDistance.getText());
			boolean membership = chckMembership.isSelected();
			JOptionPane.showMessageDialog(this, 
					"Total cost of logistic is: " + company.logistic(distance, membership));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Wrong Number Input", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void compare() {
		// As same purposes as above methods, we have compare method
		// Here, we have 2 strings in order to get values from radio buttons
		// At the beginning, we set action command to their values. So,
		// we can easily put them into the nameCheck function
		try {
			String firstComp = btnGroup.getSelection().getActionCommand();
			String secondComp = btnGroup2.getSelection().getActionCommand();
			boolean result = company.nameCheck(firstComp, secondComp);
			if (result == false)
				JOptionPane.showMessageDialog(this, "Company name is duplicated!");
			else
				JOptionPane.showMessageDialog(this, "Company name is unique!");
				
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "An Error Occured", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	private void reset() {
		// Reset function will reset or set default every elements on GUI
		txtCapital.setText("");
		txtCompanyArea.setText("");
		txtDistance.setText("");
		rdComp1.setSelected(true);
		rdComp4.setSelected(true);
		chckMembership.setSelected(false);
	}
	
	@Override
	public void focusLost(FocusEvent e) {
		// If any of the text fields has lost focus, give info on terminal
		// TODO Auto-generated method stub
		if(e.getSource().equals(txtCapital))
			System.out.println("Focus Lost on Capital Textfield");
		else if (e.getSource().equals(txtCompanyArea))
			System.out.println("Focus Lost on Company Area Textfield");
		else if (e.getSource().equals(txtDistance))
			System.out.println("Focus Lost on Distance Textfield");
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		// If any of the text fields has gained focus, give info on terminal
		if(e.getSource().equals(txtCapital))
			System.out.println("Focus Gained on Capital Textfield");
		else if (e.getSource().equals(txtCompanyArea))
			System.out.println("Focus Gained on Company Area Textfield");
		else if (e.getSource().equals(txtDistance))
			System.out.println("Focus Gained on Distance Textfield");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// This is where all the methods called by related buttons
		// Each button has different purposes, so let's call relative methods
		if (e.getSource().equals(btnCalculate))
			profit();
		else if (e.getSource().equals(btnCalculate2))
			logistic();
		else if (e.getSource().equals(btnCompare))
			compare();
		else if (e.getSource().equals(btnReset))
			reset();
		
	}
}
