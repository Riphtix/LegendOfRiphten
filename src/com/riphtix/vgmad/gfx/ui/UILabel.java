package com.riphtix.vgmad.gfx.ui;

import java.awt.*;

import com.riphtix.vgmad.util.Vector2i;

public class UILabel extends UIComponent {

	public String text;
	private Font font;

	public UILabel(Vector2i position, String text) {
		super(position);
		font = new Font("Helvetica", font.PLAIN, 24);
		this.text = text;
		color = new Color(0xffff00ff);
	}

	public UILabel setFont(Font font) {
		this.font = font;
		return this;
	}

	public void render(Graphics g) {
		g.setColor(color);
		g.setFont(font);
		g.drawString(text, position.x + offset.x, position.y + offset.y);
	}
}
