/**
 * The Knight piece
 *
 * @author Ike Hayes
 * @version 2/6/22
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Knight extends Piece{
    Icon wKnight=new ImageIcon("Images\\wN.png");
    Icon bKnight=new ImageIcon("Images\\bN.png");
    public Knight(boolean colour){
       super(colour);
       if(this.getColour()){
           this.setIcon(wKnight);
       }else{ 
           this.setIcon(bKnight);
       }
    }
    public boolean movePossible(Square start, Square end){
        return true;
    }
}
