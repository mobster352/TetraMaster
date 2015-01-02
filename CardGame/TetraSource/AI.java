import java.util.ArrayList;


public class AI {

	private ArrayList<CardAI> hand;
	private String name;
	private AIDeck1 deck;
	
	public AI()
	{
		hand = new ArrayList<CardAI>();
		name = "AI";
		deck = new AIDeck1();
	}
	public void draw()
	{
		hand.add((deck.draw()));
	}
	public void use(int i)
	{
		hand.remove(i);
	}
	public int getHandSize()
	{
		return this.hand.size();
	}
	
	public ArrayList<CardAI> getHand()
	{
		return this.hand;
	}
	public AIDeck1 getDeck()
	{
		return this.deck;
	}
	public String getName()
	{
		return this.name;
	}
}
