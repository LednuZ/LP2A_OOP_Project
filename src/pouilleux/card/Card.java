package pouilleux.card;

public class Card {
	private int value;
	private Suits suit;
	
	
	Card() // Per default, we will get a ace of Spade
	{
		this.value = 1;
		this.suit = Suits.SPADE;
	}
	
	Card(int value, Suits suit)
	{
		this.value = value;
		this.suit = suit;
	}
	
	
	/**
	 * Method to return its value
	 * @return the value of the card (between 1 to 13; 11 -> Jack, 12 -> Queen, 13 -> King)
	 */
	public int getValue()
	{
		return this.value;
	}
	
	
	/**
	 * Get the suit to which belongs the card
	 * @return either Diamond, Heart, Club or Spade
	 */
	public String getSuit()
	{
		String returnedValue = "";
		switch(this.suit) {
			case HEART:
				returnedValue = "HEART";
				break;
			case SPADE:
				returnedValue = "SPADE";
				break;
			case DIAMOND:
				returnedValue = "DIAMOND";
				break;
			case CLUB:
				returnedValue = "CLUB";
				break;
		}
		return returnedValue;
	}
	
	
	/**
	 * Return true if the two cards are of the same color
	 * @param card2 the second card to be compared with
	 * @return true or false depending if the two card are of the same color
	 */
	public boolean isSameColor(Card card2)
	{
		boolean returnedValue = false;
		if (this.getSuit().equals("DIAMOND") || this.getSuit().equals("HEART")) // 1st card is red
		{
			if (card2.getSuit().equals("DIAMOND") || card2.getSuit().equals("HEART")) { // 2nd card is red
				returnedValue = true;
			}
		}
		else //1st card is black 
		{ 
			if (card2.getSuit().equals("CLUB") || card2.getSuit().equals("SPADE")) { // 2nd card is red
				returnedValue = true;
			}
		}
		return returnedValue;
	}
	
	
	/**
	 * Comparator to know if the two cards have the same value
	 * @param card2 the second card to test it with
	 * @return true or false depending if they have the same value
	 */
	public boolean hasSameValue(Card card2)
	{
		return (this.getValue() == card2.getValue());
	}
	
	
	/**
	 * return true or false depending if the cards are forming a pair regarding the color
	 * @param card2 the second card
	 * @return true or false depending if they form a color pair
	 */
	public boolean formPair(Card card2)
	{
		return (this.hasSameValue(card2) && this.isSameColor(card2));
	}
	
	
}
