package rage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Win_Lose {
	public void renderWin(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
	
		Font fnt0 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.WHITE);
		g.drawString("You Win", Battle.WIDTH / 2 + 70, 225);
	}
	
	public void renderLose(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
	
		Font fnt0 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.WHITE);
		g.drawString("You Lose", Battle.WIDTH / 2 + 50, 225);
	}
}
