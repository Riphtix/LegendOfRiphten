package com.riphtix.vgmad.level;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

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
	}

	protected void generateLevel() {

	}
}
