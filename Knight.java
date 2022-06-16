/**
 * The Knight piece
 *
 * @author Ike Hayes
 * @version 16/6/22
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Knight extends Piece{
    Icon wKnight=new ImageIcon("Images\\wN.png");
    Icon bKnight=new ImageIcon("Images\\bN.png");
    int xDisplacement;
    int yDisplacement;
    public Knight(boolean colour){
       super(colour);
       if(this.getColour()){
           this.setPieceIcon(wKnight);
       }else{ 
           this.setPieceIcon(bKnight);
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
        if(xDisplacement==1 && yDisplacement==2){
            return true;
        }else if(xDisplacement==2 && yDisplacement==1){
            return true;
        }
        /* The knight moves in an L shape, in such a way that it can only move if displaced by 1 along one axis
         * and two on the other. It can also jump pieces so does not need a move blocked method.
         */
        return false;
    }
}