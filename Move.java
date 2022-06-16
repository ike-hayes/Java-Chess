/**
 * Creates the moves in the chess game
 *
 * @author Ike Hayes
 * @version 17/6/22
 */
public class Move{
    private Piece piece;
    private boolean capture;
    private int endX;
    private int endY;
    private boolean check;
    private boolean checkmate;
    public Move(Piece piece, boolean capture, Square end, boolean check, boolean checkmate){
        this.piece=piece;
        this.capture=capture;
        this.endX=end.getX();
        this.endY=end.getY();
        this.check=check;
        this.checkmate=checkmate;
    }
    
    public Piece getPiece(){
        return this.piece;
    }
    
    public boolean getCapture(){
        return this.capture;
    }
    
    public int getEndX(){
        return this.endX;
    }
    
    public int getEndY(){
        return this.endY;
    }
    
    public boolean getCheck(){
        return this.check;
    }
    
    public boolean getCheckmate(){
        return this.checkmate;
    }
}