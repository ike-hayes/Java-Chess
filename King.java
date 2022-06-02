/**
 * The King piece
 *
 * @author Ike Hayes
 * @version 2/6/22
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class King extends Piece{
    Icon wKing=new ImageIcon("Images\\wK.png");
    Icon bKing=new ImageIcon("Images\\bK.png");
    public King(boolean colour){
       super(colour);
       if(this.getColour()){
           this.setIcon(wKing);
       }else{ 
           this.setIcon(bKing);
       }
    }
    public boolean movePossible(Square start, Square end){
        return true;
    }
}
