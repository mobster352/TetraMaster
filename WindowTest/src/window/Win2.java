package window;

import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Win2 extends JFrame implements MouseListener,KeyListener {
	private static final long serialVersionUID = 1L;
	private final int windowWidth;
	private final int windowHeight;
	private JFrame frame;
	//background
	private ImageIcon bkgdImage;
	private JLabel bkgdLabel;
	//panel
	private Container pane;
	//player
	private ImageIcon playerImage;
	private JLabel playerLabel;
	private int activeD;
	//test object
	private ImageIcon obj1Image;
	private JLabel obj1Label;
	//enemy object
	private ImageIcon eImage;
	private JLabel eLabel;
	private Enemy enemy;
	//object list
	private ArrayList<JLabel> objList;
	//enemy label list
	private ArrayList<JLabel> eLabelList;
	//enemy list
	private ArrayList<Enemy> eList;
	private ImageIcon textIm;
	private JLabel textJl;
	private boolean msgup;
	
	public Win2()
	{
		windowWidth = 800;
		windowHeight = 650;
		frame = new JFrame();
		pane = frame.getContentPane();
		//background
		bkgdImage = new ImageIcon(getClass().getResource("/images/bkgd.png"));
		bkgdLabel = new JLabel(bkgdImage);
		//player
		playerImage = new ImageIcon(getClass().getResource("/images/player.png"));
		playerLabel = new JLabel(playerImage);
		//obj
		obj1Image = new ImageIcon(getClass().getResource("/images/obj1.png"));
		obj1Label= new JLabel(obj1Image);
		//enemy
		eImage = new ImageIcon(getClass().getResource("/images/obj2.png"));
		eLabel= new JLabel(eImage);
		objList = new ArrayList<JLabel>();
		//active direction
		activeD = 1; //1 - Forward  2 - Backward  3 - Left  4 - Right
		eLabelList = new ArrayList<JLabel>();
		eList = new ArrayList<Enemy>();
		enemy = new Enemy();
		textJl = null;
		textIm = null;
		msgup = false;
	}
	
	//start method
	
	public void run()
	{
		setLayout(null);
		frame.setSize(windowWidth, windowHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//set position and size of labels
		playerLabel.setBounds(375, 300, 50, 50);
		bkgdLabel.setBounds(0, 0, windowWidth, windowHeight);
		obj1Label.setBounds(405, 500, 50, 50);
		eLabel.setBounds(205, 200, 50, 50);
		
		//add obj labels to bkgd
		bkgdLabel.add(playerLabel);
		bkgdLabel.add(obj1Label);
		bkgdLabel.add(eLabel);
		
		//add all game object labels to list
		objList.add(obj1Label);
		
		//add all enemy labels to list
		eLabelList.add(eLabel);
		
		//add enemy to enemy tracker list
		eList.add(enemy);
		
		pane.add(bkgdLabel);
		frame.addKeyListener(this);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	//mouse events
	
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
		
	}
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	//collision methods based on direction moving
	
	public boolean isCollisionFront()
	{
		for(int i=0; i<objList.size(); i++)
		{
			if(playerLabel.getY()==objList.get(i).getY()+50 && playerLabel.getX()>objList.get(i).getX()-50
					&& playerLabel.getX()<objList.get(i).getX()+50)
				return true;
		}
		for(int i=0; i<eLabelList.size(); i++)
		{
			if(playerLabel.getY()==eLabelList.get(i).getY()+50 && playerLabel.getX()>eLabelList.get(i).getX()-50
					&& playerLabel.getX()<eLabelList.get(i).getX()+50)
				return true;
		}
		return false;
	}
	public boolean isCollisionBack()
	{
		for(int i=0; i<objList.size(); i++)
		{
			if(playerLabel.getY()==objList.get(i).getY()-50 && playerLabel.getX()>objList.get(i).getX()-50
					&& playerLabel.getX()<objList.get(i).getX()+50)
				return true;
		}
		for(int i=0; i<eLabelList.size(); i++)
		{
			if(playerLabel.getY()==eLabelList.get(i).getY()-50 && playerLabel.getX()>eLabelList.get(i).getX()-50
					&& playerLabel.getX()<eLabelList.get(i).getX()+50)
				return true;
		}
		return false;
	}
	public boolean isCollisionLeft()
	{
		for(int i=0; i<objList.size(); i++)
		{
			if(playerLabel.getX()==objList.get(i).getX()+50 && playerLabel.getY()>objList.get(i).getY()-50
					&& playerLabel.getY()<objList.get(i).getY()+50)
				return true;
		}
		for(int i=0; i<eLabelList.size(); i++)
		{
			if(playerLabel.getX()==eLabelList.get(i).getX()+50 && playerLabel.getY()>eLabelList.get(i).getY()-50
					&& playerLabel.getY()<eLabelList.get(i).getY()+50)
				return true;
		}
		return false;
	}
	public boolean isCollisionRight()
	{
		for(int i=0; i<objList.size(); i++)
		{
			if(playerLabel.getX()==objList.get(i).getX()-50 && playerLabel.getY()>objList.get(i).getY()-50
					&& playerLabel.getY()<objList.get(i).getY()+50)
				return true;
		}
		for(int i=0; i<eLabelList.size(); i++)
		{
			if(playerLabel.getX()==eLabelList.get(i).getX()-50 && playerLabel.getY()>eLabelList.get(i).getY()-50
					&& playerLabel.getY()<eLabelList.get(i).getY()+50)
				return true;
		}
		return false;
	}
	
	
	//keyboard events

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==e.VK_W && !isCollisionFront() && !msgup)//w press
		{
			for(int i=0; i<objList.size(); i++)
			{
				objList.get(i).setLocation(objList.get(i).getX(),objList.get(i).getY()+10);
				if(eLabelList.size()>0)
					eLabelList.get(i).setLocation(eLabelList.get(i).getX(),eLabelList.get(i).getY()+10);
			}
			activeD = 1;
		}
		if(e.getKeyCode()==e.VK_S && !isCollisionBack() && !msgup)//w press
		{
			for(int i=0; i<objList.size(); i++)
			{
				objList.get(i).setLocation(objList.get(i).getX(),objList.get(i).getY()-10);
				if(eLabelList.size()>0)
					eLabelList.get(i).setLocation(eLabelList.get(i).getX(),eLabelList.get(i).getY()-10);
			}
			
			activeD = 2;
		}
		if(e.getKeyCode()==e.VK_A && !isCollisionLeft() && !msgup)//w press
		{
			for(int i=0; i<objList.size(); i++)
			{
				objList.get(i).setLocation(objList.get(i).getX()+10,objList.get(i).getY());
				if(eLabelList.size()>0)
					eLabelList.get(i).setLocation(eLabelList.get(i).getX()+10,eLabelList.get(i).getY());
			}
			activeD = 3;
		}
		if(e.getKeyCode()==e.VK_D && !isCollisionRight() && !msgup)//w press
		{
			for(int i=0; i<objList.size(); i++)
			{
				objList.get(i).setLocation(objList.get(i).getX()-10,objList.get(i).getY());
				if(eLabelList.size()>0)
					eLabelList.get(i).setLocation(eLabelList.get(i).getX()-10,eLabelList.get(i).getY());
			}
			activeD = 4;
		}
		if(e.getKeyCode()==e.VK_SPACE)
		{
			if(activeD == 1)
				checkFront();
			if(activeD == 2)
				checkBack();
			if(activeD == 3)
				checkLeft();
			if(activeD == 4)
				checkRight();
		}
		if(e.getKeyCode()==e.VK_ENTER)
		{
			if(!msgup)	//talking to npc code
			{
				if(objList.get(0).getX()==playerLabel.getX()+50 && objList.get(0).getY()==playerLabel.getY())
				{//only works exactly to the left of npc
					textIm = new ImageIcon(getClass().getResource("/images/text.png"));
					textJl = new JLabel(textIm);
					textJl.setBounds(405, 225, 100, 50);
					bkgdLabel.add(textJl);
					bkgdLabel.validate();
					bkgdLabel.repaint();
					msgup = true;
				}
			}
			else
			{
				if(textJl==null)
					return;
				bkgdLabel.remove(textJl);
				bkgdLabel.validate();
				bkgdLabel.repaint();
				msgup = false;
				textJl = null;
				textIm = null;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	//next 4
	//battle methods
	
	public void checkFront()
	{
		Enemy temp=new Enemy();
		int j=0;
		JLabel bk = new JLabel();
		for(int i=0; i<eLabelList.size(); i++)
		{
			if(eLabelList.get(i).getY()>=playerLabel.getY()-60 && eLabelList.get(i).getY()<playerLabel.getY() &&
					eLabelList.get(i).getX()<playerLabel.getX()+10 && eLabelList.get(i).getX()>playerLabel.getX()-10)
			{
				eList.get(i).decHp(1);
				temp = eList.get(i);
				j = i;
				break;
			}
		}
		if(temp.getHp()==0)
		{
			bk = eLabelList.get(j);
			eList.remove(j);
			eLabelList.remove(j);
			//next 3 lines to fully remove jlabel
			bkgdLabel.remove(bk);
			bkgdLabel.validate();
			bkgdLabel.repaint();
		}
	}
	public void checkBack()
	{
		Enemy temp=new Enemy();
		int j=0;
		JLabel bk = new JLabel();
		for(int i=0; i<eLabelList.size(); i++)
		{
			if(eLabelList.get(i).getY()<=playerLabel.getY()+60 && eLabelList.get(i).getY()>playerLabel.getY() &&
					eLabelList.get(i).getX()<playerLabel.getX()+10 && eLabelList.get(i).getX()>playerLabel.getX()-10)
			{
				eList.get(i).decHp(1);
				temp = eList.get(i);
				j = i;
				break;
			}
		}
		if(temp.getHp()==0)
		{
			bk = eLabelList.get(j);
			eList.remove(j);
			eLabelList.remove(j);
			//next 3 lines to fully remove jlabel
			bkgdLabel.remove(bk);
			bkgdLabel.validate();
			bkgdLabel.repaint();
		}
	}
	public void checkLeft()
	{
		Enemy temp=new Enemy();
		int j=0;
		JLabel bk = new JLabel();
		for(int i=0; i<eLabelList.size(); i++)
		{
			if(eLabelList.get(i).getY()<playerLabel.getY()+20 && eLabelList.get(i).getY()>playerLabel.getY()-20 &&
					eLabelList.get(i).getX()<playerLabel.getX() && eLabelList.get(i).getX()>=playerLabel.getX()-70)
			{
				eList.get(i).decHp(1);
				temp = eList.get(i);
				j = i;
				break;
			}
		}
		if(temp.getHp()==0)
		{
			bk = eLabelList.get(j);
			eList.remove(j);
			eLabelList.remove(j);
			//next 3 lines to fully remove jlabel
			bkgdLabel.remove(bk);
			bkgdLabel.validate();
			bkgdLabel.repaint();
		}
	}
	public void checkRight()
	{
		Enemy temp=new Enemy();
		int j=0;
		JLabel bk = new JLabel();
		for(int i=0; i<eLabelList.size(); i++)
		{
			if(eLabelList.get(i).getY()<playerLabel.getY()+20 && eLabelList.get(i).getY()>playerLabel.getY()-20 &&
					eLabelList.get(i).getX()>playerLabel.getX() && eLabelList.get(i).getX()<=playerLabel.getX()+70)
			{
				eList.get(i).decHp(1);
				temp = eList.get(i);
				j = i;
				break;
			}
		}
		if(temp.getHp()==0)
		{
			bk = eLabelList.get(j);
			eList.remove(j);
			eLabelList.remove(j);
			//next 3 lines to fully remove jlabel
			bkgdLabel.remove(bk);
			bkgdLabel.validate();
			bkgdLabel.repaint();
		}
	}
	
	public static void main(String[] args)
	{
		Win1 win = new Win1();
		win.run();
	}
}
