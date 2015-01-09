import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Game extends JFrame implements MouseListener
{
	private static final long serialVersionUID = 1L;
	private final int WINDOW_WIDTH;
	private final int WINDOW_HEIGHT;
	private ImageIcon s; //highlighter
	private int selected; //highlighted card
	private Player p;
	private AI a;
	private ImageIcon t0; //card image
	private ImageIcon t1;
	private ImageIcon t2;
	private ImageIcon t3;
	private ImageIcon t4;
	private ImageIcon ta0;
	private ImageIcon img;
	private boolean taken1;
	private boolean taken2;
	private boolean taken3;
	private boolean taken4;
	private boolean taken5;
	private boolean taken6;
	private boolean taken7;
	private boolean taken8;
	private boolean taken9;
	private boolean taken10;
	private boolean taken11;
	private boolean taken12;
	private boolean taken13;
	private boolean taken14;
	private boolean taken15;
	private boolean taken16;
	private boolean myTurn;
	private int spot;//location on board
	private int maxL;
	private int maxT;
	private int maxR;
	private int maxD;
	private boolean full;
	private ArrayList<Card> onBoard;
	private ArrayList<CardAI> aiBoard;
	private int pscore;
	private int ascore;
	private JLabel score;
	private JLabel scoren;
	private JLabel aiscore;
	private JLabel aiscoren;
	private JFrame frame;
	private JLabel label;
	private int boardWidth;
	private int boardHeight;
	private Rectangle card; 
	private Rectangle rec;
	private JLabel sLabel;
	private JLabel tL1;
	private JLabel tL2;
	private JLabel tL3;
	private JLabel tL4;
	private JLabel tL5;
	private JLabel listener;
	private JLabel board = new JLabel();
	private boolean pspot1,pspot2,pspot3,pspot4,pspot5,pspot6,pspot7,pspot8,pspot9,pspot10,pspot11,pspot12,pspot13,pspot14,pspot15,pspot16; //player card in spot
	private boolean aspot1,aspot2,aspot3,aspot4,aspot5,aspot6,aspot7,aspot8,aspot9,aspot10,aspot11,aspot12,aspot13,aspot14,aspot15,aspot16; //ai card in spot
	
	
	public Game()
	{	
		boardWidth = 400;
		boardHeight = 410;
		WINDOW_WIDTH = 800;
		WINDOW_HEIGHT = 650;
		s = new ImageIcon(getClass().getResource("/images/s.png"));
		sLabel = new JLabel(s);
		selected = 0; 
		p = new Player();
		a = new AI();
		taken1 = false;
		taken2 = false;
		taken3 = false;
		taken4 = false;
		taken5 = false;
		taken6 = false;
		taken7 = false;
		taken8 = false;
		taken9 = false;
		taken10 = false;
		taken11 = false;
		taken12 = false;
		taken13 = false;
		taken14 = false;
		taken15 = false;
		taken16 = false;
		myTurn = true;
		ta0 = null;
		spot = 0;
		full = false;
		onBoard = new ArrayList<Card>();
		aiBoard = new ArrayList<CardAI>();
		pscore = 0;
		ascore = 0;
		score=null;
		scoren=null;
		aiscoren=null;
		aiscore=null;
		frame = new JFrame("Tetra Master");
		label = new JLabel();
		img = null;
		card = null;
		frame.setResizable(false);
		tL1 = new JLabel();
		tL2 = new JLabel();
		tL3 = new JLabel();
		tL4 = new JLabel();
		tL5 = new JLabel();
		pspot1=false;pspot2=false;pspot3=false;pspot4=false;pspot5=false;pspot6=false;pspot7=false;pspot8=false;pspot9=false;pspot10=false;
		pspot11=false;pspot12=false;pspot13=false;pspot14=false;pspot15=false;pspot16=false;
		aspot1=false;aspot2=false;aspot3=false;aspot4=false;aspot5=false;aspot6=false;aspot7=false;aspot8=false;aspot9=false;aspot10=false;
		aspot11=false;aspot12=false;aspot13=false;aspot14=false;aspot15=false;aspot16=false;
		
		frame.pack();
		frame.setVisible(true);
		frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args) throws InterruptedException
	{
		Game g = new Game();	
		g.run();
	}
	public void run() throws InterruptedException
	{
		Container pane = frame.getContentPane();
		pane.setLayout(null);
		menu(pane);
		
		listener = new JLabel();
		listener.addMouseListener(this);
		pane.add(listener);
		listener.setBounds(0, 0, 800,700);
		
		
		if(p.getHandSize()==0)
		{
			pHand(pane);
		}//end if
		
		if(a.getHand().size()==0)
		{
			aHand(pane);
		}//end if
		
		
		while(true)
		{	
			if(taken1==true && taken2==true && taken3==true && taken4==true && taken5==true
					&& taken6==true && taken7==true && taken8==true && taken9==true
					&& taken10==true && taken11==true && taken12==true && taken13==true 
					&& taken14==true && taken15==true && taken16==true)
				full = true;
			if(full==true)
			{
				endGame();
			}
			//AI LOGIC START
			if(myTurn==false)
			{
				aiLogic();
			}
			//System.out.println();//not getting back here
			//AI LOGIC END
			
		}//end while true
	}//end run
	
	public void menu(Container pane)
	{
		img = new ImageIcon(getClass().getResource("/images/board.png"));
		board = new JLabel(img);
		pane.add(board);
		board.setBounds(200, 100, boardWidth, boardHeight);
		
		score = new JLabel();
		score.setIcon(new ImageIcon(getClass().getResource("/images/score.png")));
		pane.add(score);
		Rectangle rs = new Rectangle(50,260,100,100);
		score.setBounds(rs);
		
		scoren = new JLabel();
		scoren.setIcon(new ImageIcon(getClass().getResource("/images/s0.png")));
		pane.add(scoren);
		scoren.setBounds(new Rectangle(50,280,100,100));
		
		aiscoren = new JLabel();
		aiscoren.setIcon(new ImageIcon(getClass().getResource("/images/s0.png")));
		pane.add(aiscoren);
		aiscoren.setBounds(new Rectangle(650,280,100,100));
		
		aiscore = new JLabel();
		aiscore.setIcon(new ImageIcon(getClass().getResource("/images/ascore.png")));
		pane.add(aiscore);
		Rectangle rsa = new Rectangle(650,260,100,100);
		aiscore.setBounds(rsa);
	}
	
	public void checkHand(Container pane)

	{
		//if(p.getHandSize()<5 && p.getHandSize()>0)
		//{
			if(p.getDeck().getSize()>0)
				p.draw();
			
			for(int x=0; x<p.getHandSize(); x++)		//for loop player hand
			{
				String temp = "/images/";
				temp += p.getHand().get(x).getName();
				temp+=".png";
				if(x==0)
				{
					t0 = new ImageIcon(getClass().getResource(temp));
					tL1 = new JLabel(t0);
					pane.add(tL1);
					card = new Rectangle(150,510,100,100);
					tL1.setBounds(card);
				}
				if(x==1)
				{
					t1 = new ImageIcon(getClass().getResource(temp));
					tL2 = new JLabel(t1);
					pane.add(tL2);
					card = new Rectangle(260,510,100,100);
					tL2.setBounds(card);
				}
				if(x==2)
				{
					t2 = new ImageIcon(getClass().getResource(temp));
					tL3 = new JLabel(t2);
					pane.add(tL3);
					card = new Rectangle(360,510,100,100);
					tL3.setBounds(card);
				}
				if(x==3)
				{
					t3 = new ImageIcon(getClass().getResource(temp));
					tL4 = new JLabel(t3);
					pane.add(tL4);
					card = new Rectangle(460,510,100,100);
					tL4.setBounds(card);
				}
				if(x==4)
				{
					t4 = new ImageIcon(getClass().getResource(temp));
					tL5 = new JLabel(t4);
					pane.add(tL5);
					card = new Rectangle(560,510,100,100);
					tL5.setBounds(card);
				}
			}
		//}
		
	}//end checkHand

	public void checkAiHand(Container pane)
	{
		//if(a.getHandSize()<=5 && a.getHandSize()>0)
		//{
			if(a.getDeck().getSize()>0)
				a.draw();
			ta0 = new ImageIcon(getClass().getResource("/images/back.png"));
			for(int x=0; x<a.getHandSize(); x++)		//for loop ai hand
			{	
				if(x==0)
				{
					label = new JLabel(ta0);
					pane.add(label);
					card = new Rectangle(150,10,100,100);
					label.setBounds(card);
				}
				if(x==1)
				{
					label = new JLabel(ta0);
					pane.add(label);
					card = new Rectangle(260,10,100,100);
					label.setBounds(card);
				}
				if(x==2)
				{
					label = new JLabel(ta0);
					pane.add(label);
					card = new Rectangle(360,10,100,100);
					label.setBounds(card);
				}
				if(x==3)
				{
					label = new JLabel(ta0);
					pane.add(label);
					card = new Rectangle(460,10,100,100);
					label.setBounds(card);
				}
				if(x==4)
				{
					label = new JLabel(ta0);
					pane.add(label);
					card = new Rectangle(560,10,100,100);
					label.setBounds(card);
				}
			}//end for
		//}
		
	}
	
	public void mouseClicked(MouseEvent e)
	{
		Container pane = frame.getContentPane();
		String temp = "/images/";
		if(e.getX()>150 && e.getX()<230 && e.getY()>510 && e.getY()<590 && myTurn==true && tL1!=null)
			//150 is top left corner
			//230 is top right corner
			//510 is top of card Y-d
			//590 is bottom of card Y-d
		{//player hand 1
			sLabel.setIcon(null);
			//s.png is 100x100  card is 80x80   so -10 x and y
			//offset of highlighted region is 10 on every side
			sLabel = new JLabel(s);
			pane.add(sLabel);
			Rectangle r = new Rectangle(150,510,100,100);
			sLabel.setBounds(r);
			selected = 1;//offset by one from player card index
		}
		if(e.getX()>260 && e.getX()<340 && e.getY()>510 && e.getY()<590 && myTurn==true && tL2!=null)
		{//player hand 2
			sLabel.setIcon(null);
			sLabel = new JLabel(s);
			pane.add(sLabel);
			Rectangle r = new Rectangle(260,510,100,100);
			sLabel.setBounds(r);
			selected = 2;
		}
		if(e.getX()>360 && e.getX()<440 && e.getY()>510 && e.getY()<590 && myTurn==true && tL3!=null)
		{//player hand 3
			sLabel.setIcon(null);
			sLabel = new JLabel(s);
			pane.add(sLabel);
			Rectangle r = new Rectangle(360,510,100,100);
			sLabel.setBounds(r);
			selected = 3;
		}
		if(e.getX()>460 && e.getX()<540 && e.getY()>510 && e.getY()<590 && myTurn==true && tL4!=null)
		{//player hand 4
			sLabel.setIcon(null);
			sLabel = new JLabel(s);
			pane.add(sLabel);
			Rectangle r = new Rectangle(460,510,100,100);
			sLabel.setBounds(r);
			selected = 4;
		}
		if(e.getX()>560 && e.getX()<640 && e.getY()>510 && e.getY()<590 && myTurn==true && tL5!=null)
		{//player hand 5
			sLabel.setIcon(null);
			sLabel = new JLabel(s);
			pane.add(sLabel);
			Rectangle r = new Rectangle(560,510,100,100);
			sLabel.setBounds(r);
			selected = 5;
		}
		if(e.getX()>205 && e.getX()<295 && e.getY()>105 && e.getY()<195 && taken1==false
				&& myTurn==true)
		//square 1
		//200-300 first square x
		//205-295 inside square, not edges
		//100-200 first square y
		//105-195 inside square, not edges
		{	
			if(selected==1)
			{
				temp += p.getHand().get(selected-1).getName();
				temp+=".png";
				ImageIcon q = new ImageIcon(getClass().getResource(temp));
				label = new JLabel(q);
				Rectangle r = new Rectangle(0,0,100,100);
				board.add(label);
				label.setBounds(r);
				
				sLabel.setIcon(null);
				for(int i=0; i<p.getHandSize(); i++)
				{
					tL1.setIcon(null);					
					tL2.setIcon(null);					
					tL3.setIcon(null);
					tL4.setIcon(null);
					tL5.setIcon(null);
				}
				onBoard.add(p.getHand().get(selected-1));
				p.use(selected-1);
				checkHand(pane);
				pspot1 = true;
				taken1 = true;
			}
			if(selected==2)
			{
				temp += p.getHand().get(selected-1).getName();
				temp+=".png";
				ImageIcon q = new ImageIcon(getClass().getResource(temp));
				label = new JLabel(q);
				Rectangle r = new Rectangle(0,0,100,100);
				board.add(label);
				label.setBounds(r);
				
				sLabel.setIcon(null);
				for(int i=0; i<p.getHandSize(); i++)
				{
					tL1.setIcon(null);					
					tL2.setIcon(null);					
					tL3.setIcon(null);
					tL4.setIcon(null);
					tL5.setIcon(null);
				}
				onBoard.add(p.getHand().get(selected-1));
				p.use(selected-1);
				checkHand(pane);
				pspot1 = true;
				taken1 = true;
			}
			if(selected==3)
			{
				temp += p.getHand().get(selected-1).getName();
				temp+=".png";
				ImageIcon q = new ImageIcon(getClass().getResource(temp));
				label = new JLabel(q);
				Rectangle r = new Rectangle(0,0,100,100);
				board.add(label);
				label.setBounds(r);
				
				sLabel.setIcon(null);
				for(int i=0; i<p.getHandSize(); i++)
				{
					tL1.setIcon(null);					
					tL2.setIcon(null);					
					tL3.setIcon(null);
					tL4.setIcon(null);
					tL5.setIcon(null);
				}
				onBoard.add(p.getHand().get(selected-1));
				p.use(selected-1);
				checkHand(pane);
				pspot1 = true;
				taken1 = true;
			}
			if(selected==4)
			{
				temp += p.getHand().get(selected-1).getName();
				temp+=".png";
				ImageIcon q = new ImageIcon(getClass().getResource(temp));
				label = new JLabel(q);
				Rectangle r = new Rectangle(0,0,100,100);
				board.add(label);
				label.setBounds(r);
				
				sLabel.setIcon(null);
				for(int i=0; i<p.getHandSize(); i++)
				{
					tL1.setIcon(null);					
					tL2.setIcon(null);					
					tL3.setIcon(null);
					tL4.setIcon(null);
					tL5.setIcon(null);
				}
				onBoard.add(p.getHand().get(selected-1));
				p.use(selected-1);
				checkHand(pane);
				pspot1 = true;
				taken1 = true;
			}
			if(selected==5)
			{
				temp += p.getHand().get(selected-1).getName();
				temp+=".png";
				ImageIcon q = new ImageIcon(getClass().getResource(temp));
				label = new JLabel(q);
				Rectangle r = new Rectangle(0,0,100,100);
				board.add(label);
				label.setBounds(r);
				
				sLabel.setIcon(null);
				for(int i=0; i<p.getHandSize(); i++)
				{
					tL1.setIcon(null);					
					tL2.setIcon(null);					
					tL3.setIcon(null);
					tL4.setIcon(null);
					tL5.setIcon(null);
				}
				onBoard.add(p.getHand().get(selected-1));
				p.use(selected-1);
				checkHand(pane);
				pspot1 = true;
				taken1 = true;
			}
			onBoard.get(onBoard.size()-1).setLoc(1);
			spot = 1;
			try {
				checkActionP(spot,pane);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			myTurn = false;
		}//end square 1 if
		if(e.getX()>305 && e.getX()<395 && e.getY()>105 && e.getY()<195 && taken2==false
				&& myTurn==true)
		//square 2
		{
			if(selected==1)
			{
				temp += p.getHand().get(selected-1).getName();
				temp+=".png";
				ImageIcon q = new ImageIcon(getClass().getResource(temp));
				label = new JLabel(q);
				Rectangle r = new Rectangle(100,0,100,100);
				board.add(label);
				label.setBounds(r);
				
				sLabel.setIcon(null);
				for(int i=0; i<p.getHandSize(); i++)
				{
					tL1.setIcon(null);					
					tL2.setIcon(null);					
					tL3.setIcon(null);
					tL4.setIcon(null);
					tL5.setIcon(null);
				}
				onBoard.add(p.getHand().get(selected-1));
				p.use(selected-1);
				checkHand(pane);
				pspot2 = true;
				taken2 = true;
			}
			if(selected==2)
			{
				temp += p.getHand().get(selected-1).getName();
				temp+=".png";
				ImageIcon q = new ImageIcon(getClass().getResource(temp));
				label = new JLabel(q);
				Rectangle r = new Rectangle(100,0,100,100);
				board.add(label);
				label.setBounds(r);
				
				sLabel.setIcon(null);
				for(int i=0; i<p.getHandSize(); i++)
				{
					tL1.setIcon(null);					
					tL2.setIcon(null);					
					tL3.setIcon(null);
					tL4.setIcon(null);
					tL5.setIcon(null);
				}
				onBoard.add(p.getHand().get(selected-1));
				p.use(selected-1);
				checkHand(pane);
				pspot2 = true;
				taken2 = true;
			}
			if(selected==3)
			{
				temp += p.getHand().get(selected-1).getName();
				temp+=".png";
				ImageIcon q = new ImageIcon(getClass().getResource(temp));
				label = new JLabel(q);
				Rectangle r = new Rectangle(100,0,100,100);
				board.add(label);
				label.setBounds(r);
				
				sLabel.setIcon(null);
				for(int i=0; i<p.getHandSize(); i++)
				{
					tL1.setIcon(null);					
					tL2.setIcon(null);					
					tL3.setIcon(null);
					tL4.setIcon(null);
					tL5.setIcon(null);
				}
				onBoard.add(p.getHand().get(selected-1));
				p.use(selected-1);
				checkHand(pane);
				pspot2 = true;
				taken2 = true;
			}
			if(selected==4)
			{
				temp += p.getHand().get(selected-1).getName();
				temp+=".png";
				ImageIcon q = new ImageIcon(getClass().getResource(temp));
				label = new JLabel(q);
				Rectangle r = new Rectangle(100,0,100,100);
				board.add(label);
				label.setBounds(r);
				
				sLabel.setIcon(null);
				for(int i=0; i<p.getHandSize(); i++)
				{
					tL1.setIcon(null);					
					tL2.setIcon(null);					
					tL3.setIcon(null);
					tL4.setIcon(null);
					tL5.setIcon(null);
				}
				onBoard.add(p.getHand().get(selected-1));
				p.use(selected-1);
				checkHand(pane);
				pspot2 = true;
				taken2 = true;
			}
			if(selected==5)
			{
				temp += p.getHand().get(selected-1).getName();
				temp+=".png";
				ImageIcon q = new ImageIcon(getClass().getResource(temp));
				label = new JLabel(q);
				Rectangle r = new Rectangle(100,0,100,100);
				board.add(label);
				label.setBounds(r);
				
				sLabel.setIcon(null);
				for(int i=0; i<p.getHandSize(); i++)
				{
					tL1.setIcon(null);					
					tL2.setIcon(null);					
					tL3.setIcon(null);
					tL4.setIcon(null);
					tL5.setIcon(null);
				}
				onBoard.add(p.getHand().get(selected-1));
				p.use(selected-1);
				checkHand(pane);
				pspot2 = true;
				taken2 = true;
			}
			onBoard.get(onBoard.size()-1).setLoc(2);
			spot = 2;
			try {
				checkActionP(spot,pane);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			myTurn = false;
		}//end square 2 if
		if(e.getX()>405 && e.getX()<495 && e.getY()>105 && e.getY()<195 && taken3==false
				&& myTurn==true)
			//square 3
			{
				if(selected==1)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(200,0,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot3 = true;
					taken3 = true;
				}
				if(selected==2)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(200,0,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot3 = true;
					taken3 = true;
				}
				if(selected==3)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(200,0,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot3 = true;
					taken3 = true;
				}
				if(selected==4)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(200,0,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot3 = true;
					taken3 = true;
				}
				if(selected==5)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(200,0,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot3 = true;
					taken3 = true;
				}
				onBoard.get(onBoard.size()-1).setLoc(3);
				spot = 3;
				try {
					checkActionP(spot,pane);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				myTurn = false;
			}//end square 3 if
		if(e.getX()>505 && e.getX()<595 && e.getY()>105 && e.getY()<195 && taken4==false
				&& myTurn==true)
			//square 4
			{
				if(selected==1)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(300,0,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot4 = true;
					taken4 = true;
				}
				if(selected==2)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(300,0,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot4 = true;
					taken4 = true;
				}
				if(selected==3)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(300,0,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot4 = true;
					taken4 = true;
				}
				if(selected==4)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(300,0,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot4 = true;
					taken4 = true;
				}
				if(selected==5)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(300,0,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot4 = true;
					taken4 = true;
				}
				onBoard.get(onBoard.size()-1).setLoc(4);
				spot = 4;
				try {
					checkActionP(spot,pane);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				myTurn = false;
			}//end square 4 if
		if(e.getX()>205 && e.getX()<295 && e.getY()>205 && e.getY()<295 && taken5==false
				&& myTurn==true)
			//square 5
			{
				if(selected==1)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(0,100,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot5 = true;
					taken5 = true;
				}
				if(selected==2)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(0,100,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot5 = true;
					taken5 = true;
				}
				if(selected==3)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(0,100,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot5 = true;
					taken5 = true;
				}
				if(selected==4)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(0,100,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot5 = true;
					taken5 = true;
				}
				if(selected==5)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(0,100,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot5 = true;
					taken5 = true;
				}
				onBoard.get(onBoard.size()-1).setLoc(5);
				spot = 5;
				try {
					checkActionP(spot,pane);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				myTurn = false;
			}//end square 5 if
		if(e.getX()>305 && e.getX()<395 && e.getY()>205 && e.getY()<295 && taken6==false
				&& myTurn==true)
			//square 6
			{
				if(selected==1)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(100,100,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot6 = true;
					taken6 = true;
				}
				if(selected==2)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(100,100,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot6 = true;
					taken6 = true;
				}
				if(selected==3)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(100,100,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot6 = true;
					taken6 = true;
				}
				if(selected==4)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(100,100,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot6 = true;
					taken6 = true;
				}
				if(selected==5)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(100,100,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot6 = true;
					taken6 = true;
				}
				onBoard.get(onBoard.size()-1).setLoc(6);
				spot = 6;
				try {
					checkActionP(spot,pane);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				myTurn = false;
			}//end square 6 if
		if(e.getX()>405 && e.getX()<495 && e.getY()>205 && e.getY()<295 && taken7==false
				&& myTurn==true)
			//square 7
			{
				if(selected==1)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(200,100,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot7 = true;
					taken7 = true;
				}
				if(selected==2)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(200,100,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot7 = true;
					taken7 = true;
				}
				if(selected==3)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(200,100,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot7 = true;
					taken7 = true;
				}
				if(selected==4)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(200,100,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot7 = true;
					taken7 = true;
				}
				if(selected==5)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(200,100,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot7 = true;
					taken7 = true;
				}
				onBoard.get(onBoard.size()-1).setLoc(7);
				spot = 7;
				try {
					checkActionP(spot,pane);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				myTurn = false;
			}//end square 7 if
		if(e.getX()>505 && e.getX()<595 && e.getY()>205 && e.getY()<295 && taken8==false
				&& myTurn==true)
			//square 8
			{
				if(selected==1)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(300,100,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot8 = true;
					taken8 = true;
				}
				if(selected==2)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(300,100,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot8 = true;
					taken8 = true;
				}
				if(selected==3)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(300,100,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot8 = true;
					taken8 = true;
				}
				if(selected==4)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(300,100,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot8 = true;
					taken8 = true;
				}
				if(selected==5)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(300,100,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot8 = true;
					taken8 = true;
				}
				onBoard.get(onBoard.size()-1).setLoc(8);
				spot = 8;
				try {
					checkActionP(spot,pane);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				myTurn = false;
			}//end square 8 if
		if(e.getX()>205 && e.getX()<295 && e.getY()>305 && e.getY()<395 && taken9==false
				&& myTurn==true)
			//square 9
			{
				if(selected==1)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(0,200,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot9 = true;
					taken9 = true;
				}
				if(selected==2)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(0,200,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot9 = true;
					taken9 = true;
				}
				if(selected==3)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(0,200,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot9 = true;
					taken9 = true;
				}
				if(selected==4)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(0,200,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot9 = true;
					taken9 = true;
				}
				if(selected==5)
				{
					temp+= p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(0,200,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot9 = true;
					taken9 = true;
				}
				onBoard.get(onBoard.size()-1).setLoc(9);
				spot = 9;
				try {
					checkActionP(spot,pane);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				myTurn = false;
			}//end square 9 if
		if(e.getX()>305 && e.getX()<395 && e.getY()>305 && e.getY()<395 && taken10==false
				&& myTurn==true)
			//square 10
			{
				if(selected==1)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(100,200,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot10 = true;
					taken10 = true;
				}
				if(selected==2)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(100,200,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot10 = true;
					taken10 = true;
				}
				if(selected==3)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(100,200,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot10 = true;
					taken10 = true;
				}
				if(selected==4)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(100,200,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot10 = true;
					taken10 = true;
				}
				if(selected==5)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(100,200,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot10 = true;
					taken10 = true;
				}
				onBoard.get(onBoard.size()-1).setLoc(10);
				spot = 10;
				try {
					checkActionP(spot,pane);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				myTurn = false;
			}//end square 10 if
		if(e.getX()>405 && e.getX()<495 && e.getY()>305 && e.getY()<395 && taken11==false
				&& myTurn==true)
			//square 11
			{
				if(selected==1)
				{
					temp+= p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(200,200,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot11 = true;
					taken11 = true;
				}
				if(selected==2)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(200,200,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot11 = true;
					taken11 = true;
				}
				if(selected==3)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(200,200,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot11 = true;
					taken11 = true;
				}
				if(selected==4)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(200,200,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot11 = true;
					taken11 = true;
				}
				if(selected==5)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(200,200,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot11 = true;
					taken11 = true;
				}
				onBoard.get(onBoard.size()-1).setLoc(11);
				spot = 11;
				try {
					checkActionP(spot,pane);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				myTurn = false;
			}//end square 11 if
		if(e.getX()>505 && e.getX()<595 && e.getY()>305 && e.getY()<395 && taken12==false
				&& myTurn==true)
			//square 12
			{
				if(selected==1)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(300,200,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot12 = true;
					taken12 = true;
				}
				if(selected==2)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(300,200,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot12 = true;
					taken12 = true;
				}
				if(selected==3)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(300,200,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot12 = true;
					taken12 = true;
				}
				if(selected==4)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(300,200,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot12 = true;
					taken12 = true;
				}
				if(selected==5)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(300,200,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot12 = true;
					taken12 = true;
				}
				onBoard.get(onBoard.size()-1).setLoc(12);
				spot = 12;
				try {
					checkActionP(spot,pane);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				myTurn = false;
			}//end square 12 if
		if(e.getX()>205 && e.getX()<295 && e.getY()>405 && e.getY()<495 && taken13==false
				&& myTurn==true)
			//square 13
			{
				if(selected==1)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(0,300,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot13 = true;
					taken13 = true;
				}
				if(selected==2)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(0,300,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot13 = true;
					taken13 = true;
				}
				if(selected==3)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(0,300,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot13 = true;
					taken13 = true;
				}
				if(selected==4)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(0,300,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot13 = true;
					taken13 = true;
				}
				if(selected==5)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(0,300,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot13 = true;
					taken13 = true;
				}
				onBoard.get(onBoard.size()-1).setLoc(13);
				spot = 13;
				try {
					checkActionP(spot,pane);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				myTurn = false;
			}//end square 13 if
		if(e.getX()>305 && e.getX()<395 && e.getY()>405 && e.getY()<495 && taken14==false
				&& myTurn==true)
			//square 14
			{
				if(selected==1)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(100,300,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot14 = true;
					taken14 = true;
				}
				if(selected==2)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(100,300,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot14 = true;
					taken14 = true;
				}
				if(selected==3)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(100,300,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot14 = true;
					taken14 = true;
				}
				if(selected==4)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(100,300,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot14 = true;
					taken14 = true;
				}
				if(selected==5)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(100,300,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot14 = true;
					taken14 = true;
				}
				onBoard.get(onBoard.size()-1).setLoc(14);
				spot = 14;
				try {
					checkActionP(spot,pane);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				myTurn = false;
			}//end square 14 if
		if(e.getX()>405 && e.getX()<495 && e.getY()>405 && e.getY()<495 && taken15==false
				&& myTurn==true)
			//square 15
			{
				if(selected==1)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(200,300,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot15 = true;
					taken15 = true;
				}
				if(selected==2)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(200,300,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot15 = true;
					taken15 = true;
				}
				if(selected==3)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(200,300,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot15 = true;
					taken15 = true;
				}
				if(selected==4)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(200,300,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot15 = true;
					taken15 = true;
				}
				if(selected==5)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(200,300,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot15 = true;
					taken15 = true;
				}
				onBoard.get(onBoard.size()-1).setLoc(15);
				spot = 15;
				try {
					checkActionP(spot,pane);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				myTurn = false;
			}//end square 15 if
		if(e.getX()>505 && e.getX()<595 && e.getY()>405 && e.getY()<495 && taken16==false
				&& myTurn==true)
			//square 16
			{
				if(selected==1)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(300,300,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot16 = true;
					taken16 = true;
				}
				if(selected==2)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(300,300,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot16 = true;
					taken16 = true;
				}
				if(selected==3)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(300,300,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot16 = true;
					taken16 = true;
				}
				if(selected==4)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(300,300,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot16 = true;
					taken16 = true;
				}
				if(selected==5)
				{
					temp += p.getHand().get(selected-1).getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					Rectangle r = new Rectangle(300,300,100,100);
					board.add(label);
					label.setBounds(r);
					
					sLabel.setIcon(null);
					for(int i=0; i<p.getHandSize(); i++)
					{
						tL1.setIcon(null);					
						tL2.setIcon(null);					
						tL3.setIcon(null);
						tL4.setIcon(null);
						tL5.setIcon(null);
					}
					onBoard.add(p.getHand().get(selected-1));
					p.use(selected-1);
					checkHand(pane);
					pspot16 = true;
					taken16 = true;
				}
				onBoard.get(onBoard.size()-1).setLoc(16);
				spot = 16;
				try {
					checkActionP(spot,pane);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				myTurn = false;
			}//end square 16 if
	}//end mouse clicked
	
	public void updatePscore(Container pane)
	{
		if(scoren!=null)
			scoren.setIcon(null);
		pscore++;
		if(pscore==1)
			scoren.setIcon(new ImageIcon(getClass().getResource("/images/s1.png")));
		if(pscore==2)
			scoren.setIcon(new ImageIcon(getClass().getResource("/images/s2.png")));
		if(pscore==3)
			scoren.setIcon(new ImageIcon(getClass().getResource("/images/s3.png")));
		if(pscore==4)
			scoren.setIcon(new ImageIcon(getClass().getResource("/images/s4.png")));
		if(pscore==5)
			scoren.setIcon(new ImageIcon(getClass().getResource("/images/s5.png")));
		if(pscore==6)
			scoren.setIcon(new ImageIcon(getClass().getResource("/images/s6.png")));
		if(pscore==7)
			scoren.setIcon(new ImageIcon(getClass().getResource("/images/s7.png")));
		if(pscore==8)
			scoren.setIcon(new ImageIcon(getClass().getResource("/images/s8.png")));
		if(pscore==9)
			scoren.setIcon(new ImageIcon(getClass().getResource("/images/s9.png")));
		if(pscore==10)
			scoren.setIcon(new ImageIcon(getClass().getResource("/images/s10.png")));
		if(pscore==11)
			scoren.setIcon(new ImageIcon(getClass().getResource("/images/s11.png")));
		if(pscore==12)
			scoren.setIcon(new ImageIcon(getClass().getResource("/images/s12.png")));
		if(pscore==13)
			scoren.setIcon(new ImageIcon(getClass().getResource("/images/s13.png")));
		if(pscore==14)
			scoren.setIcon(new ImageIcon(getClass().getResource("/images/s14.png")));
		if(pscore==15)
			scoren.setIcon(new ImageIcon(getClass().getResource("/images/s15.png")));
		if(pscore==16)
			scoren.setIcon(new ImageIcon(getClass().getResource("/images/s16.png")));
	}
	public void updateAscore(Container pane)
	{
		if(aiscoren!=null)
			aiscoren.setIcon(null);
		ascore++;
		if(ascore==1)
			aiscoren.setIcon(new ImageIcon(getClass().getResource("/images/s1.png")));
		if(ascore==2)
			aiscoren.setIcon(new ImageIcon(getClass().getResource("/images/s2.png")));
		if(ascore==3)
			aiscoren.setIcon(new ImageIcon(getClass().getResource("/images/s3.png")));
		if(ascore==4)
			aiscoren.setIcon(new ImageIcon(getClass().getResource("/images/s4.png")));
		if(ascore==5)
			aiscoren.setIcon(new ImageIcon(getClass().getResource("/images/s5.png")));
		if(ascore==6)
			aiscoren.setIcon(new ImageIcon(getClass().getResource("/images/s6.png")));
		if(ascore==7)
			aiscoren.setIcon(new ImageIcon(getClass().getResource("/images/s7.png")));
		if(ascore==8)
			aiscoren.setIcon(new ImageIcon(getClass().getResource("/images/s8.png")));
		if(ascore==9)
			aiscoren.setIcon(new ImageIcon(getClass().getResource("s/images/9.png")));
		if(ascore==10)
			aiscoren.setIcon(new ImageIcon(getClass().getResource("/images/s10.png")));
		if(ascore==11)
			aiscoren.setIcon(new ImageIcon(getClass().getResource("/images/s11.png")));
		if(ascore==12)
			aiscoren.setIcon(new ImageIcon(getClass().getResource("/images/s12.png")));
		if(ascore==13)
			aiscoren.setIcon(new ImageIcon(getClass().getResource("/images/s13.png")));
		if(ascore==14)
			aiscoren.setIcon(new ImageIcon(getClass().getResource("/images/s14.png")));
		if(ascore==15)
			aiscoren.setIcon(new ImageIcon(getClass().getResource("/images/s15.png")));
		if(ascore==16)
			aiscoren.setIcon(new ImageIcon(getClass().getResource("/images/s16.png")));
	}
	
	public void checkActionAI(int spot, Container pane) throws InterruptedException
	{
		Card card = null;//player card on board
		//aiBoard ai card on board
		String temp;
		ImageIcon t;
		if(spot==1)//ai put card into spot1
		{
			if(taken2==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==2)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{	
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getRight()>card.getLeft())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,0);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getRight()<card.getLeft())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(0,0);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getRight()==card.getLeft())
						{
							//nothing
						}
					}
					card = null;
				}
			}
			if(taken5==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==5)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getDown()>card.getTop())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(0,100);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getDown()<card.getTop())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,0);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getDown()==card.getTop())
						{
							//nothing
						}
					}
					card = null;
				}
			}
		}//end if spot1
		if(spot==2)
		{
			if(taken1==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==1)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getLeft()>card.getRight())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(0,0);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getLeft()<card.getRight())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,0);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getLeft()==card.getRight())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken1
			if(taken3==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==3)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getRight()>card.getLeft())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,0);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getRight()<card.getLeft())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,0);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getRight()==card.getLeft())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken3
			if(taken6==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==6)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getDown()>card.getTop())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,100);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getDown()<card.getTop())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,0);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getDown()==card.getTop())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken6
		}//end if spot 2
		if(spot==3)
		{
			if(taken2==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==2)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getLeft()>card.getRight())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,0);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getLeft()<card.getRight())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,0);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getLeft()==card.getRight())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken2
			if(taken4==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==4)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getRight()>card.getLeft())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(300,0);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getRight()<card.getLeft())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,0);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getDown()==card.getTop())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken7
			if(taken7==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==7)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getDown()>card.getTop())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,100);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getDown()<card.getTop())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,0);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getDown()==card.getTop())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken7
		}//end if spot3
		if(spot==4)
		{
			if(taken3==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==3)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getLeft()>card.getRight())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,0);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getLeft()<card.getRight())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(300,0);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getLeft()==card.getRight())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken3
			if(taken8==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==8)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getDown()>card.getTop())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(300,100);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getDown()<card.getTop())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(300,0);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getDown()==card.getTop())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken8
		}//end it spot4
		if(spot==5)
		{
			if(taken1==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==1)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getTop()>card.getDown())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(0,0);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getTop()<card.getDown())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(0,100);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getTop()==card.getDown())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken1
			if(taken6==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==6)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getRight()>card.getLeft())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,100);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getRight()<card.getLeft())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(0,100);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getRight()==card.getLeft())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken6
			if(taken9==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==9)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getDown()>card.getTop())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(0,200);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getDown()<card.getTop())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(0,100);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getDown()==card.getTop())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken9
		}//end if spot5
		if(spot==6)
		{
			if(taken2==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==2)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getTop()>card.getDown())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,0);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getTop()<card.getDown())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,100);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getTop()==card.getDown())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken2
			if(taken7==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==7)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getRight()>card.getLeft())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,100);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getRight()<card.getLeft())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,100);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getRight()==card.getLeft())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken7
			if(taken10==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==10)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getDown()>card.getTop())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,200);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getDown()<card.getTop())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,100);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getDown()==card.getTop())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken10
			if(taken5==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==5)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getLeft()>card.getRight())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(0,100);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getLeft()<card.getRight())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,100);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getLeft()==card.getRight())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken5
		}//end if spot6
		if(spot==7)
		{
			if(taken3==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==3)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getTop()>card.getDown())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,0);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getTop()<card.getDown())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,100);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getTop()==card.getDown())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken3
			if(taken8==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==8)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getLeft()>card.getRight())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(300,100);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getLeft()<card.getRight())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,100);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getLeft()==card.getRight())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken8
			if(taken11==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==11)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getDown()>card.getTop())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,200);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getDown()<card.getTop())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,100);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getDown()==card.getTop())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken11
			if(taken6==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==6)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getLeft()>card.getRight())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,100);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getLeft()<card.getRight())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,100);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getLeft()==card.getRight())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken6
		}//end if spot7
		if(spot==8)
		{
			if(taken4==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==4)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getTop()>card.getDown())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(300,0);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getTop()<card.getDown())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(300,100);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getTop()==card.getDown())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken4
			if(taken7==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==7)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getLeft()>card.getRight())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,100);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getLeft()<card.getRight())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(300,100);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getLeft()==card.getRight())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken7
			if(taken12==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==12)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getDown()>card.getTop())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(300,200);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getDown()<card.getTop())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(300,100);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getDown()==card.getTop())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken12
		}//end if spot8
		if(spot==9)
		{
			if(taken5==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==5)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getTop()>card.getDown())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(0,100);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getTop()<card.getDown())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,100);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getTop()==card.getDown())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken5
			if(taken10==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==10)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getRight()>card.getLeft())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,200);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getRight()<card.getLeft())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(0,200);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getRight()==card.getLeft())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken10
			if(taken13==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==13)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getDown()>card.getTop())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(0,300);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getDown()<card.getTop())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(0,200);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getDown()==card.getTop())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken13
		}//end if spot9
		if(spot==10)
		{
			if(taken6==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==6)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getTop()>card.getDown())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,100);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getTop()<card.getDown())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,200);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getTop()==card.getDown())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken6
			if(taken11==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==11)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getRight()>card.getLeft())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,200);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getRight()<card.getLeft())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,200);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getRight()==card.getLeft())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken11
			if(taken14==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==14)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getDown()>card.getTop())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,300);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getDown()<card.getTop())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,200);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getDown()==card.getTop())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken14
			if(taken9==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==9)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getLeft()>card.getRight())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(0,200);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getLeft()<card.getRight())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,200);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getLeft()==card.getRight())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken9
		}//end if spot10
		if(spot==11)
		{
			if(taken7==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==7)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getTop()>card.getDown())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,100);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getTop()<card.getDown())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,200);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getTop()==card.getDown())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken7
			if(taken12==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==12)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getRight()>card.getLeft())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(300,200);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getRight()<card.getLeft())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,200);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getRight()==card.getLeft())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken12
			if(taken15==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==15)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getDown()>card.getTop())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,300);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getDown()<card.getTop())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,200);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getDown()==card.getTop())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken15
			if(taken10==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==10)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getLeft()>card.getRight())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,200);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getLeft()<card.getRight())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,200);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getLeft()==card.getRight())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken10
		}//end if spot11
		if(spot==12)
		{
			if(taken8==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==8)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getTop()>card.getDown())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(300,100);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getTop()<card.getDown())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(300,200);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getTop()==card.getDown())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken8
			if(taken11==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==11)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getLeft()>card.getRight())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,200);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getLeft()<card.getRight())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(300,200);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getLeft()==card.getRight())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken11
			if(taken16==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==16)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getDown()>card.getTop())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(300,300);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getDown()<card.getTop())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(300,200);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getDown()==card.getTop())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken16
		}//end if spot12
		if(spot==13)
		{
			if(taken9==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==9)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getTop()>card.getDown())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(0,200);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getTop()<card.getDown())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(0,300);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getTop()==card.getDown())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken9
			if(taken14==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==14)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getRight()>card.getLeft())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,300);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getRight()<card.getLeft())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(0,300);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getRight()==card.getLeft())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken14
		}//end if spot13
		if(spot==14)
		{
			if(taken13==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==13)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getLeft()>card.getRight())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(0,300);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getLeft()<card.getRight())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,300);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getLeft()==card.getRight())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken13
			if(taken10==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==10)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getTop()>card.getDown())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,200);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getTop()<card.getDown())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,300);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getTop()==card.getDown())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end taken10
			if(taken15==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==15)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getRight()>card.getLeft())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,300);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getRight()<card.getLeft())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,300);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getRight()==card.getLeft())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken15
		}//end if spot14
		if(spot==15)
		{
			if(taken14==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==14)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getLeft()>card.getRight())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,300);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getLeft()<card.getRight())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,300);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getLeft()==card.getRight())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken14
			if(taken11==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==11)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getTop()>card.getDown())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,200);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getTop()<card.getDown())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,300);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getTop()==card.getDown())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken11
			if(taken16==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==16)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getRight()>card.getLeft())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(300,300);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getRight()<card.getLeft())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,300);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getRight()==card.getLeft())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken16
		}//end if spot15
		if(spot==16)
		{
			if(taken15==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==15)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getLeft()>card.getRight())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,300);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getLeft()<card.getRight())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(300,300);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getLeft()==card.getRight())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken15
			if(taken12==true)
			{
				//flip lowest card
				for(int it=0; it<onBoard.size(); it++)
				{
					if(onBoard.get(it).getLoc()==12)
					{
						card = onBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(aiBoard.get(aiBoard.size()-1).getTop()>card.getDown())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(300,200);
							tt.setIcon(t);
							
							updateAscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getTop()<card.getDown())
						{
							JOptionPane.showMessageDialog(getParent(), "FLIP");
							//flip card spot 1
							aiBoard.get(aiBoard.size()-1).setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(300,300);
							tt.setIcon(t);
							
							updatePscore(pane);
						}
						else if(aiBoard.get(aiBoard.size()-1).getTop()==card.getDown())
						{
							//nothing
						}
					}
					card = null;
				}
			}
		}//end if spot16
	}//end checkAction
	

	
	public void checkActionP(int spot, Container pane) throws InterruptedException
	{
		CardAI card = null;//ai card on board
		//aiBoard ai card on board
		String temp;
		ImageIcon t;
		if(spot==1)//player put card into spot1
		{
			if(taken2==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==2)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getRight()>card.getLeft())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,0);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getRight()<card.getLeft())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(0,0);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getRight()==card.getLeft())
						{
							//nothing
						}
					}
					card = null;
				}
			}
			if(taken5==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==5)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getDown()>card.getTop())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(0,100);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getDown()<card.getTop())
						{
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(0,0);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getDown()==card.getTop())
						{
							//nothing
						}
					}
					card = null;
				}
			}
		}//end if spot1
		if(spot==2)
		{
			if(taken1==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==1)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getLeft()>card.getRight())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(0,0);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getLeft()<card.getRight())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,0);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getLeft()==card.getRight())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken1
			if(taken3==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==3)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getRight()>card.getLeft())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,0);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getRight()<card.getLeft())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,0);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getRight()==card.getLeft())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken3
			if(taken6==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==6)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getDown()>card.getTop())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,100);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getDown()<card.getTop())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,0);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getDown()==card.getTop())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken6
		}//end if spot 2
		if(spot==3)
		{
			if(taken2==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==2)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getLeft()>card.getRight())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,0);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getLeft()<card.getRight())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,0);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getLeft()==card.getRight())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken2
			if(taken4==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==4)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getRight()>card.getLeft())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(300,0);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getRight()<card.getLeft())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,0);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getDown()==card.getTop())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken7
			if(taken7==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==7)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getDown()>card.getTop())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,100);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getDown()<card.getTop())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,0);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getDown()==card.getTop())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken7
		}//end if spot3
		if(spot==4)
		{
			if(taken3==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==3)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getLeft()>card.getRight())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,0);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getLeft()<card.getRight())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(300,0);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getLeft()==card.getRight())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken3
			if(taken8==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==8)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getDown()>card.getTop())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(300,100);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getDown()<card.getTop())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(300,0);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getDown()==card.getTop())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken8
		}//end it spot4
		if(spot==5)
		{
			if(taken1==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==1)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getTop()>card.getDown())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(0,0);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getTop()<card.getDown())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(0,100);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getTop()==card.getDown())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken1
			if(taken6==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==6)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getRight()>card.getLeft())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,100);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getRight()<card.getLeft())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(0,100);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getRight()==card.getLeft())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken6
			if(taken9==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==9)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getDown()>card.getTop())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(0,200);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getDown()<card.getTop())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(0,100);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getDown()==card.getTop())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken9
		}//end if spot5
		if(spot==6)
		{
			if(taken2==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==2)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getTop()>card.getDown())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,0);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getTop()<card.getDown())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,100);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getTop()==card.getDown())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken2
			if(taken7==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==7)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getRight()>card.getLeft())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,100);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getRight()<card.getLeft())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,100);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getRight()==card.getLeft())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken7
			if(taken10==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==10)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getDown()>card.getTop())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,200);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getDown()<card.getTop())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,100);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getDown()==card.getTop())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken10
			if(taken5==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==5)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getLeft()>card.getRight())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(0,100);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getLeft()<card.getRight())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,100);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getLeft()==card.getRight())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken5
		}//end if spot6
		if(spot==7)
		{
			if(taken3==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==3)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getTop()>card.getDown())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,0);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getTop()<card.getDown())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,100);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getTop()==card.getDown())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken3
			if(taken8==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==8)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getLeft()>card.getRight())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(300,100);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getLeft()<card.getRight())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,100);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getLeft()==card.getRight())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken8
			if(taken11==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==11)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getDown()>card.getTop())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,200);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getDown()<card.getTop())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,100);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getDown()==card.getTop())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken11
			if(taken6==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==6)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getLeft()>card.getRight())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,100);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getLeft()<card.getRight())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,100);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getLeft()==card.getRight())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken6
		}//end if spot7
		if(spot==8)
		{
			if(taken4==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==4)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getTop()>card.getDown())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(300,0);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getTop()<card.getDown())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(300,100);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getTop()==card.getDown())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken4
			if(taken7==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==7)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getLeft()>card.getRight())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,100);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getLeft()<card.getRight())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(300,100);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getLeft()==card.getRight())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken7
			if(taken12==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==12)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getDown()>card.getTop())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(300,200);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getDown()<card.getTop())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(300,100);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getDown()==card.getTop())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken12
		}//end if spot8
		if(spot==9)
		{
			if(taken5==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==5)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getTop()>card.getDown())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(0,100);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getTop()<card.getDown())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(0,200);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getTop()==card.getDown())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken5
			if(taken10==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==10)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getRight()>card.getLeft())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,200);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getRight()<card.getLeft())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(0,200);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getRight()==card.getLeft())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken10
			if(taken13==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==13)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getDown()>card.getTop())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(0,300);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getDown()<card.getTop())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(0,200);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getDown()==card.getTop())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken13
		}//end if spot9
		if(spot==10)
		{
			if(taken6==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==6)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getTop()>card.getDown())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,100);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getTop()<card.getDown())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,200);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getTop()==card.getDown())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken6
			if(taken11==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==11)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getRight()>card.getLeft())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,200);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getRight()<card.getLeft())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,200);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getRight()==card.getLeft())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken11
			if(taken14==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==14)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getDown()>card.getTop())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,300);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getDown()<card.getTop())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,200);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getDown()==card.getTop())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken14
			if(taken9==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==9)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getLeft()>card.getRight())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(0,200);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getLeft()<card.getRight())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,200);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getLeft()==card.getRight())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken9
		}//end if spot10
		if(spot==11)
		{
			if(taken7==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==7)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getTop()>card.getDown())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,100);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getTop()<card.getDown())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,200);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getTop()==card.getDown())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken7
			if(taken12==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==12)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getRight()>card.getLeft())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(300,200);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getRight()<card.getLeft())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,200);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getRight()==card.getLeft())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken12
			if(taken15==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==15)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getDown()>card.getTop())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,300);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getDown()<card.getTop())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,200);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getDown()==card.getTop())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken15
			if(taken10==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==10)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getLeft()>card.getRight())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,200);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getLeft()<card.getRight())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,200);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getLeft()==card.getRight())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken10
		}//end if spot11
		if(spot==12)
		{
			if(taken8==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==8)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getTop()>card.getDown())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(300,100);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getTop()<card.getDown())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(300,200);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getTop()==card.getDown())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken8
			if(taken11==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==11)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getLeft()>card.getRight())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,200);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getLeft()<card.getRight())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(300,200);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getLeft()==card.getRight())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken11
			if(taken16==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==16)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getDown()>card.getTop())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(300,300);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getDown()<card.getTop())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(300,200);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getDown()==card.getTop())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken16
		}//end if spot12
		if(spot==13)
		{
			if(taken9==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==9)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getTop()>card.getDown())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(0,200);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getTop()<card.getDown())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(0,300);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getTop()==card.getDown())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken9
			if(taken14==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==14)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getRight()>card.getLeft())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,300);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getRight()<card.getLeft())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(0,300);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getRight()==card.getLeft())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken14
		}//end if spot13
		if(spot==14)
		{
			if(taken13==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==13)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getLeft()>card.getRight())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(0,300);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getLeft()<card.getRight())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,300);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getLeft()==card.getRight())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken13
			if(taken10==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==10)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getTop()>card.getDown())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,200);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getTop()<card.getDown())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,300);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getTop()==card.getDown())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end taken10
			if(taken15==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==15)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getRight()>card.getLeft())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,300);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getRight()<card.getLeft())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,300);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getRight()==card.getLeft())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken15
		}//end if spot14
		if(spot==15)
		{
			if(taken14==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==14)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getLeft()>card.getRight())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(100,300);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getLeft()<card.getRight())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,300);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getLeft()==card.getRight())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken14
			if(taken11==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==11)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getTop()>card.getDown())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,200);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getTop()<card.getDown())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,300);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getTop()==card.getDown())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken11
			if(taken16==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==16)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getRight()>card.getLeft())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(300,300);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getRight()<card.getLeft())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,300);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getRight()==card.getLeft())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken16
		}//end if spot15
		if(spot==16)
		{
			if(taken15==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==15)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getLeft()>card.getRight())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(200,300);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getLeft()<card.getRight())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(300,300);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getLeft()==card.getRight())
						{
							//nothing
						}
					}
					card = null;
				}
			}//end if taken15
			if(taken12==true)
			{
				//flip lowest card
				for(int it=0; it<aiBoard.size(); it++)
				{
					if(aiBoard.get(it).getLoc()==12)
					{
						card = aiBoard.get(it);
						break;
					}
				}
				if(card!=null)
				{
					if(card.getFlipped()==false)
					{
						if(onBoard.get(onBoard.size()-1).getTop()>card.getDown())
						{
							//flip card spot 2
							card.setFlipped(true);
							temp = "/images/back.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(300,200);
							tt.setIcon(t);
							updatePscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getTop()<card.getDown())
						{
							//flip card spot 1
							onBoard.get(onBoard.size()-1).setFlipped(true);
							temp = "/images/backp.png";
							t = new ImageIcon(getClass().getResource(temp));
							label = new JLabel(t);
							JLabel tt = (JLabel) board.getComponentAt(300,300);
							tt.setIcon(t);
							updateAscore(pane);
						}
						else if(onBoard.get(onBoard.size()-1).getTop()==card.getDown())
						{
							//nothing
						}
					}
					card = null;
				}
			}
		}//end if spot16
	}//end checkAction

	
	public void aiLogic() throws InterruptedException
	{
		getMaxLeft();
		getMaxTop();
		getMaxRight();
		getMaxDown();
		
		/*for(int m=0; m<onBoard.size(); m++)//check against spots on board where player has put cards
		{
			if(onBoard.get(m).getLoc()==1)//check against right and down spots
			{
				if(taken2==false)
				{
					
				}
				else if(taken5==false)
				{
					
				}
			}
		}*/
		
		aiChoice(frame.getContentPane());
		
		
	}//end aiLogic

	
	public void aiChoice(Container pane) throws InterruptedException
	{
		CardAI card = null;
		int save = 0;
		String temp = "/images/";
		
		//logic >
		if(pspot1==true)
		{
			Card c = null;
			card = null;
			for(int i=0; i<onBoard.size(); i++)
			{
				if(onBoard.get(i).getLoc()==1)
				{
					c = onBoard.get(i);
				}
			}
			if(taken2==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getLeft()>c.getRight())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(100,0,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 2;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot2 = true;
					taken2 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken5==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getTop()>c.getDown())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(0,100,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 5;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot5 = true;
					taken5 = true;
					myTurn = true;
					return;
				}
			}
		}//end pspot1
		if(pspot2==true)
		{
			Card c = null;
			card = null;
			for(int i=0; i<onBoard.size(); i++)
			{
				if(onBoard.get(i).getLoc()==2)
				{
					c = onBoard.get(i);
				}
			}
			if(taken1==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getRight()>c.getLeft())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(0,0,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 1;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot1 = true;
					taken1 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken3==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getLeft()>c.getRight())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(200,0,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 3;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot3 = true;
					taken3 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken6==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getTop()>c.getDown())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(100,100,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 6;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot6 = true;
					taken6 = true;
					myTurn = true;
					return;
				}
			}
		}//end pspot2
		if(pspot3==true)
		{
			Card c = null;
			card = null;
			for(int i=0; i<onBoard.size(); i++)
			{
				if(onBoard.get(i).getLoc()==3)
				{
					c = onBoard.get(i);
				}
			}
			if(taken2==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getRight()>c.getLeft())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(100,0,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 2;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot2 = true;
					taken2 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken4==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getLeft()>c.getRight())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(300,0,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 4;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot4 = true;
					taken4 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken7==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getTop()>c.getDown())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(200,100,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 7;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot7 = true;
					taken7 = true;
					myTurn = true;
					return;
				}
			}
		}//end pspot3
		if(pspot4==true)
		{
			Card c = null;
			card = null;
			for(int i=0; i<onBoard.size(); i++)
			{
				if(onBoard.get(i).getLoc()==4)
				{
					c = onBoard.get(i);
				}
			}
			if(taken3==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getRight()>c.getLeft())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(200,0,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 3;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot3 = true;
					taken3 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken8==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getTop()>c.getDown())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(300,100,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 8;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot8 = true;
					taken8 = true;
					myTurn = true;
					return;
				}
			}	
		}//end pspot4
		if(pspot5==true)
		{
			Card c = null;
			card = null;
			for(int i=0; i<onBoard.size(); i++)
			{
				if(onBoard.get(i).getLoc()==5)
				{
					c = onBoard.get(i);
				}
			}
			if(taken1==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getDown()>c.getTop())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(0,0,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 1;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot1 = true;
					taken1 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken6==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getLeft()>c.getRight())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(100,100,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 6;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot6 = true;
					taken6 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken9==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getTop()>c.getDown())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(0,200,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 9;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot9 = true;
					taken9 = true;
					myTurn = true;
					return;
				}
			}
		}//end pspot5
		if(pspot6==true)
		{
			Card c = null;
			card = null;
			for(int i=0; i<onBoard.size(); i++)
			{
				if(onBoard.get(i).getLoc()==6)
				{
					c = onBoard.get(i);
				}
			}
			if(taken2==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getDown()>c.getTop())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(100,0,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 2;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot2 = true;
					taken2 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken7==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getLeft()>c.getRight())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(200,100,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 7;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot7 = true;
					taken7 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken10==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getTop()>c.getDown())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(100,200,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 10;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot10 = true;
					taken10 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken5==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getRight()>c.getLeft())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(0,100,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 5;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot5 = true;
					taken5 = true;
					myTurn = true;
					return;
				}
			}
		}//end pspot6
		if(pspot7==true)
		{
			Card c = null;
			card = null;
			for(int i=0; i<onBoard.size(); i++)
			{
				if(onBoard.get(i).getLoc()==7)
				{
					c = onBoard.get(i);
				}
			}
			if(taken3==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getDown()>c.getTop())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(200,0,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 3;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot3 = true;
					taken3 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken8==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getLeft()>c.getRight())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(300,100,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 8;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot8 = true;
					taken8 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken11==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getTop()>c.getDown())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(200,200,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 11;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot11 = true;
					taken11 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken6==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getRight()>c.getLeft())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(100,100,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 6;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot6 = true;
					taken6 = true;
					myTurn = true;
					return;
				}
			}
		}//end pspot7
		if(pspot8==true)
		{
			Card c = null;
			card = null;
			for(int i=0; i<onBoard.size(); i++)
			{
				if(onBoard.get(i).getLoc()==8)
				{
					c = onBoard.get(i);
				}
			}
			if(taken4==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getDown()>c.getTop())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(300,0,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 4;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot4 = true;
					taken4 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken12==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getTop()>c.getDown())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(300,200,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 12;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot12 = true;
					taken12 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken7==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getRight()>c.getLeft())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(200,100,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 7;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot7 = true;
					taken7 = true;
					myTurn = true;
					return;
				}
			}
		}//end pspot8
		if(pspot9==true)
		{
			Card c = null;
			card = null;
			for(int i=0; i<onBoard.size(); i++)
			{
				if(onBoard.get(i).getLoc()==9)
				{
					c = onBoard.get(i);
				}
			}
			if(taken5==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getDown()>c.getTop())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(0,100,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 5;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot5 = true;
					taken5 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken10==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getLeft()>c.getRight())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(100,200,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 10;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot10 = true;
					taken10 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken13==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getTop()>c.getDown())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(0,300,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 13;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot13 = true;
					taken13 = true;
					myTurn = true;
					return;
				}
			}
		}//end pspot9
		if(pspot10==true)
		{
			Card c = null;
			card = null;
			for(int i=0; i<onBoard.size(); i++)
			{
				if(onBoard.get(i).getLoc()==10)
				{
					c = onBoard.get(i);
				}
			}
			if(taken6==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getDown()>c.getTop())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(100,100,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 6;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot6 = true;
					taken6 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken11==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getLeft()>c.getRight())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(200,200,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 11;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot11 = true;
					taken11 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken14==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getTop()>c.getDown())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(100,300,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 14;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot14 = true;
					taken14 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken9==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getRight()>c.getLeft())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(0,200,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 9;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot9 = true;
					taken9 = true;
					myTurn = true;
					return;
				}
			}
		}//end pspot10
		if(pspot11==true)
		{
			Card c = null;
			card = null;
			for(int i=0; i<onBoard.size(); i++)
			{
				if(onBoard.get(i).getLoc()==11)
				{
					c = onBoard.get(i);
				}
			}
			if(taken7==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getDown()>c.getTop())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(200,100,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 7;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot7 = true;
					taken7 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken12==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getLeft()>c.getRight())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(300,200,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 12;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot12 = true;
					taken12 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken15==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getTop()>c.getDown())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(200,300,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 15;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot15 = true;
					taken15 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken10==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getRight()>c.getLeft())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(100,200,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 10;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot10 = true;
					taken10 = true;
					myTurn = true;
					return;
				}
			}
		}//end pspot11
		if(pspot12==true)
		{
			Card c = null;
			card = null;
			for(int i=0; i<onBoard.size(); i++)
			{
				if(onBoard.get(i).getLoc()==12)
				{
					c = onBoard.get(i);
				}
			}
			if(taken8==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getDown()>c.getTop())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(300,100,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 8;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot8 = true;
					taken8 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken16==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getTop()>c.getDown())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(300,300,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 16;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot16 = true;
					taken16 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken11==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getRight()>c.getLeft())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(200,200,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 11;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot11 = true;
					taken11 = true;
					myTurn = true;
					return;
				}
			}
		}//end pspot12
		if(pspot13==true)
		{
			Card c = null;
			card = null;
			for(int i=0; i<onBoard.size(); i++)
			{
				if(onBoard.get(i).getLoc()==13)
				{
					c = onBoard.get(i);
				}
			}
			if(taken9==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getDown()>c.getTop())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(0,200,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 9;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot9 = true;
					taken9 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken14==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getLeft()>c.getRight())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(100,300,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 14;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot14 = true;
					taken14 = true;
					myTurn = true;
					return;
				}
			}
		}//end pspot13
		if(pspot14==true)
		{
			Card c = null;
			card = null;
			for(int i=0; i<onBoard.size(); i++)
			{
				if(onBoard.get(i).getLoc()==14)
				{
					c = onBoard.get(i);
				}
			}
			if(taken10==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getDown()>c.getTop())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(100,200,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 10;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot10 = true;
					taken10 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken15==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getLeft()>c.getRight())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(200,300,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 15;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot15 = true;
					taken15 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken13==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getRight()>c.getLeft())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(0,300,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 13;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot13 = true;
					taken13 = true;
					myTurn = true;
					return;
				}
			}
		}//end pspot14
		if(pspot15==true)
		{
			Card c = null;
			card = null;
			for(int i=0; i<onBoard.size(); i++)
			{
				if(onBoard.get(i).getLoc()==15)
				{
					c = onBoard.get(i);
				}
			}
			if(taken11==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getDown()>c.getTop())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(200,200,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 11;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot11 = true;
					taken11 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken16==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getLeft()>c.getRight())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(300,300,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 16;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot16 = true;
					taken16 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken14==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getRight()>c.getLeft())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(100,300,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 14;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot14 = true;
					taken14 = true;
					myTurn = true;
					return;
				}
			}
		}//end pspot15
		if(pspot16==true)
		{
			Card c = null;
			card = null;
			for(int i=0; i<onBoard.size(); i++)
			{
				if(onBoard.get(i).getLoc()==16)
				{
					c = onBoard.get(i);
				}
			}
			if(taken12==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getDown()>c.getTop())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(300,200,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 12;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot12 = true;
					taken12 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken15==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getRight()>c.getLeft())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(200,300,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 15;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot15 = true;
					taken15 = true;
					myTurn = true;
					return;
				}
			}
		}//end pspot16
		
	
		
		//logic =
		if(pspot1==true)
		{
			Card c = null;
			card = null;
			for(int i=0; i<onBoard.size(); i++)
			{
				if(onBoard.get(i).getLoc()==1)
				{
					c = onBoard.get(i);
				}
			}
			if(taken2==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getLeft()==c.getRight())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(100,0,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 2;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot2 = true;
					taken2 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken5==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getTop()==c.getDown())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(0,100,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 5;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot5 = true;
					taken5 = true;
					myTurn = true;
					return;
				}
			}
		}//end pspot1
		if(pspot2==true)
		{
			Card c = null;
			card = null;
			for(int i=0; i<onBoard.size(); i++)
			{
				if(onBoard.get(i).getLoc()==2)
				{
					c = onBoard.get(i);
				}
			}
			if(taken1==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getRight()==c.getLeft())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(0,0,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 1;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot1 = true;
					taken1 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken3==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getLeft()==c.getRight())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(200,0,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 3;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot3 = true;
					taken3 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken6==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getTop()==c.getDown())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(100,100,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 6;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot6 = true;
					taken6 = true;
					myTurn = true;
					return;
				}
			}
		}//end pspot2
		if(pspot3==true)
		{
			Card c = null;
			card = null;
			for(int i=0; i<onBoard.size(); i++)
			{
				if(onBoard.get(i).getLoc()==3)
				{
					c = onBoard.get(i);
				}
			}
			if(taken2==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getRight()==c.getLeft())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(100,0,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 2;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot2 = true;
					taken2 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken4==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getLeft()==c.getRight())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(300,0,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 4;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot4 = true;
					taken4 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken7==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getTop()==c.getDown())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(200,100,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 7;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot7 = true;
					taken7 = true;
					myTurn = true;
					return;
				}
			}
		}//end pspot3
		if(pspot4==true)
		{
			Card c = null;
			card = null;
			for(int i=0; i<onBoard.size(); i++)
			{
				if(onBoard.get(i).getLoc()==4)
				{
					c = onBoard.get(i);
				}
			}
			if(taken3==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getRight()==c.getLeft())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(200,0,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 3;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot3 = true;
					taken3 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken8==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getTop()==c.getDown())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(300,100,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 8;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot8 = true;
					taken8 = true;
					myTurn = true;
					return;
				}
			}	
		}//end pspot4
		if(pspot5==true)
		{
			Card c = null;
			card = null;
			for(int i=0; i<onBoard.size(); i++)
			{
				if(onBoard.get(i).getLoc()==5)
				{
					c = onBoard.get(i);
				}
			}
			if(taken1==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getDown()==c.getTop())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(0,0,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 1;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot1 = true;
					taken1 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken6==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getLeft()==c.getRight())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(100,100,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 6;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot6 = true;
					taken6 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken9==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getTop()==c.getDown())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(0,200,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 9;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot9 = true;
					taken9 = true;
					myTurn = true;
					return;
				}
			}
		}//end pspot5
		if(pspot6==true)
		{
			Card c = null;
			card = null;
			for(int i=0; i<onBoard.size(); i++)
			{
				if(onBoard.get(i).getLoc()==6)
				{
					c = onBoard.get(i);
				}
			}
			if(taken2==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getDown()==c.getTop())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(100,0,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 2;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot2 = true;
					taken2 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken7==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getLeft()==c.getRight())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(200,100,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 7;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot7 = true;
					taken7 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken10==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getTop()==c.getDown())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(100,200,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 10;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot10 = true;
					taken10 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken5==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getRight()==c.getLeft())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(0,100,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 5;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot5 = true;
					taken5 = true;
					myTurn = true;
					return;
				}
			}
		}//end pspot6
		if(pspot7==true)
		{
			Card c = null;
			card = null;
			for(int i=0; i<onBoard.size(); i++)
			{
				if(onBoard.get(i).getLoc()==7)
				{
					c = onBoard.get(i);
				}
			}
			if(taken3==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getDown()==c.getTop())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(200,0,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 3;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot3 = true;
					taken3 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken8==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getLeft()==c.getRight())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(300,100,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 8;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot8 = true;
					taken8 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken11==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getTop()==c.getDown())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(200,200,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 11;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot11 = true;
					taken11 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken6==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getRight()==c.getLeft())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(100,100,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 6;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot6 = true;
					taken6 = true;
					myTurn = true;
					return;
				}
			}
		}//end pspot7
		if(pspot8==true)
		{
			Card c = null;
			card = null;
			for(int i=0; i<onBoard.size(); i++)
			{
				if(onBoard.get(i).getLoc()==8)
				{
					c = onBoard.get(i);
				}
			}
			if(taken4==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getDown()==c.getTop())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(300,0,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 4;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot4 = true;
					taken4 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken12==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getTop()==c.getDown())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(300,200,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 12;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot12 = true;
					taken12 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken7==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getRight()==c.getLeft())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(200,100,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 7;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot7 = true;
					taken7 = true;
					myTurn = true;
					return;
				}
			}
		}//end pspot8
		if(pspot9==true)
		{
			Card c = null;
			card = null;
			for(int i=0; i<onBoard.size(); i++)
			{
				if(onBoard.get(i).getLoc()==9)
				{
					c = onBoard.get(i);
				}
			}
			if(taken5==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getDown()==c.getTop())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(0,100,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 5;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot5 = true;
					taken5 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken10==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getLeft()==c.getRight())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(100,200,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 10;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot10 = true;
					taken10 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken13==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getTop()==c.getDown())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(0,300,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 13;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot13 = true;
					taken13 = true;
					myTurn = true;
					return;
				}
			}
		}//end pspot9
		if(pspot10==true)
		{
			Card c = null;
			card = null;
			for(int i=0; i<onBoard.size(); i++)
			{
				if(onBoard.get(i).getLoc()==10)
				{
					c = onBoard.get(i);
				}
			}
			if(taken6==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getDown()==c.getTop())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(100,100,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 6;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot6 = true;
					taken6 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken11==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getLeft()==c.getRight())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(200,200,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 11;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot11 = true;
					taken11 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken14==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getTop()==c.getDown())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(100,300,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 14;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot14 = true;
					taken14 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken9==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getRight()==c.getLeft())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(0,200,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 9;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot9 = true;
					taken9 = true;
					myTurn = true;
					return;
				}
			}
		}//end pspot10
		if(pspot11==true)
		{
			Card c = null;
			card = null;
			for(int i=0; i<onBoard.size(); i++)
			{
				if(onBoard.get(i).getLoc()==11)
				{
					c = onBoard.get(i);
				}
			}
			if(taken7==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getDown()==c.getTop())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(200,100,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 7;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot7 = true;
					taken7 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken12==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getLeft()==c.getRight())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(300,200,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 12;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot12 = true;
					taken12 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken15==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getTop()==c.getDown())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(200,300,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 15;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot15 = true;
					taken15 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken10==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getRight()==c.getLeft())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(100,200,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 10;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot10 = true;
					taken10 = true;
					myTurn = true;
					return;
				}
			}
		}//end pspot11
		if(pspot12==true)
		{
			Card c = null;
			card = null;
			for(int i=0; i<onBoard.size(); i++)
			{
				if(onBoard.get(i).getLoc()==12)
				{
					c = onBoard.get(i);
				}
			}
			if(taken8==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getDown()==c.getTop())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(300,100,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 8;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot8 = true;
					taken8 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken16==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getTop()==c.getDown())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(300,300,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 16;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot16 = true;
					taken16 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken11==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getRight()==c.getLeft())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(200,200,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 11;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot11 = true;
					taken11 = true;
					myTurn = true;
					return;
				}
			}
		}//end pspot12
		if(pspot13==true)
		{
			Card c = null;
			card = null;
			for(int i=0; i<onBoard.size(); i++)
			{
				if(onBoard.get(i).getLoc()==13)
				{
					c = onBoard.get(i);
				}
			}
			if(taken9==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getDown()==c.getTop())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(0,200,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 9;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot9 = true;
					taken9 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken14==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getLeft()==c.getRight())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(100,300,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 14;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot14 = true;
					taken14 = true;
					myTurn = true;
					return;
				}
			}
		}//end pspot13
		if(pspot14==true)
		{
			Card c = null;
			card = null;
			for(int i=0; i<onBoard.size(); i++)
			{
				if(onBoard.get(i).getLoc()==14)
				{
					c = onBoard.get(i);
				}
			}
			if(taken10==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getDown()==c.getTop())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(100,200,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 10;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot10 = true;
					taken10 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken15==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getLeft()==c.getRight())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(200,300,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 15;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot15 = true;
					taken15 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken13==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getRight()==c.getLeft())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(0,300,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 13;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot13 = true;
					taken13 = true;
					myTurn = true;
					return;
				}
			}
		}//end pspot14
		if(pspot15==true)
		{
			Card c = null;
			card = null;
			for(int i=0; i<onBoard.size(); i++)
			{
				if(onBoard.get(i).getLoc()==15)
				{
					c = onBoard.get(i);
				}
			}
			if(taken11==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getDown()==c.getTop())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(200,200,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 11;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot11 = true;
					taken11 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken16==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getLeft()==c.getRight())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(300,300,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 16;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot16 = true;
					taken16 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken14==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getRight()==c.getLeft())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(100,300,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 14;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot14 = true;
					taken14 = true;
					myTurn = true;
					return;
				}
			}
		}//end pspot15
		if(pspot16==true)
		{
			Card c = null;
			card = null;
			for(int i=0; i<onBoard.size(); i++)
			{
				if(onBoard.get(i).getLoc()==16)
				{
					c = onBoard.get(i);
				}
			}
			if(taken12==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getDown()==c.getTop())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(300,200,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 12;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot12 = true;
					taken12 = true;
					myTurn = true;
					return;
				}
			}
			else if(taken15==false)
			{
				for(int i=0; i<a.getHandSize(); i++)
				{
					if(a.getHand().get(i).getRight()==c.getLeft())
					{
						card = a.getHand().get(i);
						save = i;
					}
				}
				if(card==null)
				{
					
				}
				else
				{
					temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(200,300,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 15;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot15 = true;
					taken15 = true;
					myTurn = true;
					return;
				}
			}
		}//end pspot16
		
		
		
		//logic for FCFS
		if(taken1==false)
		{
			for(int m=0; m<a.getHandSize(); m++)
			{
				if(a.getHand().get(m).getRight()==maxR || a.getHand().get(m).getDown()==maxD)
				{
					card = a.getHand().get(m);
					save = m;
				}
			}
			 temp += card.getName();
			temp+=".png";
			ImageIcon q = new ImageIcon(getClass().getResource(temp));
			label = new JLabel(q);
			board.add(label);
			rec = new Rectangle(0,0,100,100);
			label.setBounds(rec);
			
			aiBoard.add(card);
			spot = 1;//first location on board
			aiBoard.get(aiBoard.size()-1).setLoc(spot);
			checkActionAI(spot,pane);//does the battling
			
			
			a.use(save);
			checkAiHand(pane);
			aspot1 = true;
			taken1 = true;
			myTurn = true;
				
			
		}//end if taken1	
		
		else if(taken2==false)
		{
			for(int m=0; m<a.getHandSize(); m++)
			{
				if(a.getHand().get(m).getRight()==maxR || a.getHand().get(m).getDown()==maxD
						|| a.getHand().get(m).getLeft()==maxL)
				{
					card = a.getHand().get(m);
					save = m;
				}
			}
			 temp += card.getName();
			temp+=".png";
			ImageIcon q = new ImageIcon(getClass().getResource(temp));
			label = new JLabel(q);
			board.add(label);
			rec = new Rectangle(100,0,100,100);
			label.setBounds(rec);
			
			aiBoard.add(card);
			spot = 2;//first location on board
			aiBoard.get(aiBoard.size()-1).setLoc(spot);
			checkActionAI(spot,pane);//does the battling
			
			a.use(save);
			checkAiHand(pane);
			aspot2 = true;
			taken2 = true;
			myTurn = true;
			
		}//end if taken2	
		
		else if(taken3==false)
		{
			for(int m=0; m<a.getHandSize(); m++)
			{
				if(a.getHand().get(m).getRight()==maxR || a.getHand().get(m).getDown()==maxD
						|| a.getHand().get(m).getLeft()==maxL)
				{
					card = a.getHand().get(m);
					save = m;
				}
			}
					 temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(200,0,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 3;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot3 = true;
					taken3 = true;
					myTurn = true;
		}//end if taken3	
		
		else if(taken4==false)
		{
			for(int m=0; m<a.getHandSize(); m++)
			{
				if(a.getHand().get(m).getDown()==maxD || a.getHand().get(m).getLeft()==maxL)
				{
					card = a.getHand().get(m);
					save = m;
				}
			}
			 temp += card.getName();
			temp+=".png";
			ImageIcon q = new ImageIcon(getClass().getResource(temp));
			label = new JLabel(q);
			board.add(label);
			rec = new Rectangle(300,0,100,100);
			label.setBounds(rec);
			
			aiBoard.add(card);
			spot = 4;//first location on board
			aiBoard.get(aiBoard.size()-1).setLoc(spot);
			checkActionAI(spot,pane);//does the battling
			
			a.use(save);
			checkAiHand(pane);
			aspot4 = true;
			taken4 = true;
			myTurn = true;
		}//end if taken4	
		
		else if(taken5==false)
		{
			for(int m=0; m<a.getHandSize(); m++)
			{
				if(a.getHand().get(m).getRight()==maxR || a.getHand().get(m).getDown()==maxD
						|| a.getHand().get(m).getTop()==maxT)
				{
					card = a.getHand().get(m);
					save = m;
				}
			}
			 temp += card.getName();
			temp+=".png";
			ImageIcon q = new ImageIcon(getClass().getResource(temp));
			label = new JLabel(q);
			board.add(label);
			rec = new Rectangle(0,100,100,100);
			label.setBounds(rec);
			
			aiBoard.add(card);
			spot = 5;//first location on board
			aiBoard.get(aiBoard.size()-1).setLoc(spot);
			checkActionAI(spot,pane);//does the battling
			
			a.use(save);
			checkAiHand(pane);
			aspot5 = true;
			taken5 = true;
			myTurn = true;
		}//end if taken5	
		
		else if(taken6==false)
		{
			for(int m=0; m<a.getHandSize(); m++)
			{
				if(a.getHand().get(m).getRight()==maxR || a.getHand().get(m).getDown()==maxD
						|| a.getHand().get(m).getLeft()==maxL || a.getHand().get(m).getTop()==maxT)
				{
					card = a.getHand().get(m);
					save = m;
				}
			}
					 temp += card.getName();
					temp+=".png";
					ImageIcon q = new ImageIcon(getClass().getResource(temp));
					label = new JLabel(q);
					board.add(label);
					rec = new Rectangle(100,100,100,100);
					label.setBounds(rec);
					
					aiBoard.add(card);
					spot = 6;//first location on board
					aiBoard.get(aiBoard.size()-1).setLoc(spot);
					checkActionAI(spot,pane);//does the battling
					
					a.use(save);
					checkAiHand(pane);
					aspot6 = true;
					taken6 = true;
					myTurn = true;
		}//end if taken6	
		
		else if(taken7==false)
		{
			for(int m=0; m<a.getHandSize(); m++)
			{
				if(a.getHand().get(m).getRight()==maxR || a.getHand().get(m).getDown()==maxD
						|| a.getHand().get(m).getLeft()==maxL || a.getHand().get(m).getTop()==maxT)
				{
					card = a.getHand().get(m);
					save = m;
				}
			}
			 temp += card.getName();
			temp+=".png";
			ImageIcon q = new ImageIcon(getClass().getResource(temp));
			label = new JLabel(q);
			board.add(label);
			rec = new Rectangle(200,100,100,100);
			label.setBounds(rec);
			
			aiBoard.add(card);
			spot = 7;//first location on board
			aiBoard.get(aiBoard.size()-1).setLoc(spot);
			checkActionAI(spot,pane);//does the battling
			
			a.use(save);
			checkAiHand(pane);
			aspot7 = true;
			taken7 = true;
			myTurn = true;
		}//end if taken7	
		
		else if(taken8==false)
		{
			for(int m=0; m<a.getHandSize(); m++)
			{
				if(a.getHand().get(m).getTop()==maxT || a.getHand().get(m).getDown()==maxD
						|| a.getHand().get(m).getLeft()==maxL)
				{
					card = a.getHand().get(m);
					save = m;
				}
			}
			 temp += card.getName();
			temp+=".png";
			ImageIcon q = new ImageIcon(getClass().getResource(temp));
			label = new JLabel(q);
			board.add(label);
			rec = new Rectangle(300,100,100,100);
			label.setBounds(rec);
			
			aiBoard.add(card);
			spot = 8;//first location on board
			aiBoard.get(aiBoard.size()-1).setLoc(spot);
			checkActionAI(spot,pane);//does the battling
		
			a.use(save);
			checkAiHand(pane);
			aspot8 = true;
			taken8 = true;
			myTurn = true;
		}//end if taken8	
		
		else if(taken9==false)
		{
			for(int m=0; m<a.getHandSize(); m++)
			{
				if(a.getHand().get(m).getRight()==maxR || a.getHand().get(m).getDown()==maxD
						|| a.getHand().get(m).getTop()==maxT)
				{
					card = a.getHand().get(m);
					save = m;
				}
			}
			 temp += card.getName();
			temp+=".png";
			ImageIcon q = new ImageIcon(getClass().getResource(temp));
			label = new JLabel(q);
			board.add(label);
			rec = new Rectangle(0,200,100,100);
			label.setBounds(rec);
			
			aiBoard.add(card);
			spot = 9;//first location on board
			aiBoard.get(aiBoard.size()-1).setLoc(spot);
			checkActionAI(spot,pane);//does the battling
		
			a.use(save);
			checkAiHand(pane);
			aspot9 = true;
			taken9 = true;
			myTurn = true;
		}//end if taken9	
		
		else if(taken10==false)
		{
			for(int m=0; m<a.getHandSize(); m++)
			{
				if(a.getHand().get(m).getRight()==maxR || a.getHand().get(m).getDown()==maxD
						|| a.getHand().get(m).getLeft()==maxL || a.getHand().get(m).getTop()==maxT)
				{
					card = a.getHand().get(m);
					save = m;
				}
			}
			 temp += card.getName();
			temp+=".png";
			ImageIcon q = new ImageIcon(getClass().getResource(temp));
			label = new JLabel(q);
			board.add(label);
			rec = new Rectangle(100,200,100,100);
			label.setBounds(rec);
			
			aiBoard.add(card);
			spot = 10;//first location on board
			aiBoard.get(aiBoard.size()-1).setLoc(spot);
			checkActionAI(spot,pane);//does the battling

			a.use(save);
			checkAiHand(pane);
			aspot10 = true;
			taken10 = true;
			myTurn = true;
		}//end if taken10	
		
		else if(taken11==false)
		{
			for(int m=0; m<a.getHandSize(); m++)
			{
				if(a.getHand().get(m).getRight()==maxR || a.getHand().get(m).getDown()==maxD
						|| a.getHand().get(m).getLeft()==maxL || a.getHand().get(m).getTop()==maxT)
				{
					card = a.getHand().get(m);
					save = m;
				}
			}
			 temp += card.getName();
			temp+=".png";
			ImageIcon q = new ImageIcon(getClass().getResource(temp));
			label = new JLabel(q);
			board.add(label);
			rec = new Rectangle(200,200,100,100);
			label.setBounds(rec);
			
			aiBoard.add(card);
			spot = 11;//first location on board
			aiBoard.get(aiBoard.size()-1).setLoc(spot);
			checkActionAI(spot,pane);//does the battling
			
			a.use(save);
			checkAiHand(pane);
			aspot11 = true;
			taken11 = true;
			myTurn = true;
		}//end if taken11	
		
		else if(taken12==false)
		{
			for(int m=0; m<a.getHandSize(); m++)
			{
				if(a.getHand().get(m).getTop()==maxT || a.getHand().get(m).getDown()==maxD
						|| a.getHand().get(m).getLeft()==maxL)
				{
					card = a.getHand().get(m);
					save = m;
				}
			}
			 temp += card.getName();
			temp+=".png";
			ImageIcon q = new ImageIcon(getClass().getResource(temp));
			label = new JLabel(q);
			board.add(label);
			rec = new Rectangle(300,200,100,100);
			label.setBounds(rec);
			
			aiBoard.add(card);
			spot = 12;//first location on board
			aiBoard.get(aiBoard.size()-1).setLoc(spot);
			checkActionAI(spot,pane);//does the battling
		
			a.use(save);
			checkAiHand(pane);
			aspot12 = true;
			taken12 = true;
			myTurn = true;
		}//end if taken12	
		
		else if(taken13==false)
		{
			for(int m=0; m<a.getHandSize(); m++)
			{
				if(a.getHand().get(m).getRight()==maxR || a.getHand().get(m).getTop()==maxT)
				{
					card = a.getHand().get(m);
					save = m;
				}
			}
			 temp += card.getName();
			temp+=".png";
			ImageIcon q = new ImageIcon(getClass().getResource(temp));
			label = new JLabel(q);
			board.add(label);
			rec = new Rectangle(0,300,100,100);
			label.setBounds(rec);
			
			aiBoard.add(card);
			spot = 13;//first location on board
			aiBoard.get(aiBoard.size()-1).setLoc(spot);
			checkActionAI(spot,pane);//does the battling
			
			a.use(save);
			checkAiHand(pane);
			aspot13 = true;
			taken13 = true;
			myTurn = true;
		}//end if taken13	
		
		else if(taken14==false)
		{
			for(int m=0; m<a.getHandSize(); m++)
			{
				if(a.getHand().get(m).getRight()==maxR || a.getHand().get(m).getTop()==maxT
						|| a.getHand().get(m).getLeft()==maxL)
				{
					card = a.getHand().get(m);
					save = m;
				}
			}
			 temp += card.getName();
			temp+=".png";
			ImageIcon q = new ImageIcon(getClass().getResource(temp));
			label = new JLabel(q);
			board.add(label);
			rec = new Rectangle(100,300,100,100);
			label.setBounds(rec);
			
			aiBoard.add(card);
			spot = 14;//first location on board
			aiBoard.get(aiBoard.size()-1).setLoc(spot);
			checkActionAI(spot,pane);//does the battling
			
			a.use(save);
			checkAiHand(pane);
			aspot14 = true;
			taken14 = true;
			myTurn = true;
		}//end if taken14	
		
		else if(taken15==false)
		{
			for(int m=0; m<a.getHandSize(); m++)
			{
				if(a.getHand().get(m).getRight()==maxR || a.getHand().get(m).getTop()==maxT
						|| a.getHand().get(m).getLeft()==maxL)
				{
					card = a.getHand().get(m);
					save = m;
				}
			}
			 temp += card.getName();
			temp+=".png";
			ImageIcon q = new ImageIcon(getClass().getResource(temp));
			label = new JLabel(q);
			board.add(label);
			rec = new Rectangle(200,300,100,100);
			label.setBounds(rec);
			
			aiBoard.add(card);
			spot = 15;//first location on board
			aiBoard.get(aiBoard.size()-1).setLoc(spot);
			checkActionAI(spot,pane);//does the battling
		
			a.use(save);
			checkAiHand(pane);
			aspot15 = true;
			taken15 = true;
			myTurn = true;
		}//end if taken15	
		
		else if(taken16==false)
		{
			for(int m=0; m<a.getHandSize(); m++)
			{
				if(a.getHand().get(m).getTop()==maxT || a.getHand().get(m).getLeft()==maxL)
				{
					card = a.getHand().get(m);
					save = m;
				}
			}
			 temp += card.getName();
			temp+=".png";
			ImageIcon q = new ImageIcon(getClass().getResource(temp));
			label = new JLabel(q);
			board.add(label);
			rec = new Rectangle(300,300,100,100);
			label.setBounds(rec);
			
			aiBoard.add(card);
			spot = 16;//first location on board
			aiBoard.get(aiBoard.size()-1).setLoc(spot);
			checkActionAI(spot,pane);//does the battling
		
			a.use(save);
			checkAiHand(pane);
			aspot16 = true;
			taken16 = true;
			myTurn = true;
		}//end if taken16	
	}
	
	public void pHand(Container pane)
	{
		p.getDeck().shuffle();
		p.draw();
		p.draw();
		p.draw();
		p.draw();
		p.draw();
		
		for(int x=0; x<p.getHandSize(); x++)		//for loop player hand
		{
			String temp = "/images/";
			temp+=p.getHand().get(x).getName();
			temp+=".png";
			if(x==0)
			{
				t0 = new ImageIcon(getClass().getResource(temp));
				tL1 = new JLabel(t0);
				pane.add(tL1);
				card = new Rectangle(150,510,100,100);
				tL1.setBounds(card);
			}
			if(x==1)
			{
				t1 = new ImageIcon(getClass().getResource(temp));
				tL2 = new JLabel(t1);
				pane.add(tL2);
				card = new Rectangle(260,510,100,100);
				tL2.setBounds(card);
			}
			if(x==2)
			{
				t2 = new ImageIcon(getClass().getResource(temp));
				tL3 = new JLabel(t2);
				pane.add(tL3);
				card = new Rectangle(360,510,100,100);
				tL3.setBounds(card);
			}
			if(x==3)
			{
				t3 = new ImageIcon(getClass().getResource(temp));
				tL4 = new JLabel(t3);
				pane.add(tL4);
				card = new Rectangle(460,510,100,100);
				tL4.setBounds(card);
			}
			if(x==4)
			{
				t4 = new ImageIcon(getClass().getResource(temp));
				tL5 = new JLabel(t4);
				pane.add(tL5);
				card = new Rectangle(560,510,100,100);
				tL5.setBounds(card);
			}
		}
	}
		
	public void aHand(Container pane)
	{
		//a.getDeck().shuffle();
		a.draw();
		a.draw();
		a.draw();
		a.draw();
		a.draw();
				
		ta0 = new ImageIcon(getClass().getResource("/images/back.png"));
		
		for(int x=0; x<a.getHand().size(); x++)		//for loop ai hand
		{
			if(x==0)
			{
				label = new JLabel(ta0);
				pane.add(label);
				card = new Rectangle(150,10,100,100);
				label.setBounds(card);
			}
			if(x==1)
			{
				label = new JLabel(ta0);
				pane.add(label);
				card = new Rectangle(260,10,100,100);
				label.setBounds(card);
			}
			if(x==2)
			{
				label = new JLabel(ta0);
				pane.add(label);
				card = new Rectangle(360,10,100,100);
				label.setBounds(card);
			}
			if(x==3)
			{
				label = new JLabel(ta0);
				pane.add(label);
				card = new Rectangle(460,10,100,100);
				label.setBounds(card);
			}
			if(x==4)
			{
				label = new JLabel(ta0);
				pane.add(label);
				card = new Rectangle(560,10,100,100);
				label.setBounds(card);
			}
		}//end for
	}

	
	public void getMaxLeft()
	{
		for(int mL=0; mL<a.getHandSize(); mL++)
		{
			if(mL+1 != a.getHandSize())
			{
				if(a.getHand().get(mL).getLeft() >= a.getHand().get(mL+1).getLeft())
				{
					if(mL==0 || a.getHand().get(mL).getLeft() > maxL)
					{
						maxL = a.getHand().get(mL).getLeft();
					}
				}
				if(a.getHand().get(mL+1).getLeft() >= a.getHand().get(mL).getLeft())
				{
					if(mL==0 || a.getHand().get(mL+1).getLeft() > maxL)
					{
						maxL = a.getHand().get(mL+1).getLeft();
					}
				}
			}
			else
			{
				if(a.getHand().get(mL).getLeft() > maxL)
				{
					maxL = a.getHand().get(mL).getLeft();
				}
			}
			
		}
	}

	
	public void getMaxTop()
	{
		for(int mT=0; mT<a.getHandSize(); mT++)
		{
			if(mT+1 != a.getHandSize())
			{
				if(a.getHand().get(mT).getTop() >= a.getHand().get(mT+1).getTop())
				{
					if(mT==0 || a.getHand().get(mT).getTop() > maxT)
					{
						maxT = a.getHand().get(mT).getTop();
					}
				}
				if(a.getHand().get(mT+1).getTop() >= a.getHand().get(mT).getTop())
				{
					if(mT==0 || a.getHand().get(mT+1).getTop() > maxT)
					{
						maxT = a.getHand().get(mT+1).getTop();
					}
				}
			}
			else
			{
				if(a.getHand().get(mT).getTop() > maxT)
				{
					maxT = a.getHand().get(mT).getTop();
				}
			}
			
		}
	}
	
	public void getMaxRight()
	{
		for(int mR=0; mR<a.getHandSize(); mR++)
		{
			if(mR+1 != a.getHandSize())
			{
				if(a.getHand().get(mR).getRight() >= a.getHand().get(mR+1).getRight())
				{
					if(mR==0 || a.getHand().get(mR).getRight() > maxR)
					{
						maxR = a.getHand().get(mR).getRight();
					}
				}
				if(a.getHand().get(mR+1).getRight() >= a.getHand().get(mR).getRight())
				{
					if(mR==0 || a.getHand().get(mR+1).getRight() > maxR)
					{
						maxR = a.getHand().get(mR+1).getRight();
					}
				}
			}
			else
			{
				if(a.getHand().get(mR).getRight() > maxR)
				{
					maxR = a.getHand().get(mR).getRight();
				}
			}
			
		}
	}
	
	public void getMaxDown()
	{
		for(int mD=0; mD<a.getHandSize(); mD++)
		{
			if(mD+1 != a.getHandSize())
			{
				if(a.getHand().get(mD).getDown() >= a.getHand().get(mD+1).getDown())
				{
					if(mD==0 || a.getHand().get(mD).getDown() > maxD)
					{
						maxD = a.getHand().get(mD).getDown();
					}
				}
				if(a.getHand().get(mD+1).getDown() >= a.getHand().get(mD).getDown())
				{
					if(mD==0 || a.getHand().get(mD+1).getDown() > maxD)
					{
						maxD = a.getHand().get(mD+1).getDown();
					}
				}
			}
			else
			{
				if(a.getHand().get(mD).getLeft() > maxD)
				{
					maxD = a.getHand().get(mD).getDown();
				}
			}
			
		}
	}
	
	public void endGame()
	{
		if(pscore>ascore)
			JOptionPane.showMessageDialog(getParent(), "You Win");
		if(pscore<ascore)
			JOptionPane.showMessageDialog(getParent(), "You Lose");
		if(pscore==ascore)
			JOptionPane.showMessageDialog(getParent(), "Draw");
		System.exit(0);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}//end class
