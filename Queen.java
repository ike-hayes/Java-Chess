/**
 * The Queen piece
 *
 * @author Ike Hayes
 * @version 2/6/22
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Queen extends Piece{
    Icon wQueen=new ImageIcon("Images\\wQ.png");
    Icon bQueen=new ImageIcon("Images\\bQ.png");
    public Queen(boolean colour){
       super(colour);
       if(this.getColour()){
           this.setPieceIcon(wQueen);
       }else{ 
           this.setPieceIcon(bQueen);
       }
    }
    public boolean movePossible(Square start, Square end){
        return true;
    }
}