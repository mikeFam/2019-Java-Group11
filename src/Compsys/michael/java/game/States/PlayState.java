package Compsys.michael.java.game.States;

import java.awt.Graphics;

import Compsys.michael.java.game.Handler;
import Compsys.michael.java.game.Entity.Creature.Dragon;
import Compsys.michael.java.game.Entity.Creature.Slime;
import Compsys.michael.java.game.Entity.statics.Tree;
import Compsys.michael.java.game.worlds.World;

public class PlayState extends State {
	
	private boolean start = true;
	//private World prevWorld;
	private World world1;
	private World world2;
	private World world3;
	private World world4;
	
	public PlayState(Handler handler) {
		super(handler);
		
		//Initializing world maps and their entities
		//First world
		world1 = new World(handler,"res/worlds/world1.txt");
		world1.addEntities(new Tree(handler, 600, 800));
		world1.addEntities(new Slime(handler, 700, 800));
		world1.addEntities(new Slime(handler, 900, 500));
		world1.addEntities(new Slime(handler, 500, 300));
		
		//Second World
		world2 = new World(handler, "res/worlds/world2.txt");
		world2.addEntities(new Tree(handler, 770, 600));
		
		//Third World
		world3 = new World(handler, "res/worlds/world3.txt");
		
		//Forth World
		world4 = new World(handler, "res/worlds/world4.txt");
		world4.addEntities(new Dragon(handler, 770, 400));
		
		//Load World1 as the the default world
		if (start) {
			handler.setWorld(world1);
			start = false;			
		}
	}

	// Update the worlds' changes
	@Override
	public void tick() {
		transferWorld();
		handler.getWorld().tick();
	}

	// Render the world map 
	@Override
	public void render(Graphics g) {
		handler.getWorld().render(g);
	}
	
	// Set the world the player wants to enter to the current world and load it.
	public void transferWorld() {
		
		// The player will enter a different world when walking towards a specific zone
		if(!start) {
			if( handler.getWorld() == world1) {
				if ((handler.getWorld().getEntityManager().getPlayer().getX() <= 50)
						&& (handler.getWorld().getEntityManager().getPlayer().getY() >= 500)
						&& (handler.getWorld().getEntityManager().getPlayer().getY() <= 650)) {
					handler.setWorld(world2);
					world2.getEntityManager().getPlayer().setHealth(world1.getEntityManager().getPlayer().getHealth());
					
				} else if ((handler.getWorld().getEntityManager().getPlayer().getX() >= 1350)
						&& (handler.getWorld().getEntityManager().getPlayer().getY() >= 500)
						&& (handler.getWorld().getEntityManager().getPlayer().getY() <= 650)) {
					handler.setWorld(world3);
					world3.getEntityManager().getPlayer().setHealth(world1.getEntityManager().getPlayer().getHealth());
					
				} else if ((handler.getWorld().getEntityManager().getPlayer().getX() >= 740)
						&& (handler.getWorld().getEntityManager().getPlayer().getX() <= 780)
						&& (handler.getWorld().getEntityManager().getPlayer().getY() <= 100)) {
					handler.setWorld(world4);
					world4.getEntityManager().getPlayer().setHealth(world1.getEntityManager().getPlayer().getHealth());
				}
			} else if(handler.getWorld() == world2) {
				if ((handler.getWorld().getEntityManager().getPlayer().getX() >= 1430)
						&& (handler.getWorld().getEntityManager().getPlayer().getY() >= 500)
						&& (handler.getWorld().getEntityManager().getPlayer().getY() <= 650)) {
					handler.setWorld(world1);
					world1.getEntityManager().getPlayer().setHealth(world2.getEntityManager().getPlayer().getHealth());
					
				}	
			} else if(handler.getWorld() == world3) {
				if ((handler.getWorld().getEntityManager().getPlayer().getX() <= 50)
						&& (handler.getWorld().getEntityManager().getPlayer().getY() >= 500)
						&& (handler.getWorld().getEntityManager().getPlayer().getY() <= 650)) {
					handler.setWorld(world1);
					world1.getEntityManager().getPlayer().setHealth(world3.getEntityManager().getPlayer().getHealth());
				}
			}
		}
	} 

}
