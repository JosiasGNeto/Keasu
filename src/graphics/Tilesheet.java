package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tilesheet {
	
	private BufferedImage tilesheet;
	
	public Tilesheet(String path) {
		
		try {
			tilesheet = ImageIO.read(getClass().getResource(path));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getTile(int x, int y, int width, int height) {
		return tilesheet.getSubimage(x, y, width, height);
	}
}