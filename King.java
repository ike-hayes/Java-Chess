/**
 * The King piece
 *
 * @author Ike Hayes
 * @version 2/6/22
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class King extends Piece{
    Icon wKing=new ImageIcon("Images\\wK.png");
    Icon bKing=new ImageIcon("Images\\bK.png");
    int xDisplacement;
    int yDisplacement;
    public King(boolean colour){
       super(colour);
       if(this.getColour()){
           this.setPieceIcon(wKing);
       }else{ 
           this.setPieceIcon(bKing);
       }
    }
    public boolean movePossible(Square start, Square end){
        if(end.getCurrentPiece()!=null){
            if(start.getCurrentPiece().getColour()==end.getCurrentPiece().getColour()){
                return false;
            }
        }
        
        xDisplacement=Math.abs(end.getX()-start.getX());
        yDisplacement=Math.abs(end.getY()-start.getY());
        if(xDisplacement<=1 && yDisplacement<=1){
            return true;
        }
        //King can only move one square in any direction
        return false;
    }
}