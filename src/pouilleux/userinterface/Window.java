package pouilleux.userinterface;

import javax.swing.*;

import pouilleux.card.Card;
import pouilleux.game.Game;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;


public class Window extends JFrame implements ActionListener{

	private Game game;
	
	private static final long serialVersionUID = 1L;
	private final int X_SIZE = 1280; 
	private final int Y_SIZE = 720; 
	private JTextField textField;
	private JButton submitButton;
	private JButton endTurn;
	private JPanel panelNorthPlayerHand;
	private JPanel panelSouthPlayerHand;
	private JPanel panelEastPlayerHand;
	private JPanel panelWestPlayerHand;
	private JPanel panelStats;
	private JPanel panelPairs;
	private JPanel panel;
	
	private boolean pairPhase = false;
	private int offset = 24;
	private int cardWidth = 70;
	private int cardHeight = (int)(cardWidth*(726.0/500.0));
	
	
	private java.util.List<Integer> selectedCardsIndices = new ArrayList<>();
	
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
		panel = new BackgroundPanel("/resources/BACKGROUND.png");
		panel.setLayout(gbl_panel);
		
		
		
		
		
		
		// Player 3
		GridBagConstraints gbcNorth = new GridBagConstraints();
		panelNorthPlayerHand = new JPanel();
		panelNorthPlayerHand.setOpaque(false);
		gbcNorth.gridx = 1; // column
		gbcNorth.gridy = 0; // row
		gbcNorth.gridwidth = 2;
		gbcNorth.gridheight = 1;
		gbcNorth.anchor = GridBagConstraints.NORTH;
		gbcNorth.fill = GridBagConstraints.BOTH;
		panel.add(panelNorthPlayerHand, gbcNorth);
		
		// Player 4
		GridBagConstraints gbcEast = new GridBagConstraints();
		panelEastPlayerHand = new JPanel();
		panelEastPlayerHand.setOpaque(false);
		gbcEast.gridx = 3; // column
		gbcEast.gridy = 1; // row
		gbcEast.gridwidth = 1;
		gbcEast.gridheight = 2;
		gbcEast.fill = GridBagConstraints.BOTH;
		gbcEast.anchor = GridBagConstraints.EAST;
		panel.add(panelEastPlayerHand,gbcEast);
		
		
				
		
		//Player 1 (The human)
		GridBagConstraints gbcSouth = new GridBagConstraints();
		panelSouthPlayerHand = new JPanel();
		panelSouthPlayerHand.setOpaque(false);
		gbcSouth.gridx = 1; // column
		gbcSouth.gridy = 3; // row
		gbcSouth.gridwidth = 2;
		gbcSouth.gridheight = 1;
		gbcSouth.fill = GridBagConstraints.BOTH;
		gbcSouth.anchor = GridBagConstraints.SOUTH;
		panel.add(panelSouthPlayerHand,gbcSouth);
		
		
		
		// Player 2
		GridBagConstraints gbcWest = new GridBagConstraints();
		panelWestPlayerHand = new JPanel();
		panelWestPlayerHand.setOpaque(false);
		gbcWest.gridx = 0; // column
		gbcWest.gridy = 1; // row
		gbcWest.gridwidth = 1;
		gbcWest.gridheight = 2;
		gbcWest.fill = GridBagConstraints.BOTH;
		gbcWest.anchor = GridBagConstraints.WEST;
		panel.add(panelWestPlayerHand,gbcWest);
		
		
		// Game stats and Nickname entry at first
		GridBagConstraints gbcStats = new GridBagConstraints();
		panelStats = new JPanel();
		panelStats.setOpaque(false);
		gbcStats.gridx = 1; // column
		gbcStats.gridy = 2; // row
		gbcStats.gridwidth = 2;
		gbcStats.gridheight = 1;
		gbcStats.fill = GridBagConstraints.BOTH;
		gbcStats.anchor = GridBagConstraints.CENTER;
		panel.add(panelStats,gbcStats);
		panelStats.add(new JLabel("NickName :"));
		
		textField = new JTextField();
		panelStats.add(textField);
		textField.setColumns(16);
		
		
		submitButton = new JButton("Submit");
		submitButton.addActionListener(this);
		panelStats.add(submitButton);
		
		
		// Discarded Pairs
		GridBagConstraints gbcPairs = new GridBagConstraints();
		panelPairs = new JPanel(new FlowLayout());
		panelPairs.setOpaque(false);
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
			
		    game.startGameUI();
		    		
			
			// Player 2 Name
			JPanel panelSW = new JPanel();
			panelSW.setOpaque(false);
			GridBagConstraints gbc_panelSW = new GridBagConstraints();
			gbc_panelSW.fill = GridBagConstraints.BOTH;
			gbc_panelSW.gridx = 0;
			gbc_panelSW.gridy = 3;
			panel.add(panelSW, gbc_panelSW);
			panelSW.setLayout(new BorderLayout());
			JLabel label2 = new JLabel(game.getPlayers().get(1).getName());
			label2.setFont(new Font("Arial", Font.PLAIN, 20));
			label2.setForeground(Color.yellow);
			label2.setBorder(BorderFactory.createEmptyBorder(10, 30, 0, 0)); // top, left, bottom, right
			panelSW.add(label2,BorderLayout.NORTH);
			
			//player 1 name
			JLabel label1 = new JLabel(game.getPlayers().get(0).getName());
			label1.setFont(new Font("Arial", Font.PLAIN, 20));
			label1.setForeground(Color.yellow);
			label1.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0)); // top, left, bottom, right
			label1.setHorizontalAlignment(SwingConstants.RIGHT);
			panelSW.add(label1,BorderLayout.SOUTH);
			
			//player 3 name			
			JPanel panelNW = new JPanel();
			panelNW.setOpaque(false);
			GridBagConstraints gbc_panelNW = new GridBagConstraints();
			gbc_panelNW.fill = GridBagConstraints.BOTH;
			gbc_panelNW.gridx = 0;
			gbc_panelNW.gridy = 0;
			panel.add(panelNW, gbc_panelNW);
			panelNW.setLayout(new BorderLayout());
			JLabel label3 = new JLabel(game.getPlayers().get(2).getName());
			label3.setFont(new Font("Arial", Font.PLAIN, 20));
			label3.setForeground(Color.yellow);
			label3.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // top, left, bottom, right
			label3.setHorizontalAlignment(SwingConstants.RIGHT);
			panelNW.add(label3,BorderLayout.NORTH);
			
			
			//player 4 name
			JPanel panelSE = new JPanel();
			panelSE.setOpaque(false);
			GridBagConstraints gbc_panelSE = new GridBagConstraints();
			gbc_panelSE.fill = GridBagConstraints.BOTH;
			gbc_panelSE.gridx = 3;
			gbc_panelSE.gridy = 3;
			panel.add(panelSE, gbc_panelSE);
			panelSE.setLayout(new BorderLayout());
			JLabel label4 = new JLabel(game.getPlayers().get(3).getName());
			label4.setFont(new Font("Arial", Font.PLAIN, 20));
			label4.setForeground(Color.yellow);
			label4.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30)); // top, left, bottom, right
			label4.setHorizontalAlignment(SwingConstants.RIGHT);
			panelSE.add(label4,BorderLayout.NORTH);
			
			this.updateHands();
			for (int i=1; i<4; i++)
			{
				ArrayList<Card[]> pairs = this.game.getPlayers().get(i).discardPairs();
				for (Card[] pair : pairs)
				{
					this.printDiscardedPairs(pair);
					this.updateHands();
				}
			}
			
			this.pairPhase();
		}
		
		
		
		
		else if (e.getSource() == endTurn)
		{
			this.pairPhase = false;
			panelStats.removeAll();
			
			panelStats.revalidate();
			panelStats.repaint();
		}
	}
	
	
	public void pairPhase()
	{
		this.pairPhase = true;
		panelStats.removeAll();
		panelStats.add(new JLabel("Pairs Selection"));
		
		endTurn = new JButton("End Turn");
		endTurn.addActionListener(this);
		panelStats.add(endTurn);
	}
	
	public void playTurn()
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
			cardLabel.putClientProperty("index", i);  // We get the index when clicked on

		    cardLabel.addMouseListener(new java.awt.event.MouseAdapter() {
		        @Override
		        public void mouseClicked(java.awt.event.MouseEvent evt) {
		            int index = (int) ((JLabel) evt.getSource()).getClientProperty("index");

		            if (pairPhase)
		            {
			            if (selectedCardsIndices.contains(index)) {
			                selectedCardsIndices.remove((Integer) index);
			                cardLabel.setLocation(cardLabel.getX(), cardLabel.getY() + 20); // Card goes back down
			            } else {
			                if (selectedCardsIndices.size() < 2) {
			                    selectedCardsIndices.add(index);
			                    cardLabel.setLocation(cardLabel.getX(), cardLabel.getY() - 20); // card goes up
			                }
			            }
	
			            if (selectedCardsIndices.size() == 2) {
			                int idx1 = selectedCardsIndices.get(0);
			                int idx2 = selectedCardsIndices.get(1);
			                Card c1 = game.getPlayers().get(0).getHand().getCard(idx1);
			                Card c2 = game.getPlayers().get(0).getHand().getCard(idx2);
	
			                if (c1.formPair(c2)) {
			                    game.getPlayers().get(0).getHand().deletePair(c1, c2);
			                    selectedCardsIndices.clear();
			                    printDiscardedPairs(new Card[] {c1, c2});
			                    updateHands(); 
			                } else {
			                    selectedCardsIndices.clear();
			                    updateHands(); 
			                }
			            }
		            }
		        }
		    });
			layeredPaneSouth.add(cardLabel, Integer.valueOf(i));
			
		}
		
		panelSouthPlayerHand.add(layeredPaneSouth, BorderLayout.SOUTH);
		
		
		JPanel cardCountPanel1 = new JPanel(new FlowLayout());
		cardCountPanel1.setOpaque(false);
		JLabel countLabel1 = new JLabel(game.getPlayers().get(0).getCardCount()+" cards");
		countLabel1.setForeground(Color.YELLOW);
		cardCountPanel1.add(countLabel1);
		countLabel1.setFont(new Font("Arial", Font.PLAIN, 20));
		panelSouthPlayerHand.add(cardCountPanel1, BorderLayout.NORTH);
		
		panelSouthPlayerHand.revalidate();
		panelSouthPlayerHand.repaint();
		
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
		
		JPanel cardCountPanel2 = new JPanel(new BorderLayout());
		cardCountPanel2.setOpaque(false);
		JLabel countLabel2 = new JLabel(game.getPlayers().get(1).getCardCount()+" cards");
		countLabel2.setForeground(Color.YELLOW);
		cardCountPanel2.add(countLabel2, BorderLayout.CENTER);
		countLabel2.setFont(new Font("Arial", Font.PLAIN, 20));
		panelWestPlayerHand.add(cardCountPanel2, BorderLayout.EAST);
		
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
		
		JPanel cardCountPanel3 = new JPanel(new FlowLayout());
		cardCountPanel3.setOpaque(false);
		JLabel countLabel3 = new JLabel(game.getPlayers().get(2).getCardCount()+" cards");
		countLabel3.setForeground(Color.YELLOW);
		cardCountPanel3.add(countLabel3);
		countLabel3.setFont(new Font("Arial", Font.PLAIN, 20));
		panelNorthPlayerHand.add(cardCountPanel3, BorderLayout.SOUTH);
		
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
		
		JPanel cardCountPanel4 = new JPanel(new BorderLayout());
		cardCountPanel4.setOpaque(false);
		JLabel countLabel4 = new JLabel(game.getPlayers().get(3).getCardCount()+" cards");
		countLabel4.setForeground(Color.YELLOW);
		cardCountPanel4.add(countLabel4, BorderLayout.CENTER);
		countLabel4.setVerticalAlignment(SwingConstants.CENTER);
		countLabel4.setFont(new Font("Arial", Font.PLAIN, 20));
		panelEastPlayerHand.add(cardCountPanel4, BorderLayout.WEST);
		
		panelEastPlayerHand.revalidate();
		panelEastPlayerHand.repaint();
		
	}

	public void printDiscardedPairs(Card[] pair)
	{
		panelPairs.removeAll();
		ImageIcon icon1 = ResizeImage.sizedImage(pair[0], cardWidth, cardHeight);
		JLabel cardLabel1 = new JLabel(icon1);
		ImageIcon icon2 = ResizeImage.sizedImage(pair[1], cardWidth, cardHeight);
		JLabel cardLabel2 = new JLabel(icon2);
		panelPairs.add(cardLabel1);
		panelPairs.add(cardLabel2);
		panelPairs.revalidate();
		panelPairs.repaint();
		
	}
}
