/**
 * Creates the moves in the chess game
 *
 * @author Ike Hayes
 * @version 7/7/22
 */
public class Move{
    private boolean colour;
    private Piece piece;
    private boolean capture;
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private Square end;
    boolean doublePawnMove=false;
    boolean shortCastle=false;
    boolean longCastle=false;
    private boolean promotion=false;
    //Contains all the information about the last move
    public Move(boolean colour, Piece piece, boolean capture, Square start, Square end, boolean promotion){
        this.colour=colour;
        this.piece=piece;
        this.capture=capture;
        this.startX=start.getX();
        this.startY=start.getY();
        this.endX=end.getX();
        this.endY=end.getY();
        this.end=end;
        this.promotion=promotion;
        if(piece.getClass().getSimpleName().equals("Pawn") && Math.abs(startY-endY)==2){
            doublePawnMove=true;
        }
        if(piece.getClass().getSimpleName().equals("King") && endX-startX==2){
            shortCastle=true;
        }
        if(piece.getClass().getSimpleName().equals("King") && startX-endX==2){
            longCastle=true;
        }
        /*These moves are used to convert to notation. Each move contains all
         * the required information to add to the move list, such as the start and end squares,
         * the type and colour of piece, and special moves such as castling or promotion
         */
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
    
    public int getStartX(){
        return this.startX;
    }
    
    public int getStartY(){
        return this.startY;
    }
    
    public int getEndX(){
        return this.endX;
    }
    
    public int getEndY(){
        return this.endY;
    }
    
    public Square getEnd(){
        return this.end;
    }
    
    public boolean getPromotion(){
        return this.promotion;
    }
}