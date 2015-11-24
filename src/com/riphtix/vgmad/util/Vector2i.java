package com.riphtix.vgmad.util;

public class Vector2i {
	//this is more like a path than a vector
	private int x, y;

	//creates a basic vector
	public Vector2i() {
		set(0, 0);
	}

	//creates a vector based on a pre-existing one
	public Vector2i(Vector2i vector) {
		set(vector.x, vector.y);
	}

	//creates a completely new vector
	public Vector2i(int x, int y) {
		set(x, y);
	}

	//sets the x and y values of a vector (vector.set(x, y))
	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}

	//makes the private variable x visible to other classes
	public int getX() {
		return x;
	}

	//makes the private variable y visible to other classes
	public int getY() {
		return y;
	}

	//adds the x and y values of a new vector to those of an old one
	public Vector2i add(Vector2i vector) {
		this.x += vector.x;
		this.y += vector.y;
		return this;
	}

	//subtracts the x and y values of a new vector from those of an old one
	public Vector2i subtract(Vector2i vector) {
		this.x -= vector.x;
		this.y -= vector.y;
		return this;
	}

	//sets the x value of a vector
	public Vector2i setX(int x) {
		this.x = x;
		return this;
	}

	//sets the y value of a vector
	public Vector2i setY(int y) {
		this.y = y;
		return this;
	}

	public static double getDistance(Vector2i v0, Vector2i v1){
		double x = v0.getX() - v1.getX();
		double y = v0.getY() - v1.getY();
		return Math.sqrt(x * x + y * y);
	}

	//checks if 2 vectors happen to be the same vectors
	public boolean equals(Object object) {
		if (!(object instanceof Vector2i)) return false;
		Vector2i vec = (Vector2i) object;
		if (vec.getX() == this.getX() && vec.getY() == this.getY()) return true;
		return false;
	}
}
