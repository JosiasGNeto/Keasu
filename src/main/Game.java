package main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{

	public static JFrame frame;
	
	//---------------------------------------------------- Window Size Settings -/
	
	private final int width = 160;
	private final int height = 120;
	private final int scale = 4;
	
	//---------------------------------------------------- Game Constructor -/
	
	public Game() {
		this.setPreferredSize(new Dimension(width * scale, height * scale));
		frame = new JFrame("My Game");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public static void main(String args[]) {
		Game game = new Game();
	}
	
	public void run() {
		
		
	}
	
}
