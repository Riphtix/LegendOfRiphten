package com.riphtix.vgmad.gfx;

import java.awt.*;

public class Rectangle extends java.awt.Rectangle {

	public int x;
	public int y;
	public int width;
	public int height;

	public Rectangle(){
		this(0, 0, 0, 0);
	}

	public Rectangle(Rectangle r){
		this(r.x, r.y, r.width, r.height);
	}

	public Rectangle(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public Rectangle(int width, int height){
		this(0, 0, width, height);
	}

	public Rectangle(Point p, Dimension d){
		this(p.x, p.y, d.width, d.height);
	}

	public Rectangle(Point p){
		this(p.x, p.y, 0, 0);
	}

	public Rectangle(Dimension d){
		this(0, 0, d.width, d.height);
	}

	public double getX(){
		return x;
	}

	public double getY(){
		return y;
	}

	public double getWidth(){
		return width;
	}

	public double getHeight(){
		return height;
	}

}
