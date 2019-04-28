package Compsys.michael.java.game.States;

import java.awt.Graphics;

import Compsys.michael.java.game.Game;
import Compsys.michael.java.game.Handler;

public abstract class State {
	
	private static State currentState = null;
	
	public static void setState(State state) {
		currentState = state;
	}
	
	public static State getState() {
		return currentState;
	}
	
	//CLASS
	
	protected Handler handler;
	
	protected Game game;
	
	public State(Handler handler) {
		this.handler = handler;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
}
