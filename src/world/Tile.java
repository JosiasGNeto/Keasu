package world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;

public class Tile {
	
	//---------------------------------- Loading Tiles ----------------------------------//
	
	public static BufferedImage tile_floor = Game.tilesheet.getTile(16, 22, 16, 16);
	
	//----------------------------------- Wall Tiles ------------------------------------//
	
	//------------ Generic -----//
	public static BufferedImage tile_wall = Game.tilesheet.getTile(48, 85, 16, 16);
	//------------ Up / Down ---//
	public static BufferedImage top_tile_wall = Game.tilesheet.getTile(14, 117, 16, 16);
	public static BufferedImage bottom_tile_wall = Game.tilesheet.getTile(4, 80, 16, 16);
	//------------ Borders -----//
	public static BufferedImage right_tile_wall = Game.tilesheet.getTile(4, 117, 16, 16);
	public static BufferedImage left_tile_wall = Game.tilesheet.getTile(32, 94, 16, 16);
	//------------ Corners -----//
	public static BufferedImage left_corner_tile_wall = Game.tilesheet.getTile(48, 85, 16, 16);
	public static BufferedImage right_corner_tile_wall = Game.tilesheet.getTile(32, 94, 16, 16);
	
	//-----------------------------------------------------------------------------------//
	
	private BufferedImage tileSprite;
	private int x, y;
	
	public Tile(int x, int y, BufferedImage tileSprite) {
		this.x = x;
		this.y = y;
		this.tileSprite = tileSprite;
	}
	
	public void render(Graphics g) {
		g.drawImage(tileSprite, x - Camera.x, y - Camera.y, null);
	}
	
}
