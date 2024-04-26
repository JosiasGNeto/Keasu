package world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class World {

	public World(String path) {
		
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[map.getWidth() * map.getHeight()];
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
			
			//---------------------------------------- World Editor ----------------------------------------/
			
			for(int xx = 0; xx < map.getWidth(); xx++) {
					
					for(int yy = 0; yy < map.getHeight(); yy++) {
						
						int currentPixel = xx + (yy * map.getWidth());
						
						//--------------------------------- Floor ---------------------------------//
						if(pixels[currentPixel] == 0xFF000000) {
							
						} 
						//-------------------------------------------------------------------------//
						
						//---------------------------------- Wall ---------------------------------//
						else if (pixels [currentPixel] == 0xFFFFFFFF) {
							
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
		
	}
	
}
