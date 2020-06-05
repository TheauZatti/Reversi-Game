package view;

import java.awt.Color;

public class Indicator {
	public Indicator(int takes, int x, int y, Color c) {
		super();
		this.takes = takes;
		this.x = x;
		this.y = y;
		this.c = c;
	}
	private int takes;
	
	public int getTakes() {
		return takes;
	}
	public void setTakes(int takes) {
		this.takes = takes;
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
	private int x;
	private int y;
	private Color c;

	public Color getC() {
		return c;
	}
	public void setC(Color c) {
		this.c = c;
	}
}
