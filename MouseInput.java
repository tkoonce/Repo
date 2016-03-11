package rage;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener
{	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		
		if(mx >= Battle.WIDTH / 2 + 120 && mx <= Battle.WIDTH/2 + 220)
		{
			if(my >= 200 && my <= 250)
			{
				if(Battle.State == Battle.State.MENU)
				{
					Battle.State = Battle.State.PLAYERSELECT;
				}
			}
		}
		
		if(mx >= Battle.WIDTH / 2 + 120 && mx <= Battle.WIDTH/2 + 220)
		{
			if(my >= 300 && my <= 350)
			{
				if(Battle.State == Battle.State.MENU)
				{
					System.exit(1);
			
				}
			}
		}
		
		if(Battle.State == Battle.State.BATTLE)
		{
			if(mx >= Battle.WIDTH / 2 + 150 && mx <= Battle.WIDTH/2 + 450)
			{
				if(my >= 250 && my <= 300)
				{	
					Battle.Turns = Battle.TURNS.NOW;
					Battle.Moves = Battle.MOVES.MOVE1;
				}
			}
			if(mx >= Battle.WIDTH / 2 + 150 && mx <= Battle.WIDTH/2 + 450)
			{
				if(my >= 300 && my <= 350)
				{
					Battle.Turns = Battle.TURNS.NOW;
					Battle.Moves = Battle.MOVES.MOVE2;
				}
			}
			if(mx >= Battle.WIDTH / 2 + 150 && mx <= Battle.WIDTH/2 + 450)
			{
				if(my >= 350 && my <= 400)
				{
					Battle.Turns = Battle.TURNS.NOW;
					Battle.Moves = Battle.MOVES.MOVE3;
				}
			}
			if(mx >= Battle.WIDTH / 2 + 150 && mx <= Battle.WIDTH/2 + 450)
			{
				if(my >= 400 && my <= 450)
				{
					Battle.Turns = Battle.TURNS.NOW;
					Battle.Moves = Battle.MOVES.MOVE4;
				}
			}
		}
		else if(Battle.State == Battle.State.WIN||Battle.State == Battle.State.LOSE)
		{
			Battle.State = Battle.State.MENU;
		}
		
		
		//tyler
		if(mx >= Battle.WIDTH / 4 && mx <= Battle.WIDTH/4 + 200)
		{
			if(my >= 100 && my <= 200)
			{
				Battle.Character = Battle.CHARACTER.TYLER;
			}
		}//drew
		if(mx >= Battle.WIDTH / 4 + 300 && mx <= Battle.WIDTH/4 + 500)
		{
			if(my >= 100 && my <= 200)
			{
				Battle.Character = Battle.CHARACTER.DREW;
			}
		}//zach
		if(mx >= Battle.WIDTH / 4 && mx <= Battle.WIDTH/4 + 200)
		{
			if(my >= 225 && my <= 325)
			{
				Battle.Character = Battle.CHARACTER.ZACH;
			}
		}//jimmy
		if(mx >= Battle.WIDTH / 4 + 300 && mx <= Battle.WIDTH/4 + 500)
		{
			if(my >= 225 && my <= 325)
			{
				Battle.Character = Battle.CHARACTER.JIMMY;
			}
		}//mike
		if(mx >= Battle.WIDTH / 4 && mx <= Battle.WIDTH/4 + 200)
		{
			if(my >= 350 && my <= 450)
			{
				Battle.Character = Battle.CHARACTER.MIKE;
			}
		}//vid
		if(mx >= Battle.WIDTH / 4 + 300 && mx <= Battle.WIDTH/4 + 500)
		{
			if(my >= 350 && my <= 450)
			{
				Battle.Character = Battle.CHARACTER.DZAVID;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
