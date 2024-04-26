package world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;

public class Tile {
	
	//---------------------------------- Loading Tiles ----------------------------------//
	
	public static BufferedImage tile_floor = Game.tilesheet.getTile(17, 17, 16, 16);
	
	//-----------------------------------------------------------------------------------//
	
	private BufferedImage tileSprite;
	private int x, y;
	
	public Tile(int x, int y, BufferedImage tileSprite) {
		this.x = x;
		this.y = y;
		this.tileSprite = tileSprite;
	}
	
	public void render(Graphics g) {
		g.drawImage(tileSprite, x, y, null);
	}
	
}
