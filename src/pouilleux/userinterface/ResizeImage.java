package pouilleux.userinterface;

import javax.swing.*;

import pouilleux.card.Card;

import java.awt.*;

public class ResizeImage {
	public static ImageIcon sizedImage(String path, int containerWidth,int containerHeight)
	{
		ImageIcon image = new ImageIcon(
					new ImageIcon(ResizeImage.class.getResource(path))
						.getImage().getScaledInstance(containerWidth, containerHeight,Image.SCALE_SMOOTH));
		return image;
	}
	
	public static ImageIcon sizedImage(ImageIcon sourceImage, int containerWidth,int containerHeight)
	{
		ImageIcon image = new ImageIcon(
				sourceImage.getImage().getScaledInstance(containerWidth, containerHeight,Image.SCALE_SMOOTH));
		return image;
	}
	
	public static ImageIcon sizedImage(Card card, int containerWidth,int containerHeight)
	{
		ImageIcon image = new ImageIcon(
				CardImage.getImageOfCard(card).getImage().getScaledInstance(containerWidth, containerHeight,Image.SCALE_SMOOTH));
		return image;
	}
}
