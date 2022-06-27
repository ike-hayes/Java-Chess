/**
 * The squares on the chess board that will hold pieces
 *
 * @author Ike Hayes
 * @version 21/6/22
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Square implements ActionListener{
    private Piece currentPiece;
    private int x;
    private int y;
    //The square is a section of the board that holds a piece. x and y show its position
    
    final int buttonWidth=100;
    final int buttonHeight=100;
    JPanel buttonPanel=new JPanel();
    JButton pieceButton=new JButton();
    
    /* Within each square it holds a jpanel that in turn holds a jbutton.
     * These are used to represent the piece in the square by drawin an icon
     */
    public Square(int x, int y, Piece piece){
        this.currentPiece=piece;
        this.x=x;
        this.y=y;
        createButton();
        if(this.currentPiece!=null){
            pieceButton.setIcon(this.currentPiece.getPieceIcon());
        }
        /*When each square is created, it sets up the jpanel and jbutton inside it.
         * A square can be created with or without a piece in it. If it is created without,
         * there will be no icon to draw. Otherwise, it has the icon of the piece inside
         */
    }
    
    public void actionPerformed(ActionEvent e){
        if(Game.selectedSquare!=this){
            if(Game.selectedPiece==null){
                if(this.getCurrentPiece()!=null && this.getCurrentPiece().getColour()==Game.getTurn()){
                    Game.selectedPiece=this.getCurrentPiece();
                    Game.selectedSquare=this;
                    //If there is no piece selected and this square contains one, it is selected
                }
            }else{ 
                if(this.getCurrentPiece()==null){
                    if(Game.selectedPiece.movePossible(Game.selectedSquare,this)){
                        if(Game.selectedPiece.getClass().getSimpleName()=="Pawn" && Game.selectedSquare.getX()!=this.getX()){
                            Game.moves.add(new Move(Game.selectedPiece.getColour(),Game.selectedPiece,true,Game.selectedSquare,this));
                        }else{
                            Game.moves.add(new Move(Game.selectedPiece.getColour(),Game.selectedPiece,false,Game.selectedSquare,this));
                        }
                        Game.selectedPiece.setMoved(true);
                        this.currentPiece=Game.selectedPiece;
                        Game.selectedSquare.setCurrentPiece(null);
                        this.redrawIcon();
                        Game.selectedSquare.redrawIcon();
                        Game.selectedPiece=null;
                        Game.selectedSquare=null;
                        Game.switchTurn();
                        //If there is a piece selected and this square is empty, that piece will move to it (if possible)
                    }
                }else{
                    if(this.getCurrentPiece().getColour()==Game.selectedPiece.getColour() && this.getCurrentPiece().getColour()==Game.getTurn()){
                        Game.selectedPiece=this.getCurrentPiece();
                        Game.selectedSquare=this;
                        //If a piece of the same colour is clicked on, this new piece is selected
                    }else{
                        if(Game.selectedPiece.movePossible(Game.selectedSquare,this)){
                            Game.moves.add(new Move(Game.selectedPiece.getColour(),Game.selectedPiece,true,Game.selectedSquare,this));
                            Game.selectedPiece.setMoved(true);
                            this.currentPiece.setCaptured(true);
                            this.currentPiece=Game.selectedPiece;
                            Game.selectedSquare.setCurrentPiece(null);
                            this.redrawIcon();
                            Game.selectedSquare.redrawIcon();
                            Game.selectedPiece=null;
                            Game.selectedSquare=null;
                            Game.switchTurn();
                            //If a piece of the opposite colour is clicked, that piece will take this one (if possible)
                        }
                    }
                }
            }
        }
    }
    
    public void redrawIcon(){
       if(this.currentPiece!=null){
           pieceButton.setIcon(this.currentPiece.getPieceIcon());
       }else{
           pieceButton.setIcon(null);
       }
       //After a piece has changed square, the icon needs to be updated to match the piece
    }
    
    private void createButton(){
        buttonPanel.setPreferredSize(new Dimension(buttonWidth,buttonHeight));
        buttonPanel.setOpaque(false);
        pieceButton.setPreferredSize(new Dimension(buttonWidth,buttonHeight));
        pieceButton.setOpaque(false);
        pieceButton.setContentAreaFilled(false);
        pieceButton.setBorderPainted(false);
        pieceButton.addActionListener(this);
        buttonPanel.add(pieceButton);
        //When a square is created, and transparent jpanel and jbutton are set up to hold pieces
    }
    
    public boolean squareWatched(boolean colour){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(GUI.squares[i][j].getCurrentPiece()!=null){
                    if(GUI.squares[i][j].getCurrentPiece().getColour()==colour && GUI.squares[i][j].getCurrentPiece().movePossible(GUI.squares[i][j],this)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public boolean squarePassable(boolean colour){
        if(this.getCurrentPiece()==null && !this.squareWatched(colour)){
            return true;
        }
        return false;
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