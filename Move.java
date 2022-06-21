/**
 * Creates the moves in the chess game
 *
 * @author Ike Hayes
 * @version 17/6/22
 */
public class Move{
    private boolean colour;
    private Piece piece;
    private boolean capture;
    private int endX;
    private int endY;
    private boolean check;
    private boolean checkmate;
    boolean doublePawnMove=false;
    boolean shortCastle=false;
    boolean longCastle=false;
    public Move(boolean colour, Piece piece, boolean capture, Square start, Square end, boolean check, boolean checkmate){
        this.colour=colour;
        this.piece=piece;
        this.capture=capture;
        this.endX=end.getX();
        this.endY=end.getY();
        this.check=check;
        this.checkmate=checkmate;
        if(piece.getClass().getSimpleName().equals("Pawn") && Math.abs(start.getY()-end.getY())==2){
            doublePawnMove=true;
        }
        if(piece.getClass().getSimpleName().equals("King") && end.getX()-start.getX()==2){
            shortCastle=true;
        }
        if(piece.getClass().getSimpleName().equals("King") && start.getX()-end.getX()==2){
            longCastle=true;
        }
    }
    
    public boolean getColour(){
        return this.colour;
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