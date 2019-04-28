package Compsys.michael.java.game.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	//STATIC STUFF HERE
	
	public static Tile[] tiles = new Tile[256];
	public static Tile grassTile = new GrassTile(false, 0);
	public static Tile grassTileDark = new GrassTile(true, 1);
	public static Tile wallTile = new WallTile(2);
	public static Tile wallTileTop = new WallTileTop(3);
	public static Tile treeStudTile = new TreeStudTile(false, 4);
	public static Tile treeStudTileDark = new TreeStudTile(true, 5);
	public static Tile flower = new FlowerTile(false,6);
	public static Tile flowerDark = new FlowerTile(true,7);
	public static Tile castleFloor = new CastleFloor(8);
	public static Tile castleFloorSecond = new CastleFloorSecond(9);
	public static Tile gateTile = new GateTile(10);
	
	
	//CLASS
	
	
	public static final int TILEWIDTH = 64, TILEHEIGHT = 64;
	
	protected BufferedImage texture;
	protected final int ID;
	
	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.ID = id;
		
		tiles[id] = this;
		
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
	}
	
	public boolean isSolid() {
		return false;
	}
	
	public int getID() {
		return ID;
	}
}
