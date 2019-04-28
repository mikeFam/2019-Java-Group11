package Compsys.michael.java.game.Entity.statics;

import java.awt.Graphics;

import Compsys.michael.java.game.Handler;
import Compsys.michael.java.game.gfx.Assets;
import Compsys.michael.java.game.tiles.Tile;

public class Tree extends StaticEntities{

	public Tree(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH*2, Tile.TILEHEIGHT*3, false);
		bounds.x = 40;
		bounds.y = (height/3)*2 + 20;
		bounds.width = width - bounds.x*2;
		bounds.height = (height - bounds.y)/4;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.tree, (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}

	@Override
	public void die() {
		
	}
	
	

}
