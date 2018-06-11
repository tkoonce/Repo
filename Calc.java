package rage;

import java.util.Random;

public class Calc {
	private Random rnd = new Random();
	
	public Calc()
	{
		
	}
	
	public int damage(double strength, double brains, double defense, double atk, boolean in, double drunk)
	{
		if(!in)
		{
			int result = (int)(atk * (1 + (strength*brains)/10) - defense);
			result = critical(result);
			return result;
		}
		else
		{
			int result = (int)(atk * (1 + (strength*brains)/10) - defense);
			result = critical(result);
			result = criticalIn(result, drunk);
			return result;
		}
	}
	
	public boolean hit(double accuracy)
	{
		int result = (rnd.nextInt(100));
		
		if((int)(accuracy+=70) >= result)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public int critical(int result)
	{
		int res = (rnd.nextInt(1000));
		if(res>850)
		{
			return (result * 2);
		}
		return result;
	}
	
	public int criticalIn(int result, double drunk)
	{
		int res = (rnd.nextInt(10));
		if(res<=(drunk+4))
		{
			return (result * 2);
		}
		return result;
	}
}
