/**
 * The abstract classes that all pieces are based on
 *
 * @author Ike Hayes
 * @version 10/5/22
 */
public abstract class Piece{
    private boolean captured=false;
    private boolean white;
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
    
}