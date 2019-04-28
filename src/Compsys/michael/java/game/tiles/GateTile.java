package Compsys.michael.java.game.tiles;


import Compsys.michael.java.game.gfx.Assets;

public class GateTile extends Tile{

	public GateTile(int id) {
		super(Assets.gate, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}

}
