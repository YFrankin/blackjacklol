package blackjack;

import java.awt.Color;

public class Card {
	public int value = 0; // ace is 1 or 11, jack/queen/king are 10
	public boolean isOwned = false, inPile = false, isCardDown = false;
	private Color color;
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}

	private String suit;


	public boolean isCardDown() {
		return isCardDown;
	}

	public void setCardDown(boolean isCardDown) {
		this.isCardDown = isCardDown;
	}

	public String name = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Card(int value, Color color, String suit) {
		this.value = value;
		this.color = color;
		this.suit = suit;
		if (value == 1) {
			name = "Ace";
			this.value=11;
		} else if (value == 11) {
			name = "Jack";
			this.value = 10;
		} else if (value == 12) {
			name = "Queen";
			this.value = 10;
		} else if (value == 13) {
			name = "King";
			this.value = 10;
		} else {
			name = String.valueOf(value);
		}

	}

	public void putCardUp() {
		isCardDown = false;
	}

	public void putCardDown() {
		isCardDown = true;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void own() { // when a card is handed to player from deck(outdated code)
		this.isOwned = true;
	}

	public void disOwn() { // put into the deck
		this.isOwned = false;
	}

	public boolean getIsOwned() {
		return isOwned;
	}

}
