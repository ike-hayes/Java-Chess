/**
 * The main class to run the chess game
 *
 * @author Ike Hayes
 * @version 7/6/22
 */
import java.io.IOException;
public class Game{
    static Piece selectedPiece=null;
    public Game() throws IOException{
        new GUI();
    }
}