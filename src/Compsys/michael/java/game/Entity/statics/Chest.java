package Compsys.michael.java.game.Entity.statics;

import java.awt.Graphics;

import Compsys.michael.java.game.Handler;
import Compsys.michael.java.game.gfx.Assets;

public class Chest extends StaticEntities {

	public Chest(Handler handler, float x, float y) {
		super(handler, x, y, 48, 48, true);
		bounds.x = 0;
		bounds.y = 20;
		bounds.width = 48;
		bounds.height = 48 - bounds.x;
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.chest[0], (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}

	@Override
	public void die() {

	}
}
