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
        
        xDisplacement=end.getX()-start.getX();
        yDisplacement=Math.abs(end.getY()-start.getY());
        if(Math.abs(xDisplacement)<=1 && yDisplacement<=1){
            return true;
        }
        //King can only move one square in any direction
        
        if(!this.getMoved() && GUI.squares[0][start.getY()].getCurrentPiece()!=null){
            if(xDisplacement==-2 && GUI.squares[0][start.getY()].getCurrentPiece().getMoved()==false && GUI.squares[1][start.getY()].getCurrentPiece()==null && GUI.squares[2][start.getY()].getCurrentPiece()==null && GUI.squares[3][start.getY()].getCurrentPiece()==null){
                GUI.squares[0][start.getY()].getCurrentPiece().setMoved(true);
                GUI.squares[3][start.getY()].setCurrentPiece(GUI.squares[0][start.getY()].getCurrentPiece());
                GUI.squares[0][start.getY()].setCurrentPiece(null);
                GUI.squares[3][start.getY()].redrawIcon();
                GUI.squares[0][start.getY()].redrawIcon();
                return true;
            }
        }
        
        if(!this.getMoved() && GUI.squares[7][start.getY()].getCurrentPiece()!=null){
            if(xDisplacement==2 && GUI.squares[7][start.getY()].getCurrentPiece().getMoved()==false && GUI.squares[6][start.getY()].getCurrentPiece()==null && GUI.squares[5][start.getY()].getCurrentPiece()==null){
                GUI.squares[7][start.getY()].getCurrentPiece().setMoved(true);
                GUI.squares[5][start.getY()].setCurrentPiece(GUI.squares[0][start.getY()].getCurrentPiece());
                GUI.squares[7][start.getY()].setCurrentPiece(null);
                GUI.squares[5][start.getY()].redrawIcon();
                GUI.squares[7][start.getY()].redrawIcon();
                return true;
            }
        }
        return false;
    }
}