package com.riphtix.vgmad.level;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomLevel extends Level {

	private static final Random random = new Random();

	public RandomLevel(int width, int height) {
		super(width, height, ThreadLocalRandom.current().nextInt(1, 60));
	}

	protected void generateLevel() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tilesInt[x + y * width] = random.nextInt(4);
			}
		}
	}
}
