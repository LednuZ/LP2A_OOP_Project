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
    			boolean pairMode = true;
    			while (pairMode)
    			{
    				System.out.println("Card selection for a pair : \n"
    						+ "Type \"-1\" if you want to pick a card\n"
    						+ "Otherwise, type the index number of the first card");
    				if (scanner.hasNextInt()) {
    					choice = scanner.nextInt();
    				} else {
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
    		while (pick_index < 0 || pick_index >= hand_size) {
				System.out.println("Choose a number between 1 and "+ hand_size +": ");
				pick_index = scanner.nextInt() -1;
    		}
			players.get(this.currentPlayer).pickCard(players.get(nextPlayer()),pick_index) ;
			
			
			// ----------------------------
			// last Pair deleting phase
			// ----------------------------
			
			
			
		} 
	}
}
