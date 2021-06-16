package blackjack;

import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class Turn {
	boolean stand = false, hit = false;
	Player player;
	JFrame f;
	CardDeck c;
	public Turn (Player p, JFrame f , CardDeck c) {
		this.player =p;
		this.f = f;
		this.c = c;
	}
	
	public void oneTurn() {
		Button b=new Button("Stand");  
	    JButton c = new JButton("Hit");
	    JTextField name = new JTextField("       " + player.name);
	    name.setBounds(800, 0, 200, 100);
	    name.disable();
		b.setBounds(300,600,200,150);  
	    c.setBounds(500,600,200,150); 
	    b.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e){  
	            name.setText("stand");  
	            stand = true;
	            hit = false;
	            player.hasStand = true;
	   //         main.mainStand = true;
	            }
	    	
	    });  
	    c.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e){  
	            name.setText("hit");  
	            hit = true;
	            stand = false;}
	    });
	    f.add(b);  
	    f.add(c);
	    ArrayList<Card> cards = new ArrayList<Card>();
	    int xPos = 200, yPos = 320, xSiz = 80, ySiz = 130;
	    for(int i =0; i<12;i++) {
	    	JTextField tf = new JTextField("       ");
	    	tf.setBackground(Color.BLACK);
	    	tf.disable();
	    	if(i > 5) {
	    		yPos = 450;
	    	}
	    	
	    	tf.setBounds(xPos, yPos, xSiz, ySiz);
	    	if(xPos == 700) {
	    		xPos = 200;
	    	}else {
	    		xPos = xPos + 100;
	    	}
	    	
	    	f.add(tf);
	    }
	    xPos = 200;
	    yPos = 320;
	    xSiz = 80;
	    ySiz = 130;
	    for(int i =0; i<player.hand.size();i++) {
	    	JTextField tf2 = new JTextField("       " + (player.hand.get(i).getValue())+1);
	    	tf2.setBackground(Color.WHITE);
	    	tf2.disable();
	    	if(i > 5) {
	    		yPos = 450;
	    	}
	    	
	    	tf2.setBounds(xPos, yPos, xSiz, ySiz);
	    	if(xPos == 700) {
	    		xPos = 200;
	    	}else {
	    		xPos = xPos + 100;
	    	}
	    	
	    	f.add(tf2);
	    }
	    if(player.hasStand) {
	    	b.disable();
	    	c.disable();
	    }
	    f.setLayout(null);  
	    f.setVisible(true);
	}

	public boolean isStand() {
		return stand;
	}

	public void setStand(boolean stand) {
		this.stand = stand;
	}

	public boolean isHit() {
		return hit;
	}

	public void setHit(boolean hit) {
		this.hit = hit;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public JFrame getF() {
		return f;
	}

	public void setF(JFrame f) {
		this.f = f;
	}

	public CardDeck getC() {
		return c;
	}

	public void setC(CardDeck c) {
		this.c = c;
	}

	
}
