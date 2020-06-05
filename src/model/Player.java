package model;

import java.awt.Color;

public class Player {
	
	public Player(Color color, String name, Score score, int nbTrap) {
		super();
		this.color = color;
		this.name = name;
		this.score = score;
		this.nbTrap = nbTrap;
	}

	protected Color color;
	protected Score score;
	protected String name;
	private int nbTrap;
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setScore(Score score) {
		this.score = score;
	}
	
	public Score getScore() {
		return this.score;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getNbTrap() {
		return this.nbTrap;
	}
	
	public void decreaseNbTrap() {
		this.nbTrap--;
	}
	
}
