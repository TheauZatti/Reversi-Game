package view;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.MouseBehaviour;


public class Menu extends JFrame {
	
	private JLabel bonjour;
	private JLabel regle1;
	private JLabel regle2;
	private JLabel regle3;
	private JLabel regle4;
	private JLabel regle5;
	private JLabel regle6;
	private JButton start;
	private JButton joueur1;
	private JButton joueur2;
	private int nbTrapMax;
	private int nbWall;
	private JComboBox trap;
	private JComboBox wall;
	private JComboBox xValue;
	private JComboBox yValue;
	private Object[] element1;
	private Object[] element2;
	private Object[] element3;
	private Object[] element4;
	private int x;
	private int y;
	protected MouseBehaviour mb;
	
	
	
	private Color j1 = Color.BLACK;
	public Color getJ1() {
		return j1;
	}
	
	private Color j2 = Color.WHITE;
	public Color getJ2() {
		return j2;
	}
	
	private String name1="joueur 1";	
	public String getName1() {
		return name1;
	}

	private String name2="joueur 2";
	public String getName2() {
		return name2;
	}	

	public int getNbTrapMax() {
		return this.nbTrapMax;
	}
	
	public int getNbWall() {
		return this.nbWall;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}

	public Menu ()
	{
		Menu m = this;
		this.setLayout(null);
		mb = new MouseBehaviour(this);
		
		

		this.setSize(1200,1000);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setUndecorated(true);
		
		
		bonjour = new JLabel("Welcome to our Reversi game");
		bonjour.setLocation(400,75);
		bonjour.setSize(1000, 50);
		bonjour.setFont(new Font("Sherif", Font.BOLD, 22));
		bonjour.setOpaque(true);
		this.add(bonjour);
		
		regle1 = new JLabel("You can place traps to steal the victory");
		regle1.setLocation(50,200);
		regle1.setSize(1000, 50);
		regle1.setFont(new Font("Sherif", Font.BOLD, 18));
		regle1.setOpaque(true);
		this.add(regle1);
		
		regle2 = new JLabel("press 'b' to place bomb, 'l' to place line trap or 'c' to place column trap");
		regle2.setLocation(50,300);
		regle2.setSize(1000, 50);
		regle2.setFont(new Font("Sherif", Font.BOLD, 18));
		regle2.setOpaque(true);
		this.add(regle2);
		
		start = new JButton("Start");
		start.setSize(250, 50);
		start.setLocation(450, 825);
		start.setFont(new Font("Sherif", Font.BOLD, 22));
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				name1 = joueur1.getText();
				name2 = joueur2.getText();
				nbTrapMax = trap.getSelectedIndex();
				nbWall = wall.getSelectedIndex()*5;
				x = xValue.getSelectedIndex()+5;
				y = yValue.getSelectedIndex()+5;
				mb.start();
				m.dispose();
			}
		});		
		this.add(start);
		
		regle3 = new JLabel("Trap limit :");
		regle3.setLocation(50,450);
		regle3.setSize(200, 50);
		regle3.setFont(new Font("Sherif", Font.BOLD, 18));
		regle3.setOpaque(true);
		this.add(regle3);
		
		element1 = new Object[]{"0","1", "2", "3", "4", "5"};
		trap = new JComboBox(element1);
		trap.setSize(100,40);
		trap.setLocation(250,450);
		this.add(trap);
		
		regle4 = new JLabel("Wall number :");
		regle4.setLocation(500,450);
		regle4.setSize(200, 50);
		regle4.setFont(new Font("Sherif", Font.BOLD, 18));
		regle4.setOpaque(true);
		this.add(regle4);
		
		element2 = new Object[]{"0","5","10", "15", "20", "25", "30"};
		wall = new JComboBox(element2);
		wall.setSize(100,40);
		wall.setLocation(700,450);
		this.add(wall);
		
		
		
		
		regle5 = new JLabel("Board width :");
		regle5.setLocation(50,600);
		regle5.setSize(200, 50);
		regle5.setFont(new Font("Sherif", Font.BOLD, 18));
		regle5.setOpaque(true);
		this.add(regle5);
		
		element3 = new Object[]{"5","6", "7", "8", "9", "10", "11", "12"};
		xValue = new JComboBox(element3);
		xValue.setSize(100,40);
		xValue.setLocation(250,600);
		this.add(xValue);
		
		regle6 = new JLabel("Board height :");
		regle6.setLocation(500,600);
		regle6.setSize(200, 50);
		regle6.setFont(new Font("Sherif", Font.BOLD, 18));
		regle6.setOpaque(true);
		this.add(regle6);
		
		element4 = new Object[]{"5","6", "7", "8", "9", "10", "11", "12"};
		yValue = new JComboBox(element4);
		yValue.setSize(100,40);
		yValue.setLocation(700,600);
		this.add(yValue);
		
		
		
		joueur1 = new JButton("Joueur1");
		joueur1.setSize(250, 50);
		joueur1.setLocation(200, 700);
		joueur1.setBackground(Color.black);
		joueur1.setForeground(Color.white);
		joueur1.setFont(new Font("Sherif", Font.BOLD, 22));
		joueur1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mb.color(joueur1);
				j1 = joueur1.getBackground();
			}
		});		
		this.add(joueur1);
		
		joueur2 = new JButton("Joueur2");
		joueur2.setSize(250, 50);
		joueur2.setLocation(700, 700);
		joueur2.setBackground(Color.white);
		joueur2.setForeground(Color.black);
		joueur2.setFont(new Font("Sherif", Font.BOLD, 22));
		joueur2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mb.color(joueur2);
				j2 = joueur2.getBackground();
			}
		});
		this.add(joueur2);
		
		
		
		
		this.setVisible(true);
	}
}
