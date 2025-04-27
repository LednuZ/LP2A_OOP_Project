package pouilleux.game;
import java.util.ArrayList ;
import java.util.Random;

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
        int first_player = random.nextInt(5); 




    }




    	/**
	 * Returns true if the only card in play is the jack of spades or 3 players are finished
	 * @return a boolean value
	 */
	public boolean isFinished() {
        int count = 0 ; 
        boolean returnedValue = false ; 
        for (Player player : players) {   
            if (player.getHand().isEmpty()){
                count ++ ; 
            }
        }
        if (count ==3){
            returnedValue = true;
        }

        return returnedValue ;       

    } 

}
