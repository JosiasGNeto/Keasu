package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{

	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;
	
	//---------------------------------------------------- Window Size Settings -/
	
	private final int viewWidth = 160;
	private final int viewHeight = 120;
	private final int scale = 4;
	
	//---------------------------------------------------- Rendering Settings -/
	
	private BufferedImage image;
	private Spritesheet sheet; 
	private BufferedImage[] player;
	private int frames = 0; 
	private int maxFrames = 5;
	private int curAnimation = 1, maxAnimation = 7;
	
	//---------------------------------------------------- Game Constructor -/
	
	public Game() {
		
		sheet = new Spritesheet("/player_basic_spritesheet.png");
		player = new BufferedImage[8];
		player[0] = sheet.getSprite(0, 128, 16, 16);
		player[1] = sheet.getSprite(16, 128, 16, 16);
		player[2] = sheet.getSprite(32, 128, 16, 16);
		player[3] = sheet.getSprite(48, 128, 16, 16);
		player[4] = sheet.getSprite(64, 128, 16, 16);
		player[5] = sheet.getSprite(80, 128, 16, 16);
		player[6] = sheet.getSprite(96, 128, 16, 16);
		player[7] = sheet.getSprite(112, 128, 16, 16);
		setPreferredSize(new Dimension(viewWidth * scale, viewHeight * scale));
		startFrame();
		image = new BufferedImage(viewWidth, viewHeight, BufferedImage.TYPE_INT_RGB);
	}
	
	//---------------------------------------------------- Frame Render Function -/
	
	public void startFrame() {
		
		frame = new JFrame("My Game");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	//---------------------------------------------------- Game Start -/
	
	public synchronized void start() {
		
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	
	//---------------------------------------------------- Game Stop -/
	
	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		
		Game game = new Game();
		game.start();
	}
	
	public void tick() {
		frames++;
		
		if(frames > maxFrames) {
			frames = 0;
			curAnimation++;
			if(curAnimation > maxAnimation) {
				curAnimation = 0;
			}
		}
			 
	}
	
	//---------------------------------------------------- Game Rendering -/
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = image.getGraphics();
		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, viewWidth, viewHeight);
		
	//---------------------------- Player Rendering -/
		Graphics2D g2 = (Graphics2D) g;  
		g2.drawImage(player[curAnimation], 20, 20, null);
		
	//-----------------------------------------------/
		
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, viewWidth * scale, viewHeight * scale, null);
		bs.show();
	}
	
	//---------------------------------------------------- Game Loop -/
	
	public void run() {
		
		long lastTime = System.nanoTime();
		double FPS = 60;
		double ns = 1000000000 / FPS;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		
		while(isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns; 
			lastTime = now;
			
			if(delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: " + frames);
				frames = 0;
				timer += 1000;
			}
		}
		
		stop();
		
	}
	
}
