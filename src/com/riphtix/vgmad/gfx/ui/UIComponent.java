package com.riphtix.vgmad.gfx.ui;

import com.riphtix.vgmad.util.Vector2i;

import java.awt.*;

public class UIComponent {

	public Vector2i position;
	protected Vector2i offset;
	public Color color;

	public UIComponent(Vector2i position) {
		this.position = position;
		offset = new Vector2i(0, 0);
	}

	public UIComponent setColor(int color){
		this.color = new Color(color);
		return this;
	}

	public void tick() {//public void update()

	}

	public void render(Graphics g) {

	}

	void setOffset(Vector2i offset) {
		this.offset = offset;
	}
}