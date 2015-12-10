package com.riphtix.vgmad.gfx.ui;

import com.riphtix.vgmad.util.Vector2i;
import org.w3c.dom.ranges.RangeException;

import java.awt.*;

public class UIProgressBar extends UIComponent {

	private double progress; //0.0 - 1.0
	private Vector2i size;
	private Color forgroundColor;

	public UIProgressBar(Vector2i position, Vector2i size) {
		super(position);
		this.size = size;

		forgroundColor = new Color(0xffff00ff);
	}

	public void setProgress(double progress){
		if(progress < 0.0 || progress > 1.0)
			throw new RangeException(RangeException.BAD_BOUNDARYPOINTS_ERR, "Progress must be between 0 and 100");
		this.progress = progress;
	}

	public void setForgroundColor(Color color){
		this.forgroundColor = color;
	}

	public double getProgress(){
		return progress;
	}

	public void tick(){ //public void update()

	}

	public void render(Graphics g){
		g.setColor(color);
		g.fillRect(position.x + offset.x, position.y + offset.y, size.x, size.y);

		g.setColor(forgroundColor);
		g.fillRect(position.x + offset.x, position.y + offset.y, (int)(progress * size.x), size.y);

	}
}
