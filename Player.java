package rage;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player {
	private double x;
	private double y;
	private String name;
	private double stg;
	private double def;
	private double spd;
	private double brn;
	private double acc;
	private double drk;
	private double move1atk;
	private double move2atk;
	private double move3atk;
	private double move4atk;
	private String move1name;
	private String move2name="Drunken State";
	private String move3name;
	private String move4name;
	private int playerNum = -1;
	public Battle bat;
	
	private BufferedImage player;
	
	public Player(double x, double y, Battle battle, String name, double stg, double def, double spd, double brn, double acc, double drk, double move1atk, double move2atk, double move3atk, double move4atk, String move1name, String move3name, String movename4)
	{
		this.x = x;
		this.y = y;
		this.name=name;
		this.stg = stg;
		this.def=def;
		this.spd=spd;
		this.brn=brn;
		this.acc=acc;
		this.drk=drk;
		this.move1atk=move1atk;
		this.move2atk=move2atk;
		this.move3atk=move3atk;
		this.move4atk=move4atk;
		this.move1name=move1name;
		this.move3name=move3name;
		this.move4name=move4name;
		this.playerNum = playerNum;
		this.bat=battle;
	}
	
	public void render(Graphics g)
	{
		g.drawImage(player, (int)x, (int)y, null);
	}
	
	public void tick()
	{
		
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public void setX(double x)
	{
		this.x=x;
	}
	
	public void setY(double y)
	{
		this.y=y;
	}
	
 	public double getStg()
	{
 		return stg;
	}
	 	
	public double getDef()
 	{
 		return def;
 	}
	 	
 	public double getSpd()
 	{
 		return spd;
 	}
	 	
 	public double getBrn()
 	{
 		return brn;
 	}
	 	
 	public double getAcc()
 	{
 		return acc;
 	}
	 	
 	public double getDrk()
 	{
 		return drk;
 	}
	 	
 	public void setStg(double strength)
	{
 		this.stg=strength;
 	}
	 	
 	public void setDef(double defense)
 	{
 		this.def=defense;
 	}
	 	
 	public void setSpd(double speed)
 	{
 		this.spd=speed;
 	}
	 	
 	public void setBrn(double brain)
 	{
 		this.brn=brain;
 	}
	 	
 	public void setAcc(double accuracy)
 	{
 		this.acc=accuracy;
 	}
	 	
 	public void setDrk(double drunk)
 	{
 		this.drk=drunk;
 	} 
 	
 	public String getName()
 	{
 		return name;
 	}
	 	
 	public void setName(String name1)
	{
 		this.name=name1;
 	}
 	
 	public double getatk1()
 	{
 		return move1atk;
 	}
	 	
 	public void setatk1(double move1)
	{
 		this.move1atk=move1;
 	}
 	
 	public double getatk2()
 	{
 		return move2atk;
 	}
	 	
 	public void setatk2(double move2)
	{
 		this.move2atk=move2;
 	}
 	
 	public double getatk3()
 	{
 		return move3atk;
 	}
	 	
 	public void setatk3(double move3)
	{
 		this.move3atk=move3;
 	}
 	
 	public double getatk4()
 	{
 		return move4atk;
 	}
	 	
 	public void setatk4(double move4)
	{
 		this.move4atk=move4;
 	}
 	
 	public String getatk1name()
 	{
 		return move1name;
 	}
	 	
 	public void setatk1name(String move1)
	{
 		this.move1name=move1;
 	}
 	
 	public String getatk2name()
 	{
 		return move2name;
 	}
	 	
 	public void setatk2name(String move2)
	{
 		this.move2name=move2;
 	}
 	
 	public String getatk3name()
 	{
 		return move3name;
 	}
	 	
 	public void setatk3name(String move3)
	{
 		this.move3name=move3;
 	}
 	
 	public String getatk4name()
 	{
 		return move4name;
 	}
	 	
 	public void setatk4name(String move4)
	{
 		this.move4name=move4;
 	}
 	
	public int getplayerNum()
	{
		return playerNum;
	}
	
	public void setplayerNum(int playerNum1)
	{
		this.playerNum = playerNum1;
	}
	
	public void setImage(Battle ba)
	{
		if(this.playerNum == 0)
		{
			SpriteSheet ss = new SpriteSheet(ba.getSpriteSheet());
			player = ss.grabImage(1, 1, 190, 155);
		}
		else if(this.playerNum == 1)
		{
			SpriteSheet ss = new SpriteSheet(ba.getSpriteSheet1());
			player = ss.grabImage(1, 1, 190, 135);
		}
		else if(this.playerNum == 2)
		{
			SpriteSheet ss = new SpriteSheet(ba.getSpriteSheet2());
			player = ss.grabImage(1, 1, 170, 155);
		}
		else if(this.playerNum == 3)
		{
			SpriteSheet ss = new SpriteSheet(ba.getSpriteSheet3());
			player = ss.grabImage(1, 1, 190, 155);
		}
		else if(this.playerNum == 4)
		{
			SpriteSheet ss = new SpriteSheet(ba.getSpriteSheet4());
			player = ss.grabImage(1, 1, 190, 155);
		}
		else if(this.playerNum == 5)
		{
			SpriteSheet ss = new SpriteSheet(ba.getSpriteSheet5());
			player = ss.grabImage(1, 1, 190, 155);
		}
	}
	
}
