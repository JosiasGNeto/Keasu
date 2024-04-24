package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import entities.Entity;
import entities.Player;
import graphics.Spritesheet;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;
	
	//---------------------------------------------------- Window Size Settings -/
	
	private final int viewWidth = 160;
	private final int viewHeight = 120;
	private final int scale = 4;
	
	//---------------------------------------------------- Rendering Settings -/
	
	private BufferedImage image;
	
	public List<Entity> entities;
	public Spritesheet spritesheet; 
	
	//---------------------------------------------------- Game Constructor -/
	
	public Game() {
		
		setPreferredSize(new Dimension(viewWidth * scale, viewHeight * scale));
		startFrame();
		
		//---------------------------- Initialize Objects -/
		
		image = new BufferedImage(viewWidth, viewHeight, BufferedImage.TYPE_INT_RGB);
		entities = new ArrayList<Entity>();
		spritesheet = new Spritesheet("/player_basic_spritesheet.png");
		entities.add(new Player(0, 0, 16, 16, spritesheet.getSprite(0, 0, 16, 16)));
		//-----------------------------------------------/
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
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.tick();
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
		g.setColor(new Color(100, 230, 0));
		g.fillRect(0, 0, viewWidth, viewHeight);
		
	//---------------------------- Player Rendering -/
		//Graphics2D g2 = (Graphics2D) g;
		
	//-----------------------------------------------/
		
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.render(g);
		}
		
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
