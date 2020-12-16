import java.util.ArrayList;
import java.util.Stack;

// The Deck object starts off by creating all of cards and then
// inserts them into an unshuffled ArrayList. The shuffling 
// process then starts and the final result is a Stack with 
// all of the shuffled cards. 

public class Deck {

	// 2's
	private Card twoOfHearts = new Card("Hearts", 2);
	private Card twoOfDiamonds = new Card("Diamonds", 2);
	private Card twoOfSpades = new Card("Spades", 2);
	private Card twoOfClubs = new Card("Clubs", 2);
	
	// 3's
	private Card threeOfHearts = new Card("Hearts", 3);
	private Card threeOfDiamonds = new Card("Diamonds", 3);
	private Card threeOfSpades = new Card("Spades", 3);
	private Card threeOfClubs = new Card("Clubs", 3);
	
	// 4's
	private Card fourOfHearts = new Card("Hearts", 4);
	private Card fourOfDiamonds = new Card("Diamonds", 4);
	private Card fourOfSpades = new Card("Spades", 4);
	private Card fourOfClubs = new Card("Clubs", 4);
	
	// 5's
	private Card fiveOfHearts = new Card("Hearts", 5);
	private Card fiveOfDiamonds = new Card("Diamonds", 5);
	private Card fiveOfSpades = new Card("Spades", 5);
	private Card fiveOfClubs = new Card("Clubs", 5);
	
	// 6's
	private Card sixOfHearts = new Card("Hearts", 6);
	private Card sixOfDiamonds = new Card("Diamonds", 6);
	private Card sixOfSpades = new Card("Spades", 6);
	private Card sixOfClubs = new Card("Clubs", 6);
	
	// 7's
	private Card sevenOfHearts = new Card("Hearts", 7);
	private Card sevenOfDiamonds = new Card("Diamonds", 7);
	private Card sevenOfSpades = new Card("Spades", 7);
	private Card sevenOfClubs = new Card("Clubs", 7);
	
	// 8's
	private Card eightOfHearts = new Card("Hearts", 8);
	private Card eightOfDiamonds = new Card("Diamonds", 8);
	private Card eightOfSpades = new Card("Spades", 8);
	private Card eightOfClubs = new Card("Clubs", 8);

	// 9's
	private Card nineOfHearts = new Card("Hearts", 9);
	private Card nineOfDiamonds = new Card("Diamonds", 9);
	private Card nineOfSpades = new Card("Spades", 9);
	private Card nineOfClubs = new Card("Clubs", 9);
	
	// 10's
	private Card tenOfHearts = new Card("Hearts", 10);
	private Card tenOfDiamonds = new Card("Diamonds", 10);
	private Card tenOfSpades = new Card("Spades", 10);
	private Card tenOfClubs = new Card("Clubs", 10);
	
	// Jacks
	private Card jackOfHearts = new Card("Hearts", 11);
	private Card jackOfDiamonds = new Card("Diamonds", 11);
	private Card jackOfSpades = new Card("Spades", 11);
	private Card jackOfClubs = new Card("Clubs", 11);
	
	// Queens
	private Card queenOfHearts = new Card("Hearts", 12);
	private Card queenOfDiamonds = new Card("Diamonds", 12);
	private Card queenOfSpades = new Card("Spades", 12);
	private Card queenOfClubs = new Card("Clubs", 12);
	
	// Kings
	private Card kingOfHearts = new Card("Hearts", 13);
	private Card kingOfDiamonds = new Card("Diamonds", 13);
	private Card kingOfSpades = new Card("Spades", 13);
	private Card kingOfClubs = new Card("Clubs", 13);
	
	// Aces
	private Card aceOfHearts = new Card("Hearts", 14);
	private Card aceOfDiamonds = new Card("Diamonds", 14);
	private Card aceOfSpades = new Card("Spades", 14);
	private Card aceOfClubs = new Card("Clubs", 14);
	
	private ArrayList<Card> unshuffledDeck;
	private Stack<Card> deck;
	
	public Deck() {
		
		deck = new Stack<>();
		unshuffledDeck = new ArrayList<Card>();
		
		unshuffledDeck.add(twoOfHearts);
		unshuffledDeck.add(twoOfDiamonds);
		unshuffledDeck.add(twoOfSpades);
		unshuffledDeck.add(twoOfClubs);
		
		unshuffledDeck.add(threeOfHearts);
		unshuffledDeck.add(threeOfDiamonds);
		unshuffledDeck.add(threeOfSpades);
		unshuffledDeck.add(threeOfClubs);
		
		unshuffledDeck.add(fourOfHearts);
		unshuffledDeck.add(fourOfDiamonds);
		unshuffledDeck.add(fourOfSpades);
		unshuffledDeck.add(fourOfClubs);
		
		unshuffledDeck.add(fiveOfHearts);
		unshuffledDeck.add(fiveOfDiamonds);
		unshuffledDeck.add(fiveOfSpades);
		unshuffledDeck.add(fiveOfClubs);
		
		unshuffledDeck.add(sixOfHearts);
		unshuffledDeck.add(sixOfDiamonds);
		unshuffledDeck.add(sixOfSpades);
		unshuffledDeck.add(sixOfClubs);
		
		unshuffledDeck.add(sevenOfHearts);
		unshuffledDeck.add(sevenOfDiamonds);
		unshuffledDeck.add(sevenOfSpades);
		unshuffledDeck.add(sevenOfClubs);
		
		unshuffledDeck.add(eightOfHearts);
		unshuffledDeck.add(eightOfDiamonds);
		unshuffledDeck.add(eightOfSpades);
		unshuffledDeck.add(eightOfClubs);
		
		unshuffledDeck.add(nineOfHearts);
		unshuffledDeck.add(nineOfDiamonds);
		unshuffledDeck.add(nineOfSpades);
		unshuffledDeck.add(nineOfClubs);
		
		unshuffledDeck.add(tenOfHearts);
		unshuffledDeck.add(tenOfDiamonds);
		unshuffledDeck.add(tenOfSpades);
		unshuffledDeck.add(tenOfClubs);
		
		unshuffledDeck.add(jackOfHearts);
		unshuffledDeck.add(jackOfDiamonds);
		unshuffledDeck.add(jackOfSpades);
		unshuffledDeck.add(jackOfClubs);
		
		unshuffledDeck.add(queenOfHearts);
		unshuffledDeck.add(queenOfDiamonds);
		unshuffledDeck.add(queenOfSpades);
		unshuffledDeck.add(queenOfClubs);
		
		unshuffledDeck.add(kingOfHearts);
		unshuffledDeck.add(kingOfDiamonds);
		unshuffledDeck.add(kingOfSpades);
		unshuffledDeck.add(kingOfClubs);
		
		unshuffledDeck.add(aceOfHearts);
		unshuffledDeck.add(aceOfDiamonds);
		unshuffledDeck.add(aceOfSpades);
		unshuffledDeck.add(aceOfClubs);
		
		shuffleDeck();
		
	}
	
	public void shuffleDeck() {
		
		// get size of deck
		// select random number from 0 to size of deck 
		// get that index from arraylist and add to shuffled deck
		
		while (!unshuffledDeck.isEmpty()) {
			
			int range = unshuffledDeck.size();
			int randomIndex = (int)(Math.random() * range) + 0;
			
			deck.push(unshuffledDeck.get(randomIndex));
			unshuffledDeck.remove(randomIndex);
		
		}
		

	}
	
	
	

	public Stack<Card> getDeck() {
		
		return deck;

	}
	
	public int getCardValue(Card card) {
		
		int value = 0;
		
		// ACES
		if (card.getFaceValue() == 14) {
			
			value = 11;
			
			
		}
		// Kings, Queens, Jacks and 10s
		else if (card.getFaceValue() >= 10 && card.getFaceValue() <= 13) {
			
			value = 10;
			
			
		}
		else {
			
			value = card.getFaceValue();
			
			
		}
		
		return value;

	}
	
	public String cardValuetoString(Card card) {
		
		String cardActualValue = "";
		
		Integer cardFaceValue = card.getFaceValue();
		
		
		if (cardFaceValue == 14) {
			
			cardActualValue = "A";
			
		}
		else if (cardFaceValue == 13) {
			
			cardActualValue = "K";
			
		}
		else if (cardFaceValue == 12) {
			
			cardActualValue = "Q";
			
		}
		else if (cardFaceValue == 11) {
			
			cardActualValue = "J";
			
		}
		else {
			
			cardActualValue = cardFaceValue.toString();
			
		}
		
		return cardActualValue;
		
	}
	

}
