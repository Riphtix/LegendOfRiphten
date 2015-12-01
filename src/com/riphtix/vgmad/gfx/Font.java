package com.riphtix.vgmad.gfx;

public class Font {

	private static SpriteSheet font = new SpriteSheet("/fonts/arial.png", 256, 32);
	private static Sprite[] characters = Sprite.split(font);

	public Font(){

	}

}
