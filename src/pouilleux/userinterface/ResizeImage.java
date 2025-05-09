package pouilleux.userinterface;

import javax.swing.*;

import java.awt.*;

public class ResizeImage {
	public static ImageIcon sizedImage(String path, int containerWidth,int containerHeight)
	{
		ImageIcon image = new ImageIcon(
					new ImageIcon(ResizeImage.class.getResource(path))
						.getImage().getScaledInstance(containerWidth, containerHeight,Image.SCALE_SMOOTH));
		return image;
	}
}
