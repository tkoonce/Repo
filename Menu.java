package rage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu 
{
	public Rectangle playButton = new Rectangle(Battle.WIDTH / 2 + 120, 200, 100, 50);  
	public Rectangle quitButton = new Rectangle(Battle.WIDTH / 2 + 120, 300, 100, 50);  
	
	public void render(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt0 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.GREEN);
		g.drawString("Rage Castle", Battle.WIDTH / 2 + 30, 100);
		
		Font fnt1 = new Font("arial", Font.BOLD, 35);
		g.setFont(fnt1);
		g.setColor(Color.CYAN);
		g2d.draw(playButton);
		g.drawString("Play", playButton.x + 15, playButton.y + 35);
		g2d.draw(quitButton);
		g.drawString("Quit", quitButton.x + 15, quitButton.y + 35);
	}
	
	
}
