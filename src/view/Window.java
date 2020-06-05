package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Window extends JFrame {
	
	public	Window(Menu m)
	{
		this.setTitle("Reversi");
		this.setSize(m.getX()*100+1, m.getY()*100+100);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setUndecorated(true);
		Gride g = new Gride(m);
		this.setContentPane(g);
		this.setVisible(true);
	}
}
