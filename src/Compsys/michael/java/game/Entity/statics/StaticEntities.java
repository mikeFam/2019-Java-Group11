package Compsys.michael.java.game.Entity.statics;



import Compsys.michael.java.game.Handler;
import Compsys.michael.java.game.Entity.Entities;

public abstract class StaticEntities extends Entities {
	
	public StaticEntities(Handler handler, float x, float y, int width, int height, boolean isUnlockable) {
		super(handler, x, y, width, height, false, false, isUnlockable);
	}

}
