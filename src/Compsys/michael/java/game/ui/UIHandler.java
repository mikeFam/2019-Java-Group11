package Compsys.michael.java.game.ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import Compsys.michael.java.game.Handler;

public class UIHandler {
	
	private Handler handler;
	private ArrayList<UIObject> objects;
	
	public UIHandler(Handler handler) {
		this.handler = handler;
		objects = new ArrayList<UIObject>();
	}
	
	public void tick() {
		for(UIObject o : objects) {
			o.tick();
		}
	}
	
	public void render(Graphics g) {
		for(UIObject o : objects) {
			o.render(g);
		}
	}
	
	public void onMouseMove(MouseEvent event) {
		for(UIObject o : objects) {
			o.onMouseMove(event);
		}
	}
	
	public void onMouseRelease(MouseEvent event) {
		for(UIObject o : objects) {
			o.onMouseRelease(event);
		}
	}
	
	public void addObject(UIObject o) { 
		objects.add(o);
	}

	public void removeObject(UIObject o) {
		objects.remove(o);
	}

	// Getters and Setters
			
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ArrayList<UIObject> getObjects() {
		return objects;
	}

	public void setObjects(ArrayList<UIObject> objects) {
		this.objects = objects;
	}
}
