package elevens;
import java.util.List;
import java.util.ArrayList;

//ElevensBoard is the board that the game will be playing on. 
public class ElevensBoard extends Board {

	//#number of cards on board
	private static final int BOARD_SIZE = 9;
	private static final String[] RANKS =
		{"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};
	private static final String[] SUITS =
		{"spades", "hearts", "diamonds", "clubs"};

	private static final int[] POINT_VALUES =
		{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 0, 0};

	/**
	 * Flag used to control debugging print statements.
	 */
	private static final boolean I_AM_DEBUGGING = false;


	//Creates new board using initial Board class parameters
	 public ElevensBoard() {
	 	super(BOARD_SIZE, RANKS, SUITS, POINT_VALUES);
	 }

	/*
	 * In Elevens, the legal groups are (1) a pair of non-face cards
	 * whose values add to 11, and (2) a group of three cards consisting of
	 * a jack, a queen, and a king in some order.
	 * Checks if the move made is allowed
	 */
	@Override
	public boolean isLegal(List<Integer> selectedCards) {
		if (selectedCards.size() == 2) {
			return containsPairSum11(selectedCards);
		} else if (selectedCards.size() == 3) {
			return containsJQK(selectedCards);
		} else {
			return false;
		}
	}

	//looks to see if there is another move available otherwise loss
	@Override
	public boolean anotherPlayIsPossible() {
		List<Integer> cIndexes = cardIndexes();
		return containsPairSum11(cIndexes) || containsJQK(cIndexes);
	}

	//checks if the board has a pair that adds to 11 on board
	private boolean containsPairSum11(List<Integer> selectedCards) {
		for (int sk1 = 0; sk1 < selectedCards.size(); sk1++) {
			int k1 = selectedCards.get(sk1).intValue();
			for (int sk2 = sk1 + 1; sk2 < selectedCards.size(); sk2++) {
				int k2 = selectedCards.get(sk2).intValue();
				if (cardAt(k1).pointValue() + cardAt(k2).pointValue() == 11) {
					return true;
				}
			}
		}
		return false;
	}

	//Checks the board if a jqk combination exists
	private boolean containsJQK(List<Integer> selectedCards) {
		boolean foundJack = false;
		boolean foundQueen = false;
		boolean foundKing = false;
		for (Integer kObj : selectedCards) {
			int k = kObj.intValue();
			if (cardAt(k).rank().equals("jack")) {
				foundJack = true;
			} else if (cardAt(k).rank().equals("queen")) {
				foundQueen = true;
			} else if (cardAt(k).rank().equals("king")) {
				foundKing = true;
			}
		}
		return foundJack && foundQueen && foundKing;
	}
}
