package pouilleux.userinterface;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;

public class Window extends JFrame /*implements ActionListener*/{

	private static final long serialVersionUID = 1L;
	private final JPanel panel_1 = new JPanel();
	
	
	public Window()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("\"Pouilleux\" Game");
		this.setResizable(false); // avoid window resizing
		this.setSize(500,500); // sets x-dimension and y-dimension
		ImageIcon image = new ImageIcon("resource/PNG-cacrds-1.3/jack_of_spades2.png");
		this.setIconImage(image.getImage());

		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 1; // column
		gbc.gridy = 0; // row
		gbc.gridwidth = 2;
		JPanel panelNorthPlayerHand = new JPanel();
		panel.add(panelNorthPlayerHand, gbc);
		panelNorthPlayerHand.setBackground(Color.BLUE);
		
		
		JPanel panelEastPlayerHand = new JPanel();
		
		JPanel panelSouthPlayerHand = new JPanel();
		
		JPanel panelWestPlayerHand = new JPanel();
		
		this.add(panel);
		
		this.setVisible(true); // makes frame visible
	}

//	@Override
//	public void actionListener(ActionEvent e)
//	{
//		
//	}
}
