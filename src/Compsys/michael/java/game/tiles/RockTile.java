package Compsys.michael.java.game.tiles;


import Compsys.michael.java.game.gfx.Assets;

public class RockTile extends Tile {

	public RockTile(int id) {
		super(Assets.tree, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}

}
