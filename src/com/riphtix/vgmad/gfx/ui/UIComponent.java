package com.riphtix.vgmad.gfx.ui;

import com.riphtix.vgmad.gfx.Screen;
import com.riphtix.vgmad.util.Vector2i;

public class UIComponent {

	public Vector2i position, offset;
	public int backgroundColor;

	public UIComponent(Vector2i position) {
		this.position = position;
		offset = new Vector2i(0, 0);
	}

	void setOffset(Vector2i offset) {
		this.offset = offset;
	}

	public void tick() {//public void update()

	}

	public void render(Screen screen) {

	}
}