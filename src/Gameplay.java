import java.util.*;
import java.awt.event.*;

import javax.swing.*;

import java.awt.*;

import javax.swing.*;
import javax.swing.Timer;

public class Gameplay  extends JPanel implements ActionListener 
{
	private brick br;
	
	private ImageIcon Tank1;	
	private int Tank1X = 200;
	private int Tank1Y = 550;	
	private boolean  Tank1right= false;
	private boolean Tank1left = false;
	private boolean Tank1down = false;
	private boolean Tank1up = true;	
	private int Tank1score = 0;
	private int Tank1lives = 5;
	private boolean Tank1Shoot = false;
	private String bulletShootDirTank1 = "";
	
	private ImageIcon player1;	
	private int player1X = 400;
	private int player1Y = 550;	
	private boolean player1right = false;
	private boolean player1left = false;
	private boolean player1down = false;
	private boolean player1up = true;
	private int player1score = 0;
	private int player1lives = 5;
	private boolean player1Shoot = false;
	private String bulletShootDirPlayer1 = "";

	private ImageIcon Tank2;
	private int Tank2X = 300;
	private int Tank2Y = 550;
	private boolean Tank2right = false;
	private boolean Tank2left = false;
	private boolean Tank2down = false;
	private boolean Tank2up = true;
	private int Tank2score = 0;
	private int Tank2lives = 5;
	private boolean Tank2Shoot = false;
	private String bulletShootDirTank2 = "";

	private ImageIcon Tank3;
	private int Tank3X = 500;
	private int Tank3Y = 550;
	private boolean Tank3right = false;
	private boolean Tank3left = false;
	private boolean Tank3down = false;
	private boolean Tank3up = true;
	private int Tank3score = 0;
	private int Tank3lives = 5;
	private boolean Tank3Shoot = false;
	private String bulletShootDirTank3 = "";
	
	private Timer timer;
	private int delay=8;
	
	private Player1Listener Tank1Listener;
	private Player2Listener player1Listener;
	private Player3Listener Tank2Listener;
	private Player4Listener Tank3Listener;
	
	private Tank1Bullet Tank1Bullet = null;
	private Player1Bullet player1Bullet = null;
	private Tank2Bullet Tank2Bullet = null;
	private Tank3Bullet Tank3Bullet = null;

	private boolean play = true;

	
	
	public Gameplay()
	{				
		br = new brick();
		Tank1Listener = new Player1Listener();
		player1Listener = new Player2Listener();
		Tank2Listener = new Player3Listener();
		Tank3Listener = new Player4Listener();

		setFocusable(true);
		//addKeyListener(this);
		addKeyListener(Tank1Listener);
		addKeyListener(player1Listener);
		addKeyListener(Tank2Listener);
		addKeyListener(Tank3Listener);

		setFocusTraversalKeysEnabled(false);
        timer=new Timer(delay,this);
		timer.start();
	}
	
	public void paint(Graphics g)
	{    		
		// play background
		g.setColor(Color.black);
		g.fillRect(0, 0, 650, 600);
		
		// right side background
		g.setColor(Color.DARK_GRAY);
		g.fillRect(660, 0, 140, 600);
		
		// draw solid bricks
		br.drawSolids(this, g);
		
		// draw Breakable bricks	
		br.draw(this, g);
		
		if(play)
		{
			// draw player 1
			if(Tank1up)
				Tank1=new ImageIcon("player1_tank_up.png");	
			else if(Tank1down)
				Tank1=new ImageIcon("player1_tank_down.png");
			else if(Tank1right)
				Tank1=new ImageIcon("player1_tank_right.png");
			else if(Tank1left)
				Tank1=new ImageIcon("player1_tank_left.png");
				
			Tank1.paintIcon(this, g, Tank1X, Tank1Y);
			
			// draw player 2
			if(player1up)
				player1=new ImageIcon("player2_tank_up.png");	
			else if(player1down)
				player1=new ImageIcon("player2_tank_down.png");
			else if(player1right)
				player1=new ImageIcon("player2_tank_right.png");
			else if(player1left)
				player1=new ImageIcon("player2_tank_left.png");
						
			player1.paintIcon(this, g, player1X, player1Y);

			// player 3'ü çiz
			if (Tank2up)
			Tank2 = new ImageIcon("player1_tank_up.png");
			else if (Tank2down)
			Tank2 = new ImageIcon("player1_tank_down.png");
			else if (Tank2right)
			Tank2 = new ImageIcon("player1_tank_right.png");
			else if (Tank2left)
			Tank2 = new ImageIcon("player1_tank_left.png");
	
			Tank2.paintIcon(this, g, Tank2X, Tank2Y);
	
			// player 4'ü çiz
			if (Tank3up)
			Tank3 = new ImageIcon("player1_tank_up.png");
			else if (Tank3down)
			Tank3 = new ImageIcon("player1_tank_down.png");
			else if (Tank3right)
			Tank3 = new ImageIcon("player1_tank_right.png");
			else if (Tank3left)
			Tank3 = new ImageIcon("player1_tank_left.png");
	
			Tank3.paintIcon(this, g, Tank3X, Tank3Y);
			
			if(Tank1Bullet != null && Tank1Shoot)
			{
				if(bulletShootDirTank1.equals(""))
				{
					if(Tank1up)
					{					
						bulletShootDirTank1 = "up";
					}
					else if(Tank1down)
					{					
						bulletShootDirTank1 = "down";
					}
					else if(Tank1right)
					{				
						bulletShootDirTank1 = "right";
					}
					else if(Tank1left)
					{			
						bulletShootDirTank1 = "left";
					}
				}
				else
				{
					Tank1Bullet.move(bulletShootDirTank1);
					Tank1Bullet.draw(g);
				}
				
				
				if(new Rectangle(Tank1Bullet.getX(), Tank1Bullet.getY(), 10, 10)
				.intersects(new Rectangle(player1X, player1Y, 50, 50)))
				{
					Tank1score += 10;
					player1lives -= 1;
					Tank1Bullet = null;
					Tank1Shoot = false;
					bulletShootDirTank1 = "";
				}
				
				if(br.checkCollision(Tank1Bullet.getX(), Tank1Bullet.getY())
						|| br.checkSolidCollision(Tank1Bullet.getX(), Tank1Bullet.getY()))
				{
					Tank1Bullet = null;
					Tank1Shoot = false;
					bulletShootDirTank1 = "";				
				}
	
				if(Tank1Bullet.getY() < 1 
						|| Tank1Bullet.getY() > 580
						|| Tank1Bullet.getX() < 1
						|| Tank1Bullet.getX() > 630)
				{
					Tank1Bullet = null;
					Tank1Shoot = false;
					bulletShootDirTank1 = "";
				}
			}
			
			if(player1Bullet != null && player1Shoot)
			{
				if(bulletShootDirPlayer1.equals(""))
				{
					if(player1up)
					{					
						bulletShootDirPlayer1 = "up";
					}
					else if(player1down)
					{					
						bulletShootDirPlayer1 = "down";
					}
					else if(player1right)
					{				
						bulletShootDirPlayer1 = "right";
					}
					else if(player1left)
					{			
						bulletShootDirPlayer1 = "left";
					}
				}
				else
				{
					player1Bullet.move(bulletShootDirPlayer1);
					player1Bullet.draw(g);
				}
				
				
				if(new Rectangle(player1Bullet.getX(), player1Bullet.getY(), 10, 10)
				.intersects(new Rectangle(Tank1X, Tank1Y, 50, 50)))
				{
					player1score += 10;
					Tank1lives -= 1;
					player1Bullet = null;
					player1Shoot = false;
					bulletShootDirPlayer1 = "";
				}
				
				if(br.checkCollision(player1Bullet.getX(), player1Bullet.getY())
						|| br.checkSolidCollision(player1Bullet.getX(), player1Bullet.getY()))
				{
					player1Bullet = null;
					player1Shoot = false;
					bulletShootDirPlayer1 = "";				
				}
				
				if(player1Bullet.getY() < 1 
						|| player1Bullet.getY() > 580
						|| player1Bullet.getX() < 1
						|| player1Bullet.getX() > 630)
				{
					player1Bullet = null;
					player1Shoot = false;
					bulletShootDirPlayer1 = "";
				}
			}

			if(Tank2Bullet != null && Tank2Shoot)
			{
				if(bulletShootDirTank2.equals(""))
				{
					if(Tank2up)
					{					
						bulletShootDirTank2 = "up";
					}
					else if(Tank1down)
					{					
						bulletShootDirTank2 = "down";
					}
					else if(Tank1right)
					{				
						bulletShootDirTank2 = "right";
					}
					else if(Tank1left)
					{			
						bulletShootDirTank2 = "left";
					}
				}
				else
				{
					Tank2Bullet.move(bulletShootDirTank2);
					Tank2Bullet.draw(g);
				}
				
				
				if(new Rectangle(Tank2Bullet.getX(), Tank2Bullet.getY(), 10, 10)
				.intersects(new Rectangle(player1X, player1Y, 50, 50)))
				{
					Tank2score += 10;
					player1lives -= 1;
					Tank2Bullet = null;
					Tank2Shoot = false;
					bulletShootDirTank2 = "";
				}
				
				if(br.checkCollision(Tank2Bullet.getX(), Tank2Bullet.getY())
						|| br.checkSolidCollision(Tank2Bullet.getX(), Tank2Bullet.getY()))
				{
					Tank2Bullet = null;
					Tank2Shoot = false;
					bulletShootDirTank2 = "";				
				}
	
				if(Tank2Bullet.getY() < 1 
						|| Tank2Bullet.getY() > 580
						|| Tank2Bullet.getX() < 1
						|| Tank2Bullet.getX() > 630)
				{
					Tank2Bullet = null;
					Tank2Shoot = false;
					bulletShootDirTank2 = "";
				}
			}

			if(Tank3Bullet != null && Tank3Shoot)
			{
				if(bulletShootDirTank3.equals(""))
				{
					if(Tank3up)
					{					
						bulletShootDirTank3 = "up";
					}
					else if(Tank3down)
					{					
						bulletShootDirTank3 = "down";
					}
					else if(Tank3right)
					{				
						bulletShootDirTank3 = "right";
					}
					else if(Tank3left)
					{			
						bulletShootDirTank3 = "left";
					}
				}
				else
				{
					Tank3Bullet.move(bulletShootDirTank3);
					Tank3Bullet.draw(g);
				}
				
				
				if(new Rectangle(Tank3Bullet.getX(), Tank3Bullet.getY(), 10, 10)
				.intersects(new Rectangle(player1X, player1Y, 50, 50)))
				{
					Tank3score += 10;
					player1lives -= 1;
					Tank3Bullet = null;
					Tank3Shoot = false;
					bulletShootDirTank3 = "";
				}
				
				if(br.checkCollision(Tank3Bullet.getX(), Tank3Bullet.getY())
						|| br.checkSolidCollision(Tank3Bullet.getX(), Tank3Bullet.getY()))
				{
					Tank3Bullet = null;
					Tank3Shoot = false;
					bulletShootDirTank3 = "";				
				}
	
				if(Tank3Bullet.getY() < 1 
						|| Tank3Bullet.getY() > 580
						|| Tank3Bullet.getX() < 1
						|| Tank3Bullet.getX() > 630)
				{
					Tank3Bullet = null;
					Tank3Shoot = false;
					bulletShootDirTank3 = "";
				}
			}
		}
	
		
		// the scores 		
		g.setColor(Color.white);
		g.setFont(new Font("serif",Font.BOLD, 15));
		g.drawString("Scores", 700,30);
		g.drawString("Enemy:  "+ (Tank1score + Tank2score + Tank3score), 670,60);
		g.drawString("Player 1:  "+player1score, 670,90);

		g.drawString("Lives", 700,150);
		g.drawString("Enemy:  "+Tank1lives, 670,180);
		g.drawString("Player 1:  "+player1lives, 670,210);

		
		if(Tank1lives == 0)
		{
			g.setColor(Color.white);
			g.setFont(new Font("serif",Font.BOLD, 60));
			g.drawString("Game Over", 200,300);
			g.drawString("Player 1 Won", 180,380);
			play = false;
			g.setColor(Color.white);
			g.setFont(new Font("serif",Font.BOLD, 30));
			g.drawString("(Space to Restart)", 230,430);
		}
		else if(player1lives == 0)
		{
			g.setColor(Color.white);
			g.setFont(new Font("serif",Font.BOLD, 60));
			g.drawString("Game Over", 200,300);
			g.drawString("Enemy Won", 180,380);
			g.setColor(Color.white);
			g.setFont(new Font("serif",Font.BOLD, 30));
			g.drawString("(Space to Restart)", 230,430);
			play = false;
		}
		
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
	
		repaint();
	}

	private class Player1Listener implements KeyListener {
        public void keyTyped(KeyEvent e) {}

        public void keyReleased(KeyEvent e) {}

        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE && (Tank1lives == 0 || player1lives == 0)) {
                br = new brick();
                Tank1X = 200;
                Tank1Y = 550;
                Tank1right = false;
                Tank1left = false;
                Tank1down = false;
                Tank1up = true;

                player1X = 400;
                player1Y = 550;
                player1right = false;
                player1left = false;
                player1down = false;
                player1up = true;

                Tank1score = 0;
                Tank1lives = 5;
                player1score = 0;
                player1lives = 5;
                play = true;
                repaint();
            }
            if (e.getKeyCode() == KeyEvent.VK_U) {
				if(!Tank1Shoot)
				{
					if(Tank1up)
					{					
						Tank1Bullet = new Tank1Bullet(Tank1X + 20, Tank1Y);
					}
					else if(Tank1down)
					{					
						Tank1Bullet = new Tank1Bullet(Tank1X + 20, Tank1Y + 40);
					}
					else if(Tank1right)
					{				
						Tank1Bullet = new Tank1Bullet(Tank1X + 40, Tank1Y + 20);
					}
					else if(Tank1left)
					{			
						Tank1Bullet = new Tank1Bullet(Tank1X, Tank1Y + 20);
					}
					
					Tank1Shoot = true;
				}
			}
			// Rastgele hareket üret
            Random rand = new Random();
            int randomMove = rand.nextInt(4); // 0: YUKARI, 1: SOL, 2: AŞAĞI, 3: SAĞ

            if (randomMove == 0) {
                Tank1right = false;
                Tank1left = false;
                Tank1down = false;
                Tank1up = true;

                if (!(Tank1Y < 10))
                    Tank1Y -= 10;
            } else if (randomMove == 1) {
                Tank1right = false;
                Tank1left = true;
                Tank1down = false;
                Tank1up = false;

                if (!(Tank1X < 10))
                    Tank1X -= 10;
            } else if (randomMove == 2) {
                Tank1right = false;
                Tank1left = false;
                Tank1down = true;
                Tank1up = false;

                if (!(Tank1Y > 540))
                    Tank1Y += 10;
            } else if (randomMove == 3) {
                Tank1right = true;
                Tank1left = false;
                Tank1down = false;
                Tank1up = false;

                if (!(Tank1X > 590))
                    Tank1X += 10;
            }
		}
	}
	
	private class Player2Listener implements KeyListener
	{
		public void keyTyped(KeyEvent e) {}
		public void keyReleased(KeyEvent e) {}		
		public void keyPressed(KeyEvent e) {	
			if(e.getKeyCode()== KeyEvent.VK_M)
			{
				if(!player1Shoot)
				{
					if(player1up)
					{					
						player1Bullet = new Player1Bullet(player1X + 20, player1Y);
					}
					else if(player1down)
					{					
						player1Bullet = new Player1Bullet(player1X + 20, player1Y + 40);
					}
					else if(player1right)
					{				
						player1Bullet = new Player1Bullet(player1X + 40, player1Y + 20);
					}
					else if(player1left)
					{			
						player1Bullet = new Player1Bullet(player1X, player1Y + 20);
					}
					
					player1Shoot = true;
				}
			}
			if(e.getKeyCode()== KeyEvent.VK_UP)
			{
				player1right = false;
				player1left = false;
				player1down = false;
				player1up = true;		
				
				if(!(player1Y < 10))
					player1Y-=10;

			}
			if(e.getKeyCode()== KeyEvent.VK_LEFT)
			{
				player1right = false;
				player1left = true;
				player1down = false;
				player1up = false;
				
				if(!(player1X < 10))
					player1X-=10;
			}
			if(e.getKeyCode()== KeyEvent.VK_DOWN)
			{
				player1right = false;
				player1left = false;
				player1down = true;
				player1up = false;
				
				if(!(player1Y > 540))
					player1Y+=10;
			}
			if(e.getKeyCode()== KeyEvent.VK_RIGHT)
			{
				player1right = true;
				player1left = false;
				player1down = false;
				player1up = false;
				
				if(!(player1X > 590))
					player1X+=10;
			}
			
		}
	}

	private class Player3Listener implements KeyListener {
		public void keyTyped(KeyEvent e) {}
		public void keyReleased(KeyEvent e) {}	
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE && (Tank2lives == 0 || player1lives == 0)) {
                br = new brick();
                Tank2X = 200;
                Tank2Y = 550;
                Tank2right = false;
                Tank2left = false;
                Tank2down = false;
                Tank2up = true;

                player1X = 400;
                player1Y = 550;
                player1right = false;
                player1left = false;
                player1down = false;
                player1up = true;

                Tank2score = 0;
                Tank2lives = 5;
                player1score = 0;
                player1lives = 5;
                play = true;
                repaint();
            }
            if (e.getKeyCode() == KeyEvent.VK_U) {
				if(!Tank2Shoot)
				{
					if(Tank2up)
					{					
						Tank2Bullet = new Tank2Bullet(Tank2X + 20, Tank2Y);
					}
					else if(Tank2down)
					{					
						Tank2Bullet = new Tank2Bullet(Tank2X + 20, Tank2Y + 40);
					}
					else if(Tank2right)
					{				
						Tank2Bullet = new Tank2Bullet(Tank2X + 40, Tank2Y + 20);
					}
					else if(Tank2left)
					{			
						Tank2Bullet = new Tank2Bullet(Tank2X, Tank2Y + 20);
					}
					
					Tank2Shoot = true;
				}
			}
			// Rastgele hareket üret
            Random rand = new Random();
            int randomMove = rand.nextInt(4); // 0: YUKARI, 1: SOL, 2: AŞAĞI, 3: SAĞ

            if (randomMove == 0) {
                Tank2right = false;
                Tank2left = false;
                Tank2down = false;
                Tank2up = true;

                if (!(Tank2Y < 10))
                    Tank2Y -= 10;
            } else if (randomMove == 1) {
                Tank2right = false;
                Tank2left = true;
                Tank2down = false;
                Tank2up = false;

                if (!(Tank2X < 10))
                    Tank2X -= 10;
            } else if (randomMove == 2) {
                Tank2right = false;
                Tank2left = false;
                Tank2down = true;
                Tank2up = false;

                if (!(Tank2Y > 540))
                    Tank2Y += 10;
            } else if (randomMove == 3) {
                Tank2right = true;
                Tank2left = false;
                Tank2down = false;
                Tank2up = false;

                if (!(Tank2X > 590))
                    Tank2X += 10;
            }
		}
	}
	
	private class Player4Listener implements KeyListener {
		
		public void keyTyped(KeyEvent e) {}
		public void keyReleased(KeyEvent e) {}	
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE && (Tank3lives == 0 || player1lives == 0)) {
                br = new brick();
                Tank3X = 200;
                Tank3Y = 550;
                Tank3right = false;
                Tank3left = false;
                Tank3down = false;
                Tank3up = true;

                player1X = 400;
                player1Y = 550;
                player1right = false;
                player1left = false;
                player1down = false;
                player1up = true;

                Tank3score = 0;
                Tank3lives = 5;
                player1score = 0;
                player1lives = 5;
                play = true;
                repaint();
            }
            if (e.getKeyCode() == KeyEvent.VK_U) {
				if(!Tank3Shoot)
				{
					if(Tank3up)
					{					
						Tank3Bullet = new Tank3Bullet(Tank3X + 20, Tank3Y);
					}
					else if(Tank3down)
					{					
						Tank3Bullet = new Tank3Bullet(Tank3X + 20, Tank3Y + 40);
					}
					else if(Tank3right)
					{				
						Tank3Bullet = new Tank3Bullet(Tank3X + 40, Tank3Y + 20);
					}
					else if(Tank3left)
					{			
						Tank3Bullet = new Tank3Bullet(Tank3X, Tank3Y + 20);
					}
					
					Tank3Shoot = true;
				}
			}
			// Rastgele hareket üret
            Random rand = new Random();
            int randomMove = rand.nextInt(4); // 0: YUKARI, 1: SOL, 2: AŞAĞI, 3: SAĞ

            if (randomMove == 0) {
                Tank3right = false;
                Tank3left = false;
                Tank3down = false;
                Tank3up = true;

                if (!(Tank3Y < 10))
                    Tank3Y -= 10;
            } else if (randomMove == 1) {
                Tank3right = false;
                Tank3left = true;
                Tank3down = false;
                Tank3up = false;

                if (!(Tank3X < 10))
                    Tank3X -= 10;
            } else if (randomMove == 2) {
                Tank3right = false;
                Tank3left = false;
                Tank3down = true;
                Tank3up = false;

                if (!(Tank3Y > 540))
                    Tank3Y += 10;
            } else if (randomMove == 3) {
                Tank3right = true;
                Tank3left = false;
                Tank3down = false;
                Tank3up = false;

                if (!(Tank3X > 590))
                    Tank3X += 10;
            }
		}
	}
}

