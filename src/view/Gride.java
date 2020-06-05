package view;

import controller.*;
import model.GrideModel;
import model.Node;
import model.TurnManager;
import java.awt.event.ActionListener;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Gride extends JPanel
{
	protected int scoreP1;
	public void setScoreP1(int scoreP1) {
		this.scoreP1 = scoreP1;
	}

	public void setScoreP2(int scoreP2) {
		this.scoreP2 = scoreP2;
	}

	protected int scoreP2;
	private int i;
	
	protected ArrayList<Indicator> indicators = new ArrayList<Indicator>();
	protected ArrayList<JLabel> labelIndicators = new ArrayList<JLabel>();
	protected ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	protected Point point;
	private int xValue;
	private int yValue;
	protected MouseBehaviour mb;
	
	protected TurnManager tm;
	protected JButton leave;
	protected JLabel scoreNoir;
	protected JLabel scoreBlanc;
	
	public void addIndicator(Node n, int takes, Color c) {
		indicators.add(new Indicator(takes,n.getX(),n.getY(),c));
	}
	
	public void setPoint(Point point) {
		this.point = point;
	}

	public Gride(Menu m)
	{
		this.xValue = m.getX();
		this.yValue = m.getY();
		this.setLayout(null);
		this.setFocusable(true);

		tm = new TurnManager(this,m);
		point = new Point(10000,100000);
		mb = new MouseBehaviour(this,tm);
		mb.init();
		
		leave = new JButton("Leave");
		leave.setSize(250, 50);
		leave.setLocation(50, 825);
		leave.setFont(new Font("Sherif", Font.BOLD, 22));
		leave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mb.leaveClick();
			}
		});		
		
		this.add(leave);
		
		scoreNoir = new JLabel(m.getName2());
		scoreNoir.setLocation(400, 802);
		scoreNoir.setSize(500, 50);
		scoreNoir.setForeground(m.getJ2());
		scoreNoir.setFont(new Font("Sherif", Font.BOLD, 22));
		scoreNoir.setOpaque(true);
		this.add(scoreNoir);
		
		scoreBlanc = new JLabel(m.getName1());
		scoreBlanc.setLocation(400, 852);
		scoreBlanc.setSize(500, 50);
		scoreBlanc.setForeground(m.getJ1());
		scoreBlanc.setFont(new Font("Sherif", Font.BOLD, 22));
		scoreBlanc.setOpaque(true);
		this.add(scoreBlanc);
	}

	public void addPion(Pion p) {
		if(!gameObjects.contains(p))
		{
			gameObjects.add(p);
		}
	}
	
	public void addWall(Wall w) {
		if(!gameObjects.contains(w))
		{
			gameObjects.add(w);
		}
	}
	
	public void clearIndicators() {
		for(JLabel jl : labelIndicators)
		{
			jl.setName("");
			this.remove(jl);
			indicators.clear();
		}
		labelIndicators.clear();
		this.revalidate();
	}

	
	public void paintComponent(Graphics g)
	{
		g.setColor(new Color(43,161,35));
		g.fillRect(0, 0, xValue*100+1, yValue*100+1);
		
		for(int i=0;i<yValue;i++)
		{
			g.setColor(Color.BLACK);	
			g.drawLine(0, 100*i, xValue*100, 100*i);
		}
		for (int j = 0;j<xValue;j++)
		{
			g.setColor(Color.BLACK);
			g.drawLine(100*j, 0, 100*j, yValue*100);
		}
		
		for(GameObject go : gameObjects)
		{
			g.setColor(go.color);
			if(go instanceof Pion)
			{
				g.fillOval(100*go.getX()+10, 100*go.getY()+10, 80, 80);
			}
			else if(go instanceof Wall)
			{
				g.fillRect(100*go.getX(), 100*go.getY(), 100, 100);
			}
		}
			
		scoreNoir.setText(tm.getPlayer2().getName() +" : "+tm.getPlayer2().getScore().getScore());
		scoreBlanc.setText(tm.getPlayer1().getName() +" : "+tm.getPlayer1().getScore().getScore());	
		
		/*if(this.point.x < 800 && this.point.y < 800)
		{
			g.setColor(Color.ORANGE);
			g.fillRect((int)(point.x/100)*100, (int)(point.y/100)*100, 100, 100);
		}*/
		
		for(Indicator i : indicators) {
			JLabel ilabel = new JLabel(""+i.getTakes());
			ilabel.setForeground(i.getC());
			ilabel.setBounds(i.getX()*100+40,i.getY()*100+25,50,50);
			ilabel.setFont(new Font("Sherif", Font.BOLD, 26));
			labelIndicators.add(ilabel);
			this.add(ilabel);
		}
	}
}