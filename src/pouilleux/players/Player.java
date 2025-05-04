package pouilleux.players;

import pouilleux.card.*;

public class Player {
	Hand hand;
	String name;
	
	public Player(String name)
	{
		this.hand = new Hand();
		this.name = name;
	}
	
	public Hand getHand()
	{
		return this.hand;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * Let the player pick the index-th card on another player
	 * @param leftPlayer other player who will "lost" his card
	 * @param index index of the card from the other player
	 * @return true if the action is complete, false otherwise
	 */
	public boolean pickCard(Player leftPlayer, int index)
	{
		boolean actionDone = false;
		Card pickedCard = leftPlayer.getHand().getRid(index);
		if (pickedCard != null) // if getRid didn't returned "null" 
		{
			this.hand.addCard(pickedCard);
			actionDone = true;
		}
		return actionDone;
	}
	
	
	/**
	 * Get the count of possessed cards
	 * @return an int corresponding to the number of its cards
	 */
	public int getCardCount()
	{
		return this.hand.getCount();
	}
	
	/**
	 * Get the statue of the player (if finish or not)
	 * @return true if the player has not any card
	 */
	public boolean hasFinished() {
		return this.getHand().getCount() ==0 ; 
	}
}
