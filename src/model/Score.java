package model;

import java.awt.Color;

public class Score {
	public Score(Color color) {
		this.color = color;
	}

	private int score = 0;
	private Color color;
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}


	public int computeScore(GrideModel gm) {
		score = 0;
		for(Node n : gm.getNodes()) {
			if(n.getGo() instanceof PionModel) {
				PionModel pm = (PionModel)n.getGo();
				if(pm.getColor().equals(color)) {
					score++;
				}
			}
		}
		return score;
	}
}
