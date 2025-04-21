package pouilleux.card;

public class Card {
	private int value;
	private String suit;
	
	Card()
	{
		this.value = 1;
		this.suit = "Spade";
	}
	
	Card(int value, String suit)
	{
		this.value = value;
		this.suit = suit;
	}
}
