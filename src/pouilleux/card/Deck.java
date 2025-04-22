package pouilleux.card;

public class Deck extends Hand{
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
}
