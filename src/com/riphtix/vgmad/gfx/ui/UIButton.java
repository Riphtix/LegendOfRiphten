package com.riphtix.vgmad.gfx.ui;

import com.riphtix.vgmad.util.Vector2i;

import java.awt.*;

public class UIButton extends UIComponent {

	private UILabel label;
	private UIButtonListener buttonListener;
	private UIActionListener actionListener;
	public boolean dropShadow = false;
	public int dropShadowOffset = 2;

	public UIButton(Vector2i position, Vector2i size, UIActionListener actionListener) {
		super(position, size);
		Vector2i labelPosition = new Vector2i(position);
		labelPosition.x += 4;
		labelPosition.y += size.y - 7;
		label = new UILabel(labelPosition, "");
		label.setColor(0xffa0a0a0);
		label.active = false;

		color = new Color(0xff6a6a6a);

		buttonListener = new UIButtonListener();
	}

	void init(UIPanel panel){
		super.init(panel);
		panel.addComponent(label);
	}

	public void setText(String text) {
		if (text == "")
			label.active = false;
		else
			label.text = text;
	}

	public void setFont(Font font){
		label.setFont(font);
	}

	public void setDropShadow(boolean shadow, int offset){
		label.dropShadow = shadow;
		label.dropShadowOffset = offset;
	}

	public void tick() {//public void update()

	}

	public void render(Graphics g) {
		if(dropShadow) {
			g.setColor(Color.BLACK);
			g.fillRect(position.x + offset.x + dropShadowOffset, position.y + offset.y + dropShadowOffset, size.x, size.y);
		}

		g.setColor(color);
		g.fillRect(position.x + offset.x, position.y + offset.y, size.x, size.y);

		if (label != null) {
			label.render(g);
		}
	}
}
