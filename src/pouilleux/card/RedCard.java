package pouilleux.card;

import java.util.Arrays;

public class RedCard extends Card{
	static enum RedSuits
	{
		HEART,
		DIAMOND
	}
	
	RedCard()
	{
		super();
	}
	
	RedCard(int value, Suits suit)
	{
		super(value, suit);
	}
	
	
	/**
	 * 
	 * @param card The other card to test with if they constitute a pair
	 * @return a boolean according to if they constitute a color pair
	 */
	public boolean formPair(Card card)
	{
		boolean arePair = false;
		if (this.getValue() == card.getValue())
		{
			if (Arrays.stream(RedSuits.values()) // We search in the RedSuits enum if the suit of the card is in it
					.anyMatch(suit -> suit.name().equals(card.getSuit())))
			{
				arePair = true;
			}
		}
		return arePair; 
	}
	
}
