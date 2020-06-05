package model;

import view.Gride;
import view.Pion;
import view.Wall;

public class WallModel extends GameObjectModel {

	protected Gride g;
	
	public WallModel(Gride g, int x, int y) {
		this.x = x;
		this.y = y;
		g.addWall(new Wall(this.x,this.y,color.DARK_GRAY));
	}
	
	public void DoSomething(GrideModel gm) {
		
	}
}
