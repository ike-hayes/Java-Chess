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
    
    static Square whiteKingSquare;
    static Square blackKingSquare;
    
    static boolean whiteInCheck;
    static boolean blackInCheck;
    public Game() throws IOException{
        new GUI();
        whiteKingSquare=GUI.squares[4][0];
        blackKingSquare=GUI.squares[4][7];
        //The squares that contain the kings are tracked for check
    }
    
    public static void switchTurn(){
        whiteInCheck=false;
        blackInCheck=false;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                GUI.squares[i][j].setWatchedWhite(false);
                GUI.squares[i][j].setWatchedBlack(false);
                if(GUI.squares[i][j].squareWatched(true)){
                    GUI.squares[i][j].setWatchedWhite(true);
                }
                if(GUI.squares[i][j].squareWatched(false)){
                    GUI.squares[i][j].setWatchedBlack(true);
                }
                /*Each square is checked for if it is watched by white or black
                 * pieces. This is so the king cannot move into check.
                 */
                if(GUI.squares[i][j].getCurrentPiece()!=null){
                    if(GUI.squares[i][j].getCurrentPiece().getClass().getSimpleName()=="King"){
                        if(GUI.squares[i][j].getCurrentPiece().getColour()==true){
                            whiteKingSquare=GUI.squares[i][j];
                            if(GUI.squares[i][j].getWatchedBlack()){
                                whiteInCheck=true;
                            }
                        }
                        if(GUI.squares[i][j].getCurrentPiece().getColour()==false){
                            blackKingSquare=GUI.squares[i][j];
                            if(GUI.squares[i][j].getWatchedWhite()){
                                blackInCheck=true;
                            }
                        }
                    }
                    /*The squares that contain kings are updated and tested
                     * to see if each colour is in check
                     */
                }
            }
        }
        GUI.switchIcon();
        GUI.updateMoveList();
        whiteTurn=!whiteTurn;
        //Finally, the turn is switched to the other player
    }
    
    public static boolean getTurn(){
        return whiteTurn;
    }
}