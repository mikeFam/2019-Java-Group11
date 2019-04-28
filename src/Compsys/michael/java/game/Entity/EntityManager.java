package Compsys.michael.java.game.Entity;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import Compsys.michael.java.game.Handler;
import Compsys.michael.java.game.Entity.Creature.Player;


public class EntityManager {

	private Handler handler;
	private Player player;
	private ArrayList<Entities> entities;
	private Comparator<Entities> renderSorter = new Comparator<Entities> () {
		@Override 
		public int compare(Entities a, Entities b) {
			if(a.getY() + a.getHeight() > b.getY() + b.getHeight()) {
				return 1;
			} else return -1;
		}
	};
	
	public EntityManager(Handler handler, Player player) {
		this.handler = handler;
		this.player = player;
		entities = new ArrayList<Entities>();
		entities.add(player);
	}
	
	public void tick() {
		
		Iterator<Entities> it = entities.iterator();
		while(it.hasNext()) {
			Entities entity = it.next();
			entity.tick();
			if(entity.isCreature && !entity.isActive()) { // change this to be all creatures
				it.remove();
			}
		}
		entities.sort(renderSorter);
	}
	
	public void render(Graphics g) {
		//For all 'entity' of type 'Entities' in the 'entities' arrayList, render.
		for(Entities entity : entities) {
			entity.render(g);
		}
	}
	
	public void addEntities(Entities entity) {
		entities.add(entity);
	}

	//Setters and getters
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}


	public ArrayList<Entities> getEntities() {
		return entities;
	}
	
	
	public void setEntities(ArrayList<Entities> entities) {
		this.entities = entities;
	}

}
