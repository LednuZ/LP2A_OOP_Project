package pouilleux.card;

import java.util.Random;

public class Deck extends Hand{
	Random rnd = new Random();
	
	
	public Deck()
	{
		super();
		for (Suits suit : Suits.values()) 
		{
			for (int i = 1; i<14; i++)
			{
				this.addCard(new Card(i, suit));
			}
		}
		
		// Deleting the Spade's Jack
		this.deleteCard(new Card(12, Suits.SPADE));
	}
	
	
	/**
	 * Get a random card from the deck, that will be deleted form it after.
	 * @return a random available card
	 */
	public Card drawCard() {
		return this.getRid(rnd.nextInt(this.cards.size()));
	}
}
