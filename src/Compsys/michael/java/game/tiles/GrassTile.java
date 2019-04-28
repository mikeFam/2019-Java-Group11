package Compsys.michael.java.game.tiles;



import java.awt.image.BufferedImage;

import Compsys.michael.java.game.gfx.Assets;

public class GrassTile extends Tile {

	private boolean dark;
	
	public GrassTile(boolean dark, int id) {
		super(getImage(dark), id);
		this.dark = dark;
	}
	
	public static BufferedImage getImage(boolean dark) {
		if(dark) {
			return Assets.grassDark;
		} else {
			return Assets.grass;
		}
	}
	
	public boolean isDark() {
		return dark;
	}
}
