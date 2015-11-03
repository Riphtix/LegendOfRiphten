package com.riphtix.vgmad.gfx;

public class Screen {
	private int width;
	private int height;

	public int[] pixels;

	public int xtime = 0;
	public int ytime = 0;
	public int counter = 0;

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;

		pixels = new int[width * height];
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void render() {
		counter++;
		if (counter % 1 == 0) xtime++;
		if (counter % 8 == 0) ytime++;


		for (int y = 0; y < height; y++) {
			if (ytime >= height || ytime < 0) break;
			for (int x = 0; x < width; x++) {
				if(xtime >= width || ytime < 0) break;
				pixels[xtime + ytime * width] = 0xff00ff;

			}
		}
	}
}
