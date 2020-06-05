package model;

import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import controller.KeyboardBehaviour;
import view.Gride;
import view.Menu;

public class TurnManager {
	
	char character;

	private Player player1;
	private Player player2;
	
	private Menu m;
	
	private Player actualPlayer;
	
	private Point position = new Point(900,900);
	
	public TurnManager(Gride g,Menu m) {
		this.g = g;
		gm = new GrideModel(m.getNbWall(),g,m);
		player1 = gm.getPlayer1();
		player2 = gm.getPlayer2();
		this.m = m;
		actualPlayer = player2;
		this.update();
		KeyboardBehaviour kb = new KeyboardBehaviour();
		kb.init(this.g, this);
	}
	
	public void setCharPressed(char c) {
		character = c;
		System.out.println(""+c);
	}
	
	private Gride g;
	
	private GrideModel gm;
	
	public void update()
	{

		ArrayList<Node> playable= gm.whichNodesArePlayable(actualPlayer);
		boolean action = false;
		for(Node n : playable)
		{
			if(position.x==n.getX() && this.position.y==n.getY())
			{
				
				if(actualPlayer.equals(player1))
				{
					for(Node ntest : gm.allPionsToTurn(n, actualPlayer)) 
					{
						if(ntest.getGo() instanceof BombTrap) 
						{
							ntest.getGo().DoSomething(gm);
							action = true;
							break;
						}
						else if(ntest.getGo() instanceof LineTrap)
						{
							ntest.getGo().DoSomething(gm);
							System.out.println("Aha");
							action = true;
							break;
						}
						else
						{
							ntest.setGo(new PionModel(player2,g,ntest.getX(),ntest.getY()));
						}
					}
					actualPlayer = player2;
				}
				else
				{
					for(Node ntest : gm.allPionsToTurn(n, actualPlayer))
					{
						if(ntest.getGo() instanceof BombTrap) 
						{
							ntest.getGo().DoSomething(gm);
							action = true;
							break;
						}
						else if(ntest.getGo() instanceof LineTrap)
						{
							ntest.getGo().DoSomething(gm);
							action = true;
							break;
						}
						else
						{
							ntest.setGo(new PionModel(player1,g,ntest.getX(),ntest.getY()));
						}
					}
					actualPlayer = player1;
				}
				
				if(character == 'b' && actualPlayer.getNbTrap() > 0) {
					actualPlayer.decreaseNbTrap();
					gm.getNodes().get(gm.getPlace(n.getX(),n.getY())).setGo(new BombTrap(actualPlayer,g,gm.getNodes().get(gm.getPlace(n.getX(),n.getY())).getX(),gm.getNodes().get(gm.getPlace(n.getX(),n.getY())).getY(),this.m));
				}
				else if(character == 'c' && actualPlayer.getNbTrap() > 0) {
					actualPlayer.decreaseNbTrap();
					gm.getNodes().get(gm.getPlace(n.getX(),n.getY())).setGo(new LineTrap(actualPlayer,g,gm.getNodes().get(gm.getPlace(n.getX(),n.getY())).getX(),gm.getNodes().get(gm.getPlace(n.getX(),n.getY())).getY(),'y',this.m));
				}
				else if(character == 'l' && actualPlayer.getNbTrap() > 0) {
					actualPlayer.decreaseNbTrap();
					gm.getNodes().get(gm.getPlace(n.getX(),n.getY())).setGo(new LineTrap(actualPlayer,g,gm.getNodes().get(gm.getPlace(n.getX(),n.getY())).getX(),gm.getNodes().get(gm.getPlace(n.getX(),n.getY())).getY(),'x',this.m));
				}
				else
				{
					if(action==false)
					{
						gm.getNodes().get(gm.getPlace(n.getX(),n.getY())).setGo(new PionModel(actualPlayer,g,gm.getNodes().get(gm.getPlace(n.getX(),n.getY())).getX(),gm.getNodes().get(gm.getPlace(n.getX(),n.getY())).getY()));
					}
					else
					{
						if(actualPlayer.equals(player1)) {
							gm.getNodes().get(gm.getPlace(n.getX(),n.getY())).setGo(new PionModel(player2,g,gm.getNodes().get(gm.getPlace(n.getX(),n.getY())).getX(),gm.getNodes().get(gm.getPlace(n.getX(),n.getY())).getY()));
						}
						else
						{
							gm.getNodes().get(gm.getPlace(n.getX(),n.getY())).setGo(new PionModel(player1,g,gm.getNodes().get(gm.getPlace(n.getX(),n.getY())).getX(),gm.getNodes().get(gm.getPlace(n.getX(),n.getY())).getY()));
						}
						action = false;
					}
				}
				character = ' ';
				
				//gm.getNodes().get(gm.getPlace(n.getX(),n.getY())).setGo(new PionModel(actualPlayer,g,n.getX(),n.getY()));
				g.setScoreP2(player1.getScore().computeScore(gm));
				g.setScoreP1(player2.getScore().computeScore(gm));
			}
		}
		
		ArrayList<Node> next_play = gm.whichNodesArePlayable(actualPlayer);

		if(actualPlayer == player1)
		{
			if(next_play.size() ==0) {
				if(gm.whichNodesArePlayable(player2).size()!=0) {
					actualPlayer = player2;
				}else {
					fin();
				}
			}
		}else {
			if(next_play.size() ==0) {
				if(gm.whichNodesArePlayable(player1).size()!=0) {
					actualPlayer = player1;
				}else {
					fin();
				}
			}
		}
		
	}
	
	public void fin() {
		if( player1.getScore().getScore() >  player2.getScore().getScore()) {
			JOptionPane.showMessageDialog(null, "FIN ! "+player1.getName()+" a gagné avec " + player1.getScore().getScore() + " points");
			
		}else if(player1.getScore().getScore() == player2.getScore().getScore()) {
			JOptionPane.showMessageDialog(null, "FIN ! Egalité ! " + player1.getScore().getScore() + " points");

		}else {
			JOptionPane.showMessageDialog(null, "FIN ! "+player2.getName()+" a gagné avec " + player2.getScore().getScore() + " points");

		}
	}
	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}
	public void setPoint(Point pos)
	{
		this.position = pos;
		this.position.x = this.position.x/100;
		this.position.y = this.position.y/100;
		this.update();
		g.clearIndicators();
		gm.whichNodesArePlayable(actualPlayer);
	}
}
