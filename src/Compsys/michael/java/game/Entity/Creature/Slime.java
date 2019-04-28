package Compsys.michael.java.game.Entity.Creature;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Compsys.michael.java.game.Handler;
import Compsys.michael.java.game.Entity.Entities;
import Compsys.michael.java.game.Entity.EntityManager;
import Compsys.michael.java.game.gfx.Animation;
import Compsys.michael.java.game.gfx.Assets;
import Compsys.michael.java.game.items.Item;

public class Slime extends Creature{
	
	private boolean attackLeft ,attackRight, attackUp, attackDown;
	
	//Animation 
	private Animation aniLeft, aniRight, aniIdle, aniAttackLeft, aniAttackRight;
	
	//Attack Timer
	private long lastAttackTimer, attackCoolDown = 2000, attackTimer = attackCoolDown;
	
	public Slime(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT - 32, false);
		
		bounds.x = 0;
		bounds.y = 15;
		bounds.width = 64;
		bounds.height = 30;
		setHealth(3);
		
		//Animations
		aniLeft = new Animation(50, Assets.slime_move_left);
		aniRight = new Animation(50, Assets.slime_move_right);
		aniIdle = new Animation(200, Assets.slime_idle);
		aniAttackLeft = new Animation(50, Assets.slime_attack_left);
		aniAttackRight = new Animation(100, Assets.slime_attack_right);
	}
	
	@Override
	public void tick() {
		
		//Animations
		if(this.yMove > 0) {
			if(!attackLeft && !attackDown) {
				if(this.xMove > 0) {
					aniRight.tick();
				
				} else if (this.xMove < 0) {
					aniLeft.tick();
				}
			} else aniAttackLeft.tick();
			
		} else if (this.yMove < 0) {
			if(!attackRight && !attackUp) {
				if(this.xMove < 0) {
					aniLeft.tick();
				
				} else if(this.xMove > 0) {
					aniRight.tick();
				}
			} else  aniAttackRight.tick();
		} else aniIdle.tick();
		
		//Movement
		moveSlime();
		move();
		
		//Attack
		checkAttacks();
	}
	

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		g.setColor(Color.RED);
		g.drawRect((int) (x - handler.getGameCamera().getxOffset() - 128), 
				(int) (y - handler.getGameCamera().getyOffset()) - 128, 256 + 64, 256 + 64);
	}

	public void checkAttacks() {
		
		attackLeft = false;
		attackRight = false;
		attackUp = false;
		attackDown = false;
		
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer < attackCoolDown)
			return;
		
		Rectangle cb = getCollisionBounds(0,0);
		Rectangle ar = new Rectangle();	
		int arSize = 30;
		ar.width = arSize;
		ar.height = arSize;
		
		if(allowAttack(handler.getWorld().getEntityManager(), 70)) {
			if(yPostionRelativeToPlayer(handler.getWorld().getEntityManager()) <= 0 
					&& Math.abs(yPostionRelativeToPlayer(handler.getWorld().getEntityManager())) 
					> Math.abs(xPostionRelativeToPlayer(handler.getWorld().getEntityManager()))) {
				System.out.println("Up");
				ar.x = cb.x + cb.width/2 - arSize/2;
				ar.y = cb.y - arSize;
				attackUp = true;
				
			} else if(yPostionRelativeToPlayer(handler.getWorld().getEntityManager()) >= 0 
					&& Math.abs(yPostionRelativeToPlayer(handler.getWorld().getEntityManager())) 
					> Math.abs(xPostionRelativeToPlayer(handler.getWorld().getEntityManager()))) {
				System.out.println("Down");
				ar.x = cb.x + cb.width/2 - arSize/2;
				ar.y = cb.y + cb.height;
				attackDown = true;
				
			} else if(xPostionRelativeToPlayer(handler.getWorld().getEntityManager()) <= 0 
					&& Math.abs(yPostionRelativeToPlayer(handler.getWorld().getEntityManager())) 
					< Math.abs(xPostionRelativeToPlayer(handler.getWorld().getEntityManager()))) {
				System.out.println("Left");
				ar.x = cb.x - arSize;
				ar.y = cb.y + cb.height/2 - arSize/2;
				attackLeft = true;
				
			} else if(xPostionRelativeToPlayer(handler.getWorld().getEntityManager()) >= 0 
					&& Math.abs(yPostionRelativeToPlayer(handler.getWorld().getEntityManager())) 
					< Math.abs(xPostionRelativeToPlayer(handler.getWorld().getEntityManager()))) {
				System.out.println("Right");
				ar.x = cb.x + cb.width;
				ar.y = cb.y + cb.height/2 - arSize/2;
				attackRight = true;
				
			} else {
				return;
			}
			
			attackTimer = 0;
			
			for(Entities entity : handler.getWorld().getEntityManager().getEntities()) {
				if(entity.equals(this)) {
					continue;
				}
				if(entity.getIsCreature() && entity.getIsPlayer() && entity.getCollisionBounds(0, 0).intersects(ar) ) {
					entity.hurt(1);
					System.out.println("SLIME ATTACK");
					return;
				}
			}
		}
	}
	
	
	public boolean allowAttack(EntityManager entityManager, int range) {
		if(Math.abs(xPostionRelativeToPlayer(entityManager)) <= range || Math.abs(yPostionRelativeToPlayer(entityManager)) <= range){
			return true;
		}
		return false;
	}
	
	//This returns the difference between the players x coordinate and the slime x coordinate (taking from the middle of the sprite)
	public int xPostionRelativeToPlayer(EntityManager entityManager) {
		return (int) ((entityManager.getPlayer().getX() - handler.getGameCamera().getxOffset() + entityManager.getPlayer().getWidth()/2)
				- (x - handler.getGameCamera().getxOffset() + width/2));
	}
	
	//This returns the difference between the players y coordinate and the slime y coordinate (taking from the middle of the sprite)
	public int yPostionRelativeToPlayer(EntityManager entityManager) {
		return (int) ((entityManager.getPlayer().getY() - handler.getGameCamera().getyOffset() + entityManager.getPlayer().getHeight()/2 
				- (y - handler.getGameCamera().getyOffset() + height/2)));
	}
	
	
	public boolean checkForPlayer(EntityManager entityManager) {
		if((entityManager.getPlayer().getX() - handler.getGameCamera().getxOffset() + entityManager.getPlayer().getWidth()/2) >= (x - handler.getGameCamera().getxOffset() - 192 + width/2) && 
				(entityManager.getPlayer().getX() - handler.getGameCamera().getxOffset() + entityManager.getPlayer().getWidth()/2 <= (x - handler.getGameCamera().getxOffset() + 192 + width/2))) {
			if((entityManager.getPlayer().getY() - handler.getGameCamera().getyOffset() + entityManager.getPlayer().getHeight()/2) >= (y - handler.getGameCamera().getyOffset() - 192 + height/2) && 
					(entityManager.getPlayer().getY() - handler.getGameCamera().getyOffset() + entityManager.getPlayer().getHeight()/2) <= (y - handler.getGameCamera().getyOffset() + 192 + height/2)) {
				return true;
			}		
		}
		return false;
	}
	
	
	public void moveSlime() {
		
		if(this.checkForPlayer(handler.getWorld().getEntityManager())) {
			if(this.getX() + width/2 < handler.getWorld().getEntityManager().getPlayer().getX() + handler.getWorld().getEntityManager().getPlayer().getWidth()/2) {
				if(this.getY() < handler.getWorld().getEntityManager().getPlayer().getY()) {
					this.xMove = (speed - 1);
					this.yMove = (speed - 1);
				} else if(this.getY() + height/2 > handler.getWorld().getEntityManager().getPlayer().getY() + handler.getWorld().getEntityManager().getPlayer().getHeight()/2) {
					this.xMove = (speed - 1);
					this.yMove = -(speed - 1);
				} else if(this.getY() + height/2 == handler.getWorld().getEntityManager().getPlayer().getY() + handler.getWorld().getEntityManager().getPlayer().getHeight()/2) {
					this.xMove = (speed - 1);
					this.yMove = 0;
				}
			}	
			if(this.getX() + width/2 > handler.getWorld().getEntityManager().getPlayer().getX() + handler.getWorld().getEntityManager().getPlayer().getWidth()/2 ) {
				if(this.getY() + height/2 < handler.getWorld().getEntityManager().getPlayer().getY() + handler.getWorld().getEntityManager().getPlayer().getHeight()/2) {
					this.xMove = -(speed - 1);
					this.yMove = (speed - 1);
				} else if(this.getY() + height/2 > handler.getWorld().getEntityManager().getPlayer().getY() + handler.getWorld().getEntityManager().getPlayer().getHeight()/2) {
					this.xMove = -(speed - 1);
					this.yMove = -(speed - 1);
				} else if(this.getY() +  height/2 == handler.getWorld().getEntityManager().getPlayer().getY() + handler.getWorld().getEntityManager().getPlayer().getHeight()/2) {
					this.xMove = -(speed - 1);
					this.yMove = 0;
				}
			}
			if(this.getX() + width/2 == handler.getWorld().getEntityManager().getPlayer().getX()) {
				if(this.getY() < handler.getWorld().getEntityManager().getPlayer().getY() + handler.getWorld().getEntityManager().getPlayer().getHeight()/2) {
					this.xMove = 0;
					this.yMove = (speed - 1);
				} else if(this.getY() + height/2 > handler.getWorld().getEntityManager().getPlayer().getY() + handler.getWorld().getEntityManager().getPlayer().getHeight()/2) {
					this.xMove = 0;
					this.yMove = -(speed - 1);
				} else if(this.getY() + height/2 == handler.getWorld().getEntityManager().getPlayer().getY() + handler.getWorld().getEntityManager().getPlayer().getHeight()/2) {
					this.xMove = 0;   
					this.yMove = 0;  
				}
			}
		} else {
			xMove = 0;
			yMove = 0;
		} 
	}
	
	
	@Override
	public void die() {
		handler.getWorld().getItemManager().addItem(Item.healthPotion.createNew((int) x, (int) y));
	}

	
	private BufferedImage getCurrentAnimationFrame() {
		
		if(this.yMove > 0) {
			if(!attackLeft && !attackDown) {
				if(this.xMove > 0) {
					return aniRight.getCurrentFrame();
				
				} else if (this.xMove < 0) {
					return aniLeft.getCurrentFrame();
				}
			} else return aniAttackLeft.getCurrentFrame();
			
		} else if (this.yMove < 0) {
			if(!attackRight && !attackUp) {
				if(this.xMove < 0) {
					return aniLeft.getCurrentFrame();
				
				} else if(this.xMove > 0) {
					return aniRight.getCurrentFrame();
				}
			} else  return aniAttackRight.getCurrentFrame();
		} else return aniIdle.getCurrentFrame();
		
		return aniIdle.getCurrentFrame();
	
	}
	
}
