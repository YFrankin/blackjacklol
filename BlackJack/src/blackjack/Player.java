package blackjack;
import java.util.ArrayList;

public class Player {
	public boolean hasStand = false, hasAce = false;

	public boolean hasStand() {
		return hasStand;
	}

	public void setLost(boolean hasStand) {
		this.hasStand = hasStand;
	}

	public String name = "";
	
	public ArrayList<Card> hand = new ArrayList<Card>();

	public Player(String name) {
		this.name = name;
	}

	public void addToHand(Card card) {
		hand.add(card);
		System.out.println("A card has been added to your hand");
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public void displayHand() {
		for (int i = 0; i < hand.size(); i++) {
			System.out.print(hand.get(i).getValue() + " ");
		}
	}

	public boolean checkNatural() {
		boolean has10 = false, hasAce = false;
		for (int i = 0; i < hand.size(); i++) {
			if (hand.get(i).getName().equalsIgnoreCase("ace")) {
				hasAce = true;
			} else if (hand.get(i).getValue() == 10) {
				has10 = true;
			}
		}
		return (hasAce && has10);
	}

	public void handCheck() {
		int count = 0;
		for (int i = 0; i < hand.size(); i++) {
			count = count + hand.get(i).getValue();
		}
		if (count < 21) {
			hasStand = true;
		}
	}
	
	public int getPoints() {
		int points = 0;
		for (Card card : getHand()) {
			points += card.getValue();
		}
		if (points > 21 && hasAce)
			points = points-10;
		return points;
	}

}
