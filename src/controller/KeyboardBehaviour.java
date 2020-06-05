package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.TurnManager;
import view.Gride;

public class KeyboardBehaviour implements KeyListener {
	
	private TurnManager tm;
	
	public void init(Gride g, TurnManager tm) {
		 g.addKeyListener(this);
		 this.tm = tm;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(Character.toLowerCase(e.getKeyChar()) == 'b')
		{
			//System.out.println("b");
			tm.setCharPressed(Character.toLowerCase(e.getKeyChar()));
		}
		else if(Character.toLowerCase(e.getKeyChar()) =='l')
		{
			//CALL
			tm.setCharPressed(Character.toLowerCase(e.getKeyChar()));
		}
		else if(Character.toLowerCase(e.getKeyChar()) =='c')
		{
			//CALL
			tm.setCharPressed(Character.toLowerCase(e.getKeyChar()));
		}
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
