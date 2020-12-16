import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;

public class Blackjack implements ActionListener, ChangeListener  {
	
	// Layout manager
	private GridBagConstraints c = new GridBagConstraints();
	
	// Images for the back of the card. 
	private ImageIcon backOfCard = new ImageIcon("fullsizecards\\back.png");
	private ImageIcon miniBackOfCard = new ImageIcon("smallsizecards\\MBOC.png");
	
	// JFRAMES
	private static JFrame mainFrame;
	
	// JLABELS
	private static JLabel playerCard;
	private static JLabel playerCard2;
	private static JLabel dealerCard1;
	private static JLabel dealerCard2;
	private static JLabel displayPlayersChipAmount;
	private static JLabel playerHitCard0;
	private static JLabel playerHitCard1;
	private static JLabel playerHitCard2;
	private static JLabel playerHitCard3;
	private static JLabel playerHitCard4;
	private static JLabel playerHitCard5;
	private static JLabel playerHitCard6;
	private static JLabel dealerHitCard0;
	private static JLabel dealerHitCard1;
	private static JLabel dealerHitCard2;
	private static JLabel dealerHitCard3;
	private static JLabel dealerHitCard4;
	private static JLabel dealerHitCard5;
	
	// JPANELS
	private static JPanel playerCards;
	
	// JSLIDER
	private static JSlider betSizeSlider;

	// JBUTTONS
	private static JButton hitButton;
	private static JButton stayButton;
	private static JButton doubleDown;
	private static JButton splitButton;
	private static JButton betButton;

	// Deck object
	private static Deck deckClass;
	
	// Stack which resembles a deck, where pop() is like taking a card off the top
	private static Stack<Card> theDeck;
	private static boolean cardsDealt = false;
	
	// Variables for player.
	private static String imageNameForP1C;
	private static String imageNameForP2C;
	private static String playersCard1NameFirstLetter;
	private static String playersCard2NameFirstLetter;
	
	private static Card players1stCard;
	private static Card players2ndCard;
	
	private static int hitCardLabelPos = 0; // Position for the JLabels which hold the hit cards. This keeps track so cards don't overlap.
	private static int playersHandValue = 0; // This variable holds the total of both players cards (A + J = 21)
	private static int playersChipCount = 1000;
	private static int betSize = 0; // Players bet size.
	private static int timesBusted = 0;
	private static boolean bettingIsLocked = false;
	private static boolean endOfPlayersTurn = false;
	private static int acesInHitCards = 0;
	
	private static ArrayList<String> playersHitCards = new ArrayList<String>();
	private static ArrayList<String> dealersHitCards = new ArrayList<String>();
	private static Queue<Hand> playersHands = new LinkedList<>();
	private static ArrayList<Integer> playersHandValues = new ArrayList<Integer>();
	
	private static Hand currentHand;
	
	
	// Variables for dealer
	private static String imageNameForDealers1stCard;
	private static String imageNameForDealers2ndCard;
	private static String dealersCard1NameFirstLetter;
	private static String dealersCard2NameFirstLetter;

	private static Card dealers1stCard;
	private static Card dealers2ndCard;
	
	private static int hitCardLabelPosDealer = 0;
	private static int dealersHandValue;
	private static int timesDealerBusted = 0;
	private static boolean dealerBusted = false;
	private static boolean dealersHandWins = false;
	private static boolean dealerPushes = false;
	private static int acesInDealersHitCards = 0;
	private static int dealersAcessubtracted = 0;
	private static boolean doubledDown = false;
	private static boolean split = false;

	public Blackjack() {

		deckClass = new Deck(); // Create instance of Deck class
		
		theDeck = deckClass.getDeck(); // Get deck from Deck class. Deck Object. 

		// JFRAME TO HOLD THE GUI
		mainFrame = new JFrame();
		mainFrame.setTitle("Blackjack");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// MAIN BACKGROUND PANEL
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(53, 101, 77));
		mainPanel.setLayout(new GridBagLayout());

		// Label to display the back of the dealers first card
		dealerCard1 = new JLabel();
		dealerCard1.setPreferredSize(new Dimension(100, 130));
		dealerCard1.setIcon(backOfCard);

		// Label to display the dealers the second card (show card)
		dealerCard2 = new JLabel();
		dealerCard2.setPreferredSize(new Dimension(100, 130));

		// Panel to hold dealers cards
		JPanel dealerCards = new JPanel();
		dealerCards.add(dealerCard1);
		dealerCards.add(dealerCard2);
		c.gridx = 0;
		c.gridy = 0;
		mainPanel.add(dealerCards, c);
		
		// JPANEL TO HOLD THE DEALERS HIT CARDS
		JPanel dealerHitCards = new JPanel();
		c.gridx = 0;
		c.gridy = 1;
		dealerHitCard0 = new JLabel();
		dealerHitCard0.setIcon(miniBackOfCard);
		dealerHitCard1= new JLabel();
		dealerHitCard1.setIcon(miniBackOfCard);
		dealerHitCard2= new JLabel();
		dealerHitCard2.setIcon(miniBackOfCard);
		dealerHitCard3= new JLabel();
		dealerHitCard3.setIcon(miniBackOfCard);
		dealerHitCard4= new JLabel();
		dealerHitCard4.setIcon(miniBackOfCard);
		dealerHitCard5= new JLabel();
		dealerHitCard5.setIcon(miniBackOfCard);
		
		dealerHitCards.add(dealerHitCard0);
		dealerHitCards.add(dealerHitCard1);
		dealerHitCards.add(dealerHitCard2);
		dealerHitCards.add(dealerHitCard3);
		dealerHitCards.add(dealerHitCard4);
		dealerHitCards.add(dealerHitCard5);
		mainPanel.add(dealerHitCards, c);
		
		// Label for players 1st Card
		playerCard = new JLabel();
		playerCard.setPreferredSize(new Dimension(100, 130));

		// Label for players 2nd Card
		playerCard2 = new JLabel();
		playerCard2.setPreferredSize(new Dimension(100, 130));

		// Panel to hold both players cards
		playerCards = new JPanel();
		playerCards.add(playerCard);
		playerCards.add(playerCard2);
		c.gridx = 3;
		c.gridy = 4;
		c.insets = new Insets(100, 0, 0, 0);
		mainPanel.add(playerCards, c);

		// Options Panel to hold (in order):
		// 
		// Chip count, (players hit cards) *topPanel*
		// *betSizingSlider*
		// *buttonPanel*
		
		JPanel optionsPanel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(optionsPanel, BoxLayout.Y_AXIS);
		optionsPanel.setLayout(boxlayout);
		
		
		// TOP PANEL TO SHOW PLAYERS CHIP COUNT AND HIT CARDS
		JPanel topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(200, 30));
	
		displayPlayersChipAmount = new JLabel("Chips: " + playersChipCount);
		
		playerHitCard0 = new JLabel();
		playerHitCard1 = new JLabel();
		playerHitCard2 = new JLabel();
		playerHitCard3 = new JLabel();
		playerHitCard4 = new JLabel();
		playerHitCard5 = new JLabel();
		playerHitCard6 = new JLabel();
		
		topPanel.add(displayPlayersChipAmount);
		topPanel.add(playerHitCard0);
		topPanel.add(playerHitCard1);
		topPanel.add(playerHitCard2);
		topPanel.add(playerHitCard3);
		topPanel.add(playerHitCard4);
		topPanel.add(playerHitCard5);
		topPanel.add(playerHitCard6);
		
		
		// PANEL TO HOLD THE BETTING SLIDER AND JLABEL
		JPanel betSizingPanel = new JPanel();
		JLabel betSizeLabel = new JLabel("Bet Size");
		
		
		// SLIDER FOR PLAYER TO CHOOSE BET SIZE, give player x amount
		betSizeSlider = new JSlider(JSlider.HORIZONTAL, 0, playersChipCount, 0);
		betSizeSlider.addChangeListener(this);
		betSizeSlider.setMajorTickSpacing(500);
		//betSizeSlider.setMinorTickSpacing(50);
		betSizeSlider.setPaintTicks(true);
		betSizeSlider.setPaintLabels(true);
		
		// JBUTTON THAT LOCKS IN THE PLAYERS BET
		betButton = new JButton("Bet");
		betButton.addActionListener(this);
		
		betSizingPanel.add(betSizeLabel);
		betSizingPanel.add(betSizeSlider);
		betSizingPanel.add(betButton);

		
		// Button for the player to hit
		hitButton = new JButton();
		hitButton.setText("Hit");
		hitButton.setEnabled(false);
		hitButton.setPreferredSize(new Dimension(70, 30));
		hitButton.addActionListener(this);

		// Button for the player to stay
		stayButton = new JButton("Stay");
		stayButton.setPreferredSize(new Dimension(70, 30));
		stayButton.addActionListener(this);
		stayButton.setEnabled(false);
		stayButton.addActionListener(this);
		

		// Button for player to double down
		doubleDown = new JButton("DD");
		doubleDown.setPreferredSize(new Dimension(70, 30));
		doubleDown.addActionListener(this);
		doubleDown.setEnabled(false);
		doubleDown.addActionListener(this);

		// Button for the player to split
		splitButton = new JButton("Split");
		splitButton.setPreferredSize(new Dimension(70, 30));
		splitButton.addActionListener(this);
		splitButton.setEnabled(false);
		splitButton.addActionListener(this);

		// Panel to hold buttons for player
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.add(hitButton);
		buttonsPanel.add(stayButton);
		buttonsPanel.add(doubleDown);
		buttonsPanel.add(splitButton);

		
		// Add all options to options panel
		optionsPanel.add(topPanel);
		optionsPanel.add(betSizingPanel);
		optionsPanel.add(buttonsPanel);

		// Add optionsPanel to mainPanel
		c.anchor = GridBagConstraints.LAST_LINE_END;
		c.gridx = 4;
		c.gridy = 4;
		mainPanel.add(optionsPanel, c);

		// Add mainPanel to mainFrame
		mainFrame.add(mainPanel);
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);

	}
	
	// Method to reset all of game variables for new round
	public void resetGame() {
		
		ArrayList<String> resetArrayList = new ArrayList<String>();
		
		hitCardLabelPos = 0;
		hitCardLabelPosDealer = 0;
		timesBusted = 0;
		timesDealerBusted = 0;
		acesInHitCards = 0;
		dealersAcessubtracted = 0;
		
		betSizeSlider.setMaximum(playersChipCount);
		betSizeSlider.setMajorTickSpacing((int)(playersChipCount * 0.50));

		
		endOfPlayersTurn = false;
		doubledDown = false;
		bettingIsLocked = false;
		betButton.setEnabled(true);
		hitButton.setEnabled(false);
		stayButton.setEnabled(false);
		doubleDown.setEnabled(false);
		splitButton.setEnabled(false);
		betSizeSlider.setEnabled(true);
		playersHitCards = resetArrayList;
		playersHandValues = new ArrayList<Integer>();
		
		playerHitCard0.setIcon(miniBackOfCard);
		playerHitCard1.setIcon(miniBackOfCard);
		playerHitCard2.setIcon(miniBackOfCard);
		playerHitCard3.setIcon(miniBackOfCard);
		playerHitCard4.setIcon(miniBackOfCard);
		playerHitCard5.setIcon(miniBackOfCard);
		playerHitCard6.setIcon(miniBackOfCard);
		playerCard.setIcon(null);
		playerCard2.setIcon(null);
		
		dealerBusted = false;
		dealersHandWins = false;
		dealerPushes = false;
		dealersHitCards = resetArrayList;
	
		dealerHitCard0.setIcon(miniBackOfCard);
		dealerHitCard1.setIcon(miniBackOfCard);
		dealerHitCard2.setIcon(miniBackOfCard);
		dealerHitCard3.setIcon(miniBackOfCard);
		dealerHitCard4.setIcon(miniBackOfCard);
		dealerHitCard5.setIcon(miniBackOfCard);
		dealerCard1.setIcon(backOfCard);
		dealerCard2.setIcon(null);

		
	}
	
	public void setCardLabel(String person, Card hitCard) {
		
		String cardsFirstInitial = deckClass.cardValuetoString(hitCard); 
		String fileNameforCard = "M" + cardsFirstInitial + hitCard.getSuit().substring(0,1) + ".png";
	
		ImageIcon hitCardImage = new ImageIcon("smallsizecards\\" + fileNameforCard);
		
		
		if (person == "Player") {
			
			if (hitCardLabelPos == 0) {
				
				playerHitCard0.setIcon(hitCardImage);
				hitCardLabelPos++;
				
			}
			else if (hitCardLabelPos == 1) {
				
				playerHitCard1.setIcon(hitCardImage);
				hitCardLabelPos++;
				
			}
			else if (hitCardLabelPos == 2) {
				
				playerHitCard2.setIcon(hitCardImage);
				hitCardLabelPos++;
				
			}
			else if (hitCardLabelPos == 3) {
				
				playerHitCard3.setIcon(hitCardImage);
				hitCardLabelPos++;
				
			}
			else if (hitCardLabelPos == 4) {
				
				playerHitCard4.setIcon(hitCardImage);
				hitCardLabelPos++;
				
			}
			else if (hitCardLabelPos == 5) {
				
				playerHitCard5.setIcon(hitCardImage);
				hitCardLabelPos++;
				
			}
			else if (hitCardLabelPos == 6) {
				
				playerHitCard6.setIcon(hitCardImage);
				hitCardLabelPos++;
				
			}
			
			
		}
		else {
			
			if (hitCardLabelPosDealer == 0) {
				
				dealerHitCard0.setIcon(hitCardImage);
				hitCardLabelPosDealer++;
				
			}
			else if (hitCardLabelPosDealer == 1) {
				
				dealerHitCard1.setIcon(hitCardImage);
				hitCardLabelPosDealer++;
				
			}
			else if (hitCardLabelPosDealer == 2) {
				
				dealerHitCard2.setIcon(hitCardImage);
				hitCardLabelPosDealer++;
				
			}
			else if (hitCardLabelPosDealer == 3) {
				
				dealerHitCard3.setIcon(hitCardImage);
				hitCardLabelPosDealer++;
				
			}
			else if (hitCardLabelPosDealer == 4) {
				
				dealerHitCard4.setIcon(hitCardImage);
				hitCardLabelPosDealer++;
				
			}
			else if (hitCardLabelPosDealer == 5) {
				
				dealerHitCard5.setIcon(hitCardImage);
				hitCardLabelPosDealer++;
				
			}
	
		}

	}
	
	// ActionListener method. 
	public void actionPerformed(ActionEvent e) {

		Object buttonPressed = e.getSource(); // get input source

		if (buttonPressed == hitButton) { // If hit button is pressed 

			doubleDown.setEnabled(false);
			splitButton.setEnabled(false);
			Card hitCard = theDeck.pop();
			
			int cardValue = deckClass.getCardValue(hitCard);
			playersHandValue = playersHandValue + cardValue;
			
			String cardsFirstInitial = deckClass.cardValuetoString(hitCard); // For example, an Ace would be "A" or a 9 would be "9"
			playersHitCards.add(cardsFirstInitial);
		
			// Count each Ace that the player hits
			if (cardsFirstInitial == "A") {
				
				acesInHitCards++; 
				
			}
			
			// Set label to hit card image, depending on what position hitCardLabelPos is at. 
			setCardLabel("Player", hitCard);

		}
		
		else if (buttonPressed == betButton) { // If bet button is pressed 
			
			if (playersChipCount >= betSize && betSize > 0) {
				
				playersChipCount = playersChipCount - betSize;
				bettingIsLocked = true;
				betButton.setEnabled(false);
				
				
			}
			else {
				
				if (betSize == 0) {
					
					System.out.println("You need to bet before you play.");
					
				}
				else {
					
					System.out.println(betSize);
					System.out.println("NOT ENOUGH CHIPS, YOU HAVE " + playersChipCount + "CHIPS.");
					
				}	
				
			}
			
		}
		
		else if (buttonPressed == stayButton) {
			
			if (endOfPlayersTurn == false) {
				
				endOfPlayersTurn = true; // Player is staying
				playersHandValues.add(playersHandValue);

				// Disable all buttons because player hit the stay button
				hitButton.setEnabled(false);
				stayButton.setEnabled(false);
				doubleDown.setEnabled(false);
				splitButton.setEnabled(false);
				
				
			}
		

		}
		
		else if (buttonPressed == doubleDown) {
			
			System.out.println("Betsize:" + betSize);
			
			System.out.println("doubledDown value:" + doubledDown);
			
			
			if (playersChipCount >= betSize && doubledDown == false) {
				
				doubledDown = true;
				playersChipCount = playersChipCount - betSize;
				betSize = betSize * 2;
				System.out.println("You doubled down. Your bet size is now: " + betSize);
				displayPlayersChipAmount.setText("Chips: " + playersChipCount);
				
				doubleDown.setEnabled(false);
				Card hitCard = theDeck.pop();
				
				int cardValue = deckClass.getCardValue(hitCard);
				playersHandValue = playersHandValue + cardValue;
				
				String cardsFirstInitial = deckClass.cardValuetoString(hitCard); // For example, an Ace would be "A" or a 9 would be "9"
				playersHitCards.add(cardsFirstInitial);
			
				// Count each Ace that the player hits
				if (cardsFirstInitial == "A") {
					
					acesInHitCards++; 
					
				}
				
				// Set label to hit card image, depending on what position hitCardLabelPos is at. 
				setCardLabel("Player", hitCard);
	
				hitButton.setEnabled(false);
				stayButton.setEnabled(false);
				doubleDown.setEnabled(false);
				splitButton.setEnabled(false);
		

				
			}
			
			else if (playersChipCount < betSize) {
				
				System.out.println("You cannot double down. You cannot match your first ante of " + betSize + ".");
				
			}

			

		}
		
		else if (buttonPressed == splitButton) {
		
			if (deckClass.cardValuetoString(players1stCard).equals(deckClass.cardValuetoString(players2ndCard)) && split == false) {

				split = true;
				Card firstHandNewCard = theDeck.pop();
				Hand splitHand1 = new Hand(players1stCard, firstHandNewCard);
	
				Card SecondHandNewCard = theDeck.pop();
				Hand splitHand2 = new Hand(players2ndCard, SecondHandNewCard);
				
				playersHands.add(splitHand1);
				playersHands.add(splitHand2);
				
				splitButton.setEnabled(false);
				
					
			}
			
		}
		

	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		
		 JSlider source = (JSlider)e.getSource();
		
		 if (source.getValueIsAdjusting() && bettingIsLocked == false) {
			 
			 betSize = (int)source.getValue();
			 
		 }
		 else {
			 
			 if (betSize == 0) {
				 
				 System.out.println("Make a bet to continue.");
				 
				 
			 }
			 
		 }

	}
	
	
	public void dealerHitMethod(Card card1, Card card2) {
		
		while (dealerBusted == false && dealersHandWins == false && dealerPushes == false) {
			

			// Edge case: dealer has two Aces
			if (deckClass.cardValuetoString(card1) == "A" && deckClass.cardValuetoString(card2) == "A" && timesDealerBusted < 2) {
				
				if (timesDealerBusted == 0) {
					
					// AA was set to 12 in the main method. One Ace has already been set to 1.
					
					while (dealersHandValue <= playersHandValue && dealersHandValue < 17) {
						
						Card hitCard1 = theDeck.pop();
						
						String cardsFirstInitial = deckClass.cardValuetoString(hitCard1); // For example, an Ace would be "A" or a 9 would be "9"
						dealersHitCards.add(cardsFirstInitial);
						
						if (cardsFirstInitial == "A") {
							
							acesInDealersHitCards++;
							
						}
						
						setCardLabel("Dealer", hitCard1);
						dealersHandValue = dealersHandValue + deckClass.getCardValue(hitCard1);
						
						if (dealersHandValue > 21) {
							
							if (dealersHitCards.contains("A") && (dealersAcessubtracted < acesInDealersHitCards)) {
								
								dealersHandValue = dealersHandValue - 10;
								dealersAcessubtracted++;
				
							}
							else {
								
								timesDealerBusted++;
								
							}
	
							
						}
						
						else if (dealersHandValue > playersHandValue) {

							dealersHandWins = true;
							break;
							
						}
						
						else if (dealersHandValue == playersHandValue) {
							
							// if dealersHandValue < 17, then keep hitting 
							// else 
							if (dealersHandValue >= 17) {
								
								timesDealerBusted++;
								
							}
							
						}
						
						// Conditional for when the dealer has more than 17 inclusive
						// and their hand is less than the players.
						else if (dealersHandValue >= 17) { 
														   
							timesDealerBusted++;
				
						}
						
						
					}
					
					
				}
				else if (timesDealerBusted == 1) {
					
					// set both Aces to 1
					dealersHandValue = dealersHandValue - 10;
					
					while (dealersHandValue <= playersHandValue && dealersHandValue < 17) {
						
						Card hitCard1 = theDeck.pop();
						String cardsFirstInitial = deckClass.cardValuetoString(hitCard1); // For example, an Ace would be "A" or a 9 would be "9"
						dealersHitCards.add(cardsFirstInitial);
			
						if (cardsFirstInitial == "A") {
							
							acesInDealersHitCards++;
							
						}
	
						setCardLabel("Dealer", hitCard1);
						dealersHandValue = dealersHandValue + deckClass.getCardValue(hitCard1);
						
						if (dealersHandValue > 21) {
							
							if (dealersHitCards.contains("A") && (dealersAcessubtracted < acesInDealersHitCards)) {
								
								dealersHandValue = dealersHandValue - 10;
								dealersAcessubtracted++;
				
							}
							else {
								
								dealerBusted = true;
								
							}
							
						}
				
						else if (dealersHandValue > playersHandValue) {
						
							dealersHandWins = true;
							break;
								
			
						}
						
						else if (dealersHandValue == playersHandValue) {
							
							dealerPushes = true;
							break;
							
						}
			
						else if (dealersHandValue >= 17) {
							
							dealerPushes = true; // Not true, only being used to break the loop. 
												 // This is for when both aces in the hand AA have 
												 // been turned into ones and the dealer cannot hit 
												 // anymore. 	
						}
			
					}
		
				}
		
			}
			
			//
			// Edge case: dealer has at least one Ace
			//
			
			else if (deckClass.cardValuetoString(card1) == "A" || deckClass.cardValuetoString(card2) == "A") {
				
				if (timesDealerBusted == 0) {
					
					if (dealersHandValue >= 17) {
						
						if (dealersHandValue == playersHandValue) {
							
							dealerPushes = true;
							
						}
						else {
							
							timesDealerBusted++;
							
						}
						
					}
					else {
						
						while (dealersHandValue < playersHandValue && dealersHandValue < 21) {
							
							Card hitCard1 = theDeck.pop();
							String cardsFirstInitial = deckClass.cardValuetoString(hitCard1); // For example, an Ace would be "A" or a 9 would be "9"
							dealersHitCards.add(cardsFirstInitial);
				
							if (cardsFirstInitial == "A") {
								
								acesInDealersHitCards++;
								
							}
							
							setCardLabel("Dealer", hitCard1);
							dealersHandValue = dealersHandValue + deckClass.getCardValue(hitCard1);
							
							if (dealersHandValue > 21) {
								
								if (dealersHitCards.contains("A") && (dealersAcessubtracted < acesInDealersHitCards)) {
									
									dealersHandValue = dealersHandValue - 10;
									dealersAcessubtracted++;
					
								}
								else {
									
									timesDealerBusted++;
									
								}
						
							}
							else if (dealersHandValue > playersHandValue) {
						
								dealersHandWins = true;
						
							}
							
							else if (dealersHandValue == playersHandValue) {
								
								// if dealersHandValue < 17, then keep hitting 
								// else 
								if (dealersHandValue >= 17) {
									
									timesDealerBusted++;
									break;
									
								}
								else {

									continue;
									
								}
								
							}
							
							else if (dealersHandValue >= 17) {
								
								timesDealerBusted++;
								break;
		
							}
							
						}
						
					}
					
				}
				
				else if (timesDealerBusted == 1) {
					
					dealersHandValue = dealersHandValue - 10;
					
					while (dealersHandValue <= playersHandValue) {
						
						Card hitCard1 = theDeck.pop();
						String cardsFirstInitial = deckClass.cardValuetoString(hitCard1); // For example, an Ace would be "A" or a 9 would be "9"
						dealersHitCards.add(cardsFirstInitial);
			
						if (cardsFirstInitial == "A") {
							
							acesInDealersHitCards++;
							
						}
						
						setCardLabel("Dealer", hitCard1);
						dealersHandValue = dealersHandValue + deckClass.getCardValue(hitCard1);
						
						if (dealersHandValue > 21) {
							
							if (dealersHitCards.contains("A") && (dealersAcessubtracted < acesInDealersHitCards)) {
								
								dealersHandValue = dealersHandValue - 10;
								dealersAcessubtracted++;
				
							}
							else {
								
								dealerBusted = true;
								
							}
					
						}
						
						else if (dealersHandValue == playersHandValue) {

							// if dealersHandValue < 17, then keep hitting 
							// else 
							if (dealersHandValue >= 17) {
								
								dealerPushes = true;
								
							}
								
						}
						
						else if (dealersHandValue >= 17) {
							
							dealerPushes = true; // dealer cannot hit anymore because they are over 17
							break;
	
						}
							
					}
			
				}

			}
			else {
				
				if (dealersHandValue >= 17) {
					
					dealerPushes = true;
					
				}
				else {
					
					while (dealersHandValue <= playersHandValue && dealersHandValue < 17) {
						
						Card hitCard1 = theDeck.pop();
						
						
						String cardsFirstInitial = deckClass.cardValuetoString(hitCard1); // For example, an Ace would be "A" or a 9 would be "9"
						dealersHitCards.add(cardsFirstInitial);
			
						if (cardsFirstInitial == "A") {
							
							acesInDealersHitCards++;
							
						}
						
						setCardLabel("Dealer", hitCard1);
						dealersHandValue = dealersHandValue + deckClass.getCardValue(hitCard1);
						
						if (dealersHandValue > 21) {
							
							if (dealersHitCards.contains("A") && (dealersAcessubtracted < acesInDealersHitCards)) {
								
								System.out.println(dealersAcessubtracted);
								System.out.println(acesInDealersHitCards);

								
								dealersHandValue = dealersHandValue - 10;
								dealersAcessubtracted++;
				
							}
							else {
								
								dealerBusted = true;	
								
							}
					
						}
						// add conditional for push
						else if (dealersHandValue > playersHandValue && dealersHandValue <= 21) {
			
							dealersHandWins = true;
							
						}			
						else if (dealersHandValue == playersHandValue) {
			
							if (dealersHandValue >= 17) {
								
								dealerPushes = true;
								
							}
							
						}
						else if (dealersHandValue >= 17 && dealersHandValue < 21 && dealersHandValue < playersHandValue) {
							
							dealerPushes = true; // dealer cannot hit anymore because they are over 17

						}
		
						
					}
					
					
				}

				
			}
			
			try {
				  
				Thread.sleep(2500); 
			} 
			catch (InterruptedException e) {
			  
				e.printStackTrace(); 
			 
			}
			

		}
		
	}
	
	public void checkIfHandWins() {
		
		for (int i = 0; i < playersHandValues.size(); i++) {
			
			boolean bust = false;
			
			System.out.println("");
			System.out.println("");
			System.out.println("Hand #" + i);
			
			int currentHandValue = playersHandValues.get(i);
			
			// Check if player has went over 21
			if (currentHandValue > 21) {
				
				//JOptionPane.showMessageDialog(mainFrame,"You Bust! Hand value: " + playersHandValue);
				System.out.println("BUST");
				bust = true;
				displayPlayersChipAmount.setText("Chips: " + playersChipCount);
				cardsDealt = false;
				
				
			}
			
			// If not, then run the dealerHitMethod which hits for the dealer
			else if (dealersHandValue > currentHandValue) {
				
				//JOptionPane.showMessageDialog(mainFrame, dealersHandValue + " beats your " + playersHandValue);
				cardsDealt = false;
				

			}
			else {
				
				dealerHitMethod(dealers1stCard, dealers2ndCard);
				
				
			}
			
			System.out.println("Dealers hand value: " + dealersHandValue);
			System.out.println("Players hand value: " + currentHandValue);
		
			// Dealer busts, player wins
			if (dealersHandValue > 21 && bust == false) {
				
				// Players Wins
				playersChipCount = playersChipCount + (int)(betSize * 1.5);
				displayPlayersChipAmount.setText("Chips: " + playersChipCount);
				//JOptionPane.showMessageDialog(mainFrame,"The Dealer busts, You Win! New Round will begin shortly.");
				System.out.println("Dealer busts, you win this hand!");
				cardsDealt = false;
				
			}
			
			// Dealer and player have same hand, push.
			else if (dealersHandValue == playersHandValue && (bust == false)) {
				
				//JOptionPane.showMessageDialog(mainFrame,"Push!");
				playersChipCount = playersChipCount + betSize;
				displayPlayersChipAmount.setText("Chips: " + playersChipCount);
				System.out.println("PUSH");
				cardsDealt = false;
				
			}
			
			// Dealer has a higher hand than the player, dealer wins. 
			else if (dealersHandValue > playersHandValue && (bust == false)) {
				
				// The house wins, players loses
				//JOptionPane.showMessageDialog(mainFrame,"The House wins. Better luck next round!");
				displayPlayersChipAmount.setText("Chips: " + playersChipCount);
				System.out.println("Dealers hand is better than your current hand.");
				cardsDealt = false;
				
			}
			
			// Player has a higher hand than the dealer, player wins.
			else if (dealersHandValue < playersHandValue && (bust == false)) {
				
				// The player wins, gets double his money and continues playing
				playersChipCount = playersChipCount + (int)(betSize * 1.5);
				displayPlayersChipAmount.setText("Chips: " + playersChipCount);
				//JOptionPane.showMessageDialog(mainFrame,"Your hand Wins!");
				System.out.println("Your current hand wins!");
				cardsDealt = false;

				
			}
			
		}
		
	}
	
	public void newHand(){	
		
		playersHandValue = 0;
		acesInHitCards = 0;
		hitCardLabelPos = 0;
		timesBusted = 0;
		doubledDown = false;
		betSizeSlider.setEnabled(false);
		
		playersHitCards = new ArrayList<String>();
		
		betButton.setEnabled(false);
		hitButton.setEnabled(true);
		stayButton.setEnabled(true);
		doubleDown.setEnabled(true);
		splitButton.setEnabled(true);
		
		playerHitCard0.setIcon(miniBackOfCard);
		playerHitCard1.setIcon(miniBackOfCard);
		playerHitCard2.setIcon(miniBackOfCard);
		playerHitCard3.setIcon(miniBackOfCard);
		playerHitCard4.setIcon(miniBackOfCard);
		playerHitCard5.setIcon(miniBackOfCard);
		playerHitCard6.setIcon(miniBackOfCard);
		
	}
	

	public static void main(String[] args) {

		Blackjack game = new Blackjack(); // Create new game
		
		boolean gameIsDone = false; // Set loop condition
		
		while (!gameIsDone) {
			
			// Method to reset all fields and booleans at the beginning of each round
			game.resetGame();
	
			// if deck gets below 25 cards, reshuffle it
			if (theDeck.size() < 25) {
	
				theDeck = new Deck().getDeck();
				
			}
			
			if (cardsDealt == false) {
				
				// Deal one card to player
				players1stCard = theDeck.pop();

				// Deal one card to dealer
				dealers1stCard = theDeck.pop();

				// Deal one card to player
				players2ndCard = theDeck.pop();

				// Deal one card to dealer
				dealers2ndCard = theDeck.pop(); // SHOW THIS CARD FACE UP
				
				// Create Hand object with players Cards
				Hand playerInitialHand = new Hand(players1stCard, players2ndCard);
				playersHands.add(playerInitialHand);
				
				// CARDS HAVE BEEN DEALT
				cardsDealt = true;
				
				// Initialize variables to hold file names for the images of the dealers cards
				imageNameForDealers1stCard = "";
				imageNameForDealers2ndCard = "";
				
				// GET IMAGE NAME FOR DEALERS SHOW CARD
				dealersCard1NameFirstLetter = deckClass.cardValuetoString(dealers1stCard);
				imageNameForDealers1stCard = dealersCard1NameFirstLetter + dealers1stCard.getSuit().substring(0, 1) + ".png";
				
				dealersCard2NameFirstLetter = deckClass.cardValuetoString(dealers2ndCard);
				imageNameForDealers2ndCard = dealersCard2NameFirstLetter + dealers2ndCard.getSuit().substring(0, 1) + ".png";
				
				
			}
		
			// Slows down the loop so it has time to register events.
			try {
				  
				Thread.sleep(100); 
			} 
			catch (InterruptedException e) {
			  
				e.printStackTrace(); 
			 
			}
			

			// IF PLAYER IS FINISHED BETTING, START GAME
			if (bettingIsLocked) {
				
				displayPlayersChipAmount.setText("Chips: " + playersChipCount);
				
				// Disable betting, enable access to options like: hitting, staying, doubling down and splitting 
				betSizeSlider.setEnabled(false);
				hitButton.setEnabled(true);
				stayButton.setEnabled(true);
				doubleDown.setEnabled(true);
				splitButton.setEnabled(true);
				
				// SET DEALERS SHOW CARD IMAGE AND BOTH PLAYERS CARD IMAGES
				ImageIcon dealerShowCard = new ImageIcon("fullsizecards\\" + imageNameForDealers2ndCard);
				dealerCard2.setIcon(dealerShowCard);

				// Get initial values of the Dealers hand. 
				dealersHandValue = deckClass.getCardValue(dealers1stCard) + deckClass.getCardValue(dealers2ndCard);
				
				if (dealersHandValue == 22) {
					
					dealersHandValue = 12;
					
				}
				
				
				// MOVE INTO HIT/STAY loop 
				
				Iterator iterator = playersHands.iterator();

				while (iterator.hasNext()) {
					
					game.newHand();

					// Dequeue Hand from Hands
					currentHand = playersHands.poll();
					
					players1stCard = currentHand.getFirstCard();
					players2ndCard = currentHand.getSecondCard();
					
					playersHandValue = deckClass.getCardValue(players1stCard) + deckClass.getCardValue(players2ndCard);


					//Create Images for Cards
					imageNameForP1C = "";
					imageNameForP2C = "";
					
					playersCard1NameFirstLetter = deckClass.cardValuetoString(players1stCard);
					imageNameForP1C = playersCard1NameFirstLetter + players1stCard.getSuit().substring(0, 1) + ".png";

					playersCard2NameFirstLetter = deckClass.cardValuetoString(players2ndCard);
					imageNameForP2C = playersCard2NameFirstLetter + players2ndCard.getSuit().substring(0, 1) + ".png";
					
					ImageIcon playerShowCard1 = new ImageIcon("fullsizecards\\" + imageNameForP1C);
					playerCard.setIcon(playerShowCard1);
					ImageIcon playerShowCard2 = new ImageIcon("fullsizecards\\" + imageNameForP2C);
					playerCard2.setIcon(playerShowCard2);
				
	
					int acesSubtracted = 0; // Counter to keep track of how many aces in the players hit cards have been converted to 1
					endOfPlayersTurn = false;
					
					while (endOfPlayersTurn == false) {
						
						if (split == true) {
							
							endOfPlayersTurn = true;
							split = false;
	
						}

						// If player busts, check if they actually did.
						if (playersHandValue > 21) {

							// if they have two Aces
							if (playersCard1NameFirstLetter == "A" && playersCard2NameFirstLetter == "A" && timesBusted < 2) {
								
								// Convert 1 Ace to a 1
								
								if (timesBusted == 0) {
									
									playersHandValue = 12;
									timesBusted++;
									
								}
								// Convert both to a 1
								else if (timesBusted == 1){
									
									playersHandValue = playersHandValue - 10;
									timesBusted++;
									
								}
					
				
							}
							else if ((playersCard1NameFirstLetter == "A" || playersCard2NameFirstLetter == "A") && timesBusted < 1) {
								
								
								if (timesBusted == 0) {
									
									playersHandValue = playersHandValue - 10;
									timesBusted++;
									
								}
								
							}
							
							// If player busts and has an Ace in hit cards, then convert that Ace to a 1. 
							else if (playersHitCards.contains("A") && (acesSubtracted < acesInHitCards)) {
								
								playersHandValue = playersHandValue - 10;
								acesSubtracted++;
								
							}
							
							// If the player has no Aces they can convert to 1 either in their hand or in their hit cards and their 
							// hand value is over 21, then they busts.
							else if (playersHandValue > 21) {
								
								endOfPlayersTurn = true;
								playersHandValues.add(playersHandValue);
			
							}
						

						} 
						
						// If player hits 21, move out of loop and let dealer hit.
						else if (playersHandValue == 21) {

							endOfPlayersTurn = true;
							playersHandValues.add(playersHandValue);

						}
						else if (doubledDown == true) {
							
							endOfPlayersTurn = true;
							playersHandValues.add(playersHandValue);
							
							
						}

						try {
							  
							Thread.sleep(100); 
						} 
						catch (InterruptedException e) {
						  
							e.printStackTrace(); 
						 
						}

						 
					}
		
					
				}
				// End turn for player
			
				// Display dealers other card
				ImageIcon dealersHiddenCard = new ImageIcon("fullsizecards\\" + imageNameForDealers1stCard);
				dealerCard1.setIcon(dealersHiddenCard);
				
				game.checkIfHandWins();

				
			}
			
			// IF PLAYERS AMOUNT OF MONEY IS EQUAL TO 0, GAME IS OVER
			if (playersChipCount == 0) {

				gameIsDone = true; // ends game loop
				
				mainFrame.dispose(); // deletes frame

			}
			
		}

	}

}

	
	
	





