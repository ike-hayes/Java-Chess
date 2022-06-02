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
    public Rook(boolean colour){
       super(colour);
       if(this.getColour()){
           this.setIcon(wRook);
       }else{ 
           this.setIcon(bRook);
       }
    }
    public boolean movePossible(Square start, Square end){
        return true;
    }
}