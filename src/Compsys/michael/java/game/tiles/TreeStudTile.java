package Compsys.michael.java.game.tiles;

import java.awt.image.BufferedImage;

import Compsys.michael.java.game.gfx.Assets;

public class TreeStudTile extends Tile{

	private boolean dark;
	
	public TreeStudTile(boolean dark, int id) {
		super(getImage(dark), id);
		this.dark = dark;
	}
	
	public static BufferedImage getImage(boolean dark) {
		if(dark) {
			return Assets.treeStudDark;
		} else {
			return Assets.treeStud;
		}
	}
	
	public boolean isDark() {
		return dark;
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}
	
}
