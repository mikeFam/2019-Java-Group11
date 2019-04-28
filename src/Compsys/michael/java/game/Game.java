package Compsys.michael.java.game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import Compsys.michael.java.game.InputHandler.KeyHandler;
import Compsys.michael.java.game.InputHandler.MouseHandler;
import Compsys.michael.java.game.States.Menu;
import Compsys.michael.java.game.States.PlayState;
import Compsys.michael.java.game.States.State;
import Compsys.michael.java.game.gfx.Assets;
import Compsys.michael.java.game.gfx.GameCamera;
import Compsys.michael.java.game.gfx.PlayStateUI;

// Main Game class
public class Game implements Runnable {

	private Display display;
	private int width, height;
	public String title;
	
	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	//States
	public State playState; // should change to private
	private State menuState;
	
	//Input
	private KeyHandler keyHandler;
	private MouseHandler mouseHandler;
	
	//Camera and PlayStateUI
	private GameCamera gameCamera;
	private PlayStateUI playStateUI;
	
	//Handler
	private Handler handler;
	
	public Game(String title, int width, int height){
		this.width = width;
		this.height = height;
		this.title = title;
		keyHandler = new KeyHandler();
		mouseHandler = new MouseHandler();
	}
	
	private void init(){
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyHandler);
		display.getFrame().addMouseListener(mouseHandler);
		display.getFrame().addMouseMotionListener(mouseHandler);
		display.getCanvas().addMouseListener(mouseHandler);
		display.getCanvas().addMouseMotionListener(mouseHandler);
		Assets.init();
		
		handler = new Handler(this);
		gameCamera = new GameCamera(handler, 0, 0);
		playStateUI = new PlayStateUI(handler, Assets.health, 0, 0, 100);
		
		playState = new PlayState(handler);
		menuState = new Menu(handler);
		State.setState(playState);
	}
	
	// Updating the states of the game
	private void tick(){
		
		if(State.getState() != null)
			State.getState().tick();
	}
	
	// Render the different states
	private void render(){
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Clear Screen
		g.clearRect(0, 0, width, height);
		//Draw Here!
		
		if(State.getState() != null)
			State.getState().render(g);
		
		//End Drawing!
		bs.show();
		g.dispose();
	}
	
	public void run(){
		
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1){
				tick();
				render();
				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000){
				System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
		
	}
	
	
	// Getters
	public KeyHandler getKeyHandler(){
		return keyHandler;
	}
	
	public MouseHandler getMouseHandler() {
		return mouseHandler;
	}
	
	public GameCamera getGameCamera(){
		return gameCamera;
	}
	
	public PlayStateUI getPlayStateUI() {
		return playStateUI;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	
	//Start game method 
	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	//Stop game method
	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}












