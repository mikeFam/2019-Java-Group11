package Compsys.michael.java.game.Entity.statics;

import java.awt.Graphics;

import Compsys.michael.java.game.Handler;
import Compsys.michael.java.game.gfx.Assets;
import Compsys.michael.java.game.tiles.Tile;

public class Door extends StaticEntities {
	public Door(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT, true);
		bounds.x = 0;
		bounds.y = 0;
		bounds.width = Tile.TILEWIDTH;
		bounds.height = Tile.TILEHEIGHT;
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.gate, (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}

	@Override
	public void die() {

	}
}
