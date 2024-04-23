package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{

	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;
	
	//---------------------------------------------------- Window Size Settings -/
	
	private final int width = 160;
	private final int height = 120;
	private final int scale = 4;
	
	//---------------------------------------------------- Rendering Settings -/
	
	private BufferedImage image;
	
	//---------------------------------------------------- Game Constructor -/
	
	public Game() {
		
		setPreferredSize(new Dimension(width * scale, height * scale));
		startFrame();
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
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
		
	}
	
	public static void main(String args[]) {
		
		Game game = new Game();
		game.start();
	}
	
	public void tick() {
		
	}
	
	//---------------------------------------------------- Game Rendering -/
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = image.getGraphics();
		g.setColor(new Color(255,255,0));
		g.fillRect(0, 0, width, height);
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, width * scale, height * scale, null);
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
	}
	
}
