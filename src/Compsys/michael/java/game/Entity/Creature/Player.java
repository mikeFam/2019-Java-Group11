package Compsys.michael.java.game.Entity.Creature;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Compsys.michael.java.game.Handler;
import Compsys.michael.java.game.Entity.Entities;
import Compsys.michael.java.game.gfx.Animation;
import Compsys.michael.java.game.gfx.Assets;

public class Player extends Creature {

	// Animation
	private Animation aniDown, aniUp, aniLeft, aniRight;
	// Attack Timer
	private long lastAttackTimer, attackCoolDown = 500, attackTimer = attackCoolDown;

	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT, true);

		bounds.x = 12;
		bounds.y = 40;
		bounds.width = 40;
		bounds.height = 50;

		// Animations
		aniDown = new Animation(100, Assets.player_down);
		aniUp = new Animation(100, Assets.player_up);
		aniLeft = new Animation(100, Assets.player_left);
		aniRight = new Animation(100, Assets.player_right);
	}

	// Update the Player's animation and position
	@Override
	public void tick() {
		// Animations
		if (handler.getKeyHandler().down.isPressed()) {
			aniDown.tick();
		} else if (handler.getKeyHandler().up.isPressed()) {
			aniUp.tick();
		} else if (handler.getKeyHandler().left.isPressed()) {
			aniLeft.tick();
		} else {
			aniRight.tick();
		}

		// Movement
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
		handler.getPlayStateUI().setOffsetsToGameCameraOffset(this);

		// Attack
		checkAttacks();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null);

		// Draw player's hearts
		int spacing = 0;
		for (int i = 0; i < health; i++) {
			g.drawImage(handler.getPlayStateUI().getCurrentFrame(i),
					(int) (x - handler.getPlayStateUI().getxOffset() + spacing),
					(int) (y - handler.getPlayStateUI().getyOffset()), 32, 32, null);
			spacing += 40;
		}

//		g.setColor(Color.red);
//		g.fillRect((int) (x + bounds.x  - handler.getGameCamera().getxOffset()),
//				(int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
	}

	public void checkAttacks() {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if (attackTimer < attackCoolDown)
			return;

		Rectangle cb = getCollisionBounds(0, 0); // Get collision box of the player.
		Rectangle ar = new Rectangle(); // The area of the attack.
		int arSize = 20;
		ar.width = arSize;
		ar.height = arSize;

		if (handler.getKeyHandler().attackUp.isPressed()) {
			ar.x = cb.x + cb.width / 2 - arSize / 2; // Sets the area of attack to the top of
			ar.y = cb.y - arSize; // the players collision box.
		} else if (handler.getKeyHandler().attackDown.isPressed()) {
			ar.x = cb.x + cb.width / 2 - arSize / 2; // Sets the area of attack to the bottom of
			ar.y = cb.y + cb.height; // the players collision box.
		} else if (handler.getKeyHandler().attackLeft.isPressed()) {
			ar.x = cb.x - arSize; // Sets the area of attack to the left of
			ar.y = cb.y + cb.height / 2 - arSize / 2;// the players collision box.
		} else if (handler.getKeyHandler().attackRight.isPressed()) {
			ar.x = cb.x + cb.width; // Sets the area of attack to the right of
			ar.y = cb.y + cb.height / 2 - arSize / 2;// the players collision box.
		} else {
			return;
		}

		attackTimer = 0;

		for (Entities entity : handler.getWorld().getEntityManager().getEntities()) {
			if (entity.equals(this)) {
				continue;
			}
			if (entity.getIsCreature() && entity.getCollisionBounds(0, 0).intersects(ar)) {
				entity.hurt(1);
				return;
			}
		}
	}

	@Override
	public void die() {
		System.out.println("You lose");
		System.exit(0);
		;
	}

	private void getInput() {
		xMove = 0;
		yMove = 0;

		if (handler.getKeyHandler().up.pressed) {
			yMove = -speed;
		}
		if (handler.getKeyHandler().down.pressed) {
			yMove = speed;
		}
		if (handler.getKeyHandler().left.pressed) {
			xMove = -speed;
		}
		if (handler.getKeyHandler().right.pressed) {
			xMove = speed;
		}
	}

	private BufferedImage getCurrentAnimationFrame() {
		if (xMove < 0) {
			return aniLeft.getCurrentFrame();
		} else if (xMove > 0) {
			return aniRight.getCurrentFrame();
		} else if (yMove < 0) {
			return aniUp.getCurrentFrame();
		} else {
			return aniDown.getCurrentFrame();
		}
		

	}

}
