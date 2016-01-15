package elevens;

/**
 *
 * @author Cheng
 */
public class Elevens {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Board board = new ElevensBoard();
	CardGameGUI gui = new CardGameGUI(board);
	gui.displayGame();
        
    }
    
}
