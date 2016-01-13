package com.riphtix.vgmad.level;

import com.riphtix.vgmad.entity.items.Item;
import com.riphtix.vgmad.entity.mob.ChampionShooter;
import com.riphtix.vgmad.entity.mob.Mob;
import com.riphtix.vgmad.entity.mob.Shooter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Floor2Level extends Level {

	public Floor2Level(String path, int mapLevel) {
		super(path, mapLevel);
	}

	protected void loadLevel(String path){
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

		ChampionShooter genie1 = new ChampionShooter(12, 13, ThreadLocalRandom.current().nextInt(bottomRank, topRank) + 2, Mob.Classification.CHAMPION);
		ChampionShooter genie2 = new ChampionShooter(52, 13, ThreadLocalRandom.current().nextInt(bottomRank, topRank) + 2, Mob.Classification.CHAMPION);
		if(new Random().nextInt(2) == 1){
			genie1.inventory.add(item.key);
		} else genie2.inventory.add(item.key);

		add(genie1);
		add(genie2);
		add(new Shooter(35, 40, ThreadLocalRandom.current().nextInt(bottomRank, topRank) + 1, Mob.Classification.BASIC));
		add(new Shooter(28, 40, ThreadLocalRandom.current().nextInt(bottomRank, topRank) + 1, Mob.Classification.BASIC));
		add(new Shooter(32, 42, ThreadLocalRandom.current().nextInt(bottomRank, topRank) + 1, Mob.Classification.BASIC));
		add(new Shooter(28, 31, ThreadLocalRandom.current().nextInt(bottomRank, topRank) + 1, Mob.Classification.BASIC));
		add(new Shooter(36, 31, ThreadLocalRandom.current().nextInt(bottomRank, topRank) + 1, Mob.Classification.BASIC));
		add(new Shooter(47, 29, ThreadLocalRandom.current().nextInt(bottomRank, topRank) + 1, Mob.Classification.BASIC));
		add(new Shooter(47, 37, ThreadLocalRandom.current().nextInt(bottomRank, topRank) + 1, Mob.Classification.BASIC));
		add(new Shooter(17, 29, ThreadLocalRandom.current().nextInt(bottomRank, topRank) + 1, Mob.Classification.BASIC));
		add(new Shooter(17, 37, ThreadLocalRandom.current().nextInt(bottomRank, topRank) + 1, Mob.Classification.BASIC));
		add(new Shooter(28, 25, ThreadLocalRandom.current().nextInt(bottomRank, topRank) + 1, Mob.Classification.BASIC));
		add(new Shooter(36, 25, ThreadLocalRandom.current().nextInt(bottomRank, topRank) + 1, Mob.Classification.BASIC));
		add(new Shooter(32, 17, ThreadLocalRandom.current().nextInt(bottomRank, topRank) + 1, Mob.Classification.BASIC));

		addItem(item.commonChestPlate, 30, 50);
		addItem(item.commonLeggings, 43, 28);
		addItem(item.commonHelmet, 14, 33);
		addItem(item.commonFireStaff, 33, 51);
	}

	protected void generateLevel() {

	}
}
