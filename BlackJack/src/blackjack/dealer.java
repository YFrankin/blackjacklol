package blackjack;

public class dealer extends Player {
	private CardDeck cardDeck;
	public dealer(String name) {
		super(name);
		//game director shuffles a card deck
		cardDeck = new CardDeck();
	}

	public CardDeck getCardDeck() {
		return cardDeck;
	}
	
	public void dealerStart() {
		int points = getPoints();
		while (points < 17) {
			Card card = cardDeck.draw();
			addToHand(card);
			points = getPoints();
		}
	}
	
	/**
	 * Compare Player and Dealer 
	 * @param p
	 * @return 0 tie; -1: dealer wins; 1: player wins
	 */
	public int compare(Player p) {
		int playerPoints = p.getPoints();
		
		int dealerPoints = getPoints();
		if (playerPoints == dealerPoints)
			return 0;
		
		if (playerPoints > 21) {
			if (dealerPoints > 21) {
				return 0;
			} else {
				return -1;
			}
		} else {
			if (dealerPoints > 21) {
				return 1;
			} else {
				if (playerPoints < dealerPoints)
					return -1;
				else 
					return 1;
						
			}
		}
	}
}
