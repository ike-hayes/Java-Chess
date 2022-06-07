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
    public Bishop(boolean colour){
       super(colour);
       if(this.getColour()){
           this.setPieceIcon(wBishop);
       }else{ 
           this.setPieceIcon(bBishop);
       }
    }
    public boolean movePossible(Square start, Square end){
        return true;
    }
}