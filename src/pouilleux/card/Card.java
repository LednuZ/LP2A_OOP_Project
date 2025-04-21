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
				returnedValue = "Heart";
				break;
			case SPADE:
				returnedValue = "Spade";
				break;
			case DIAMOND:
				returnedValue = "Diamond";
				break;
			case CLUB:
				returnedValue = "Club";
				break;
		}
		return returnedValue;
	}
	
	
}
