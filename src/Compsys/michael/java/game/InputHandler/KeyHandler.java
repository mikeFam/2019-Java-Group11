package Compsys.michael.java.game.InputHandler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class KeyHandler implements KeyListener{
	
	public class Key {
		public int numTimesPressed = 0;
		public boolean pressed = false;
		
		public int getNumTimesPressed() {
			return numTimesPressed;
		}
		
		public void toggle(boolean isPressed) {
			pressed = isPressed;
			if (isPressed) numTimesPressed++;
		}
		
		public boolean isPressed() {
			return pressed;
		}
	}
	
	public List<Key> keys = new ArrayList<Key>();
	
	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();
	public Key attackUp = new Key();
	public Key attackDown = new Key();
	public Key attackLeft = new Key();
	public Key attackRight = new Key();
	
	@Override
	public void keyPressed(KeyEvent e) {
		toggleKey(e.getKeyCode(),true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		toggleKey(e.getKeyCode(),false);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	public void toggleKey(int keyCode, boolean isPressed) {
		if(keyCode == KeyEvent.VK_W){
			up.toggle(isPressed);
		}
		if(keyCode == KeyEvent.VK_S) {
			down.toggle(isPressed);
		}
		if(keyCode == KeyEvent.VK_A) {
			left.toggle(isPressed);
		}
		if(keyCode == KeyEvent.VK_D) {
			right.toggle(isPressed);
		}
		if(keyCode == KeyEvent.VK_UP) {
			attackUp.toggle(isPressed);
		}
		if(keyCode == KeyEvent.VK_DOWN) {
			attackDown.toggle(isPressed);
		}
		if(keyCode == KeyEvent.VK_LEFT) {
			attackLeft.toggle(isPressed);
		}
		if(keyCode == KeyEvent.VK_RIGHT) {
			attackRight.toggle(isPressed);
		}
	}

	
	
}
