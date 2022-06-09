/**
 * The main class to run the chess game
 *
 * @author Ike Hayes
 * @version 7/6/22
 */
import java.io.IOException;
public class Game{
    static Piece selectedPiece=null;
    static Square selectedSquare=null;
    //Tracks the current piece and square selected for moving pieces
    public Game() throws IOException{
        new GUI();
    }
}