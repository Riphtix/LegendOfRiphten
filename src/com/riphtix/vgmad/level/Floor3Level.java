package com.riphtix.vgmad.level;

import com.riphtix.vgmad.entity.mob.Boss;
import com.riphtix.vgmad.entity.mob.ChampionShooter;
import com.riphtix.vgmad.entity.mob.Mob;
import com.riphtix.vgmad.entity.mob.Shooter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class Floor3Level extends Level {

	public Floor3Level(String path, int mapRank) {
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

		//ThreadLocalRandom.current().nextInt(bottomRank, topRank)
		Boss dragon = new Boss(34, 34, 7, Mob.Classification.BOSS);
		dragon.inventory.add(item.key);
		dragon.inventory.add(item.dragonHeart);

		add(dragon);
		addItem(item.uncommonFireStaff, 34, 56);
		addItem(item.uncommonChestPlate, 29, 55);
		addItem(item.uncommonLeggings, 21, 38);
		addItem(item.uncommonHelmet, 44, 27);
	}

	protected void generateLevel() {

	}
}
