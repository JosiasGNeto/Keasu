package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import world.Camera;

public class Player extends Entity{
	
	//---------------------------------------------------- Player Movement -/
	
	public boolean playerIsMoving = false;
	public boolean moveUp;
	public boolean moveDown;
	public boolean moveRight;
	public boolean moveLeft;
	public double speed = 1.5;
	
	private int frames = 0;
	private int maxFrames = 8;
	private int index = 0;
	private int maxIndex = 7;
	
	private int idleIndex = 0;
	private int idleMaxIndex = 3;
	
	//------ Movement Animation -/
	private BufferedImage[] upPlayerMove;
	private BufferedImage[] downPlayerMove;
	private BufferedImage[] leftPlayerMove;
	private BufferedImage[] rightPlayerMove;

	//------ Idle Animation -/
	private BufferedImage[] upPlayerIdle;
	private BufferedImage[] downPlayerIdle;
	private BufferedImage[] leftPlayerIdle;
	private BufferedImage[] rightPlayerIdle;
	
	//------ Position the player is looking -/
	
	public boolean lookingUp = false;
	public boolean lookingDown = false;
	public boolean lookingLeft = false;
	public boolean lookingRight = false;
	
	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
	//----------------------------------------------------- Animations Settings -/
		
		upPlayerMove = new BufferedImage[8];
		downPlayerMove = new BufferedImage[8];
		leftPlayerMove = new BufferedImage[8];
		rightPlayerMove = new BufferedImage[8];
		
		upPlayerIdle = new BufferedImage[4];
		downPlayerIdle = new BufferedImage[4];
		leftPlayerIdle = new BufferedImage[4];
		rightPlayerIdle = new BufferedImage[4];
		
		//---- Move Animation Get Sprite
		for(int i = 0; i < 8; i++) {
			upPlayerMove[i] = Game.spritesheet.getSprite(0 + (i * 16), 225, 16, 16); 
			downPlayerMove[i] = Game.spritesheet.getSprite(0 + (i * 16), 193, 16, 16); 
			leftPlayerMove[i] = Game.spritesheet.getSprite(0 + (i * 16), 161, 16, 16); 
			rightPlayerMove[i] = Game.spritesheet.getSprite(0 + (i * 16), 129, 16, 16); 
		}
		
		//---- Idle Animation Get Sprite
		for(int i = 0; i < 4; i++) {
			upPlayerIdle[i] = Game.spritesheet.getSprite(0 + (i * 16), 96, 16, 16); 
			downPlayerIdle[i] = Game.spritesheet.getSprite(0 + (i * 16), 64, 16, 16); 
			leftPlayerIdle[i] = Game.spritesheet.getSprite(0 + (i * 16), 32, 16, 16); 
			rightPlayerIdle[i] = Game.spritesheet.getSprite(0 + (i * 16), 0, 16, 16); 
		}
		
	}

	public void tick() {
		
		//------ General Player Movement Actions
		
	    playerIsMoving = moveRight || moveLeft || moveUp || moveDown;

	    if (moveRight) {
	        lookingRight = true;
	        lookingLeft = lookingUp = lookingDown = false;
	        x += speed;
	    } else if (moveLeft) {
	        lookingLeft = true;
	        lookingRight = lookingUp = lookingDown = false;
	        x -= speed;
	    } else if (moveUp) {
	        lookingUp = true;
	        lookingRight = lookingLeft = lookingDown = false;
	        y -= speed;
	    } else if (moveDown) {
	        lookingDown = true;
	        lookingRight = lookingLeft = lookingUp = false;
	        y += speed;
	    }	
		
		//------ Moving Frames
		
		if(playerIsMoving) {
			frames++;
			if(frames == maxFrames){
				frames = 0;
				index++;
				if(index > maxIndex) {
					index = 0;
				}
			}
		}
		
		//------ Idle Frames
		
		if(!playerIsMoving) {
			frames++;
			if(frames == maxFrames){
				frames = 0;
				idleIndex++;
				if(idleIndex > idleMaxIndex) {
					idleIndex = 0;
				}
			}
		}
		
		Camera.x = this.getX() - (Game.viewWidth / 2);
		Camera.y = this.getY() - (Game.viewHeight / 2);
		
	}
	
	public void render(Graphics g) {
		
		//------ Render Moving Animation -/
		
		int cameraX = this.getX() - Camera.x - (Game.scale * 2);
		int cameraY = this.getY() - Camera.y - (Game.scale * 2);
		
		if(moveRight) {
			g.drawImage(rightPlayerMove[index], cameraX, cameraY, null);
		} else if(moveLeft) {
			g.drawImage(leftPlayerMove[index], cameraX, cameraY, null);
		} else if(moveUp) {
			g.drawImage(upPlayerMove[index], cameraX, cameraY, null);
		} else if(moveDown) {
			g.drawImage(downPlayerMove[index], cameraX, cameraY, null);
		}
		
		//------ Render Idle Animation -/
		
		if(lookingRight && !playerIsMoving) {
			g.drawImage(rightPlayerIdle[idleIndex], cameraX, cameraY, null);
		} else if(lookingLeft && !playerIsMoving) {
			g.drawImage(leftPlayerIdle[idleIndex], cameraX, cameraY, null);
		} else if(lookingUp && !playerIsMoving) {
			g.drawImage(upPlayerIdle[idleIndex], cameraX, cameraY, null);
		} else if(lookingDown && !playerIsMoving) {
			g.drawImage(downPlayerIdle[idleIndex], cameraX, cameraY, null);
		}
		
	}
	
}
