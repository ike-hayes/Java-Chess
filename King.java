/**
 * The King piece
 *
 * @author Ike Hayes
 * @version 16/6/22
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class King extends Piece{
    Icon wKing=new ImageIcon("Images\\wK.png");
    Icon bKing=new ImageIcon("Images\\bK.png");
    int xDisplacement;
    int yDisplacement;
    int steps;
    public King(boolean colour){
       super(colour);
       if(this.getColour()){
           this.setPieceIcon(wKing);
       }else{ 
           this.setPieceIcon(bKing);
       }
    }
    public boolean movePossible(Square start, Square end){
        if(end.getWatchedBlack() && this.getColour()){
            return false;
        }
        
        if(end.getWatchedWhite() && !this.getColour()){
            return false;
        }
        
        xDisplacement=end.getX()-start.getX();
        yDisplacement=Math.abs(end.getY()-start.getY());
        steps=xDisplacement+yDisplacement;
        if(Math.abs(xDisplacement)<=1 && yDisplacement<=1 && steps!=0){
            return true;
        }
        //King can only move one square in any direction
        
        if(!this.getMoved() && GUI.squares[0][start.getY()].getCurrentPiece()!=null){
            if(xDisplacement==-2 && GUI.squares[0][start.getY()].getCurrentPiece().getMoved()==false && GUI.squares[1][start.getY()].squarePassable(this.getOpponent()) && GUI.squares[2][start.getY()].squarePassable(this.getOpponent()) && GUI.squares[3][start.getY()].squarePassable(this.getOpponent())){
                return true;
            }
        }
        
        if(!this.getMoved() && GUI.squares[7][start.getY()].getCurrentPiece()!=null){
            if(xDisplacement==2 && GUI.squares[7][start.getY()].getCurrentPiece().getMoved()==false && GUI.squares[6][start.getY()].squarePassable(this.getOpponent()) && GUI.squares[5][start.getY()].squarePassable(this.getOpponent())){
                return true;
            }
        }
        //The king can also castle if neither it or the rook has moved and all the squares between are empty
        return false;
    }
}