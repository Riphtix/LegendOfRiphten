package com.riphtix.vgmad.gfx.ui;

import com.riphtix.vgmad.util.Vector2i;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UIPanel extends UIComponent{
	private List<UIComponent> components = new ArrayList<UIComponent>();
	private Vector2i size;

	public UIPanel(Vector2i position, Vector2i size) {
		super(position);
		this.position = position;
		this.size = size;
		color = new Color(0xff606060);
	}

	public void addComponent(UIComponent component){
		components.add(component);
	}

	public void tick() {//public void update()
		for (UIComponent component : components) {
			component.setOffset(position);
			component.tick();
		}
	}

	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(position.x, position.y, size.x, size.y);
		for (UIComponent component : components) {
			component.render(g);
		}
	}
}
