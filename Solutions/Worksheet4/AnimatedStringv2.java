// Umut Geyik - 120200145 - CMPE 261.01/0102

package worksheet04;

// Import necessary libraries
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

// Extend the class with JPanel and implement ActionListener in order to
// run different methods on different button click 
public class AnimatedStringv2 extends JPanel implements ActionListener {

	// Create necessary elements for UI, animation and menu
	private int x, y;
	private Font yourFont;
	private int size;
	private Timer timer;
	private boolean blnMoveRight, blnMoveDown, change = true;
	private JMenuBar menuBar;
	private JMenu menuMethod1, menuMethod2, menuMethod3, menuAnimation;
	private JMenuItem itemFont, itemColor, itemDirection, itemExit, itemStart, itemStop;
	private Color col;
	
	// We have to get text's width to let it stay on the screen
	private static int textWidth = 0;
	public AnimatedStringv2() {
		
		// To put menu bar into the UI, border layout is a good choice
		setLayout(new BorderLayout());
		
		// Set size of the font and text's location on the frame
		size = 30;
		x = 10;
		y = 50;
		
		// Let's create a default font element with given attributes
		yourFont = new Font("Verdana", Font.BOLD, size);
		
		// Set the timer to get ready. Tick tock!
		timer = new Timer(100, this);
		
		// Set the booleans true. We want to start moving it to right and down side
		blnMoveRight = true;
		blnMoveDown = true;
		
		// Initialize menu bar and put all the related elements
		menuBar = new JMenuBar();
		
		itemFont = new JMenuItem("Change Font");
		itemColor = new JMenuItem("Change Color");
		itemDirection = new JMenuItem("Change Direction");
		itemStart = new JMenuItem("Start Timer");
		itemStop = new JMenuItem("Stop Timer");
		itemExit = new JMenuItem("Exit");
		
		menuAnimation = new JMenu("Animation");
		menuMethod1 = new JMenu("Font");
		menuMethod2 = new JMenu("Color");
		menuMethod3 = new JMenu("Direction");
		
		menuMethod1.add(itemFont);
		menuMethod2.add(itemColor);
		menuMethod3.add(itemDirection);
		menuAnimation.add(itemStart);
		menuAnimation.add(itemStop);
		
		menuBar.add(menuAnimation);
		menuBar.add(menuMethod1);
		menuBar.add(menuMethod2);
		menuBar.add(menuMethod3);
		menuBar.add(itemExit);
		
		// Add the menu bar to north of the frame
		add(menuBar, BorderLayout.NORTH);
		
		// Add action listeners to call methods
		itemColor.addActionListener(this);
		itemFont.addActionListener(this);
		itemDirection.addActionListener(this);
		itemExit.addActionListener(this);
		itemStart.addActionListener(this);
		itemStop.addActionListener(this);
		
		// Set frame size and its visibility
		setSize(800, 600);
		setVisible(true);
	}
	
	// In order to draw given text, I needed to create a global variable
	private static String myText;
	
	public static void main(String[] args) {
		// If given text is empty, even with spaces, take input again
		String text = "";
		while (text.isEmpty())
			text = JOptionPane.showInputDialog(null, "What is your input?").trim();
		
		// Set the myText to text to draw it on UI
		myText = text;
		
		// Call default constructor and create the UI without resizable
		AnimatedStringv2 myString = new AnimatedStringv2();
		JFrame myFrame = new JFrame("Animated String v2");
		myFrame.setSize(myString.getSize());
		myFrame.setVisible(true);
		myFrame.setResizable(false);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.add(myString);
	}
	
	@Override
	// Call the paint method and set attributes of given user input
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.setFont(yourFont);
		g.setColor(col);
		g.drawString(myText, x, y);
		
		// Here, we have the textWidth. So, we can help it to stay on the boundaries
		textWidth = g.getFontMetrics().stringWidth(myText);
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub\
		// Let's set every button's and timer's method
		if (e.getSource().equals(timer))
			startAnimation();
		else if (e.getSource().equals(itemStart))
			timer.start();
		else if (e.getSource().equals(itemStop))
			timer.stop();
		else if (e.getSource().equals(itemColor))
			itemColor();
		else if (e.getSource().equals(itemFont))
			itemFont();
		else if (e.getSource().equals(itemDirection)) {
			itemDirection();
			// We set the boolean change true and text will move to the opposite way
			// When user clicked the button again, it will move as default way
			if (change == true)
				change = false;
			else if (change == false)
				change = true;
		}
		else if (e.getSource().equals(itemExit))
			System.exit(1);
		
		repaint();
	}

	private void startAnimation() {
		// TODO Auto-generated method stub
		// startAnimation will start moving the text on UI frame
		// and it will not go out the frame's borders

		if (x < 770-textWidth && blnMoveRight) 
			x += 17;
		
		else if (x > 10) {
			blnMoveRight = false;
			x -= 17;
		}
		else
			blnMoveRight = true;
		
		if (y < 550 && blnMoveDown) {
			y += 17;
		}
		else if (y > 50) {
			blnMoveDown = false;
			y -= 17;
		}
		else 
			blnMoveDown = true;
	}

	private void itemDirection() {
		// TODO Auto-generated method stub
		// If user wants to change animation direction and clicked once, change the direction
		// in the opposite way. Else, move it to default way
		if (change) {
			blnMoveRight = false;
			blnMoveDown = false;
		}
		if (!change) {
			blnMoveRight = true;
			blnMoveDown = true;
		}
	}
	
	// Create a random object to randomize color of text
	Random rand = new Random();
	
	private void itemFont() {
		// TODO Auto-generated method stub
		// When user click on Change Font button, program will set text's font from the user's computer
		// which fonts are available at that moment. So, let's create an array and take it's length
		// After that, call the array with random number within the array length. Also, change font's style
		String currentFonts [] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		int fontNum = currentFonts.length;
		yourFont = new Font(currentFonts[rand.nextInt(fontNum)], Font.ITALIC, size+10);
	}

	private void itemColor() {
		// TODO Auto-generated method stub
		// Randomize the text color with rand object. Everytime user clicks, it return an RGB code
		Color random = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
		col = random;
	}
}
