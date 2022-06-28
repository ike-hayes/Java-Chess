/**
 * The main class to run the chess game
 *
 * @author Ike Hayes
 * @version 17/6/22
 */
import java.io.IOException;
import java.util.ArrayList;
public class Game{
    static Piece selectedPiece=null;
    static Square selectedSquare=null;
    //Tracks the current piece and square selected for moving pieces
    private static boolean whiteTurn=true;
    static ArrayList<Move> moves=new ArrayList<Move>();
    public Game() throws IOException{
        new GUI();
    }
    
    public static void switchTurn(){
        GUI.updateMoveList();
        if(whiteTurn){
            whiteTurn=false;
            GUI.switchIcon();
        }else{
            whiteTurn=true;
            GUI.switchIcon();
        }
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(GUI.squares[i][j].squareWatched(true)){
                    GUI.squares[i][j].setWatchedWhite(true);
                }
                if(GUI.squares[i][j].squareWatched(false)){
                    GUI.squares[i][j].setWatchedBlack(true);
                }
            }
        }
    }
    
    public static boolean getTurn(){
        return whiteTurn;
    }
}