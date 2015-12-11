package com.riphtix.vgmad.gfx.ui;

import com.riphtix.vgmad.util.Vector2i;
import org.w3c.dom.ranges.RangeException;

import java.awt.*;

public class UIProgressBar extends UIComponent {

	private double progress; //0.0 - 1.0
	private Color foregroundColor;
	public boolean dropShadow = false;
	public int dropShadowOffset = 2;

	public UIProgressBar(Vector2i position, Vector2i size) {
		super(position);
		this.size = size;

		foregroundColor = new Color(0xff5f5f5f);
	}

	public void setProgress(double progress){
		if(progress < 0.0 || progress > 1.0)
			throw new RangeException(RangeException.BAD_BOUNDARYPOINTS_ERR, "Progress must be between 0 and 100");
		this.progress = progress;
	}

	public void setForegroundColor(Color color){
		this.foregroundColor = color;
	}

	public double getProgress(){
		return progress;
	}

	public void tick(){ //public void update()

	}

	public void render(Graphics g){
		if(dropShadow){
			g.setColor(Color.BLACK);
			g.fillRect(position.x + offset.x + dropShadowOffset, position.y + offset.y + dropShadowOffset, size.x, size.y);
		}
		g.setColor(color);
		g.fillRect(position.x + offset.x, position.y + offset.y, size.x, size.y);

		g.setColor(foregroundColor);
		g.fillRect(position.x + offset.x, position.y + offset.y, (int)(progress * size.x), size.y);

	}
}
