package com.riphtix.vgmad.level;

import com.riphtix.vgmad.entity.items.Item;
import com.riphtix.vgmad.entity.mob.ChampionShooter;
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

		ChampionShooter keyHolder = new ChampionShooter(17, 17, ThreadLocalRandom.current().nextInt(bottomRank, topRank));
		System.out.println(keyHolder.inventory);
		keyHolder.inventory.add(Item.key);

		add(keyHolder);

		//ThreadLocalRandom.current().nextInt(bottomRank, topRank)

		addItem(Item.key, 25, 25);
	}

	protected void generateLevel() {

	}
}
