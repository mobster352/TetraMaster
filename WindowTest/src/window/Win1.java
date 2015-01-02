package window;

import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Win1 extends JFrame implements MouseListener {
	
	private static final long serialVersionUID = 1L;
	private final int windowWidth;
	private final int windowHeight;
	private JFrame frame;
	private ImageIcon bkgdImage;
	private JLabel bkgdLabel;
	private Container pane;
	private JButton newB;
	private JButton loadB;
	
	public Win1()
	{
		windowWidth = 800;
		windowHeight = 650;
		frame = new JFrame();
		pane = frame.getContentPane();
		bkgdImage = new ImageIcon(getClass().getResource("/images/bkgd.png"));
		bkgdLabel = new JLabel(bkgdImage);
		newB = new JButton("New Game");
		loadB = new JButton("Load Game");
	}
	
	public void run()
	{
		setLayout(null);
		frame.setSize(windowWidth, windowHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		newB.addMouseListener(this);
		loadB.addMouseListener(this);
		
		newB.setBounds(300, 200, 200, 50); //x,y,width,height
		loadB.setBounds(300, 300, 200, 50);
		bkgdLabel.setBounds(0, 0, windowWidth, windowHeight);
		
		pane.add(bkgdLabel);
		bkgdLabel.add(newB);
		bkgdLabel.add(loadB);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		Win1 win = new Win1();
		win.run();
	}
	
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(newB))
		{
			frame.dispose();
			Win2 w = new Win2();
			w.run();
		}
	}
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
