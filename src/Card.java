
// Card object, which when instantiated creates a new Card 
// with it's suit and face value. 


public class Card {

	private String suit;
	private int face_value;
	
	public Card(String suit, int face_value) {
		
		this.suit = suit;
		this.face_value = face_value;
	
		
	}
	
	public String getSuit() {
		
		return suit;
		
	}
	
	public int getFaceValue() {
		
		return face_value;
		
	}
	

}
