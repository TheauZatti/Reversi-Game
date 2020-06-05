package view;

import java.awt.Color;

import javax.swing.JPanel;

public abstract class GameObject {
	
	protected int x;
	protected int y;
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public GameObject(int x, int y, Color color)
	{
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
	protected Color color;
	
	public Color getColor() 
	{
		return color;
	}
	public void setColor(Color color) 
	{
		this.color = color;
	}

}
