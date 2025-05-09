package pouilleux.players;

import java.util.Scanner;

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
	 * Get the state of the player (if finish or not)
	 * @return true if the player has not any card
	 */
	public boolean hasFinished() {
		return this.getHand().getCount() ==0 ; 
	}
	
	public void playTurn(Player leftPlayer)
	{
		System.out.println(this.getHand().toString()); // print the player's hand
		try (Scanner scanner = new Scanner(System.in)) {
    		int choice = -1 ; 
    		while (choice < 1 || choice > 2) {
    			System.out.println("\nChoose your next action :\n"
    					+ "1 : Delete pair from your hand\n"
    					+ "2 : Pick a card from the player on your left");
    			if (scanner.hasNextInt()) {
    	            choice = scanner.nextInt() -1;
    	        } else {
    	            System.out.println("Invalid input. Please try again.");
    	            System.out.println();
    	            scanner.nextLine();
    	        }
    		}
    		
    		
    		// -----------------------------
    		// Choice 1
    		// -----------------------------
    		
    		if (choice == 1) 
    		{	
    			int card1, card2 = -1;
    			boolean secondCard = false;
    			boolean pairMode = true;
    			while (pairMode)
    			{
    				System.out.println("Card selection for a pair : \n" // first card
    						+ "Type \"-1\" if you want to pick a card from the player to your left\n"
    						+ "Otherwise, type the index number of the first card");
    				System.out.println(this.getHand().toString());
    				
    				if (scanner.hasNextInt()) { // valid entry (integer)
    					card1 = scanner.nextInt();
    					if (card1 == -1) pairMode = false; // stop if -1
    					else if (card1 < this.getCardCount() && card1 >= 0)
    					{
    						secondCard = true;
    						while(!secondCard) {
    							System.out.println("Your first card is n°"+card1); //second card
    							System.out.println("Please select the second card index to constitute a pair");
    							System.out.println(this.getHand().toString());
    							if (scanner.hasNextInt()) {
    		    					card2 = scanner.nextInt();
    		    					secondCard = true;
    		    					if (this.getHand().deletePair(card1, card2)) {
    		    						System.out.println("Pair discarded");
    		    					}
    		    					else
			    					{
			    						System.out.println("Not a valid pair, try again");
			    					}
    							}
    							
    							
    							else {
    								System.out.println("Invalid input. Please try again.");
    		    					System.out.println();
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
    					scanner.nextLine();
    				}
    			}
    		}
    		
    		// -----------------------------
    		// Choice 2 goes here directly
    		// -----------------------------
    		// player picks a card
    		int pick_index = -1 ; 
    		while (pick_index < 0 || pick_index >= leftPlayer.getCardCount()) {
				System.out.println("Choose a number between 1 and "+ leftPlayer.getCardCount() +": ");
				pick_index = scanner.nextInt() -1;
    		}
			this.pickCard(leftPlayer,pick_index) ;
			
			
			// ----------------------------
			// last Pair deleting phase
			// ----------------------------
			
			int card1, card2 = -1;
			boolean secondCard = false;
			boolean pairMode = true;
			while (pairMode)
			{
				System.out.println("Card selection for a pair : \n" // first card
						+ "Type \"-1\" if you want to end your turn\n"
						+ "Otherwise, type the index number of the first card");
				System.out.println(this.getHand().toString());
				
				if (scanner.hasNextInt()) { // valid entry (integer)
					card1 = scanner.nextInt();
					if (card1 == -1) pairMode = false; // stop if -1
					
					
					//verifying if card1 is in range
					else if (card1 < this.getCardCount() && card1 >= 0) 
					{
						while(!secondCard) {
							System.out.println("Your first card is n°"+card1); //second card
							System.out.println("Please select the second card index to constitute a pair");
							System.out.println(this.getHand().toString());
							if (scanner.hasNextInt()) { // valid entry (integer)
		    					card2 = scanner.nextInt();
		    					
		    					// verifying if card1 is in range
		    					if (card2 >= 0 && card2 < this.getCardCount())
		    					{
			    					secondCard = true;
			    					boolean valid  = false;
			    				
			    					if (this.getHand().deletePair(card1, card2)) { // the validity of the pair matters since we can only discard one pair
			    						pairMode = false;
			    						System.out.println("Pair discarded");
			    					}
			    					else
			    					{
			    						System.out.println("Not a valid pair, try again");
			    					}
								}
			    				else {
			    					System.out.println("Card index out of range, max can be "+ (this.getCardCount() -1));
			    				}
							}
							
							
							else { // invalid entry (not an integer)
								System.out.println("Invalid input. Please try again.");
		    					System.out.println();
		    					scanner.nextLine();
							}
							
						}
						
					}
					else {
						System.out.println("Card index out of range, max can be "+ (this.getCardCount() -1));
					}
					
				}
				else {
					System.out.println("Invalid input. Please try again.");
					System.out.println();
					scanner.nextLine();
				}
			}
			
			System.out.println("End of turn");
			
		} 
	}
}
