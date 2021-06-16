package blackjack;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class CardDeck {
	public  ArrayList<Card> deck = new ArrayList<Card>();

	public CardDeck() { // constructor that initializes all of the
		shuffle();
	}

	public ArrayList<Card> getDeck() {
		return deck;
	}

	public void setDeck(ArrayList<Card> deck) {
		this.deck = deck;
	}

	public Card draw() {
		Random random = new Random();
		int cardNo = random.nextInt(deck.size()-1);
		Card targ = deck.get(cardNo);
		deck.remove(cardNo);
		return targ;
	}
	public void shuffle() {
		String suit = "\u2660";
		Color color = Color.BLACK;
		for (int i = 1; i <= 13; i++) {
			deck.add(new Card(i, color, suit));
		}
		suit = "\u2665";
		color = Color.RED;
		for (int i = 1; i <= 13; i++) {
			deck.add(new Card(i, color, suit));
		}
		suit = "\u2666";
		color = Color.black;
		for (int i = 1; i <= 13; i++) {
			deck.add(new Card(i, color, suit));
		}
		suit = "\u2663";
		color = Color.RED;
		for (int i = 1; i <= 13; i++) {
			deck.add(new Card(i, color, suit));
		}
	}
}
