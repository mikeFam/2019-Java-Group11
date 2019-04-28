package Compsys.michael.java.game.gfx;

import java.awt.image.BufferedImage;

import Compsys.michael.java.game.Handler;
import Compsys.michael.java.game.Entity.Entities;

// This is to display the players health and the game timer 

public class PlayStateUI {
	
	private int speed, index;
	private long lastTime, timer;
	private BufferedImage[] healthBar;
	private Handler handler;
	private float xOffset, yOffset;
	
	
	public PlayStateUI (Handler handler, BufferedImage[] healthBar,float xOffset, float yOffset, int speed) {
		this.handler = handler;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.speed = speed;
		this.healthBar = healthBar;
		index = 0;
		timer = 0;
		lastTime = System.currentTimeMillis();
	}
	
	public void tick() {
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(timer > speed) {
			timer = 0;
			if(index >= healthBar.length) {
				index = 0;
			}
		}
		//checkOffsetBoundaries(handler);
	}
	
	public BufferedImage getCurrentFrame(int index) {
		return healthBar[index];
	}
	
	public void setOffsetsToGameCameraOffset(Entities entity) {
		// set the offSets.
			xOffset = entity.getX() - handler.getWidth() / 2 + entity.getWidth() / 2 + 620;  
			yOffset = entity.getY() - handler.getHeight() / 2 + entity.getHeight() / 2 + 290;
		//checkOffsetBoundaries(handler);
	}
	
	public void checkOffsetBoundaries(Handler handler) {	
			if(xOffset <= 0) {
				xOffset = 100;
			} else if(xOffset >= handler.getWorld().getWidth()*64 - handler.getWidth()) {
				xOffset = handler.getWorld().getWidth()*64 - handler.getWidth() + 100;
			} else if(yOffset <= 0) {
				yOffset = 0;
			} else if(yOffset >= handler.getWorld().getHeight()*64 - handler.getHeight()) {
				yOffset = handler.getWorld().getHeight()*64 - handler.getHeight() + 100;
			}
	}

	public long getTimer() {
		return timer;
	}

	public void setTimer(long timer) {
		this.timer = timer;
	}

	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}

	
}
