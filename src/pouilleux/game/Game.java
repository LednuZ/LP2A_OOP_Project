package pouilleux.game;
import java.util.ArrayList ;
import java.util.Random;
import java.util.Scanner;

import pouilleux.players.*;
import pouilleux.card.*;


public class Game {
    ArrayList<Player> players ;
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
	            Card card = deck.drawCard() ;
	            player.getHand().addCard(card) ; 
	        } 
	    }

    }

    /**
	* Select a random dealer to start the game 
	*/
	public void selectRandomDealer() {
        Random random = new Random();
        this.currentPlayer = random.nextInt(5); 

    }
	
	/**
	 * Returns true if the only card in play is the jack of spades or 3 players are finished
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
	* Play a turn 
	*/
	public void playTurn() {
		selectRandomDealer() ; 
        Random random = new Random();
		while (!isFinished()) {
        	if(!players.get(this.currentPlayer).hasFinished()) {
				int hand_size = players.get(this.leftPlayer()).getHand().getCount() ;
		        if(players.get(this.currentPlayer) instanceof Bot) {   //if the current player is a bot 
		            players.get(this.currentPlayer).pickCard(players.get(leftPlayer()), random.nextInt(hand_size)) ; 
		            ((Bot)players.get(currentPlayer)).deleteAllPairs() ; 
		        }
		        else {  //if it's the player
		        	try (Scanner scanner = new Scanner(System.in)) {
		        		int pick_index = -1 ; 
		        		while (pick_index < 0 || pick_index >= hand_size) {
							System.out.println("Choose a number between 1 and "+ hand_size +": ");
							pick_index = scanner.nextInt();
		        		}
						players.get(this.currentPlayer).pickCard(players.get(leftPlayer()),pick_index) ;
						//ajouter la vérification des pairs pour le joueur 
					} 
	
		        }
        	}
	        this.currentPlayer = this.nextPlayer() ; 
		}
		
	}
	
    /**
	* give the player at the left 
	* @return the player at the left of the current player
	*/
	public int leftPlayer() {
		return (this.currentPlayer-1)%4 ;
		
	}

    /**
	* give the next player 
	* @return the player at the right of the current player
	*/
	public int nextPlayer() {
		return (this.currentPlayer+1)%4 ;
		
	}
	
	

}
