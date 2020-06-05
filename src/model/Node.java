package model;

public class Node {
	
	protected GameObjectModel go;
	
	public GameObjectModel getGo() {
		return go;
	}
	public void setGo(GameObjectModel go) {
		this.go = go;
	}
	
	public Node(int x, int y) 
	{
		this.x = x;
		this.y = y;
	}
	
	protected int x;
	protected int y;
	
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
}
