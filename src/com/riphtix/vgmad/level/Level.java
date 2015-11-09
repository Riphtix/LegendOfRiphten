package com.riphtix.vgmad.level;

import com.riphtix.vgmad.gfx.Screen;

public class Level {

	protected int width;
	protected int height;
	protected int[] tiles;

	public Level(int width, int height){
		this.width = width;
		this.height = height;
		tiles = new int[width * height];
		generateLevel();
	}

	public Level(String path){
		loadLevel(path);
	}

	protected void generateLevel(){

	}

	private void loadLevel(String path){

	}

	public void tick(){//public void update()

	}

	private void time(){

	}

	public void render(int xScroll, int yScroll, Screen screen){

	}
}
