package blackjack;

import javax.swing.*;

import canvas.SaveImg;
import canvas.printImg;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Main extends Canvas implements ActionListener {
	private CardDeck cd;
	private dealer dealer;
	private Player player;
	private ArrayList<JTextField> cardHolders, cardDealer;
	private JButton standButton;
	private JButton hitButton;
	private JButton newGameButton;
	private JButton quitButton;
	private JTextField name;
	private SaveImg s;
	private printImg p; 

	public void initGame(Main m) throws IOException {
		newGame();
		cardHolders = new ArrayList<JTextField>();
		cardDealer = new ArrayList<JTextField>();
		JFrame frame = new JFrame("BlackJack");
		frame.getContentPane().setBackground(Color.BLACK);
		frame.add(m);
		frame.setSize(1000, 1000);
		boolean mainStand = false;
		name = new JTextField("       ");
		JTextField playerName = new JTextField("Player");
		JTextField dealerName = new JTextField("Dealer");
		name.setHorizontalAlignment(JTextField.CENTER);
		playerName.setHorizontalAlignment(JTextField.CENTER);
		dealerName.setHorizontalAlignment(JTextField.CENTER);
		standButton = new JButton("Stand");
		hitButton = new JButton("Hit");
		newGameButton = new JButton("New Game");
		quitButton = new JButton("Quit");

		saveImage();
		
		name.setBounds(390, 0, 220, 150);
		dealerName.setBounds(0, 170, 200, 100);
		playerName.setBounds(0,400,200,100);
		name.setEditable(false);
		playerName.setEditable(false);
		dealerName.setEditable(false);
		frame.add(dealerName);
		frame.add(playerName);
		standButton.setBounds(100, 600, 200, 150);
		hitButton.setBounds(300, 600, 200, 150);
		newGameButton.setBounds(500, 600, 200, 150);
		quitButton.setBounds(700, 600, 200, 150);
		standButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name.setText("stand");
				dealerPlay();
				hitButton.setEnabled(false);
				standButton.setEnabled(false);
			}

		});
		hitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name.setText("hit");
				Card card = cd.draw();
				player.addToHand(card);
				drawCards(false);
				int points = player.getPoints();
				if (points > 21) {
					hitButton.setEnabled(false);
					standButton.setEnabled(false);
				
					dealerPlay();
				}
			}
		});
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name.setText("");
				newGame();
				hitButton.setEnabled(true);
				standButton.setEnabled(true);
				drawCards(false);
			}
		});
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		frame.add(standButton);
		frame.add(hitButton);
		frame.add(newGameButton);
		frame.add(quitButton);
		
		int xPos = 200, yPos = 150, xSiz = 80, ySiz = 130;
		
		for (int i = 0; i < 6; i++) {
			JTextField textField = new JTextField();
			textField.setHorizontalAlignment(JTextField.CENTER);
			textField.setBackground(Color.BLACK);
			textField.setEditable(false);
			

			textField.setBounds(xPos, yPos, xSiz, ySiz);
			if (xPos == 700) {
				xPos = 200;
			} else {
				xPos = xPos + 100;
			}

			cardDealer.add(textField);
			frame.add(textField);
		}
		
		yPos  =320;
		for (int i = 0; i < 12; i++) {
			JTextField textField = new JTextField();
			textField.setHorizontalAlignment(JTextField.CENTER);
			textField.setBackground(Color.BLACK);
			textField.setEditable(false);
			if (i > 5) {
				yPos = 450;
			}

			textField.setBounds(xPos, yPos, xSiz, ySiz);
			if (xPos == 700) {
				xPos = 200;
			} else {
				xPos = xPos + 100;
			}

			cardHolders.add(textField);
			frame.add(textField);
		}

		drawCards(false);
		frame.add(name);

		frame.setLayout(null);
		frame.setVisible(true);

	}
	
	/**
	 * Start a new game, draw two cards to both player and dealer
	 */
	private void newGame() {
		dealer = new dealer("Dealer");
		cd = dealer.getCardDeck();

		player = new Player("Player");

		//draw two cards for player
		Card card = cd.draw();
		player.addToHand(card);
		card = cd.draw();
		player.addToHand(card);
		if (player.getPoints() == 21) {
			declareWinner(1);
			return;
		}
		
		// draw two cards for dealer
		card = cd.draw();
		dealer.addToHand(card);
		card = cd.draw();
		dealer.addToHand(card);

		if (dealer.getPoints() == 21) {
			declareWinner(-1);
			return;
			
		}
		
	}
	private void dealerPlay() {
		dealer.dealerStart();
		
		int result = dealer.compare(player);
		declareWinner(result);

	}
	
	/**
	 * Declare winner
	 * @param result -1: dealer wins; 0: tie; 1: player wins
 	 */
	private void declareWinner (int result) {
		switch (result) {
		case -1:
			name.setText(dealer.name + " Wins!");
			break;
		case 0:
			name.setText("Tie");
			break;
		case 1:
			name.setText(player.name + " Wins!");
		}
		drawCards(true);
	}
	
	private void drawCards(boolean showAllDealerCards) {
		ArrayList<Card> hand = player.getHand();
		ArrayList<Card> dHand = dealer.getHand();
		int counter = 0;
		for (JTextField holder : cardDealer) {
			holder.setText(null);
			holder.setBackground(Color.BLACK);
			if(counter ==0) {
				holder.setBackground(Color.gray);
				
			}
			counter++;
		}
		for (JTextField holder : cardHolders) {
			holder.setText(null);
			holder.setBackground(Color.BLACK);
		}
		for(int i = 0; i<dHand.size();i++) {
			if (showAllDealerCards || i==1) {
				cardDealer.get(i).setText(dHand.get(i).getName() + dHand.get(i).getSuit());
				cardDealer.get(i).setBackground(Color.WHITE);
				cardDealer.get(i).setForeground(dHand.get(i).getColor());
			}
		}
		for (int i = 0; i < hand.size(); i++) {
			cardHolders.get(i).setText(hand.get(i).getName() + hand.get(i).getSuit());
			cardHolders.get(i).setBackground(Color.WHITE);
			cardHolders.get(i).setForeground(hand.get(i).getColor());
		}
	}

	/**
	 * @throws IOException 
	 * 
	 */

	public static void main(String[] args) throws IOException {
		Main m = new Main();
		m.initGame(m);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
	public void paint(Graphics g) {  
		  
        Toolkit t=Toolkit.getDefaultToolkit();  
        Image i=t.getImage("amogus.jpg");  
        g.drawImage(i, 0,0,this);  
          
    }  
	public static void saveImage() throws IOException {
	    URL url = new URL("https://pbs.twimg.com/media/EoaXIXbXIAgDrwD.jpg");
	    InputStream is = url.openStream();
	    OutputStream os = new FileOutputStream("sample.jpg");

	    byte[] b = new byte[2048];
	    int length;

	    while ((length = is.read(b)) != -1) {
	        os.write(b, 0, length);
	    }

	    is.close();
	    os.close();
	}

}