import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;


public class Menu extends JFrame implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final int windowWidth;
	private final int windowHeight;
	private JFrame frame;
	private Container pane;
	
	public Menu()
	{
		windowWidth = 800;
		windowHeight = 650;
		frame = new JFrame();
		pane = frame.getContentPane();
	}
	
	public void run()
	{
		//pane.setLayout(null);
		frame.setSize(windowWidth, windowHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton button = new JButton("New Game");
		button.addMouseListener(this);
		pane.add(button);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		Menu m = new Menu();
		m.run();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//frame.setVisible(false);
		frame.dispose();
		Game g = new Game();
		try {
			g.run();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
