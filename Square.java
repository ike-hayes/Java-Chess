/**
 * The squares on the chess board that will hold pieces
 *
 * @author Ike Hayes
 * @version 10/5/22
 */
public class Square{
    private Piece currentPiece;
    private int x;
    private int y;
    public Square(int x, int y, Piece piece)
    {
        this.currentPiece=piece;
        this.x=x;
        this.y=y;
    }
        
     public int getX(){
        return this.x;
    }
  
    public void setX(int x){
        this.x=x;
    }
  
    public int getY(){
        return this.y;
    }
  
    public void setY(int y){
        this.y=y;
    }
    
    public Piece getCurrentPiece(){
        return this.currentPiece;
    }
    
    public void setCurrentPiece(Piece piece){
        this.currentPiece=piece;
    }
}
