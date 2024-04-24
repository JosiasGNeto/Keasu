package entities;

import java.awt.image.BufferedImage;

public class Player extends Entity{
	
	//---------------------------------------------------- Player Movement -/
	
	public boolean moveUp;
	public boolean moveDown;
	public boolean moveRight;
	public boolean moveLeft;
	public double speed = 1.5;

	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}

	public void tick() {
		
		if(moveRight) {
			x += speed;
		} else if(moveLeft) {
			x -= speed;
		} else if(moveUp) {
			y -= speed;
		} else if(moveDown) {
			y += speed;
		}
		
	}
	
}
