package Compsys.michael.java.game.worlds;

import java.awt.Graphics;

import Compsys.michael.java.game.Handler;
import Compsys.michael.java.game.Entity.Entities;
import Compsys.michael.java.game.Entity.EntityManager;
import Compsys.michael.java.game.Entity.Creature.Player;
import Compsys.michael.java.game.items.ItemManager;
import Compsys.michael.java.game.tiles.Tile;
import Compsys.michael.java.game.utils.Utils;

public class World {

	
	// size of map in number of tiles;
	
	private Handler handler;
	private int width, height; //Number of tiles.
	private int spawnX, spawnY;
	
	
	private int[][] tilesMap;
	
	//Entities
	private EntityManager entityManager;
	//Item 
	private ItemManager itemManager;
	
	
	public World(Handler handler, String path) {
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler, 100, 100));
		itemManager = new ItemManager(handler);
		
		loadWorld(path); 
		
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
	}
	
	public void addEntities(Entities entity) {
		entityManager.addEntities(entity);
	}

	public void tick() {
		itemManager.tick();
		entityManager.tick();
	}
	
	public void render(Graphics g) {
		
		//Render Tiles
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
		
		for(int y = yStart; y < yEnd; y++) {
			for(int x = xStart; x < xEnd; x++) {
				getTile(x,y).render(g,(int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()), 
										(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		//Items 
		itemManager.render(g);
		
		//Render Entities 
		entityManager.render(g);
	}
	
	// Getting the Tiles for the corresponding tile ID in the tilesMap 2D array.
	public Tile getTile(int x, int y) {
		
		if(x < 0 || y < 0 || x >= width || y >= height) {
			return Tile.grassTile;
		}
		
		Tile t = Tile.tiles[tilesMap[x][y]];
		if(t == null) {
			return Tile.grassTile;
		}
		
		return t;
	}
	
	private void loadWorld(String path) {
	
		String file = Utils.loadFileAsString(path);
		String [] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tilesMap = new int[width][height];
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				tilesMap[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
				
			}
		}
	}
	
	//Getters
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}

}
