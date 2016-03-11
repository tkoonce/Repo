package rage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class PlayerSelect {
	public Rectangle player0Button = new Rectangle(Battle.WIDTH / 4 , 100, 200, 100);  
	public Rectangle player1Button = new Rectangle(Battle.WIDTH / 4 + 300, 100, 200, 100);  
	public Rectangle player2Button = new Rectangle(Battle.WIDTH / 4 , 225, 200, 100);  
	public Rectangle player3Button = new Rectangle(Battle.WIDTH / 4 + 300, 225, 200, 100);  
	public Rectangle player4Button = new Rectangle(Battle.WIDTH / 4 , 350, 200, 100);  
	public Rectangle player5Button = new Rectangle(Battle.WIDTH / 4 + 300, 350, 200, 100);  
	
	public void render(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt0 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.WHITE);
		g.drawString("Player Select", Battle.WIDTH / 2 + 30, 50);
		
		Font fnt1 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt1);
		g.setColor(Color.RED);
		g2d.draw(player0Button);
		g.drawString("Tyler", player0Button.x + 25, player0Button.y + 50);
		g2d.draw(player1Button);
		g.drawString("Drew", player1Button.x + 25, player1Button.y + 50);
		g2d.draw(player2Button);
		g.drawString("Zach", player2Button.x + 25, player2Button.y + 50);
		g2d.draw(player3Button);
		g.drawString("Jimmy", player3Button.x + 25, player3Button.y + 50);
		g2d.draw(player4Button);
		g.drawString("Mike", player4Button.x + 25, player4Button.y + 50);
		g2d.draw(player5Button);
		g.drawString("Dzavid", player5Button.x + 25, player5Button.y + 50);
		
	}
}
