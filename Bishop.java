/**
 * The Bishop piece
 *
 * @author Ike Hayes
 * @version 2/6/22
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Bishop extends Piece{
    Icon wBishop=new ImageIcon("Images\\wB.png");
    Icon bBishop=new ImageIcon("Images\\bB.png");
    int xDisplacement;
    int yDisplacement;
    int xDirection;
    int yDirection;
    int steps;
    public Bishop(boolean colour){
       super(colour);
       if(this.getColour()){
           this.setPieceIcon(wBishop);
       }else{ 
           this.setPieceIcon(bBishop);
       }
    }
    public boolean movePossible(Square start, Square end){
        if(end.getCurrentPiece()!=null){
            if(start.getCurrentPiece().getColour()==end.getCurrentPiece().getColour()){
                return false;
            }
        }
        xDisplacement=end.getX()-start.getX();
        yDisplacement=end.getY()-start.getY();
        steps=Math.abs(yDisplacement);
        xDirection=xDisplacement/steps;
        yDirection=yDisplacement/steps;
        if(yDisplacement==xDisplacement || yDisplacement==-xDisplacement){
            if(!moveBlocked(start,end)) return true;
        }
        Game.selectedPiece=null;
        Game.selectedSquare=null;
        return false;
    }
    private boolean moveBlocked(Square start, Square end){
        for(int i=1;i<steps;i++){
            if(GUI.squares[start.getX()+(i*xDirection)][start.getY()+(i*yDirection)].getCurrentPiece()!=null){
                return true;
            }
        }
        return false;
    }
}