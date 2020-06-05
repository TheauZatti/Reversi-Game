package model;

import java.awt.Color;

import view.*;

public class PionModel extends GameObjectModel{
	
	protected Player owner;
	
	public Player getOwner() {
		return owner;
	}

	protected Gride g;
	
	public PionModel(Player p, Gride g, int x, int y) {
		owner = p;
		color = p.getColor();
		this.x = x;
		this.y = y;
		g.addPion(new Pion(this.x,this.y,this.color));
	}
	
	public void DoSomething(GrideModel gm) {
		
	}
}
