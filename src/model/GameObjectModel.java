package model;

import java.awt.Color;

public abstract class GameObjectModel {
	protected Color color;
	protected int x;
	protected int y;
	
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public abstract void DoSomething(GrideModel gm);
}
