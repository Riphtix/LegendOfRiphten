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

		add(new Dummy(32, 31));
		add(new Chaser(25, 31));
		add(new AStar(37, 31));
		add(new Shooter(20, 31));
	}

	protected void generateLevel() {

	}
}
