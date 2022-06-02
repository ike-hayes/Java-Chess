/**
 * The abstract class that all pieces are based on
 *
 * @author Ike Hayes
 * @version 24/5/22
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public abstract class Piece{
    private boolean captured=false;
    private boolean white;
    private boolean hasMoved=false;
    private Icon icon;
    public Piece(boolean colour){
        this.white=colour;
    }
    
    public abstract boolean movePossible(Square start, Square end);
    
    public boolean getColour(){
        return this.white;
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
    
    public Icon getIcon(){
        return this.icon;
    }
    
    public void setIcon(Icon icon){
        this.icon=icon;
    }
}