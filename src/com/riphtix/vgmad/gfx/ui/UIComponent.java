package com.riphtix.vgmad.gfx.ui;

import com.riphtix.vgmad.util.Vector2i;

import java.awt.*;

public class UIComponent {

	public Vector2i position, size;
	protected Vector2i offset;
	public Color color;
	protected UIPanel panel;

	public boolean active = true;

	public UIComponent(Vector2i position) {
		this.position = position;
		offset = new Vector2i(0, 0);
	}

	public UIComponent(Vector2i position, Vector2i size) {
		this.position = position;
		this.size = size;
		offset = new Vector2i(0, 0);
	}

	void init(UIPanel panel){
		this.panel = panel;
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