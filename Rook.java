/**
 * The Rook piece
 *
 * @author Ike Hayes
 * @version 16/6/22
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Rook extends Piece{
    Icon wRook=new ImageIcon("Images\\wR.png");
    Icon bRook=new ImageIcon("Images\\bR.png");
    //The two possible icons for different coloured rooks
    int steps;
    int xDirection;
    int yDirection;
    public Rook(boolean colour){
       super(colour);
       if(this.getColour()){
           this.setPieceIcon(wRook);
       }else{ 
           this.setPieceIcon(bRook);
       }
       /*Sets the correct icon based on the colour of the piece. This is used the same
        * in all piece classes
        */
    }
    public boolean movePossible(Square start, Square end){
        if(start.getX()==end.getX()){
            xDirection=0;
            steps=Math.abs(end.getY()-start.getY());
            if(steps==0){
                return false;
            }
            yDirection=(end.getY()-start.getY())/steps;
        }else{
            yDirection=0;
            steps=Math.abs(end.getX()-start.getX());
            if(steps==0){
                return false;
            }
            xDirection=(end.getX()-start.getX())/steps;
        }
        /*Maths to find how the piece is moving. The number of squares it moves is counted as well as the direction
         * which can be either 1,-1 or 0 in each direction. A rook can only ever move along the x or y axis, not both
         */
        if(start.getX()==end.getX() || start.getY()==end.getY()){
            if(!moveBlocked(start,end)) return true;
        }
        return false;
    }
    private boolean moveBlocked(Square start, Square end){
        for(int i=1;i<steps;i++){
            if(GUI.squares[start.getX()+(i*xDirection)][start.getY()+(i*yDirection)].getCurrentPiece()!=null && GUI.squares[start.getX()+(i*xDirection)][start.getY()+(i*yDirection)].getTemporaryEmpty()!=true ){
                return true;
            }
            if(GUI.squares[start.getX()+(i*xDirection)][start.getY()+(i*yDirection)].getTemporaryBlock()){
                return true;
            }
        }
        //Checks each square in between the rook and its target for if they are blocked by other pieces
        return false;
    }
}