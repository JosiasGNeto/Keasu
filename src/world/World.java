package world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import graphics.tiles.FloorTile;

public class World {
	
	private Tile[] tiles;
	
	public static int width;
	public static int height;
	
	public World(String path) {
		
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[map.getWidth() * map.getHeight()];
			width = map.getWidth();
			height = map.getHeight();
			tiles = new Tile[map.getWidth() * map.getHeight()];
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
			
			//---------------------------------------- World Editor ----------------------------------------/
			
			for(int xx = 0; xx < map.getWidth(); xx++) {
					
					for(int yy = 0; yy < map.getHeight(); yy++) {
						
						int currentPixel = xx + (yy * map.getWidth());
						
						//--------------------------------- Floor ---------------------------------//
						if(pixels[currentPixel] == 0xFFFFFFFF) {
							
							tiles[xx + (yy * width)] = new FloorTile(xx * 16, yy * 16, Tile.tile_floor);
							
						} 
						//-------------------------------------------------------------------------//
						
						//---------------------------------- Wall ---------------------------------//
						else if (pixels [currentPixel] == 0xFF000000) {
							
						}
						//-------------------------------------------------------------------------//
						
					}
				
				}
			
			//--------------------------------------------------------------------------------------------/
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void render(Graphics g) {
		
		for (int xx = 0; xx < width; xx++) {
			
			for (int yy = 0; yy < height; yy++) {
				
				Tile tile = tiles[xx + (yy * width)];
				tile.render(g);
				
			}
			
		}
		
	}
	
}
