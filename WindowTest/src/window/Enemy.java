package window;

public class Enemy {

	private int hp;
	
	public Enemy()
	{
		hp = 2;
	}
	
	public void decHp(int decrement)
	{
		hp -= decrement;
	}
	
	public int getHp()
	{
		return hp;
	}
}
