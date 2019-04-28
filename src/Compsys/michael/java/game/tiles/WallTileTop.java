package Compsys.michael.java.game.tiles;

import Compsys.michael.java.game.gfx.Assets;

public class WallTileTop extends Tile{

	public WallTileTop(int id) {
		super(Assets.wallTop, id);
	}

	@Override 
	public boolean isSolid() {
		return true;
	}
}
