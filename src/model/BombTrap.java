package model;

import java.util.ArrayList;

import view.Gride;
import view.Menu;

public class BombTrap extends PionModel {

	private Menu m;
	public BombTrap(Player p, Gride g, int x, int y,Menu m) {
		super(p, g, x, y);
		this.m =m;
	}
	
	public void DoSomething(GrideModel gm) {
		ArrayList<Node> nlist = new ArrayList<Node>();
		ArrayList<Node> nfinal = new ArrayList<Node>();
		Node n = gm.getNodes().get(gm.getPlace(x,y));
		if(n.getX() != 0) {
			nlist.add(gm.getNodes().get(gm.getPlace(n)-1));
			if(n.getY() != 0) {
				nlist.add(gm.getNodes().get(gm.getPlace(n)-(m.getX()+1)));
			}
		}
		if(n.getX() != (m.getX()-1)) {
			nlist.add(gm.getNodes().get(gm.getPlace(n)+1));
			if(n.getY() != (m.getY()-1)) {
				nlist.add(gm.getNodes().get(gm.getPlace(n)+(m.getX()+1)));
			}
		}
		if(n.getY() != 0) {
			nlist.add(gm.getNodes().get(gm.getPlace(n)-(m.getX())));
			if(n.getX() != (m.getX()-1)) {
				nlist.add(gm.getNodes().get(gm.getPlace(n)-(m.getX()-1)));
			}
		}
		if(n.getY() != -(m.getY()-1)) {
			nlist.add(gm.getNodes().get(gm.getPlace(n)+(m.getX())));
			if(n.getX() != 0) {
				nlist.add(gm.getNodes().get(gm.getPlace(n)+(m.getX()-1)));
			}
		}
		
		for(Node ntest : nlist)
		{
			if(!(ntest.getGo() instanceof WallModel))
			{
				nfinal.add(ntest);
				if(owner.equals(gm.player2))
				{
					ntest.setGo(new PionModel(gm.player2,gm.g,ntest.getX(),ntest.getY()));
					n.setGo(new PionModel(gm.player2,gm.g,n.getX(),n.getY()));			}
				else
				{
					ntest.setGo(new PionModel(gm.player1,gm.g,ntest.getX(),ntest.getY()));
					n.setGo(new PionModel(gm.player1,gm.g,n.getX(),n.getY()));
				}
			}
		}
		System.out.println("HEy");
	}

}
