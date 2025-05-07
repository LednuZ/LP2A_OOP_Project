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
        Random random = new Random();
		while (!isFinished()) {
        	if(!players.get(this.currentPlayer).hasFinished()) {
				int hand_size = players.get(this.nextPlayer()).getCardCount() ;
		        if(players.get(this.currentPlayer) instanceof Bot) {   //if the current player is a bot 
		            ((Bot)players.get(currentPlayer)).playTurn(this.players.get(this.nextPlayer())) ; 
		        }
		        else {  //if the player is human
		        	try (Scanner scanner = new Scanner(System.in)) {
		        		int pick_index = -1 ; 
		        		while (pick_index < 0 || pick_index >= hand_size) {
							System.out.println("Choose a number between 1 and "+ hand_size +": ");
							pick_index = scanner.nextInt() -1;
		        		}
						players.get(this.currentPlayer).pickCard(players.get(nextPlayer()),pick_index) ;
						//ajouter la vérification des pairs pour le joueur 
					} 
	
		        }
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
