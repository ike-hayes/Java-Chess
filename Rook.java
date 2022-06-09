/**
 * The Rook piece
 *
 * @author Ike Hayes
 * @version 2/6/22
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Rook extends Piece{
    Icon wRook=new ImageIcon("Images\\wR.png");
    Icon bRook=new ImageIcon("Images\\bR.png");
    //The two possible icons for different coloured rooks
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
        if(end.getCurrentPiece()!=null){
            if(start.getCurrentPiece().getColour()==end.getCurrentPiece().getColour()){
                return false;
            }
        }
        if(start.getX()==end.getX() || start.getY()==end.getY()){
            if(!moveBlocked(start,end))return true;
        }
        return false;
    }
    private boolean moveBlocked(Square start, Square end){
        if(start.getY()==end.getY()){
            if(end.getX()>start.getX()){
                for(int i=start.getX()+1;i<end.getX();i++){
                    if(GUI.squares[i][start.getY()].getCurrentPiece()!=null) return true;
                    System.out.println("piece is blocked");
                }
            }else{
                for(int i=start.getX()-1;i>end.getX();i--){
                    if(GUI.squares[i][start.getY()].getCurrentPiece()!=null) return true;
                    System.out.println("piece is blocked");
                }
            }
        }else{
            if(end.getY()>start.getY()){
                for(int i=start.getY()+1;i<end.getY();i++){
                    if(GUI.squares[start.getX()][i].getCurrentPiece()!=null) return true;
                    System.out.println("piece is blocked");
                }
            }else{
                for(int i=start.getY()-1;i>end.getY();i--){
                    if(GUI.squares[start.getX()][i].getCurrentPiece()!=null) return true;
                    System.out.println("piece is blocked");
                }
            }
        }
        return false;
    }
}