import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Menu extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int windowWidth;
	private int windowHeight;
	private JFrame frame;
	private Container pane;
	private ImageIcon img;
	private JLabel board;
	private JButton button;
	private JButton button2;
	
	public Menu()
	{
		windowWidth = 800;
		windowHeight = 650;
		frame = new JFrame("Tetra Master");
		pane = frame.getContentPane();
		frame.setSize(windowWidth, windowHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		button = new JButton("New Game");
		button2 = new JButton("Quit");
		pane.add(button,BorderLayout.NORTH);
		pane.add(button2,BorderLayout.SOUTH);
		//frame.pack();
		frame.setVisible(true);
	}
	
	public void run()
	{	
		button.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
			frame.dispose();
			Game g = new Game();
			try {
				g.run();
				//new Game().run();
				frame.dispose();
			} catch (InterruptedException ee) {
				//System.err.println("An InterruptedException was caught: " + ee.getMessage());
			}
         }          
		});
	  
		button2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
			System.exit(0);
		 }
		});
	}
	public static void main(String[] args)
	{
		Menu m = new Menu();
		m.run();
	}

}	