/**
 * The abstract class that all pieces are based on
 *
 * @author Ike Hayes
 * @version 7/7/22
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public abstract class Piece{
    private boolean captured=false;
    private boolean white;
    private boolean hasMoved=false;
    private Icon icon;
    /* This abstract piece class is what each piece extends. It holds info that 
     * all pieces require such as the colour, icon, if it is captured, whether it has moved
     * etc.
     */
    public Piece(boolean colour){
        this.white=colour;
    }
    
    public abstract boolean movePossible(Square start, Square end);
    /*An abstract method needs to be overriden by each class separately. This is because
     * all the pieces move differently.
     */
    
    public boolean getColour(){
        return this.white;
    }
    
    public boolean getOpponent(){
        return !this.white;
    }
    
    public boolean getCaptured(){
        return this.captured;
    }
    
    public void setCaptured(boolean captured){
        this.captured=captured;
    }
    
    public boolean getMoved(){
        return this.hasMoved;
    }
    
    public void setMoved(boolean moved){
        this.hasMoved=moved;
    }
    
    public Icon getPieceIcon(){
        return this.icon;
    }
    
    public void setPieceIcon(Icon icon){
        this.icon=icon;
    }
    //Getters and setters 
}