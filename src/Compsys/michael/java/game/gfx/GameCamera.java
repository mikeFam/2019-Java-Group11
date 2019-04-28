package Compsys.michael.java.game.gfx;


import Compsys.michael.java.game.Handler;
import Compsys.michael.java.game.Entity.Entities;
import Compsys.michael.java.game.tiles.Tile;


public class GameCamera {
	
	private Handler handler;
	private float xOffset, yOffset;
	
	public GameCamera(Handler handler, float xOffset, float yOffset){
		this.handler = handler;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	// check for blank space when the camera reaches the edge of the map 
	// and setting the offset values to the minimum and maximum value to 
	// void these black spaces
	public void checkBlankSpace(){
		if(xOffset < 0){
			xOffset = 0;
		}else if(xOffset > handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth()){ // if xOffset is larger than the map's width 
			xOffset = handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth();		 // then set it to be the map's width
		}
		
		if(yOffset < 0){
			yOffset = 0;
		}else if(yOffset > handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight()){ // if the yOffset is larger than the map's height 
			yOffset = handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight(); 		// then set it to the map's height
		}
	}
	
	// Center the camera on the player.
	public void centerOnEntity(Entities entity){
		xOffset = entity.getX() - handler.getWidth() / 2 + entity.getWidth() / 2;  // xOffset is the X-coordinate in pixel of the player on the map
																				   // minus half of the screen's width plus half of the players width 
		yOffset = entity.getY() - handler.getHeight() / 2 + entity.getHeight() / 2;// yOffset is the Y-coordinate in pixel of the player on the map
																				   // minus half of the screen's height plus the half of the players height				
		checkBlankSpace();
	}
	
	//Move the camera
	public void move(float xAmt, float yAmt){
		xOffset += xAmt;
		yOffset += yAmt;
		checkBlankSpace();
	}

	
	//Getters and Setters
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
