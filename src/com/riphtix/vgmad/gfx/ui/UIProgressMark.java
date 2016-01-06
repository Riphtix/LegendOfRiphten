package com.riphtix.vgmad.gfx.ui;

import com.riphtix.vgmad.util.Vector2i;

import java.awt.*;

public class UIProgressMark extends UIComponent{

	private int percent;
	private Color color;

	public UIProgressMark(Vector2i position, Vector2i size){
		super(position);
		this.size = size;

		color = new Color(0xff000000);
	}

	public void render(Graphics g){
		g.setColor(color);
		g.fillRect(position.x + offset.x, position.y + offset.y, size.x, size.y);
	}
}
