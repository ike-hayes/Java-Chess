/**
 * The Pawn piece
 *
 * @author Ike Hayes
 * @version 16/6/22
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Pawn extends Piece{
    Icon wPawn=new ImageIcon("Images\\wP.png");
    Icon bPawn=new ImageIcon("Images\\bP.png");
    int xDisplacement;
    int yDisplacement;
    public Pawn(boolean colour){
       super(colour);
       if(this.getColour()){
           this.setPieceIcon(wPawn);
       }else{ 
           this.setPieceIcon(bPawn);
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
        if(xDisplacement==0 && end.getCurrentPiece()==null){
            if(yDisplacement==1 && this.getColour()){
                return true;
            }else if(yDisplacement==2 && this.getColour()){
                if(!this.getMoved() && !moveBlocked(start)){
                    return true;
                }
            }
            if(yDisplacement==-1 && !this.getColour()){
                return true;
            }else if(yDisplacement==-2 && !this.getColour()){
                if(!this.getMoved() && !moveBlocked(start)){
                    return true;
                }
            }
        }
        
        if(yDisplacement==1 && Math.abs(xDisplacement)==1 && end.getCurrentPiece()!=null && this.getColour()){
            return true;
        }
        if(yDisplacement==-1 && Math.abs(xDisplacement)==1 && end.getCurrentPiece()!=null && !this.getColour()){
            return true;
        }
        
        if(GUI.lastMove.doublePawnMove && GUI.lastMove.getEndY()==start.getY() && GUI.lastMove.getPiece()==GUI.squares[start.getX()+xDisplacement][start.getY()].getCurrentPiece()){
            if(yDisplacement==1 && end.getCurrentPiece()==null && this.getColour() && start.getY()==4){
                return true;
            }
            if(yDisplacement==-1 && end.getCurrentPiece()==null && !this.getColour() && start.getY()==3){
                return true;
            }
        }
        
        /* The pawn moves in different ways. On its first move, it can move either one or two squares forwards. 
         * After that, it can only move one square. However, it captures diagonally one square left or right. It is
         * important to distinguish between the black and white pawns because they cannot move backwards. white
         * pawns can only move up the board and black pawns only down the board. A pawn
         */
        return false;
    }
    private boolean moveBlocked(Square start){
        if(this.getColour()){
            if(GUI.squares[start.getX()][start.getY()+1].getCurrentPiece()!=null){
                return true;
            }
        }else{
            if(GUI.squares[start.getX()][start.getY()-1].getCurrentPiece()!=null){
                return true;
            }
        }
        return false;
    }
}