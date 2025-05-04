package pouilleux.players;

import java.util.Random;
import pouilleux.card.Card;
import java.util.ArrayList;

public class Bot extends Player{
	Random rnd = new Random(); 
	
	public Bot(String name)
	{
		super(name);
	}
	
	public boolean playTurn(Player leftPlayer)
	{
		// We search all the pairs to delete them
		ArrayList<Card[]> foundPairs = new ArrayList<Card[]>();
		for (int i=0; i<this.getCardCount() - 1;i++)
		{
			for (int j=i+1; j <this.getCardCount();j++)
			{
				if (this.hand.getCard(i).formPair(this.hand.getCard(j)))
				{
					Card[] pair = {this.hand.getCard(i),this.hand.getCard(j)};
					foundPairs.add(pair);
				}
			}
		}
		for (Card[] pair : foundPairs) {
			this.hand.deletePair(pair[0], pair[1]);
		}
		
		//Now we want to play a turn
		
		//We choose a rnd card to pick form the player to the left
		
		
		// we end the turn
	}
	
	
	/**
	 * Delete all the pairs of the bot automatically
	 */
	public void deleteAllPairs() {
		
		
	}
}
