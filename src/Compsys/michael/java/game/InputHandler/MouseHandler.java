package Compsys.michael.java.game.InputHandler;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Compsys.michael.java.game.ui.UIHandler;

public class MouseHandler implements MouseListener, MouseMotionListener {
	
	private boolean leftPressed, rightPressed;
	private int mouseX,mouseY;
	private UIHandler uiHandler;
	
	public MouseHandler() {
		
	}
	
	public void setUIHandler(UIHandler uiHandler) {
		this.uiHandler = uiHandler;
	}
	
	// Getters
	
	public boolean isLeftPressed() {
		return leftPressed;
	}
	
	public boolean isRightPressed() {
		return rightPressed;
	}
	
	public int getMouseX() {
		return mouseX;
	}
	
	public int getMouseY() {
		return mouseY;
	}
	
	//Implemented methods
	@Override
	public void mouseDragged(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent event) {
		// TODO Auto-generated method stub
		mouseX = event.getX();
		mouseY = event.getY();
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent event) {
		if(event.getButton() == MouseEvent.BUTTON1) {
			leftPressed = true;
		} else if (event.getButton() == MouseEvent.BUTTON3) {
			rightPressed = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		if(event.getButton() == MouseEvent.BUTTON1) {
			leftPressed = false;
		} else if (event.getButton() == MouseEvent.BUTTON3) {
			rightPressed = false;
		}
		
		if(uiHandler != null) {
			uiHandler.onMouseMove(event);
		}
		
	}
	
}
