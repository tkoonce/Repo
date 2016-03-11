package rage;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;

import javax.swing.*;

import sun.audio.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Battle extends Canvas implements Runnable {
		private static final long serialVersionUID = 1L;
		public static final int WIDTH = 320;
		public static final int HEIGHT = WIDTH / 12 * 9;
		public static final int SCALE = 2;
		public final String TITLE = "Rage Castle";
		
		private boolean running = false;
		private Thread thread;
		
		private int selection=0;
		private int playerTurn = 0;
		private int enemyTurn = 0;
		private boolean turn = false;
		
		private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		private BufferedImage spriteSheet = null;
		private BufferedImage spriteSheet1 = null;
		private BufferedImage spriteSheet2 = null;
		private BufferedImage spriteSheet3 = null;
		private BufferedImage spriteSheet4 = null;
		private BufferedImage spriteSheet5 = null;
		private BufferedImage background = null;
		
		private Player p;
		private Enemy en;
		private Menu menu;
		private Win_Lose wl;
		private PlayerSelect ps;
		private DisplayMoves dm;
		public int HP = 300;
		public int HP1 = 300;
		private boolean inebriated = false;
		private boolean enInebriated = false;
		
		private Calc calc;
		
		public static enum STATE
		{
			MENU,
			PLAYERSELECT,
			BATTLE,
			WIN,
			LOSE
		};
		
		public static enum CHARACTER
		{
			NONE,
			TYLER,
			DREW,
			ZACH,
			JIMMY,
			MIKE,
			DZAVID
		};
		
		public static enum MOVES
		{
			MOVE1,
			MOVE2,
			MOVE3,
			MOVE4
		};
		
		public static enum TURNS
		{
			NOW,
			NEVER
		};
		
		public static STATE State = STATE.MENU;
		public static CHARACTER Character = CHARACTER.NONE;
		public static MOVES Moves = MOVES.MOVE1;
		public static TURNS Turns = TURNS.NEVER;
		
		public void init()
		{
			BufferedImageLoader loader = new BufferedImageLoader();
			try
			{
				spriteSheet = loader.loadImage("/ty.png");
				spriteSheet1 = loader.loadImage("/drew.png");
				spriteSheet2 = loader.loadImage("/zach.png");
				spriteSheet3 = loader.loadImage("/jimmy2.png");
				spriteSheet4 = loader.loadImage("/mike.png");
				spriteSheet5 = loader.loadImage("/Yellow Blob1.png");
				background = loader.loadImage("/background.png");
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			
			addKeyListener(new KeyInput(this));
			addMouseListener(new MouseInput());
						
			p= new Player(20, 250, this,"0",0,0,0,0,0,0,0,0,0,0,"0","0","0");
			en = new Enemy(420,20,this,"0",0,0,0,0,0,0,0,0,0,0,"0","0","0");
			
			ps = new PlayerSelect();
			menu = new Menu();
			dm = new DisplayMoves();
			calc = new Calc();
			wl = new Win_Lose();
			
			playerTurn += p.getSpd();
			enemyTurn += en.getSpd();
			
		}
		
		private synchronized void start()
		{
			if(running)
			{
				return;
			}
			
			running = true;
			thread = new Thread(this);
			thread.start();
		}
		
		private synchronized void stop()
		{
			if(!running)
			{
				return;
			}
			
			running = false;
			try
			{
				thread.join();
			}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
			
			System.exit(1);
		}
		
		public void run()
		{
			init();
			long prevTime = System.nanoTime();
			final double amountOfTicks = 60.0;
			double ns = 1000000000 / amountOfTicks;
			double delta =0;
			int updates = 0;
			int frames =0;
			long timer = System.currentTimeMillis();
			
			while(running)
			{
				long present = System.nanoTime();
				delta += (present - prevTime ) / ns;
				prevTime = present;
				if(delta >= 1)
				{
					tick();
					updates++;
					delta--;
				}
				render();
				frames++;
				
				if(System.currentTimeMillis() - timer >1000)
				{
					timer += 1000;
					//System.out.println(updates + " Ticks, Fps " + frames);
					updates = 0;
					frames = 0;
				}
			}
			stop();
		}
		
		private void tick()
		{
			
			if(State == STATE.BATTLE)
			{
				int count =0;
				int encount =0;
				p.tick();
				if(Turns == Turns.NOW)
				{
					while(!playerturn(p,en))
					{
						boolean miss=calc.hit(p.getAcc());
						if(encount == 3)
						{
							miss=false;
							enInebriated=false;
							encount=0;
						}
						
						if(miss==true)
						{
							//code for enemy
							Random rnd = new Random();
							int result = (rnd.nextInt(4));
						
							if(result == 0)
							{
								HP1-=calc.damage(p.getStg(), p.getBrn(), en.getDef(), p.getatk1(), enInebriated, en.getDrk());
							}
							else if(result == 1)
							{
								enInebriated = true;
							}
							else if(result == 2)
							{
								HP1-=calc.damage(p.getStg(), p.getBrn(), en.getDef(), p.getatk3(), enInebriated, en.getDrk());
							}
							else if(result == 3)
							{
								HP1-=calc.damage(p.getStg(), p.getBrn(), en.getDef(), p.getatk4(), enInebriated, en.getDrk());
							}
							if(HP1 <=0)
							{
								State=State.LOSE;
								HP=300;
								HP1=300;
							}
						}
						playerTurn += p.getSpd();
					}
					
					boolean miss=calc.hit(p.getAcc());
					if(count == 3)
					{
						miss=false;
						inebriated=false;
						count=0;
					}
					
					if(miss==true)
					{
						if(Moves == Moves.MOVE1)
						{
							HP-=calc.damage(p.getStg(), p.getBrn(), en.getDef(), p.getatk1(), inebriated, en.getDrk());
						}
						else if(Moves == Moves.MOVE2)
						{
							inebriated = true;
						}
						else if(Moves == Moves.MOVE3)
						{
							HP-=calc.damage(p.getStg(), p.getBrn(), en.getDef(), p.getatk3(), inebriated, en.getDrk());
						}	
						else if(Moves == Moves.MOVE4)
						{
							HP-=calc.damage(p.getStg(), p.getBrn(), en.getDef(), p.getatk4(), inebriated, en.getDrk());
						}
						if(HP<=0)
						{
							State=State.WIN;
							HP=300;
							HP1=300;
						}	
					}
					
					enemyTurn += en.getSpd();
					Turns = Turns.NEVER;	
				}
			}
			
		}
		
		private void render()
		{
			BufferStrategy bs = this.getBufferStrategy();
			if(bs == null)
			{
				createBufferStrategy(3);
				return;
			}
			
			Graphics g = bs.getDrawGraphics();
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			
			if(State == STATE.BATTLE)
			{
				g.drawImage(background, 0, 0, null);
				p.render(g);
				en.render(g);
				dm.render(g,p);
				
				
				g.setColor(Color.GRAY);
				g.fillRect(5, 5, 300, 25);
				
				g.setColor(Color.GREEN);
				g.fillRect(5, 5, HP, 25);
				
				g.setColor(Color.GRAY);
				g.fillRect(5, 425, 300, 25);
				
				g.setColor(Color.GREEN);
				g.fillRect(5, 425, HP1, 25);
			}
			else if(State == STATE.MENU)
			{
				menu.render(g);
			}
			else if(State == STATE.PLAYERSELECT)
			{
				ps.render(g);
				
				if(Character == CHARACTER.TYLER)
				{
					if(selection==1)
					{
						en.setenemyNum(0);
						en.setName("Tyler");
						en.setStg(2);
						en.setDef(3);
						en.setSpd(6);
						en.setBrn(5);
						en.setAcc(4);
						en.setDrk(1);
						en.setatk1(4);
						en.setatk2(0);
						en.setatk3(7);
						en.setatk4(10);
						en.setatk1name("Slow Shower");
						en.setatk3name("Change Collector");
						en.setatk4name("Invisible GF");
						en.setImage(this);
						Battle.State = Battle.State.BATTLE;
						Character = CHARACTER.NONE;
						selection=0;
					}
					else
					{
						p.setplayerNum(0);
						p.setName("Tyler");
						p.setStg(2);
						p.setDef(3);
						p.setSpd(6);
						p.setBrn(5);
						p.setAcc(4);
						p.setDrk(1);
						p.setatk1(4);
						p.setatk2(0);
						p.setatk3(7);
						p.setatk4(10);
						p.setatk1name("Slow Shower");
						p.setatk3name("Change Collector");
						p.setatk4name("Invisible GF");
						p.setImage(this);
						selection=1;
						Battle.State = Battle.State.PLAYERSELECT;
						Character = CHARACTER.NONE;
					}
				}
				else if(Character == CHARACTER.DREW)
				{
					if(selection==1)
					{
						en.setenemyNum(1);
						en.setName("Drew");
						en.setStg(1);
						en.setDef(6);
						en.setSpd(5);
						en.setBrn(4);
						en.setAcc(2);
						en.setDrk(3);
						en.setatk1(4);
						en.setatk2(0);
						en.setatk3(7);
						en.setatk4(10);
						en.setatk1name("Shining");
						en.setatk3name("Spanish");
						en.setatk4name("Disappear");
						en.setImage(this);
						Battle.State = Battle.State.BATTLE;
						Character = CHARACTER.NONE;
						selection=0;
					}
					else
					{
						p.setplayerNum(1);
						p.setName("Drew");
						p.setStg(1);
						p.setDef(6);
						p.setSpd(5);
						p.setBrn(4);
						p.setAcc(2);
						p.setDrk(3);
						p.setatk1(4);
						p.setatk2(0);
						p.setatk3(7);
						p.setatk4(10);
						p.setatk1name("Shining");
						p.setatk3name("Spanish");
						p.setatk4name("Disappear");
						p.setImage(this);
						selection=1;
						Battle.State = Battle.State.PLAYERSELECT;
						Character = CHARACTER.NONE;
					}
				}
				else if(Character == CHARACTER.ZACH)
				{
					if(selection==1)
					{
						en.setenemyNum(2);
						en.setName("Zach");
						en.setStg(5);
						en.setDef(1);
						en.setSpd(2);
						en.setBrn(3);
						en.setAcc(6);
						en.setDrk(4);
						en.setatk1(4);
						en.setatk2(0);
						en.setatk3(7);
						en.setatk4(10);
						en.setatk1name("Political Argument");
						en.setatk3name("Singing");
						en.setatk4name("Wet Floor");
						en.setImage(this);
						Battle.State = Battle.State.BATTLE;
						Character = CHARACTER.NONE;
						selection=0;
					}
					else
					{
						p.setplayerNum(2);
						p.setName("Zach");
						p.setStg(5);
						p.setDef(1);
						p.setSpd(2);
						p.setBrn(3);
						p.setAcc(6);
						p.setDrk(4);
						p.setatk1(4);
						p.setatk2(0);
						p.setatk3(7);
						p.setatk4(10);
						p.setatk1name("Political Argument");
						p.setatk3name("Singing");
						p.setatk4name("Wet Floor");
						p.setImage(this);
						selection=1;
						Battle.State = Battle.State.PLAYERSELECT;
						Character = CHARACTER.NONE;
					}
				}
				else if(Character == CHARACTER.JIMMY)
				{
					if(selection==1)
					{
						en.setenemyNum(3);
						en.setName("Jimmy");
						en.setStg(6);
						en.setDef(2);
						en.setSpd(4);
						en.setBrn(1);
						en.setAcc(3);
						en.setDrk(5);
						en.setatk1(4);
						en.setatk2(0);
						en.setatk3(7);
						en.setatk4(10);
						en.setatk1name("Political Argument");
						en.setatk3name("Destructo");
						en.setatk4name("Chow");
						en.setImage(this);
						Battle.State = Battle.State.BATTLE;
						Character = CHARACTER.NONE;
						selection=0;
					}
					else
					{
						p.setplayerNum(3);
						p.setName("Jimmy");
						p.setStg(6);
						p.setDef(2);
						p.setSpd(4);
						p.setBrn(1);
						p.setAcc(3);
						p.setDrk(5);
						p.setatk1(4);
						p.setatk2(0);
						p.setatk3(7);
						p.setatk4(10);
						p.setatk1name("Political Argument");
						p.setatk3name("Destructo");
						p.setatk4name("Chow");
						p.setImage(this);
						selection=1;
						Battle.State = Battle.State.PLAYERSELECT;
						Character = CHARACTER.NONE;
					}
				}
				else if(Character == CHARACTER.MIKE)
				{
					if(selection==1)
					{
						en.setenemyNum(4);
						en.setName("Mike");
						en.setStg(4);
						en.setDef(5);
						en.setSpd(3);
						en.setBrn(2);
						en.setAcc(1);
						en.setDrk(6);
						en.setatk1(4);
						en.setatk2(0);
						en.setatk3(7);
						en.setatk4(10);
						en.setatk1name("Spagetti Naselli");
						en.setatk3name("Destructo");
						en.setatk4name("Ape");
						en.setImage(this);
						Battle.State = Battle.State.BATTLE;
						Character = CHARACTER.NONE;
						selection=0;
					}
					else
					{
						p.setplayerNum(4);
						p.setName("Mike");
						p.setStg(4);
						p.setDef(5);
						p.setSpd(3);
						p.setBrn(2);
						p.setAcc(1);
						p.setDrk(6);
						p.setatk1(4);
						p.setatk2(0);
						p.setatk3(7);
						p.setatk4(10);
						p.setatk1name("Spagetti Naselli");
						p.setatk3name("Destructo");
						p.setatk4name("Ape");
						p.setImage(this);
						Battle.State = Battle.State.PLAYERSELECT;
						Character = CHARACTER.NONE;
						selection=1;
					}
				}
				else if(Character == CHARACTER.DZAVID)
				{
					if(selection==1)
					{
						en.setenemyNum(5);
						en.setName("Dzavid");
						en.setStg(3);
						en.setDef(4);
						en.setSpd(1);
						en.setBrn(6);
						en.setAcc(5);
						en.setDrk(2);
						en.setatk1(4);
						en.setatk2(0);
						en.setatk3(7);
						en.setatk4(10);
						en.setatk1name("Controller Throw");
						en.setatk3name("Butt Stuff");
						en.setatk4name("Camero Rundown");
						en.setImage(this);
						Battle.State = Battle.State.BATTLE;
						Character = CHARACTER.NONE;
						selection=0;
					}
					else
					{
						p.setplayerNum(5);
						p.setName("Dzavid");
						p.setStg(3);
						p.setDef(4);
						p.setSpd(1);
						p.setBrn(6);
						p.setAcc(5);
						p.setDrk(2);
						p.setatk1(4);
						p.setatk2(0);
						p.setatk3(7);
						p.setatk4(10);
						p.setatk1name("Controller Throw");
						p.setatk3name("Butt Stuff");
						p.setatk4name("Camero Rundown");
						p.setImage(this);
						selection=1;
						Battle.State = Battle.State.PLAYERSELECT;
						Character = CHARACTER.NONE;
					}
				}
			}
			else if(State == STATE.WIN)
			{
				wl.renderWin(g);
			}
			else if(State == STATE.LOSE)
			{
				wl.renderLose(g);
			}
			
			g.dispose();
			bs.show();
			
		}
		
		public static void main(String args[])
		{
			Battle bat = new Battle();
			
			bat.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
			bat.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
			bat.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
			JFrame frame = new JFrame(bat.TITLE);
			
			frame.add(bat);
			frame.pack();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setResizable(false);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			
			bat.start();
			try{
				Random rnd = new Random();
				int result = (rnd.nextInt(12));
				//int result = (7);
				if(result == 0)
				{
					String f0 = new File("res/HighwayStar.wav").getAbsolutePath();
					InputStream in = new FileInputStream(f0);
					AudioStream audioStream = new AudioStream(in);
			        AudioPlayer.player.start(audioStream);
				}
				else if(result == 1)
				{
					String f1 = new File("res/SweetDreams.wav").getAbsolutePath();
					InputStream in = new FileInputStream(f1);
					AudioStream audioStream = new AudioStream(in);
			        AudioPlayer.player.start(audioStream);
				}
				else if(result == 2)
				{
					String f2 = new File("res/MasterOfPuppets.wav").getAbsolutePath();
					InputStream in = new FileInputStream(f2);
					AudioStream audioStream = new AudioStream(in);
			        AudioPlayer.player.start(audioStream);
				}
				else if(result == 3)
				{
					String f3 = new File("res/RideTheLightning.wav").getAbsolutePath();
					InputStream in = new FileInputStream(f3);
					AudioStream audioStream = new AudioStream(in);
			        AudioPlayer.player.start(audioStream);
				}
				else if(result == 4)
				{
					String f4 = new File("res/Redneck.wav").getAbsolutePath();
					InputStream in = new FileInputStream(f4);
					AudioStream audioStream = new AudioStream(in);
			        AudioPlayer.player.start(audioStream);
				}
				else if(result == 5)
				{
					String f5 = new File("res/WakeUp.wav").getAbsolutePath();
					InputStream in = new FileInputStream(f5);
					AudioStream audioStream = new AudioStream(in);
			        AudioPlayer.player.start(audioStream);
				}
				else if(result == 6)
				{
					String f6 = new File("res/PsychoHoliday.wav").getAbsolutePath();
					InputStream in = new FileInputStream(f6);
					AudioStream audioStream = new AudioStream(in);
			        AudioPlayer.player.start(audioStream);
				}
				else if(result == 7)
				{
					String f7 = new File("res/ChopSuey.wav").getAbsolutePath();
					InputStream in = new FileInputStream(f7);
					AudioStream audioStream = new AudioStream(in);
			        AudioPlayer.player.start(audioStream);
				}
				else if(result == 8)
				{
					String f8 = new File("res/Davidian.wav").getAbsolutePath();
					InputStream in = new FileInputStream(f8);
					AudioStream audioStream = new AudioStream(in);
			        AudioPlayer.player.start(audioStream);
				}
				else if(result == 9)
				{
					String f9 = new File("res/Becoming.wav").getAbsolutePath();
					InputStream in = new FileInputStream(f9);
					AudioStream audioStream = new AudioStream(in);
			        AudioPlayer.player.start(audioStream);
				}
				else if(result == 10)
				{
					String f10 = new File("res/ShortChangeHero.wav").getAbsolutePath();
					InputStream in = new FileInputStream(f10);
					AudioStream audioStream = new AudioStream(in);
			        AudioPlayer.player.start(audioStream);
				}
				else if(result == 11)
				{
					String f11 = new File("res/CowboysFromHell.wav").getAbsolutePath();
					InputStream in = new FileInputStream(f11);
					AudioStream audioStream = new AudioStream(in);
			        AudioPlayer.player.start(audioStream);
				}
			}
			catch(Exception e){
				e.printStackTrace();

			}
		}
		
		public BufferedImage getSpriteSheet()
		{
			return spriteSheet;
		}
		
		public BufferedImage getSpriteSheet1()
		{
			return spriteSheet1;
		}
		
		public BufferedImage getSpriteSheet2()
		{
			return spriteSheet2;
		}
		
		public BufferedImage getSpriteSheet3()
		{
			return spriteSheet3;
		}
		
		public BufferedImage getSpriteSheet4()
		{
			return spriteSheet4;
		}
		
		public BufferedImage getSpriteSheet5()
		{
			return spriteSheet5;
		}

		public void keyPressed(KeyEvent e) 
		{
			int key = e.getKeyCode();
			
			if(State == STATE.BATTLE)
			{
				if(key == KeyEvent.VK_RIGHT)
				{
					//p.setX(p.getX() + 15);
				}
				if(key == KeyEvent.VK_LEFT)
				{
					//p.setX(p.getX() - 15);
				}
				if(key == KeyEvent.VK_DOWN)
				{
					//p.setY(p.getY() + 15);
				}
				if(key == KeyEvent.VK_UP)
				{
					//p.setY(p.getY() - 15);
				}
				if(key == KeyEvent.KEY_PRESSED)
				{
					State=STATE.MENU;
				}
			}
		}
		
		public void keyReleased(KeyEvent e) 
		{
			
		}
		
		public int getHP()
		{
			return HP;
		}
		
		public void setHP(int hp)
		{
			this.HP=hp;
		}
		
		public void setHP1(int hp)
		{
			this.HP1=hp;
		}
		
		public int getHP1()
		{
			return HP1;
		}
		
		public Player getPlayer()
		{
			return p;
		}
		
		public Enemy getEnemy()
		{
			return en;
		}
		
		public boolean playerturn(Player p, Enemy en)
		{
			if(playerTurn > enemyTurn)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
}


