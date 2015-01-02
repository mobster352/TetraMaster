import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class MouseTest extends JFrame implements MouseListener
{
     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MouseTest()
     {
          super("MouseListener Test");

          JPanel p = new JPanel();
          JButton button = new JButton();
          
          button.setText("Click");
          
          JOptionPane.showMessageDialog(getParent(), "FLIP");

          JTextArea exitText = new JTextArea(2, 10);
          exitText.setText("Click me to exit");
          exitText.addMouseListener(this);

          p.add(exitText);
          p.add(button);

          getContentPane().add(p);
          
          pack();

          setVisible(true);
     }

     public static void main(String[] args)
     {
          new MouseTest();
     }
     
     //when the mouse is clicked on the Component
     public void mouseClicked(MouseEvent e)
     {
          System.exit(0);
     }
     
     //when the mouse enters the Component
     public void mouseEntered(MouseEvent e)
     {
     }
     
     //when the mouse leaves the Component
     public void mouseExited(MouseEvent e)
     {
     }

     //when the mouse left button is held down on the Component
     public void mousePressed(MouseEvent e)
     {
     }

     //when the mouse left button is released on the Component
     public void mouseReleased(MouseEvent e)
     {
     }
}
