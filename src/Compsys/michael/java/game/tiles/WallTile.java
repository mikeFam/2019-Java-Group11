package Compsys.michael.java.game.tiles;


import Compsys.michael.java.game.gfx.Assets;

public class WallTile extends Tile {

	public WallTile( int id) {
		super(Assets.wall, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}

}
