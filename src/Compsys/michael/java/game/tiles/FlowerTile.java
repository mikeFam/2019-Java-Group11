package Compsys.michael.java.game.tiles;

import java.awt.image.BufferedImage;

import Compsys.michael.java.game.gfx.Assets;

public class FlowerTile extends Tile{
	
	private boolean dark;

	public FlowerTile(boolean dark, int id) {
		super(getImage(dark), id);
		this.dark = dark;
	}
	
	public static BufferedImage getImage(boolean dark) {
		if(dark) {
			return Assets.flowerDark;
		} else {
			return Assets.flower;
		}
	}
	
	public boolean isDark() {
		return dark;
	}
}
