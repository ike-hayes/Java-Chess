/**
 * The Bishop piece
 *
 * @author Ike Hayes
 * @version 16/6/22
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
        xDisplacement=end.getX()-start.getX();
        yDisplacement=end.getY()-start.getY();
        steps=Math.abs(yDisplacement);
        if(steps==0){
            return false;
        }
        xDirection=xDisplacement/steps;
        yDirection=yDisplacement/steps;
        /*The way a bishop moves, the x and y displacements will always be the same, but sometimes one or both will
         * be negative. The direction can be 1 or -1 and indicates how the bishop is moving along that axis. A bishop
         * always moves along both axes.
         */
        if(Math.abs(yDisplacement)==Math.abs(xDisplacement)){
            if(!moveBlocked(start,end)) return true;
        }
        Game.selectedPiece=null;
        Game.selectedSquare=null;
        return false;
    }
    private boolean moveBlocked(Square start, Square end){
        for(int i=1;i<steps;i++){
            if(GUI.squares[start.getX()+(i*xDirection)][start.getY()+(i*yDirection)].getCurrentPiece()!=null && GUI.squares[start.getX()+(i*xDirection)][start.getY()+(i*yDirection)].getTemporaryEmpty()!=true){
                return true;
            }
            if(GUI.squares[start.getX()+(i*xDirection)][start.getY()+(i*yDirection)].getTemporaryBlock()){
                return true;
            }
        }
        //Checks each square in between the bishop and its target for if they are blocked by other pieces
        return false;
    }
}