package pouilleux.players;

import java.util.Scanner;

import pouilleux.card.*;

public class Player {
	Hand hand;
	String name;
	private static final Scanner scanner = new Scanner(System.in);
	
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
	public Card pickCard(Player leftPlayer, int index)
	{
		Card pickedCard = leftPlayer.getHand().getRid(index);
		if (pickedCard != null) // if getRid didn't returned "null" 
		{
			this.hand.addCard(pickedCard);
		}
		return pickedCard;
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
	 * Get the state of the player (if finish or not)
	 * @return true if the player has not any card
	 */
	public boolean hasFinished() {
		return this.getHand().getCount() ==0 ; 
	}
	
	public void discardPairs() { 
		int card1, card2 = -1;
		boolean secondCard = false;
		boolean pairMode = true;
		while (pairMode && this.getCardCount() > 1)
		{
			System.out.println("\n\nCard selection for a pair : \n" // first card
					+ "Type \"-1\" if you have finished\n"
					+ "Otherwise, type the index number of the first card");
			System.out.println(this.getHand().toString());
			
			if (scanner.hasNextInt()) { // valid entry (integer)
				card1 = scanner.nextInt();
				if (card1 == -1) pairMode = false; // stop if -1
				else if (card1 < this.getCardCount() && card1 >= 0)
				{
					secondCard = false;
					while(!secondCard) {
						System.out.println("\nYour first card is n°"+card1); //second card
						System.out.println("Please select the second card index to constitute a pair");
						System.out.println(this.getHand().toString());
						if (scanner.hasNextInt()) {
	    					card2 = scanner.nextInt();
	    					secondCard = true;
	    					if (this.getHand().deletePair(card1, card2)) {
	    						System.out.println("Pair "+ this.hand.getAllCards().get(card1) + " and " +this.hand.getAllCards().get(card2)+" discarded");
	    					}
	    					else
	    					{
	    						System.out.println("Not a valid pair, try again");
	    					}
						}
						
						
						else {
							System.out.println("Invalid input. Please try again.");
	    					System.out.println();
						}							
						if (scanner.hasNextLine()) {
							scanner.nextLine();
						}
					}
					
				}
				else {
					System.out.println("Card index out of range, max can be "+ (this.getCardCount() -1));
				}
				
			}
			
			else { // invalid entry
				System.out.println("Invalid input. Please try again.");
				System.out.println();
				if (scanner.hasNextLine()) {
					scanner.nextLine();
				}    				
			}
		}
		if (this.getCardCount() == 0)
		{
			System.out.println("\n\nYou won !!!\n\n");
		}
	}
	
	
	public void playTurn(Player leftPlayer)
	{
		System.out.println(this.getHand().toString()); // print the player's hand
  
			// --------------------
		// player picks a card
		// --------------------
		
		System.out.println("\n\nPick a card from the player to your left !");
		int pick_index = -1 ; 
		while (pick_index < 0 || pick_index >= leftPlayer.getCardCount()) {
			System.out.println("Choose a number between 1 and "+ leftPlayer.getCardCount() +": ");
			if (scanner.hasNextInt()) {
				pick_index = scanner.nextInt() -1;
			}
			else { // invalid entry
				System.out.println("Invalid input. Please try again.");
				System.out.println();
				if (scanner.hasNext()) {
					scanner.next();
				}    				
			}
		}
		System.out.println("You picked this card : " +this.pickCard(leftPlayer,pick_index).toString()) ;
		
		
		// ----------------------------
		// last Pair deleting phase
		// ----------------------------
		
		this.discardPairs();
		
		System.out.println("End of turn");
	}
}
