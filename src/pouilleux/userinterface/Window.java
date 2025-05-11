package pouilleux.userinterface;

import javax.swing.*;

import pouilleux.card.Card;
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
	
	private int offset = 24;
	private int cardWidth = 70;
	private int cardHeight = (int)(cardWidth*(726.0/500.0));
	
	
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
			
			SwingUtilities.invokeLater(() -> { // a way to make the button disappear before the game starts
		        game.startGameUI();
		    });
			
			this.updateHands();
		}
	}
	
	
	public void pairPhase()
	{
		
	}
	
	
	
	public void updateHands()
	{
		// -----------------------
		// Player 1 (Human)
		// -----------------------
		panelSouthPlayerHand.removeAll();
		panelSouthPlayerHand.setLayout(new BorderLayout());
		JLayeredPane layeredPaneSouth= new JLayeredPane();
		
		layeredPaneSouth.setPreferredSize(new Dimension(X_SIZE/2, Y_SIZE/4 - 20));
		
		for (int i =0; i<game.getPlayers().get(0).getCardCount();i++)
		{
			ImageIcon icon = ResizeImage.sizedImage(game.getPlayers().get(0).getHand().getCard(i), cardWidth, cardHeight);
			JLabel cardLabel = new JLabel(icon);
			cardLabel.setBounds(((X_SIZE/2)-((game.getPlayers().get(0).getCardCount()-1)*offset+cardWidth))/2 + i*offset,
					40,cardWidth, cardHeight);
			layeredPaneSouth.add(cardLabel, Integer.valueOf(i));
		}
		
		panelSouthPlayerHand.add(layeredPaneSouth, BorderLayout.SOUTH);
		
		panelSouthPlayerHand.revalidate();
		panelSouthPlayerHand.repaint();
		
		JPanel cardCountPanel = new JPanel(new FlowLayout());
		JLabel countLabel = new JLabel(game.getPlayers().get(0).getCardCount()+" cards");
		countLabel.setForeground(Color.YELLOW);
		cardCountPanel.add(countLabel);
		countLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		panelSouthPlayerHand.add(cardCountPanel, BorderLayout.NORTH);
		
		
		// -----------------------
		// Player 2
		// -----------------------
		panelWestPlayerHand.removeAll();
		panelWestPlayerHand.setLayout(new BorderLayout());
		JLayeredPane layeredPaneWest= new JLayeredPane();

		
		layeredPaneWest.setPreferredSize(new Dimension(X_SIZE/4, Y_SIZE/2 - 20));
		
		for (int i =0; i<game.getPlayers().get(1).getCardCount();i++)
		{
			ImageIcon icon = ImageUtil.rotateImageIcon(ResizeImage.sizedImage("/resources/cards/CARDBACK.png", cardWidth, cardHeight),90);
			JLabel cardLabel = new JLabel(icon);
			cardLabel.setBounds(10,
					((Y_SIZE/2)-((game.getPlayers().get(1).getCardCount()-1)*offset+cardWidth))/2 + i*offset,
					cardHeight, cardWidth);
			layeredPaneWest.add(cardLabel, Integer.valueOf(i));
		}
		
		panelWestPlayerHand.add(layeredPaneWest, BorderLayout.WEST);
		
		panelWestPlayerHand.revalidate();
		panelWestPlayerHand.repaint();
		
		// -----------------------
		// Player 3
		// -----------------------
		panelNorthPlayerHand.removeAll();
		panelNorthPlayerHand.setLayout(new BorderLayout());
		JLayeredPane layeredPaneNorth= new JLayeredPane();

		
		layeredPaneNorth.setPreferredSize(new Dimension(X_SIZE/2, Y_SIZE/4 - 20));
		
		for (int i =0; i<game.getPlayers().get(2).getCardCount();i++)
		{
			ImageIcon icon = ResizeImage.sizedImage("/resources/cards/CARDBACK.png", cardWidth, cardHeight);
			JLabel cardLabel = new JLabel(icon);
			cardLabel.setBounds(((X_SIZE/2)-((game.getPlayers().get(2).getCardCount()-1)*offset+cardWidth))/2 + i*offset,
					Y_SIZE/4 - 60 - cardHeight ,cardWidth, cardHeight);
			layeredPaneNorth.add(cardLabel, i);
		}
		
		panelNorthPlayerHand.add(layeredPaneNorth, BorderLayout.NORTH);
		
		panelNorthPlayerHand.revalidate();
		panelNorthPlayerHand.repaint();
		
		// -----------------------
		// Player 4
		// -----------------------
		panelEastPlayerHand.removeAll();
		panelEastPlayerHand.setLayout(new BorderLayout());
		JLayeredPane layeredPaneEast= new JLayeredPane();

		
		layeredPaneEast.setPreferredSize(new Dimension(X_SIZE/4, Y_SIZE/2 - 20));
		
		for (int i =0; i<game.getPlayers().get(3).getCardCount();i++)
		{
			ImageIcon icon = ImageUtil.rotateImageIcon(ResizeImage.sizedImage("/resources/cards/CARDBACK.png", cardWidth, cardHeight),90);
			JLabel cardLabel = new JLabel(icon);
			cardLabel.setBounds(X_SIZE/4 -10 - cardHeight,
					((Y_SIZE/2)-((game.getPlayers().get(3).getCardCount()-1)*offset+cardWidth))/2 + i*offset,
					cardHeight, cardWidth);
			layeredPaneEast.add(cardLabel, Integer.valueOf(i));
		}
		
		panelEastPlayerHand.add(layeredPaneEast, BorderLayout.EAST);
		
		panelEastPlayerHand.revalidate();
		panelEastPlayerHand.repaint();
		
	}
}
