/**
 * The squares on the chess board that will hold pieces
 *
 * @author Ike Hayes
 * @version 10/5/22
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Square implements ActionListener{
    private Piece currentPiece;
    private int x;
    private int y;
    
    final int buttonWidth=100;
    final int buttonHeight=100;
    JPanel buttonPanel=new JPanel();
    JButton pieceButton=new JButton();
    public Square(int x, int y, Piece piece){
        this.currentPiece=piece;
        this.x=x;
        this.y=y;
        createButton();
        if(this.currentPiece!=null){
            pieceButton.setIcon(this.currentPiece.getPieceIcon());
        }
    }
    
    public void actionPerformed(ActionEvent e){
        if((Game.selectedPiece==null && this.currentPiece!=null) || (Game.selectedPiece.getColour()==this.currentPiece.getColour())){
            Game.selectedPiece=this.currentPiece;
        }else if(Game.selectedPiece!=null){
            this.currentPiece=Game.selectedPiece;
            pieceButton.setIcon(this.currentPiece.getPieceIcon());
        }
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