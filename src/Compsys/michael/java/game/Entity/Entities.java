package Compsys.michael.java.game.Entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import Compsys.michael.java.game.Handler;

public abstract class Entities {


	public static final int DEFAULT_HEALTH = 5;
	protected Handler handler;
	protected float x, y;
	protected int width, height;
	protected Rectangle bounds;
	public boolean active = true;
	protected int health;
	protected boolean isCreature;
	protected boolean isPlayer;
	protected boolean isPickable;
	protected boolean isUnlockable;
	
	public Entities(Handler handler, float x, float y, int width, int height, boolean isCreature, boolean isPlayer, boolean isUnlockable) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.health = DEFAULT_HEALTH;
		this.isCreature = isCreature;
		this.isPlayer = isPlayer;
		this.isUnlockable = isUnlockable;
		
		bounds = new Rectangle(0, 0, width, height);
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public abstract void die();
	
	//Decreases the creature's health when attacked and de-spawn when die
	public void hurt(int amt) {
		health -= amt;
		if(health <= 0) {
			active = false;
			die();
		}
	}
	
	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		for(Entities entity : handler.getWorld().getEntityManager().getEntities()) {
			if(entity.equals(this)) {
				continue;
			}
			if(entity.getCollisionBounds(0f,0f).intersects(getCollisionBounds(xOffset,yOffset))){
				return true;
			}
		}
		return false;
	}
	
	//Return the bounding area of the entity.
	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
	}
	
	// Getters and setters for class fields;
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public boolean getIsCreature() {
		return isCreature;
	}
	
	public boolean getIsPlayer() {
		return isPlayer;
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
