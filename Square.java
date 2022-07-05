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
     * These are used to represent the piece in the square by drawing an icon
     */
    private boolean squareWatchedWhite=false;
    private boolean squareWatchedBlack=false;
    
    private boolean temporaryBlock=false;
    private boolean temporaryEmpty=false;
    
    private boolean moveRemovesCheck;
    
    private Integer chosenNumber;
    private boolean promotionMove;
    private boolean failedPromotion;
    String[] options=new String[] {"Queen","Rook","Bishop","Knight"};
    
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
        if(Game.selectedSquare!=this && Game.gameActive){
            if(Game.selectedPiece==null){
                if(this.getCurrentPiece()!=null && this.getCurrentPiece().getColour()==Game.whiteTurn){
                    Game.selectedPiece=this.getCurrentPiece();
                    Game.selectedSquare=this;
                    //If there is no piece selected and this square contains one, it is selected
                }
            }else{
                moveRemovesCheck=false;
                Game.selectedSquare.setTemporaryEmpty(true);
                this.setTemporaryBlock(true);
                if(Game.whiteTurn){
                    if(Game.selectedPiece.getClass().getSimpleName()=="King"){
                        if(this.getCurrentPiece()!=null){
                            if(!this.squareWatched(false) && this.getCurrentPiece().getColour()!=Game.selectedPiece.getColour()){
                                moveRemovesCheck=true;
                            }
                        }else if(!this.squareWatched(false)){
                            moveRemovesCheck=true;
                        }
                     }else if(!Game.whiteKingSquare.squareWatched(false)){
                        moveRemovesCheck=true;
                    } 
                }else{
                    if(Game.selectedPiece.getClass().getSimpleName()=="King"){
                        if(this.getCurrentPiece()!=null){
                            if(!this.squareWatched(true) && this.getCurrentPiece().getColour()!=Game.selectedPiece.getColour()){
                                moveRemovesCheck=true;
                            }
                        }else if(!this.squareWatched(true)){
                            moveRemovesCheck=true;
                        }
                     }else if(!Game.blackKingSquare.squareWatched(true)){
                        moveRemovesCheck=true;
                    } 
                }
                Game.selectedSquare.setTemporaryEmpty(false);
                this.setTemporaryBlock(false);
                /* This section checks if the move would leave the players king
                 * in check. The square the piece is moving too is set to be temporarily
                 * containing a 'piece', and the start square is temporarily empty.
                 * Each enemy piece is then checked to see if it attacks the king.
                 */
                promotionMove=false;
                failedPromotion=false;
                chosenNumber=null;
                if(Game.selectedPiece.getClass().getSimpleName()=="Pawn" && Game.selectedPiece.movePossible(Game.selectedSquare,this) && moveRemovesCheck){
                    if((Game.selectedPiece.getColour() && this.getY()==7) || (!Game.selectedPiece.getColour() && this.getY()==0)){
                        promotionMove=true;
                        chosenNumber=JOptionPane.showOptionDialog(null,"Please choose a piece to promote to","Promotion",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null,options,options[0]);
                        switch(chosenNumber){
                                case(0):Game.selectedSquare.setCurrentPiece(new Queen(Game.selectedPiece.getColour()));
                                        break;
                                case(1):Game.selectedSquare.setCurrentPiece(new Rook(Game.selectedPiece.getColour()));
                                        break;
                                case(2):Game.selectedSquare.setCurrentPiece(new Bishop(Game.selectedPiece.getColour()));
                                        break;
                                case(3):Game.selectedSquare.setCurrentPiece(new Knight(Game.selectedPiece.getColour()));
                                        break;
                                case(-1):failedPromotion=true;
                                         break;
                        }
                    }
                }
                if(this.getCurrentPiece()==null){
                    if(Game.selectedPiece.movePossible(Game.selectedSquare,this) && moveRemovesCheck && !failedPromotion){
                        if(Game.selectedPiece.getClass().getSimpleName()=="Pawn" && (Game.selectedSquare.getX()==this.getX()+1 || Game.selectedSquare.getX()==this.getX()-1)){
                            Game.moves.add(new Move(Game.selectedPiece.getColour(),Game.selectedPiece,true,Game.selectedSquare,this,false));
                            GUI.squares[this.getX()][this.getY()-1].getCurrentPiece().setCaptured(true);
                            GUI.squares[this.getX()][this.getY()-1].setCurrentPiece(null);
                            GUI.squares[this.getX()][this.getY()-1].redrawIcon();
                        }else if(promotionMove){
                            Game.moves.add(new Move(Game.selectedPiece.getColour(),Game.selectedPiece,false,Game.selectedSquare,this,true));
                        }else{
                            Game.moves.add(new Move(Game.selectedPiece.getColour(),Game.selectedPiece,false,Game.selectedSquare,this,false));
                        }
                        //An en passant move requires a pawn to be taken that is on a different square
                        if(Game.selectedPiece.getClass().getSimpleName()=="King" && Game.selectedSquare.getX()==this.getX()-2){
                            GUI.squares[7][this.getY()].getCurrentPiece().setMoved(true);
                            GUI.squares[5][this.getY()].setCurrentPiece(GUI.squares[0][this.getY()].getCurrentPiece());
                            GUI.squares[7][this.getY()].setCurrentPiece(null);
                            GUI.squares[5][this.getY()].redrawIcon();
                            GUI.squares[7][this.getY()].redrawIcon();
                        }
                        if(Game.selectedPiece.getClass().getSimpleName()=="King" && Game.selectedSquare.getX()==this.getX()+2){
                            GUI.squares[0][this.getY()].getCurrentPiece().setMoved(true);
                            GUI.squares[3][this.getY()].setCurrentPiece(GUI.squares[0][this.getY()].getCurrentPiece());
                            GUI.squares[0][this.getY()].setCurrentPiece(null);
                            GUI.squares[3][this.getY()].redrawIcon();
                            GUI.squares[0][this.getY()].redrawIcon();
                        }
                        //Castling king or queenside also moves the rook involved
                        Game.selectedPiece.setMoved(true);
                        this.currentPiece=Game.selectedSquare.getCurrentPiece();
                        Game.selectedSquare.setCurrentPiece(null);
                        this.redrawIcon();
                        Game.selectedSquare.redrawIcon();
                        Game.selectedPiece=null;
                        Game.selectedSquare=null;
                        Game.switchTurn();
                        //If there is a piece selected and this square is empty, that piece will move to it (if possible)
                    }
                }else{
                    if(this.getCurrentPiece().getColour()==Game.selectedPiece.getColour() && this.getCurrentPiece().getColour()==Game.whiteTurn){
                        Game.selectedPiece=this.getCurrentPiece();
                        Game.selectedSquare=this;
                        //If a piece of the same colour is clicked on, this new piece is selected
                    }else{
                        if(Game.selectedPiece.movePossible(Game.selectedSquare,this) && moveRemovesCheck && !failedPromotion){
                            if(promotionMove){
                                Game.moves.add(new Move(Game.selectedPiece.getColour(),Game.selectedPiece,true,Game.selectedSquare,this,true));
                            }else{
                                Game.moves.add(new Move(Game.selectedPiece.getColour(),Game.selectedPiece,true,Game.selectedSquare,this,false));
                            }
                            Game.selectedPiece.setMoved(true);
                            this.currentPiece.setCaptured(true);
                            this.currentPiece=Game.selectedSquare.getCurrentPiece();
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
                    if(GUI.squares[i][j].getCurrentPiece().getClass().getSimpleName()=="Pawn" && GUI.squares[i][j].getCurrentPiece().getColour()==colour){
                        if((GUI.squares[i][j].getX()==this.getX()+1 || GUI.squares[i][j].getX()==this.getX()-1) && GUI.squares[i][j].getY()==this.getY()+1 && !colour){
                            return true;
                        }
                        if((GUI.squares[i][j].getX()==this.getX()+1 || GUI.squares[i][j].getX()==this.getX()-1) && GUI.squares[i][j].getY()==this.getY()-1 && colour){
                            return true;
                        }
                    }else if(GUI.squares[i][j].getCurrentPiece().getColour()==colour && GUI.squares[i][j].getCurrentPiece().movePossible(GUI.squares[i][j],this)){
                        return true;
                    }
                }
            }
        }
        return false;
        //A method to see if any enemy pieces are watching a certain square
    }
    
    public boolean squarePassable(boolean colour){
        if(this.getCurrentPiece()==null && colour && !squareWatchedWhite){
            return true;
        }
        
        if(this.getCurrentPiece()==null && !colour && !squareWatchedBlack){
            return true;
        }
        return false;
        //Checks if a square is passable for the king when castling
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
    
    public boolean getWatchedWhite(){
        return this.squareWatchedWhite;
    }
    
    public void setWatchedWhite(boolean watched){
        this.squareWatchedWhite=watched;
    }
    
    public boolean getWatchedBlack(){
        return this.squareWatchedBlack;
    }
    
    public void setWatchedBlack(boolean watched){
        this.squareWatchedBlack=watched;
    }
    
    public boolean getTemporaryBlock(){
        return this.temporaryBlock;
    }
    
    public void setTemporaryBlock(boolean blocked){
        this.temporaryBlock=blocked;
    }
    
    public boolean getTemporaryEmpty(){
        return this.temporaryEmpty;
    }
    
    public void setTemporaryEmpty(boolean empty){
        this.temporaryEmpty=empty;
    }
    //Getters and setters
}