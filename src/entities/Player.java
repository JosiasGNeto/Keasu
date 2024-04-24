package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;

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
		
		playerIsMoving = false;
		
		if(moveRight) {
			playerIsMoving = true;
			x += speed;
		} else if(moveLeft) {
			playerIsMoving = true;
			x -= speed;
		} else if(moveUp) {
			playerIsMoving = true;
			y -= speed;
		} else if(moveDown) {
			playerIsMoving = true;
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
	}
	
	public void render(Graphics g) {
		
		//------ Render Moving Animation -/
		
		if(moveRight) {
			g.drawImage(rightPlayerMove[index], this.getX(), this.getY(), null);
		} else if(moveLeft) {
			g.drawImage(leftPlayerMove[index], this.getX(), this.getY(), null);
		} else if(moveUp) {
			g.drawImage(upPlayerMove[index], this.getX(), this.getY(), null);
		} else if(moveDown) {
			g.drawImage(downPlayerMove[index], this.getX(), this.getY(), null);
		}
		
		//------ Render Idle Animation -/
		
		if(lookingRight) {
			g.drawImage(rightPlayerIdle[idleIndex], this.getX(), this.getY(), null);
		} else if(lookingLeft) {
			g.drawImage(leftPlayerIdle[idleIndex], this.getX(), this.getY(), null);
		} else if(lookingUp) {
			g.drawImage(upPlayerIdle[idleIndex], this.getX(), this.getY(), null);
		} else if(lookingDown) {
			g.drawImage(downPlayerIdle[idleIndex], this.getX(), this.getY(), null);
		}
		
	}
	
}
