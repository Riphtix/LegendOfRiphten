package com.riphtix.vgmad.gfx.ui;

import java.awt.*;

import com.riphtix.vgmad.util.Vector2i;

public class UILabel extends UIComponent {

	public String text;
	private Font font;
	public boolean dropShadow = false;
	public int dropShadowOffset = 2;

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
		if(dropShadow) {
			g.setColor(Color.BLACK);
			g.setFont(font);
			g.drawString(text, position.x + offset.x + dropShadowOffset, position.y + offset.y + dropShadowOffset);
		}
		g.setColor(color);
		g.setFont(font);
		g.drawString(text, position.x + offset.x, position.y + offset.y);
	}
}
