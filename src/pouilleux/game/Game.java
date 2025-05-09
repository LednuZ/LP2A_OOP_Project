package pouilleux.game;
import java.util.ArrayList ;
import java.util.Random;

import pouilleux.players.*;
import pouilleux.card.*;


public class Game {
    ArrayList<Player> players = new ArrayList<Player>();
    private int currentPlayer ;

    public Game(String name ){
    	// Creation of 4 players: one user and 3 bots 
	    this.players.add(new Player(name));
	    this.players.add(new Bot("Player 2"));
	    this.players.add(new Bot("Player 3"));
	    this.players.add(new Bot("Player 4"));
	    
	    //creating the deck 
	    Deck deck = new Deck() ; 
	
	    //distributing the cards 
	    while (!deck.isEmpty()){
	        for (Player player : players) {   
	        	if (!deck.isEmpty()) {
		            Card card = deck.drawCard() ;
		            player.getHand().addCard(card) ; 
	        	}
	        } 
	    }

    }

    /**
	* Select a random dealer to start the game 
	*/
	public void selectRandomDealer() {
        Random random = new Random();
        this.currentPlayer = random.nextInt(4); 

    }
	
	/**
	 * Returns true if the only card in play is the jack of spades and 3 players finished
	 * @return a boolean value
	 */
	public boolean isFinished() {
        int count = 0 ; 
        boolean returnedValue = false ; 
        for (Player player : players) {   
            if (player.hasFinished()){
                count ++ ; 
            }
        }
        if (count ==3){
            returnedValue = true;
        }

        return returnedValue ;       

    } 
	
    /**
	* Play a game 
	*/
	public void startGame() {
		selectRandomDealer() ;
		// ------------------------
		//Phase 1
		// ------------------------
		
		System.out.println("First Phase !!");
		System.out.println();
		
		for (Player player : this.players) 
		{
			player.discardPairs();
		}
		
		
		System.out.println("Second Phase !!");
		System.out.println();
		// ------------------------
		// Phase 2
		// ------------------------
		while (!isFinished()) {
        	if(!players.get(this.currentPlayer).hasFinished()) {
        		System.out.println("It's the turn of the player "+ players.get(currentPlayer).getName() + " !!");
		        players.get(currentPlayer).playTurn(this.players.get(this.nextPlayer())) ; 
		        
        	}
	        this.currentPlayer = this.nextPlayer() ; 
		}
		
	}
	

    /**
	* give the next player still in game
	* @return the player at the right of the current player
	*/
	public int nextPlayer() {
		int leftPlayer = (this.currentPlayer+1)%4; 
		while (players.get(leftPlayer).hasFinished())
		{
			leftPlayer = (leftPlayer + 1) %4;
		}
		return leftPlayer ;
		
	}
	
	

}
