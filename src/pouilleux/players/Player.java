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
}
