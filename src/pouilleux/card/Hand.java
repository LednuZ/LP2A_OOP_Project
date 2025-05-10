package pouilleux.card;

import java.util.ArrayList;

public class Hand {
	ArrayList<Card> cards;
	
	public Hand()
	{
		this.cards = new ArrayList<Card>();
	}
	
	/**
	 * is the hand of cards empty ?
	 * @return true if empty, false otherwise
	 */
	public boolean isEmpty()
	{
		return (this.cards.size() == 0);
	}
	
	
	/**
	 * give the card at the index-th position
	 * @param index position of the card in the hand
	 * @return a card if valid index, null otherwise
	 */
	public Card getCard(int index)
	{
		Card card = null;
		if (index < this.getCount())
		{
			card = this.cards.get(index);
		}
		return card;
	}
	
	/**
	 * delete the designed card
	 * @param card to be deleted
	 */
	protected void deleteCard(Card card2)
	{
		int i = 0;
		int index = 0;
		for (Card card : this.cards) {
			if (card.equals(card2)) {
				index = i; 
			}
			i++;
		}
		if (index >= 0) {
			this.cards.remove(index);
		}
	}
	
	/**
	 * method used when a player want to pick the index-th card of the hand
	 * @param index 
	 * @return the card at the designed index, null if the index is out of range
	 */
	public Card getRid(int index)
	{
		Card disposedCard = null;
		if (index < this.getCount())
		{
			disposedCard = this.cards.get(index);
			this.cards.remove(disposedCard);
		}
		return disposedCard;
	}
	
	/**
	 * Delete a valid pair of two cards
	 * @param index1 index of the first card in the hand
	 * @param index2 index of the second card in the hand must be different of index1
	 */
	public boolean deletePair(int index1, int index2)
	{
		boolean returnedValue = false;
		if (index1 < this.getCount() && index2 < this.getCount() && index1 != index2) // index1 and index2 aren't out of range
		{
			Card card1 = this.getCard(index1);
			Card card2 = this.getCard(index2);
			if (card1.formPair(card2))
			{
				this.cards.remove(card1);
				this.cards.remove(card2);
				returnedValue = true;
			}
			System.out.println("Pair "+ card1.toString() + " and " +card2.toString()+" discarded");

		}
		return returnedValue;
	}
	
	/**
	 * Delete a valid pair of two cards
	 * @param index1 index of the first card in the hand
	 * @param index2 index of the second card in the hand must be different of index1
	 */
	public boolean deletePair(Card card1, Card card2)
	{
		boolean returnedValue = false;
		if (card1!=card2 && (this.cards.contains(card1) && this.cards.contains(card2)) ) // Both cards are different and in the hand
		{
			if (card1.formPair(card2))
			{
				this.cards.remove(card1);
				this.cards.remove(card2);
				returnedValue = true;
			}
			System.out.println("Pair "+ card1.toString() + " and " +card2.toString()+" discarded");

		}
		return returnedValue;
	}
	
	
	/**
	 * Add a single card
	 * @param card to be added
	 */
	public void addCard(Card card)
	{
		this.cards.add(card);
	}
	
	/**
	 * get a copy of the hand's card's list
	 * @return an array list composed of cards
	 */
	public ArrayList<Card> getAllCards()
	{
		ArrayList<Card> clonedHand = new ArrayList<Card>();
		clonedHand.addAll(this.cards);
		return clonedHand;
	}
	
	/**
	 * get the number of cards in the hand
	 * @return the count of cards
	 */
	public int getCount()
	{
		return this.cards.size();
	}
	
	public String toString()
	{
		String returnedString = "";
		int index = 0;
		for (Card card : cards)
		{
			returnedString += index + " : " + card.toString()+"\n";
			index ++;
		}
		return returnedString;
	}
}
