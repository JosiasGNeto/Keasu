package world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

//-------------------------------- Importing Tiles -/
import graphics.tiles.FloorTile;
import graphics.tiles.WallTile;
import main.Game;

//--------------------------------------------------/

public class World {
	
	public static Tile[] tiles;
	
	public static int width;
	public static int height;
	public static final int tile_size = 16;
	
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
						
						//------------------------------------ Floor ------------------------------------//
						if(pixels[currentPixel] == 0xFFFFFFFF) {
							
							tiles[xx + (yy * width)] = new FloorTile(xx * tile_size, yy * tile_size, Tile.tile_floor);
							
						} 
						//-------------------------------------------------------------------------------//
						
						//------------------------------------- Wall ------------------------------------//
						else if (pixels [currentPixel] == 0xFF000000) {
							
							tiles[xx + (yy * width)] = new WallTile(xx * tile_size, yy * tile_size, Tile.tile_wall);
							
						}
						//-------------------------------------------------------------------------------//
						
						//----------------------------------- Top Wall ----------------------------------//
						else if (pixels [currentPixel] == 0xFF000001) {
							
							tiles[xx + (yy * width)] = new WallTile(xx * tile_size, yy * tile_size, Tile.top_tile_wall);
							
						}
						//-------------------------------------------------------------------------------//
						
						//---------------------------------- Bottom Wall --------------------------------//
						else if (pixels [currentPixel] == 0xFF000002) {
							
							tiles[xx + (yy * width)] = new WallTile(xx * tile_size, yy * tile_size, Tile.bottom_tile_wall);
							
						}
						//-------------------------------------------------------------------------------//
						
						//---------------------------------- Right Wall ---------------------------------//
						else if (pixels [currentPixel] == 0xFF000003) {
							
							tiles[xx + (yy * width)] = new WallTile(xx * tile_size, yy * tile_size, Tile.right_tile_wall);
							
						}
						//-------------------------------------------------------------------------------//
						
						//----------------------------------- Left Wall ---------------------------------//
						else if (pixels [currentPixel] == 0xFF000004) {
							
							tiles[xx + (yy * width)] = new WallTile(xx * tile_size, yy * tile_size, Tile.left_tile_wall);
							
						}
						//-------------------------------------------------------------------------------//
						
					}
				
				}
			
			//--------------------------------------------------------------------------------------------/
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//--------------------------------- Player Tile Collision -------------------------------//
	
	public static boolean isFree(int xNext, int yNext) {
		
		//--- checking the entities collision with the sides of the rectangle ---//
		
		int cameraFix = (Game.scale * 2);
		
		int x1 = (xNext - cameraFix) / tile_size;
		int y1 = (yNext - cameraFix) / tile_size;
		
		int x2 = (xNext + tile_size - 1 - cameraFix) / tile_size;
		int y2 = (yNext - cameraFix) / tile_size;
		
		int x3 = (xNext - cameraFix) / tile_size;
		int y3 = (yNext + tile_size - 1 - cameraFix) / tile_size;
		
		int x4 = (xNext + tile_size - 1 - cameraFix) / tile_size;
		int y4 = (yNext + tile_size - 1 - cameraFix) / tile_size;
		
		return !((tiles[x1 + (y1 * World.width)] instanceof WallTile) || 
				 (tiles[x2 + (y2 * World.width)] instanceof WallTile) ||
				 (tiles[x3 + (y3 * World.width)] instanceof WallTile) ||
				 (tiles[x4 + (y4 * World.width)] instanceof WallTile));
		
	}
	
	//---------------------------------------------------------------------------------------//
	
	public void render(Graphics g) {
		
		int xStart = Camera.x >> 4;
		int yStart = Camera.y >> 4;
		
		int xFinal = xStart + (Game.viewWidth >> 4) + 2;
		int yFinal = yStart + (Game.viewHeight >> 4) + 2;
		
		for (int xx = xStart; xx <= xFinal; xx++) {
			
			for (int yy = yStart; yy <= yFinal; yy++) {
				
				if (xx < 0 || yy < 0 || xx >= width || yy >= height)
					continue;
				
				Tile tile = tiles[xx + (yy * width)];
				tile.render(g);
				
			}
			
		}
		
	}
	
}
