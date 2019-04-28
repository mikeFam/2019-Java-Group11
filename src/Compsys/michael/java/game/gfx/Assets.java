package Compsys.michael.java.game.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 32, height = 32;
	
	public static BufferedImage  grass, treeStud, tree, wall, gate, flower, wallTop, castleFloor, castleFloorSecond; 
	public static BufferedImage  grassDark, treeStudDark, treeDark, flowerDark;
	public static BufferedImage[] player_down, player_up, player_left, player_right, player_attack_down, player_attack_up, player_attack_left, player_attack_right;
	public static BufferedImage[] slime_idle, slime_move_right, slime_move_left, slime_attack_right, slime_attack_left, slime_hurt_right, slime_hurt_left, slime_die_right;
	public static BufferedImage[] dragon_down, dragon_up, dragon_left, dragon_right;
	public static BufferedImage[] health;
	public static BufferedImage[] chest;
	public static BufferedImage[] keys;

	
	public static void init() {
		SpriteSheet sheet1 = new SpriteSheet(ImageLoader.loadImage("/texture/gameTiles.png"));
		SpriteSheet sheet2 = new SpriteSheet(ImageLoader.loadImage("/texture/gameTiles_dark.jpg"));
		SpriteSheet floor = new SpriteSheet(ImageLoader.loadImage("/texture/floorTiles.png"));
		SpriteSheet playerSprites = new SpriteSheet(ImageLoader.loadImage("/player/hero_sprite_sheet.png"));
		SpriteSheet playerHealth = new SpriteSheet(ImageLoader.loadImage("/player/health.png"));
		SpriteSheet slimeSpritesLeft = new SpriteSheet(ImageLoader.loadImage("/Slime/slime_sheet_left.png"));
		SpriteSheet slimeSpritesRight = new SpriteSheet(ImageLoader.loadImage("/Slime/slime_sheet_right.png"));
		SpriteSheet dragonSprites = new SpriteSheet(ImageLoader.loadImage("/dragon/dragon.png"));
		SpriteSheet chestSprites = new SpriteSheet(ImageLoader.loadImage("/chest/chest.png"));
		SpriteSheet keySprites = new SpriteSheet(ImageLoader.loadImage("/keys/keys.png"));
		
		//Key sprite 
		keys = new BufferedImage[2];
		
		keys[0] = keySprites.crop(width, 0, width, height);
		keys[1] = keySprites.crop(width*3, 0, width, height);
		
		//Chest animations and sprite
		chest = new BufferedImage[2];
		
		chest[0] = chestSprites.crop(0, 0, width, height);
		chest[1] = chestSprites.crop(width, 0, width, height);
		
		
		//Dragon animations 
		dragon_down = new BufferedImage[4];
		
		dragon_down[0] = dragonSprites.crop(0, 0, width*3, height*3);
		dragon_down[1] = dragonSprites.crop(width*3, 0, width*3, height*3);
		dragon_down[2] = dragonSprites.crop(width*6, 0, width*3, height*3);
		dragon_down[3] = dragonSprites.crop(width*9, 0, width*3, height*3);
		
		dragon_up = new BufferedImage[4];
		
		dragon_up[0] = dragonSprites.crop(0, height*9, width*3, height*3);
		dragon_up[1] = dragonSprites.crop(width*3, height*9, width*3, height*3);
		dragon_up[2] = dragonSprites.crop(width*6, height*9 , width*3, height*3);
		dragon_up[3] = dragonSprites.crop(width*9, height*9 , width*3, height*3);
		
		dragon_left = new BufferedImage[4];
		
		dragon_left[0] = dragonSprites.crop(0, height*3, width*3, height*3);
		dragon_left[1] = dragonSprites.crop(width*3, height*3 , width*3, height*3);
		dragon_left[2] = dragonSprites.crop(width*6, height*3 , width*3, height*3);
		dragon_left[3] = dragonSprites.crop(width*9, height*3 , width*3, height*3);
		
		dragon_right = new BufferedImage[4];
		
		dragon_right[0] = dragonSprites.crop(0, height*6, width*3, height*3);
		dragon_right[1] = dragonSprites.crop(width*3, height*6, width*3, height*3);
		dragon_right[2] = dragonSprites.crop(width*6, height*6, width*3, height*3);
		dragon_right[3] = dragonSprites.crop(width*9, height*6, width*3, height*3);
		
		//Slime animations
		slime_idle = new BufferedImage[4];
		
		slime_idle[0] = slimeSpritesLeft.crop(0, 0, 28, 28);
		slime_idle[1] = slimeSpritesLeft.crop(32, 0, 28, 28);
		slime_idle[2] = slimeSpritesLeft.crop(32*2, 0, 28, 28);
		slime_idle[3] = slimeSpritesLeft.crop(32*3, 0, 28, 28);
		
		slime_move_left = new BufferedImage[4];
		
		slime_move_left[0] = slimeSpritesLeft.crop(32*4, 0, 28, 28);
		slime_move_left[1] = slimeSpritesLeft.crop(32*5, 0, 28, 28);
		slime_move_left[2] = slimeSpritesLeft.crop(32*6, 0, 28, 28);
		slime_move_left[3] = slimeSpritesLeft.crop(32*7, 0, 28, 28);
		
		slime_move_right = new BufferedImage[4];
		
		slime_move_right[0] = slimeSpritesRight.crop(32*3, 0, 28, 28);
		slime_move_right[1] = slimeSpritesRight.crop(32*2, 0, 28, 28);
		slime_move_right[2] = slimeSpritesRight.crop(32*1, 0, 28, 28);
		slime_move_right[3] = slimeSpritesRight.crop(0, 0, 28, 28);
		
		slime_attack_left = new BufferedImage[5];
		
		slime_attack_left[0] = slimeSpritesLeft.crop(0, 28, 28, 28);
		slime_attack_left[1] = slimeSpritesLeft.crop(32*1, 28, 28, 28);
		slime_attack_left[2] = slimeSpritesLeft.crop(32*2, 32, 28, 28);
		slime_attack_left[3] = slimeSpritesLeft.crop(32*3, 28, 28, 28);
		slime_attack_left[4] = slimeSpritesLeft.crop(32*4, 28, 28, 28);
		
		slime_attack_right = new BufferedImage[5];
		
		slime_attack_right[0] = slimeSpritesRight.crop(32*7, 0, 28, 28);
		slime_attack_right[1] = slimeSpritesRight.crop(32*6, 0, 28, 28);
		slime_attack_right[2] = slimeSpritesRight.crop(32*5, 32, 28, 28);
		slime_attack_right[3] = slimeSpritesRight.crop(32*4, 0, 28, 28);
		slime_attack_right[4] = slimeSpritesRight.crop(32*3, 0, 28, 28);
		
		
		
		//Player animations
		health = new BufferedImage[10];
		
		health[0] = playerHealth.crop(0, 0, 36, 32);
		health[1] = playerHealth.crop(0, 0, 36, 32);
		health[2] = playerHealth.crop(0, 0, 36, 32);
		health[3] = playerHealth.crop(0, 0, 36, 32);
		health[4] = playerHealth.crop(0, 0, 36, 32);
		health[5] = playerHealth.crop(0, 0, 36, 32);
		health[6] = playerHealth.crop(0, 0, 36, 32);
		health[7] = playerHealth.crop(0, 0, 36, 32);
		health[8] = playerHealth.crop(0, 0, 36, 32);
		health[9] = playerHealth.crop(0, 0, 36, 32);
		
		
		player_down = new BufferedImage[4];
		
		player_down[0] = playerSprites.crop(0, 0, width, height + 16);
		player_down[1] = playerSprites.crop(width, 0, width, height + 16);
		player_down[2] = playerSprites.crop(width*2, 0, width, height + 16);
		player_down[3] = playerSprites.crop(width*3, 0, width, height + 16);
		
		player_up = new BufferedImage[4];
		
		player_up[0] = playerSprites.crop(0, height*4 +16, width, height + 16);
		player_up[1] = playerSprites.crop(width, height*4 +16, width, height + 16);
		player_up[2] = playerSprites.crop(width*2, height*4 +16, width, height + 16);
		player_up[3] = playerSprites.crop(width*3, height*4 +16, width, height + 16);
		
		player_left = new BufferedImage[4];
		
		player_left[0] = playerSprites.crop(0, height + 16, width, height + 16);
		player_left[1] = playerSprites.crop(width, height +16, width, height + 16);
		player_left[2] = playerSprites.crop(width*2, height + 16, width, height + 16);
		player_left[3] = playerSprites.crop(width*3, height + 16, width, height + 16);
		
		player_right = new BufferedImage[4];
		
		player_right[0] = playerSprites.crop(0, height*3, width, height + 16);
		player_right[1] = playerSprites.crop(width, height*3, width, height + 16);
		player_right[2] = playerSprites.crop(width*2, height*3, width, height + 16);
		player_right[3] = playerSprites.crop(width*3, height*3, width, height + 16);
		
		player_attack_up = new BufferedImage[1];
		
		player_attack_up[0] = player_up[1];
		
		player_attack_down = new BufferedImage[1];
		
		player_attack_down[0] = player_down[1];
		
		player_attack_left = new BufferedImage[1];
		
		player_attack_left[0] = player_left[1];
		
		player_attack_right = new BufferedImage[1];
		
		player_attack_right[0] = player_right[1];
		
		//Tile assets :
		//normal tiles
		grass = sheet1.crop(width*3 + 11, height +1, width, height);
		tree = sheet1.crop(0, height*3, width*2, height*3);
		wall = sheet1.crop(width*5 + 13, height*3 + 3, width, height);
		wallTop = sheet1.crop(width*5 + 13, height*2 + 2, width, height);
		gate = sheet1.crop(width*8 + 16, height*3 + 3, width, height);
		treeStud = sheet1.crop(width*4 + 12, height*7 + 7, width, height);
		flower = sheet1.crop(width*2 + 10, height*7 + 7, width, height);
		castleFloor = floor.crop(width + 8, height + 9, width + 8, height + 8);
		castleFloorSecond = floor.crop(width*3 + 24, height + 9, width + 8, height + 8);	
		//darkTiles
		grassDark = sheet2.crop(width*3 + 11, height + 1, width, height);
		treeDark = sheet2.crop(0, height*3, width*2, height*3);
		treeStudDark = sheet2.crop(width*4 + 12, height*7 + 7, width, height);
		flowerDark = sheet2.crop(width*2 + 10, height*7 + 7, width, height);
		

	
	}
	
	public BufferedImage getAsset(BufferedImage image) {
		return image;
	}
}





 