package Compsys.michael.java.game.States;

import java.awt.Graphics;

import Compsys.michael.java.game.Handler;
import Compsys.michael.java.game.gfx.Assets;
import Compsys.michael.java.game.ui.ClickListener;
import Compsys.michael.java.game.ui.UIHandler;
import Compsys.michael.java.game.ui.UIImageButton;

public class Menu extends State{
	
	private UIHandler uiHandler;
	
	public Menu(Handler handler) {
		super(handler);
		uiHandler = new UIHandler(handler);
		handler.getMouseHandler().setUIHandler(uiHandler);
		
		uiHandler.addObject(new UIImageButton(300, 300, 64, 64 , Assets.health, new ClickListener() {
			@Override
			public void onClick() {
				State.setState(handler.getGame().playState);
			}}));
	}

	@Override
	public void tick() {
		uiHandler.tick();
	}

	@Override
	public void render(Graphics g) {
		uiHandler.render(g);
	}
}
