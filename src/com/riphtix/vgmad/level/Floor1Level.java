package com.riphtix.vgmad.level;

import com.riphtix.vgmad.entity.items.Item;
import com.riphtix.vgmad.entity.mob.ChampionShooter;
import com.riphtix.vgmad.entity.mob.Mob;
import com.riphtix.vgmad.entity.mob.Shooter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class Floor1Level extends Level {

	public Floor1Level(String path, int mapRank) {
		super(path, mapRank);
	}

	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(Floor1Level.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not load level file!!!");
		}

		int bottomRank = 0;
		if (mapRank - 2 > 0) {
			bottomRank = mapRank - 2;
		}
		int topRank = mapRank + 2;

		ChampionShooter keyHolder = new ChampionShooter(47, 48, ThreadLocalRandom.current().nextInt(bottomRank, topRank) + 2, Mob.Classification.CHAMPION);
		keyHolder.inventory.add(Item.key);

		add(keyHolder);
		add(new Shooter(49, 15, ThreadLocalRandom.current().nextInt(bottomRank, topRank) + 1, Mob.Classification.BASIC));
		add(new Shooter(13, 16, ThreadLocalRandom.current().nextInt(bottomRank, topRank) + 1, Mob.Classification.BASIC));
		add(new Shooter(16, 21, ThreadLocalRandom.current().nextInt(bottomRank, topRank) + 1, Mob.Classification.BASIC));
		add(new Shooter(23, 16, ThreadLocalRandom.current().nextInt(bottomRank, topRank) + 1, Mob.Classification.BASIC));
		add(new Shooter(30, 38, ThreadLocalRandom.current().nextInt(bottomRank, topRank) + 1, Mob.Classification.BASIC));
		add(new Shooter(23, 37, ThreadLocalRandom.current().nextInt(bottomRank, topRank) + 1, Mob.Classification.BASIC));
		add(new Shooter(24, 48, ThreadLocalRandom.current().nextInt(bottomRank, topRank) + 1, Mob.Classification.BASIC));
		add(new Shooter(46, 42, ThreadLocalRandom.current().nextInt(bottomRank, topRank) + 1, Mob.Classification.BASIC));
		add(new Shooter(47, 34, ThreadLocalRandom.current().nextInt(bottomRank, topRank) + 1, Mob.Classification.BASIC));
		add(new Shooter(44, 21, ThreadLocalRandom.current().nextInt(bottomRank, topRank) + 1, Mob.Classification.BASIC));
		add(new Shooter(44, 33, ThreadLocalRandom.current().nextInt(bottomRank, topRank) + 1, Mob.Classification.BASIC));

		//ThreadLocalRandom.current().nextInt(bottomRank, topRank)
	}

	protected void generateLevel() {

	}
}
