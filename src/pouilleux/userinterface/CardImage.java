package pouilleux.userinterface;

import javax.swing.ImageIcon;

import pouilleux.card.Card;

public class CardImage {
	public static ImageIcon getImageOfCard(Card card)
	{
		return new ImageIcon(CardImage.class.getResource("/resources/cards/"+card.getValue()+card.getSuit()+".png"));
	}
}
