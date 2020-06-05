package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import view.Gride;
import view.Menu;

public class GrideModel {
	
	protected Gride g;
	
	protected Player player1;
	protected Player player2;
	private int xValue;
	private int yValue;
	
	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	protected ArrayList<Node> nodes = new ArrayList<Node>();

	public ArrayList<Node> getNodes() {
		return nodes;
	}

	/*public GrideModel(Gride g,Menu m)
	{
		this.g = g;
		player1 = new Player(m.getJ1(),m.getName1(),new Score(m.getJ1()),m.getNbTrapMax());
		player2 = new Player(m.getJ2(),m.getName2(),new Score(m.getJ2()),m.getNbTrapMax());
		
		for(int y=0;y<8;y++)
		{
			for(int x=0;x<8;x++) {
				nodes.add(new Node(x,y));
			}
		}
		nodes.get(3+3*8).setGo(new PionModel(player1,g,3,3));
		nodes.get(4+3*8).setGo(new PionModel(player2,g,4,3));
		nodes.get(3+4*8).setGo(new PionModel(player2,g,3,4));
		nodes.get(4+4*8).setGo(new PionModel(player1,g,4,4));
	}*/
	
	public GrideModel(int obstacles, Gride g,Menu m)
	{
		this.g = g;
		player1 = new Player(m.getJ1(),m.getName1(),new Score(m.getJ1()),m.getNbTrapMax());
		player2 = new Player(m.getJ2(),m.getName2(),new Score(m.getJ2()),m.getNbTrapMax());
		this.xValue = m.getX();
		this.yValue = m.getY();
		
		for(int y=0;y<m.getY();y++)
		{
			for(int x=0;x<m.getX();x++) {
				nodes.add(new Node(x,y));
			}
		}
		nodes.get(xValue/2-1+(yValue/2-1)*xValue).setGo(new PionModel(player1,g,xValue/2-1,(yValue/2-1)));
		nodes.get(xValue/2-1+(yValue/2)*xValue).setGo(new PionModel(player2,g,(xValue/2-1),(yValue/2)));
		nodes.get(xValue/2+(yValue/2-1)*xValue).setGo(new PionModel(player2,g,xValue/2,(yValue/2-1)));
		nodes.get(xValue/2+(yValue/2)*xValue).setGo(new PionModel(player1,g,xValue/2,(yValue/2)));
		
		for(int i=0;i<obstacles;i++) 
		{
			Random r = new Random();
			int random = r.nextInt(nodes.size());
			if(nodes.get(random).getGo() == null)
			{
				nodes.get(random).setGo(new WallModel(g,random%xValue,random/xValue));
			}
			else
			{
				i--;
			}
		}
	}
	
	public ArrayList<Node> whichNodesArePlayable(Player pAdverse)
	{
		ArrayList<Node> nodesPlayable = new ArrayList<Node>();
		for(Node n : nodes)
		{
			if(n.getGo() == null)
			{
				if(this.checkAround(n, pAdverse)==n)
				{
					nodesPlayable.add(n);
				}
			}
		}
		return nodesPlayable;
	}
	
	public int getPlace(Node n)
	{
		return n.getX()+n.getY()*(xValue);
	}
	
	public int getPlace(int x, int y)
	{
		return x+y*(xValue);
	}
	
	public Node checkAround(Node n, Player pAdverse)
	{
		int takes = 0;
		for(Node ntest : nodesAroundOwnedByPlayer(n,pAdverse)) 
		{
			int dirX = ntest.getX()-n.getX();
			int dirY = ntest.getY()-n.getY();
			takes += direction(dirX,dirY,ntest,pAdverse,1);
		}
		if(takes != 0)
		{
			if(pAdverse == player1)
			{
				g.addIndicator(n,takes,player2.getColor());
			}
			else
			{
				g.addIndicator(n,takes,player1.getColor());
			}
			return n;
		}
		else
		{
			return null;
		}
	}
	
	public ArrayList<Node> nodesAroundOwnedByPlayer(Node n, Player pAdverse)
	{
		ArrayList<Node> nlist = new ArrayList<Node>();
		ArrayList<Node> nfinal = new ArrayList<Node>();
		
		if(n.getX() != 0) {
			nlist.add(nodes.get(this.getPlace(n)-1));
			if(n.getY() != 0) {
				nlist.add(nodes.get(this.getPlace(n)-(xValue+1)));
			}
		}
		if(n.getX() != xValue-1) {
			nlist.add(nodes.get(this.getPlace(n)+1));
			if(n.getY() != yValue-1) {
				nlist.add(nodes.get(this.getPlace(n)+(xValue+1)));
			}
		}
		if(n.getY() != 0) {
			nlist.add(nodes.get(this.getPlace(n)-(xValue)));
			if(n.getX() != xValue-1) {
				nlist.add(nodes.get(this.getPlace(n)-(xValue-1)));
			}
		}
		if(n.getY() != yValue-1) {
			nlist.add(nodes.get(this.getPlace(n)+(xValue)));
			if(n.getX() != 0) {
				nlist.add(nodes.get(this.getPlace(n)+(xValue-1)));
			}
		}
		
		for(Node ntest : nlist)
		{
			if(ntest.getGo()!=null) {
				if(ntest.getGo().getColor()==pAdverse.getColor())
				{
					nfinal.add(ntest);
				}
			}
		}
		
		return nfinal;
	}

	public int direction(int dirX, int dirY, Node n, Player pAdverse, int nbr)
	{
		if((n.getX()+dirX < 0 || n.getX()+dirX > xValue-1) || (n.getY()+dirY < 0 || n.getY()+dirY > yValue-1))
		{
			return 0;
		}
		else if(nodes.get(this.getPlace(n.getX()+dirX, n.getY()+dirY)).getGo() instanceof WallModel) 
		{
			return 0;
		}
		else if(nodes.get(this.getPlace(n.getX()+dirX, n.getY()+dirY)).getGo() == null)
		{
			return 0;
		}
		else if(nodes.get(this.getPlace(n.getX()+dirX, n.getY()+dirY)).getGo().getColor() == pAdverse.getColor())
		{
			return this.direction(dirX, dirY, nodes.get(this.getPlace(n.getX()+dirX, n.getY()+dirY)), pAdverse, nbr+1);
		}
		else
		{
			return nbr;
		}
	}
	
	public ArrayList<Node> allPionsToTurn(Node n, Player pAdverse){
		ArrayList<Node> nToTurn = new ArrayList<Node>();
		for(Node ntest : this.nodesAroundOwnedByPlayer(n, pAdverse)) {
			nToTurn.addAll(this.direction(ntest.getX()-n.getX(), ntest.getY()-n.getY(), n, pAdverse));
		}
		return nToTurn;
	}
	
	public ArrayList<Node> direction(int dirX, int dirY, Node n, Player pAdverse)
	{
		ArrayList<Node> ntaken = new ArrayList<Node>();
		boolean resume = true;
		int baseX = dirX;
		int baseY = dirY;
		while(resume == true)
		{
			if((n.getX()+dirX < 0 || n.getX()+dirX > xValue-1) || (n.getY()+dirY < 0 || n.getY()+dirY > yValue-1))
			{
				resume = false;
				return new ArrayList<Node>();
			}
			else if(nodes.get(this.getPlace(n.getX()+dirX, n.getY()+dirY)).getGo() instanceof WallModel) 
			{
				resume = false;
				return new ArrayList<Node>();
			}
			else if(nodes.get(this.getPlace(n.getX()+dirX, n.getY()+dirY)).getGo() == null)
			{
				resume = false;
				return new ArrayList<Node>();
			}
			else if(nodes.get(this.getPlace(n.getX()+dirX, n.getY()+dirY)).getGo().getColor() == pAdverse.getColor())
			{
				resume = true;
				ntaken.add(nodes.get(this.getPlace(n.getX()+dirX, n.getY()+dirY)));
				dirX += baseX;
				dirY += baseY;
			}
			else
			{
				resume = false;
				return ntaken;
			}
		}
		return ntaken;
	}
}
