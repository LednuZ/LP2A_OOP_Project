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
	
	@Override
	public void discardPairs() { 
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
	}
	
	
	
	@Override
	public void playTurn(Player leftPlayer)
	{
		// play a turn (pick a card form the left player)
		int cardCount = leftPlayer.getCardCount();
		this.pickCard(leftPlayer, rnd.nextInt(cardCount));
		
		
		/** We suppress the pair if he picked a good card
		 Since the picked card is added to the end of hand, we only need to compare all the cards
		 for the first to the n-1 card with the last card
		**/
		boolean foundPair = false;
		int index = 0; 
		while (!foundPair && index < this.getCardCount()-1) 
		{
			if (this.hand.getCard(index).formPair(this.hand.getCard(this.getCardCount() - 1))) {
				this.getHand().deletePair(index, this.getCardCount()-1);;
				foundPair = true;
			}
			else index+=1;
		}
		
		if (this.getCardCount() == 0) 
		{
			System.out.println();
		}
		
		// Turn ended
	}
}
