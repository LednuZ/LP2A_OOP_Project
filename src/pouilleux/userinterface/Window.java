package pouilleux.userinterface;

import javax.swing.*;

import pouilleux.game.Game;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Window extends JFrame implements ActionListener{

	private Game game;
	
	private static final long serialVersionUID = 1L;
	private final int X_SIZE = 1280; 
	private final int Y_SIZE = 720; 
	private JTextField textField;
	private JButton submitButton;
	private JPanel panelNorthPlayerHand;
	private JPanel panelSouthPlayerHand;
	private JPanel panelEastPlayerHand;
	private JPanel panelWestPlayerHand;
	private JPanel panelStats;
	private JPanel panelPairs;
	
	
	
	public Window()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("\"Pouilleux\" Game");
		this.setResizable(false); // avoid window resizing
		this.setSize(X_SIZE,Y_SIZE); // sets x-dimension and y-dimension
		ImageIcon image = new ImageIcon("resource/PNG-cacrds-1.3/jack_of_spades2.png");
		this.setIconImage(image.getImage());

		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.rowHeights = new int[]{(Y_SIZE/4), (Y_SIZE/4), (Y_SIZE/4), (Y_SIZE/4)};
		gbl_panel.columnWidths = new int[]{(X_SIZE/4), (X_SIZE/4), (X_SIZE/4), (X_SIZE/4)};
		JPanel panel = new JPanel(gbl_panel);
		
		
		// Player 3
		GridBagConstraints gbcNorth = new GridBagConstraints();
		panelNorthPlayerHand = new JPanel();
		gbcNorth.gridx = 1; // column
		gbcNorth.gridy = 0; // row
		gbcNorth.gridwidth = 2;
		gbcNorth.gridheight = 1;
		gbcNorth.anchor = GridBagConstraints.NORTH;
		gbcNorth.fill = GridBagConstraints.BOTH;
		panelNorthPlayerHand.setBackground(Color.BLUE);
		panel.add(panelNorthPlayerHand, gbcNorth);
		
		// Player 4
		GridBagConstraints gbcEast = new GridBagConstraints();
		panelEastPlayerHand = new JPanel();
		gbcEast.gridx = 3; // column
		gbcEast.gridy = 1; // row
		gbcEast.gridwidth = 1;
		gbcEast.gridheight = 2;
		gbcEast.fill = GridBagConstraints.BOTH;
		gbcEast.anchor = GridBagConstraints.EAST;
		panelEastPlayerHand.setBackground(Color.ORANGE);
		panel.add(panelEastPlayerHand,gbcEast);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 3;
		panel.add(panel_2, gbc_panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel();
		panel_2.add(lblNewLabel, BorderLayout.EAST);
				
		
		//Player 1 (The human)
		GridBagConstraints gbcSouth = new GridBagConstraints();
		panelSouthPlayerHand = new JPanel();
		gbcSouth.gridx = 1; // column
		gbcSouth.gridy = 3; // row
		gbcSouth.gridwidth = 2;
		gbcSouth.gridheight = 1;
		gbcSouth.fill = GridBagConstraints.BOTH;
		gbcSouth.anchor = GridBagConstraints.SOUTH;
		panelSouthPlayerHand.setBackground(Color.RED);
		panel.add(panelSouthPlayerHand,gbcSouth);
		

		JLayeredPane layeredPane = new JLayeredPane();
		panelSouthPlayerHand.add(layeredPane);
		
		
		// Player 2
		GridBagConstraints gbcWest = new GridBagConstraints();
		panelWestPlayerHand = new JPanel();
		gbcWest.gridx = 0; // column
		gbcWest.gridy = 1; // row
		gbcWest.gridwidth = 1;
		gbcWest.gridheight = 2;
		gbcWest.fill = GridBagConstraints.BOTH;
		gbcWest.anchor = GridBagConstraints.WEST;
		panelWestPlayerHand.setBackground(Color.GREEN);
		panel.add(panelWestPlayerHand,gbcWest);
		
		
		// Game stats and Nickname entry at first
		GridBagConstraints gbcStats = new GridBagConstraints();
		panelStats = new JPanel();
		gbcStats.gridx = 1; // column
		gbcStats.gridy = 2; // row
		gbcStats.gridwidth = 2;
		gbcStats.gridheight = 1;
		gbcStats.fill = GridBagConstraints.BOTH;
		gbcStats.anchor = GridBagConstraints.CENTER;
		panel.add(panelStats,gbcStats);
		
		textField = new JTextField();
		panelStats.add(textField);
		textField.setColumns(16);
		
		
		submitButton = new JButton("Submit");
		submitButton.addActionListener(this);
		panelStats.add(submitButton);
		
		
		// Discarded Pairs
		GridBagConstraints gbcPairs = new GridBagConstraints();
		panelPairs = new JPanel();
		gbcPairs.gridx = 1; // column
		gbcPairs.gridy = 1; // row
		gbcPairs.gridwidth = 2;
		gbcPairs.gridheight = 1;
		gbcPairs.fill = GridBagConstraints.BOTH;
		gbcPairs.anchor = GridBagConstraints.CENTER;
		panelPairs.setBackground(Color.YELLOW);
		panel.add(panelPairs,gbcPairs);
		
		
		
		getContentPane().add(panel);
		
		this.setVisible(true); // makes frame visible
	}

	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == submitButton)
		{
			game = new Game(textField.getText());
			panelStats.removeAll();
			panelStats.revalidate();
			panelStats.repaint();
			
			SwingUtilities.invokeLater(() -> { // a way to make the button disapear before the game starts
		        game.startGameUI();
		    });
			
			
		}
	}
	
	public void updateHands()
	{
		// Player 1 (Human)
		
		
		panelSouthPlayerHand.revalidate();
		panelSouthPlayerHand.repaint();
		
		// Player 2
		
		
		// Player 3
		
		
		// Player 4
		
		
	}
}
