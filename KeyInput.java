package rage;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter
{
	Battle battle;
	
	public KeyInput(Battle battle)
	{
		this.battle = battle;
	}
	
	public void keyPressed(KeyEvent e)
	{
		battle.keyPressed(e);
	}
	
	public void keyReleased(KeyEvent e)
	{
		battle.keyReleased(e);
	}
}
