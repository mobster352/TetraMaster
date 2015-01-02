import java.awt.*;

import javax.swing.*;

public class FramesTest extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int WINDOW_WIDTH=800;
	private static final int WINDOW_HEIGHT=700;
	private static final int boardWidth = 400;
	private static final int boardHeight = 400;
	private static final int cw = 100;
	private static final int ch = 100;
	
	public FramesTest()
	{
		//JLabel rect = new JLabel();
        ImageIcon rec = new ImageIcon("/home/mob/workspace/CardGame/bin/back.png");
        //rect = new JLabel(rec);
       // add(rect);
        
        JLabel board = new JLabel();
        rec = new ImageIcon("/home/mob/workspace/CardGame/bin/board.png");
        board = new JLabel(rec);
        board.setLocation(boardWidth, boardHeight);
        //add(board);
	}
	
	public static void addComponentstoPane(Container pane)
	{
		pane.setLayout(null);
		
		JLabel board = new JLabel();
        ImageIcon rec = new ImageIcon("/home/mob/workspace/CardGame/bin/board.png");
        board = new JLabel(rec);
        pane.add(board);
        board.setBounds(200, 100, boardWidth, boardHeight);//top left corner(x,y) then size(w,h)
        
        JLabel rect = new JLabel();
        rec = new ImageIcon("/home/mob/workspace/CardGame/bin/back.png");
        rect = new JLabel(rec);
        pane.add(rect);
        Rectangle r = new Rectangle(150,510,cw,ch);
        rect.setBounds(r);
		
	}
	 private static void createAndShowGUI() {
	        //Create and set up the window.
	        JFrame frame = new JFrame("Tetra Master");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));

	        //Create the menu bar.  Make it have a green background.
	        JMenuBar greenMenuBar = new JMenuBar();
	        greenMenuBar.setOpaque(true);
	        greenMenuBar.setBackground(new Color(154, 165, 127));
	        greenMenuBar.setPreferredSize(new Dimension(200, 20));

	        //Create a yellow label to put in the content pane.
	        JLabel yellowLabel = new JLabel();
	        yellowLabel.setOpaque(true);
	        yellowLabel.setBackground(new Color(248, 213, 131));
	        yellowLabel.setPreferredSize(new Dimension(200, 180));
	        
	        
	        addComponentstoPane(frame.getContentPane());
	        

	        //Set the menu bar and add the label to the content pane.
	        frame.setJMenuBar(greenMenuBar);
	        //frame.getContentPane().add(yellowLabel, BorderLayout.CENTER);
	        
	        //add image to window
	        //frame.add(new FramesTest());

	        //Display the window.
	        frame.pack();
	        frame.setVisible(true);
	    }

	    public static void main(String[] args) {
	        //Schedule a job for the event-dispatching thread:
	        //creating and showing this application's GUI.
	        javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                createAndShowGUI();
	            }
	        });
	    }
}
