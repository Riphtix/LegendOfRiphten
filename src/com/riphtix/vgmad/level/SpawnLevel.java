package com.riphtix.vgmad.level;

import com.riphtix.vgmad.level.tile.Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpawnLevel extends Level{

	private int[] levelPixels;

	public SpawnLevel(String path) {
		super(path);
	}

	protected void loadLevel(String path){
		try{
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			tiles = new Tile[w * h];
			image.getRGB(0, 0, w, h, levelPixels, 0 , w);
		} catch(IOException e){
			e.printStackTrace();
			System.out.println("Could not load level file!!!");
		}
	}

	//Grass = 0x267f00
	//Dirt = 0x7F3300
	//Stone = 0x808080
	protected void generateLevel(){
		for(int i = 0; i < levelPixels.length; i++){
			if(levelPixels[i] == 0x267f00) tiles[i] = Tile.grassTile;
			if(levelPixels[i] == 0x7f3300) tiles[i] = Tile.dirtTile;
			if(levelPixels[i] == 0x808080) tiles[i] = Tile.stoneTile;
		}
	}
}
