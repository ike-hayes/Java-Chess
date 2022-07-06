/**
 * The Queen piece
 *
 * @author Ike Hayes
 * @version 16/6/22
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Queen extends Piece{
    Icon wQueen=new ImageIcon("Images\\wQ.png");
    Icon bQueen=new ImageIcon("Images\\bQ.png");
    int xDisplacement;
    int yDisplacement;
    int xDirection;
    int yDirection;
    int steps;
    public Queen(boolean colour){
       super(colour);
       if(this.getColour()){
           this.setPieceIcon(wQueen);
       }else{ 
           this.setPieceIcon(bQueen);
       }
    }
    public boolean movePossible(Square start, Square end){
        if(start.getTemporaryBlock()){
            return false;
        }
        if(start.getX()==end.getX()){
            xDirection=0;
            steps=Math.abs(end.getY()-start.getY());
            if(steps==0){
                return false;
            }
            yDirection=(end.getY()-start.getY())/steps;
        }else if(start.getY()==end.getY()){
            yDirection=0;
            steps=Math.abs(end.getX()-start.getX());
            if(steps==0){
                return false;
            }
            xDirection=(end.getX()-start.getX())/steps;
        }
        if(start.getX()==end.getX() || start.getY()==end.getY()){
            if(!moveBlocked(start,end)) return true;
        }
        
        xDisplacement=end.getX()-start.getX();
        yDisplacement=end.getY()-start.getY();
        steps=Math.abs(yDisplacement);
        if(steps==0){
            return false;
        }
        xDirection=xDisplacement/steps;
        yDirection=yDisplacement/steps;
        if(Math.abs(yDisplacement)==Math.abs(xDisplacement)){
            if(!moveBlocked(start,end)) return true;
        }
        /*The queen moves either like a rook or a bishop, so first the rook code is used and then the bishop version
         * if it is moving like either of these pieces it will successfully move.
         */
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
        return false;
    }
}