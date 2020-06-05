package model;

import java.util.ArrayList;

import view.Gride;
import view.Menu;

public class LineTrap extends PionModel {

private char axis;
private Menu m;
	public LineTrap(Player p, Gride g, int x, int y, char axis,Menu m) {
		super(p, g, x, y);
		this.axis = axis;
		this.m = m;
	}

	public void DoSomething(GrideModel gm) {
		//public ArrayList<Node> direction(int dirX, int dirY, Node n, Player pAdverse)
		//{
			int dirX=0, dirY=0;
			ArrayList<Node> ntaken = new ArrayList<Node>();
			Node n = gm.getNodes().get(gm.getPlace(x,y));
			boolean resume = true;
			if(axis == 'x') {
				dirX = 1;
			}
			if(axis == 'y')
			{
				dirY = 1;
			}
			int baseX = dirX;
			int baseY = dirY;
			while(resume == true)
			{
				if((n.getX()+dirX < 0 || n.getX()+dirX > 7) || (n.getY()+dirY < 0 || n.getY()+dirY > 7))
				{
					resume = false;
				}
				else if(gm.getNodes().get(gm.getPlace(n.getX()+dirX, n.getY()+dirY)).getGo() instanceof WallModel) 
				{
					resume = false;
				}
				else if(gm.getNodes().get(gm.getPlace(n.getX()+dirX, n.getY()+dirY)).getGo() == null)
				{
					resume = false;
				}
				else
				{
					resume = true;
					ntaken.add(gm.getNodes().get(gm.getPlace(n.getX()+dirX, n.getY()+dirY)));
					dirX += baseX;
					dirY += baseY;
				}
			}
			resume = true;
			if(axis == 'x') {
				dirX = -1;
			}
			if(axis == 'y')
			{
				dirY = -1;
			}
			baseX = dirX;
			baseY = dirY;
			while(resume == true)
			{
				if((n.getX()+dirX < 0 || n.getX()+dirX > 7) || (n.getY()+dirY < 0 || n.getY()+dirY > 7))
				{
					resume = false;
				}
				else if(gm.getNodes().get(gm.getPlace(n.getX()+dirX, n.getY()+dirY)).getGo() instanceof WallModel) 
				{
					resume = false;
				}
				else if(gm.getNodes().get(gm.getPlace(n.getX()+dirX, n.getY()+dirY)).getGo() == null)
				{
					resume = false;
				}
				else
				{
					resume = true;
					ntaken.add(gm.getNodes().get(gm.getPlace(n.getX()+dirX, n.getY()+dirY)));
					dirX += baseX;
					dirY += baseY;
				}
			}
			for(Node ntest : ntaken)
			{
				if(owner.equals(gm.player2))
				{
					ntest.setGo(new PionModel(gm.player2,gm.g,ntest.getX(),ntest.getY()));
					//n.setGo(new PionModel(gm.player2,gm.g,n.getX(),n.getY()));
				}
				else
				{
					ntest.setGo(new PionModel(gm.player1,gm.g,ntest.getX(),ntest.getY()));
					//n.setGo(new PionModel(gm.player1,gm.g,n.getX(),n.getY()));
				}
			}
	}

}
