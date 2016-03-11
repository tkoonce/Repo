package rage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class DisplayMoves {
	public Rectangle moveButton0 = new Rectangle(Battle.WIDTH / 2 + 150, 250, 300, 50);  
	public Rectangle moveButton1 = new Rectangle(Battle.WIDTH / 2 + 150, 300, 300, 50); 
	public Rectangle moveButton2 = new Rectangle(Battle.WIDTH / 2 + 150, 350, 300, 50);  
	public Rectangle moveButton3 = new Rectangle(Battle.WIDTH / 2 + 150, 400, 300, 50);
	
	public void render(Graphics g, Player p)
	{
		Graphics2D g2d = (Graphics2D) g;
		
		g.setColor(Color.WHITE);
		g.fillRect(Battle.WIDTH / 2+150, 250, 300, 200);
		Font fnt1 = new Font("arial", Font.PLAIN, 30);
		g.setFont(fnt1);
		g.setColor(Color.BLACK);
		g2d.draw(moveButton0);
		g.drawString(p.getatk1name(), moveButton0.x + 15, moveButton0.y + 35);
		g2d.draw(moveButton1);
		g.drawString(p.getatk2name(), moveButton1.x + 15, moveButton1.y + 35);
		g2d.draw(moveButton2);
		g.drawString(p.getatk3name(), moveButton2.x + 15, moveButton2.y + 35);
		g2d.draw(moveButton3);
		g.drawString(p.getatk4name(), moveButton3.x + 15, moveButton3.y + 35);
		
	}
}
