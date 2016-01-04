package com.riphtix.vgmad.level;

import com.riphtix.vgmad.entity.mob.AStar;
import com.riphtix.vgmad.entity.mob.Chaser;
import com.riphtix.vgmad.entity.mob.Dummy;
import com.riphtix.vgmad.entity.mob.Shooter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpawnLevel extends Level {

	public SpawnLevel(String path) {
		super(path);
	}

	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not load level file!!!");
		}

		//add(new Shooter(20, 31));
		Dummy d0 = new Dummy(32, 31);
		Dummy d1 = new Dummy(26, 31);
		Dummy d2 = new Dummy(38, 31);
		add(d0);
		System.out.println("Dummy" + getMobs().indexOf(d0) + " Called Hitbox");
		add(d1);
		System.out.println("Dummy" + getMobs().indexOf(d1) + " Called Hitbox");
		add(d2);
		System.out.println("Dummy" + getMobs().indexOf(d2) + " Called Hitbox");
		//add(new Chaser(25, 31));
		//add(new AStar(37, 31));
	}

	protected void generateLevel() {

	}
}
