package controller;

import view.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.TurnManager;

public class MouseBehaviour implements MouseListener,ActionListener {
	
	public MouseBehaviour() {}
	
	public MouseBehaviour(Menu m) {
		this.m = m;
	}
	
	public MouseBehaviour(Gride gr, TurnManager tm) {
		this.gr = gr;
		this.tm = tm;
	}
	
	public void init() {
		 gr.addMouseListener(this);
	}

	protected Gride gr;
	
	protected TurnManager tm;
	
	private Color color;
	private Menu m;
	private JTextField textField;
	private JPanel p;
	private JFrame f;
	private JButton b;
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getButton()==MouseEvent.BUTTON1)
        {
             Point position = e.getPoint();
             gr.setPoint(position);
             tm.setPoint(position);
             System.out.println(""+position.x+" | "+position.y);
             gr.repaint();
        }
	}
	
	public void leaveClick() {
		System.exit(0);
	}
	
	public void start() {
		Window w = new Window(m);
	}
	
	public void name(JButton b,String name) {
		b.setText(name);
		
	}
	
	public void color(JButton button) {
		MouseBehaviour mb = new MouseBehaviour(m);
		
		color = JColorChooser.showDialog(null,"Choisir la couleur",color);
		button.setBackground(color);
		
		f = new JFrame("choose player's name");
		f.setSize(300,80);
		f.setLocationRelativeTo(null);
		b=new JButton("OK");
		b.setSize(50,50);
		
		textField = new JTextField("joueur");
		textField.setColumns(15);
		textField.setOpaque(true);
		textField.setForeground(Color.black);
		textField.setLocation(0,0);
		
		p = new JPanel();
		f.getContentPane().add(p);
		p.setLayout(new BorderLayout());
		p.add(b,BorderLayout.SOUTH);
		p.setSize(200, 50);
		p.setLocation(500,500);
        p.setVisible(true); 
		p.add(textField,BorderLayout.NORTH);
		f.setVisible(true);
		
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mb.name(button,textField.getText());
				f.dispose();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
