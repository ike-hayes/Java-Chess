/**
 * The main class to run the chess game
 *
 * @author Ike Hayes
 * @version 7/7/22
 */
import java.io.IOException;
import java.util.ArrayList;
public class Game{
    
    static Piece selectedPiece=null;
    static Square selectedSquare=null;
    //Tracks the current piece and square selected for moving pieces
    static boolean whiteTurn=true;
    static ArrayList<Move> moves=new ArrayList<Move>();
    
    static Square whiteKingSquare;
    static Square blackKingSquare;
    
    static boolean whiteInCheck;
    static boolean blackInCheck;
    static boolean whiteHasMoves;
    static boolean blackHasMoves;
    static boolean whiteRemovesCheck;
    static boolean blackRemovesCheck;
    static boolean whiteCheckmated;
    static boolean blackCheckmated;
    static boolean stalemate=false;
    static boolean gameActive=true;
    static boolean whiteResigns=false;
    static boolean blackResigns=false;
    //These variables contain various information about the gamestate
    
    public Game() throws IOException{
        new GUI();
        whiteKingSquare=GUI.squares[4][0];
        blackKingSquare=GUI.squares[4][7];
        //The squares that contain the kings are tracked for check
    }
    
    public static void switchTurn(){
        GUI.lastMove=moves.get(moves.size()-1);
        whiteInCheck=false;
        blackInCheck=false;
        whiteHasMoves=false;
        blackHasMoves=false;
        whiteTurn=!whiteTurn;
        //The properties of each colour are reset and the turn is switched
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
                    // The squares that contain kings are updated and tested to see if each colour is in check
                     
                    for(int a=0;a<8;a++){
                        for(int b=0;b<8;b++){
                            GUI.squares[i][j].setTemporaryEmpty(true);
                            GUI.squares[a][b].setTemporaryBlock(true);
                            if(GUI.squares[i][j].getCurrentPiece().getColour() && whiteTurn){
                                whiteRemovesCheck=false;
                                if(GUI.squares[i][j].getCurrentPiece().getClass().getSimpleName()=="King"){
                                    if(GUI.squares[a][b].getCurrentPiece()!=null){
                                        if(!GUI.squares[a][b].squareWatched(false) && GUI.squares[a][b].getCurrentPiece().getColour()!=GUI.squares[i][j].getCurrentPiece().getColour()){
                                            whiteRemovesCheck=true;
                                        }
                                    }else if(!GUI.squares[a][b].squareWatched(false)){
                                        whiteRemovesCheck=true;
                                    }
                                }else if(!Game.whiteKingSquare.squareWatched(false)){
                                    whiteRemovesCheck=true;
                                }
                                if(GUI.squares[i][j].getCurrentPiece().movePossible(GUI.squares[i][j],GUI.squares[a][b]) && whiteRemovesCheck){
                                    if(GUI.squares[a][b].getCurrentPiece()!=null){
                                        if(GUI.squares[a][b].getCurrentPiece().getColour()!=GUI.squares[i][j].getCurrentPiece().getColour()){
                                            whiteHasMoves=true;
                                        }
                                    }else{
                                        whiteHasMoves=true;
                                    }
                                }
                            }else if(!GUI.squares[i][j].getCurrentPiece().getColour() && !whiteTurn){
                                blackRemovesCheck=false;
                                if(GUI.squares[i][j].getCurrentPiece().getClass().getSimpleName()=="King"){
                                    if(GUI.squares[a][b].getCurrentPiece()!=null){
                                        if(!GUI.squares[a][b].squareWatched(true) && GUI.squares[a][b].getCurrentPiece().getColour()!=GUI.squares[i][j].getCurrentPiece().getColour()){
                                            blackRemovesCheck=true;
                                        }
                                    }else if(!GUI.squares[a][b].squareWatched(true)){
                                        blackRemovesCheck=true;
                                    }
                                }else if(!Game.blackKingSquare.squareWatched(true)){
                                    blackRemovesCheck=true;
                                }
                                if(GUI.squares[i][j].getCurrentPiece().movePossible(GUI.squares[i][j],GUI.squares[a][b]) && blackRemovesCheck){
                                    if(GUI.squares[a][b].getCurrentPiece()!=null){
                                        if(GUI.squares[a][b].getCurrentPiece().getColour()!=GUI.squares[i][j].getCurrentPiece().getColour()){
                                            blackHasMoves=true;
                                        }
                                    }else{
                                        blackHasMoves=true;
                                    }
                                }
                            }
                            GUI.squares[i][j].setTemporaryEmpty(false);
                            GUI.squares[a][b].setTemporaryBlock(false);
                            //All availible moves are tested for each colour to see if they remove check
                       }
                    }
                    /*Each square is then tested to see if the piece in it has any availible moves.
                     * if there are no pieces with any moves availible, the game has ended in either checkmate
                     * or stalemate
                     */
                }
            }
        }
        if(!whiteHasMoves && whiteTurn){
            gameActive=false;
            if(whiteInCheck){
                whiteCheckmated=true;
            }else{
                stalemate=true;
            }
        }
        if(!blackHasMoves && !whiteTurn){
            gameActive=false;
            if(blackInCheck){
                blackCheckmated=true;
            }else{
                stalemate=true;
            }
        }
        /*If a players turn comes round and they can't make any moves that wouldn't leave
         * their king in check, this is either a checkmate or stalemate. If their king is in check,
         * it is a checkmate. Otherwise, this is a stalemate and the game is a draw.
         */
        if(GUI.drawOffered && !GUI.drawButtonClicked){
            GUI.drawOffered=false;
        }
        GUI.drawButtonClicked=false;
        GUI.switchIcon();
        GUI.updateMoveList();
        //Finally, the GUI is updated to reflect the current gamestate
    }
}