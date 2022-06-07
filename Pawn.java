/**
 * The Pawn piece
 *
 * @author Ike Hayes
 * @version 2/6/22
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Pawn extends Piece{
    Icon wPawn=new ImageIcon("Images\\wP.png");
    Icon bPawn=new ImageIcon("Images\\bP.png");
    public Pawn(boolean colour){
       super(colour);
       if(this.getColour()){
           this.setPieceIcon(wPawn);
       }else{ 
           this.setPieceIcon(bPawn);
       }
    }
    public boolean movePossible(Square start, Square end){
        return true;
    }
}