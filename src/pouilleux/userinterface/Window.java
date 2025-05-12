package pouilleux.userinterface;

import javax.swing.*;

import pouilleux.card.Card;
import pouilleux.card.Deck;
import pouilleux.game.Game;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;


public class Window extends JFrame implements ActionListener{

	private Game game;
	
	private static final long serialVersionUID = 1L;
	private final int X_SIZE = 1280; 
	private final int Y_SIZE = 720; 
	private JTextField textField;
	private JButton submitButton;
	private JButton endTurn;
	private JButton endFirstPhase;
	private JPanel panelNorthPlayerHand;
	private JPanel panelSouthPlayerHand;
	private JPanel panelEastPlayerHand;
	private JPanel panelWestPlayerHand;
	private JPanel panelStats;
	private JPanel panelPairs;
	private JPanel panel;
	
	private boolean pairPhase = false;
	private boolean pickPhase = false;
	
	private int offset = 24;
	private int cardWidth = 70;
	private int cardHeight = (int)(cardWidth*(726.0/500.0));
	
	private final Map<String, ImageIcon> imageCache = new HashMap<>(); // avoid to search each time for images
	
	private java.util.List<Integer> selectedCardsIndices = new ArrayList<>();
	
	public Window()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("\"Pouilleux\" Game");
		this.setResizable(false); // avoid window resizing
		this.setSize(X_SIZE,Y_SIZE); // sets x-dimension and y-dimension
		ImageIcon image = new ImageIcon("resource/cards/11SPADE.png");
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
		
		
		this.preloadCardImages();

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
			
			this.firstPhase();
			
		}
		
		
		
		else if (e.getSource() == endTurn)
		{
			this.pairPhase = false;
			
			panelStats.removeAll();
			selectedCardsIndices.clear();

			game.nextPlayerTurn();
			panelStats.revalidate();
			panelStats.repaint();
			
			this.playTurn();
		}
		
		else if (e.getSource() == endFirstPhase)
		{
			this.pairPhase = false;
			
			panelStats.removeAll();
			selectedCardsIndices.clear();
			panelStats.revalidate();
			panelStats.repaint();

			this.playFirstTurn();
		}
	}
	
	
	
	/**
	 * This phase corresponds to the First phase where everyone discards all of their pairs
	 * It is followed by the First Turn
	 */
	public void firstPhase()
	{
		this.pairPhase = true;
		panelStats.removeAll();
		panelStats.add(new JLabel("Pairs Selection"));
		
		endFirstPhase = new JButton("End First Phase");
		endFirstPhase.addActionListener(this);
		panelStats.add(endFirstPhase);
	}
	
	/**
	 * We choose the pair that we want to discard
	 * It's followed by playTurn
	 */
	public void pairPhase()
	{
		this.pairPhase = true;
		panelStats.removeAll();
		panelStats.add(new JLabel("Pairs Selection"));
		
		endTurn = new JButton("End Turn");
		endTurn.addActionListener(this);
		panelStats.add(endTurn);
	}
	
	
	/**
	 * The first turn starts on the randomly selected dealer, until we're on the human player
	 * It is followed by the pick Phase
	 */
	public void playFirstTurn() // since we don't always start the game after player 1
	{
		if (game.getCurrentPlayer()!=0)
		{
			for (int i=game.getCurrentPlayer(); i<4; i++)
			{
				
				game.getPlayers().get(i).playTurn(game.getPlayers().get(game.nextPlayer()));
				game.nextPlayerTurn();
				updateHands();
			}
		}
		
		pickPhase();
	}
	
	
	/**
	 * We do a complete turn on each of the other players
	 * It's followed by the Pick Phase
	 */
	public void playTurn()
	{
		for (int i=1; i<4; i++)
		{
			if (game.getPlayers().get(i).getCardCount()!=0) 
				game.getPlayers().get(i).playTurn(game.getPlayers().get(game.nextPlayer()));
			updateHands();
			game.nextPlayerTurn();
		}
		pickPhase();
	}
	
	
	/**
	 * This is when the player pick a card from the player on his left
	 * It's followed by the Pair Phase
	 */
	public void pickPhase()
	{
		panelStats.removeAll();
		if (!game.isFinished()) 
		{
			pickPhase = true;
			JLabel info = new JLabel("Select a card from the next player ! (Clockwise)");
			info.setFont(new Font("Arial", Font.PLAIN, 20));
			info.setForeground(Color.yellow);
			
			panelStats.add(info);
			panel.revalidate();
			panel.repaint();			
		}
		else if (game.getPlayers().get(0).hasFinished())
		{
			JLabel info = new JLabel("Congratulations !!! YOU WON !!!");
			info.setFont(new Font("Arial", Font.PLAIN, 20));
			info.setForeground(Color.yellow);
			updateHands();
			panelStats.add(info);
			panel.revalidate();
			panel.repaint();
		} else {
			JLabel info = new JLabel("GAME OVER !");
			info.setFont(new Font("Arial", Font.PLAIN, 20));
			info.setForeground(Color.yellow);
			updateHands();
			panelStats.add(info);
			panel.revalidate();
			panel.repaint();
		}
	}
	
	
	
	
	
	private void updateHands() {
	    updateHandPanel(panelSouthPlayerHand, game.getPlayers().get(0).getHand().getAllCards(), "SOUTH");
	    updateHandPanel(panelWestPlayerHand, game.getPlayers().get(1).getHand().getAllCards(), "WEST");
	    updateHandPanel(panelNorthPlayerHand, game.getPlayers().get(2).getHand().getAllCards(), "NORTH");
	    updateHandPanel(panelEastPlayerHand, game.getPlayers().get(3).getHand().getAllCards(), "EAST");
	    panel.revalidate();
	    panel.repaint();
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
	
	private void preloadCardImages() {
	    for (Card card : (new Deck()).getAllCards()) {
	        String key = card.toString();
	        imageCache.put(key, ResizeImage.sizedImage(CardImage.getImageOfCard(card), cardWidth, cardHeight));
	    }
	    // Add the back
	    imageCache.put("BACK", ResizeImage.sizedImage("/resources/cards/CARDBACK.png", cardWidth, cardHeight));
	}
	
	
	private void updateHandPanel(JPanel panel, List<Card> hand, String direction) {
	    panel.removeAll();

	    boolean isSouth = direction.equals("SOUTH");

	    JLayeredPane layeredPane = new JLayeredPane();
	    layeredPane.setLayout(null);
	    if (direction.equals("NORTH") || direction.equals("SOUTH"))
	    {
	    	layeredPane.setPreferredSize(new Dimension(X_SIZE/2, Y_SIZE/4));	    	
	    }
	    else
	    {
	    	layeredPane.setPreferredSize(new Dimension(X_SIZE/4, Y_SIZE/2));
	    }

	    for (int i = 0; i < hand.size(); i++) 
	    {
	        Card card = hand.get(i);
	        boolean faceUp = isSouth || game.isFinished();

	        String key = faceUp ? card.toString() : "BACK";
	        ImageIcon icon = imageCache.get(key);
	        
        	JLabel label = new JLabel(icon);
	        
	        if (isSouth)
	        {
	        	int x = i * offset + ((X_SIZE)/4-((hand.size()-1)*offset+cardWidth)/2);
	        	int y = selectedCardsIndices.contains(i) ? 20 : 50; // get an offset if card selectionned
	        	
	        	label.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
	        	
	        	final int cardIndex = i;
        		label.addMouseListener(new MouseAdapter() {
        			@Override
        			public void mouseClicked(MouseEvent e) {
        				if (pairPhase) toggleCardSelection(cardIndex);
        			}
        		});
        		layeredPane.add(label,Integer.valueOf(i));
	        }
	        else if (direction.equals("WEST") || direction.equals("EAST"))
	        {
	        	label = new JLabel(ImageUtil.rotateImageIcon(icon, 90));
	        	int x = direction.equals("WEST") ? 10 : X_SIZE/4 -10 - cardHeight;
	        	int y = i * offset + ((Y_SIZE)/4-((hand.size()-1)*offset+cardWidth)/2);
	        	
	        	label.setBounds(x, y,cardHeight, cardWidth);
	        	if (game.nextPlayer(0) == 1)
	        	{
	        		final int cardIndex = i;
	        		label.addMouseListener(new MouseAdapter() {
	        			@Override
	        			public void mouseClicked(MouseEvent e) {
	        				if (pickPhase) {
	        					game.getPlayers().get(0)
	        						.pickCard(game.getPlayers().get(1), cardIndex);
	        					pickPhase = false;
	        					updateHands();
	        					pairPhase();
	        				}
	        			}
	        		});
	        		layeredPane.add(label,Integer.valueOf(i));
	        	}
	        	else if (game.nextPlayer(0) == 3)
	        	{
	        		final int cardIndex = i;
	        		label.addMouseListener(new MouseAdapter() {
	        			@Override
	        			public void mouseClicked(MouseEvent e) {
	        				if (pickPhase) {
	        					game.getPlayers().get(0)
	        						.pickCard(game.getPlayers().get(3), cardIndex);
	        					pickPhase = false;
	        					updateHands();
	        					pairPhase();
	        				}
	        			}
	        		});
	        		layeredPane.add(label);
	        	}
	        }
	        else if (direction.equals("NORTH"))
	        {
	        	int x = i * offset + ((X_SIZE)/4-((hand.size()-1)*offset+cardWidth)/2);
	        	int y = Y_SIZE/4 -60 - cardHeight;
	        	
	        	label.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
	        	
	        	if (game.nextPlayer(0) == 2)
	        	{
	        		final int cardIndex = i;
	        		label.addMouseListener(new MouseAdapter() {
	        			@Override
	        			public void mouseClicked(MouseEvent e) {
	        				if (pickPhase) {
	        					game.getPlayers().get(0)
	        						.pickCard(game.getPlayers().get(2), cardIndex);
	        					pickPhase = false;
	        					updateHands();
	        					pairPhase();
	        				}
	        			}
	        		});
	        	}
	        	layeredPane.add(label);
	        }

	        
	    }

	    panel.setLayout(new BorderLayout());
	    
	    
	    JPanel cardCountPanel = new JPanel((direction.equals("NORTH")||direction.equals("SOUTH"))?new FlowLayout():new BorderLayout());
	    cardCountPanel.setOpaque(false);
	    JLabel cardCountLabel = new JLabel(hand.size()+" cards");
	    cardCountLabel.setFont(new Font("Arial", Font.PLAIN, 20));
	    cardCountLabel.setForeground(Color.YELLOW);
		cardCountPanel.add(cardCountLabel, BorderLayout.CENTER);
		
		
	    if (direction.equals("SOUTH"))
	    {
	    	panel.add(layeredPane, BorderLayout.SOUTH);
			panel.add(cardCountPanel, BorderLayout.NORTH);

	    }
	    else if (direction.equals("WEST"))
	    {
	    	panel.add(layeredPane, BorderLayout.WEST);
			panel.add(cardCountPanel, BorderLayout.EAST);

	    }
	    else if (direction.equals("NORTH"))
	    {
	    	panel.add(layeredPane, BorderLayout.NORTH);
			panel.add(cardCountPanel, BorderLayout.SOUTH);

	    }
	    else if (direction.equals("EAST"))
	    {
	    	panel.add(layeredPane, BorderLayout.EAST);
			panel.add(cardCountPanel, BorderLayout.WEST);

	    }
	    
		


	    
	    
	    panelPairs.revalidate();
		panelPairs.repaint();
	}
	
	private void toggleCardSelection(int index) {
	    if (selectedCardsIndices.contains(index)) { // remove card if clicked a second time
	        selectedCardsIndices.remove(Integer.valueOf(index));
	    } else if (selectedCardsIndices.size()<2) // add card
	    {
	    	selectedCardsIndices.add(index);	    	
	    }
	    
	    if (selectedCardsIndices.size() == 2) {
            int idx1 = selectedCardsIndices.get(0);
            int idx2 = selectedCardsIndices.get(1);
            Card c1 = game.getPlayers().get(0).getHand().getCard(idx1); // get(0) since we only work on player's 1 hand
            Card c2 = game.getPlayers().get(0).getHand().getCard(idx2);

            if (c1.formPair(c2)) {
                game.getPlayers().get(0).getHand().deletePair(c1, c2);
                selectedCardsIndices.clear();
                printDiscardedPairs(new Card[] {c1, c2});
            } else {
                selectedCardsIndices.clear();
            }
        }
	    
	    updateHandPanel(panelSouthPlayerHand, game.getPlayers().get(0).getHand().getAllCards(), "SOUTH");
	}
}