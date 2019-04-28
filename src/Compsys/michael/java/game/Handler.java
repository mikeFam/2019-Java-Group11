package Compsys.michael.java.game;

import Compsys.michael.java.game.InputHandler.KeyHandler;
import Compsys.michael.java.game.InputHandler.MouseHandler;
import Compsys.michael.java.game.gfx.GameCamera;
import Compsys.michael.java.game.gfx.PlayStateUI;
import Compsys.michael.java.game.worlds.World;

// This class handles all other classes that affiliate with the Game 
// Is used to set and get objects of these classes
public class Handler {
	
	private Game game;
	private World world;
	
	public Handler(Game game) {
		this.game = game;
	}
	
	// Getter
	public int getWidth() {
		return game.getWidth(); 
	}

	public int getHeight() {
		return game.getHeight();	
	}
	
	public Game getGame() {
		return game;
	}
	
	public World getWorld() {
		return world;
	}
	
	public KeyHandler getKeyHandler() {
		return game.getKeyHandler();
	}
	
	public MouseHandler getMouseHandler() {
		return game.getMouseHandler();
	}
	
	public GameCamera getGameCamera() {
		return game.getGameCamera();
	}
	
	public PlayStateUI getPlayStateUI() {
		return game.getPlayStateUI();
	}
	
	//Setter
	public void setGame(Game game) {
		this.game = game;
	}

	public void setWorld(World world) {
		this.world = world;
	}

}
