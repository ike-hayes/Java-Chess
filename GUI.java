/**
 * The UI which shows the game to the player
 *
 * @author Ike Hayes
 * @version 7/7/22
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.border.Border;
import java.util.HashMap;
//Imports all libraries required
public class GUI extends JFrame implements ActionListener{
    static Icon wPawn=new ImageIcon("Images\\wP.png");
    static Icon bPawn=new ImageIcon("Images\\bP.png");
    //These two icons are used to indicate whose turn it is
    static JPanel piecePanel=new JPanel();
    static JLabel statusLabel=new JLabel("White's turn", wPawn, SwingConstants.LEFT);
    static JTextArea moveList=new JTextArea();
    static Move lastMove;
    static String lastMoveString="";
    static int nMoves=0;
    static HashMap<Integer,String> convertXNotation=new HashMap<Integer,String>();
    static HashMap<Integer,String> convertYNotation=new HashMap<Integer,String>();
    
    static Square[][] squares=new Square[8][8];
    //Setting up panel and array to store the pieces in
    
    static JButton resignButton=new JButton("Resign?");
    static JButton drawButton=new JButton("Offer Draw?");
    
    static boolean drawOffered=false;
    static boolean drawButtonClicked;
    
    static float whiteWins=0;
    static float blackWins=0;
    
    static JDialog gameOver=new JDialog();
    static JLabel gameOverLabel=new JLabel("",SwingConstants.CENTER);
    
    public GUI() throws IOException{
        setTitle("Chess");
        this.getContentPane().setSize(new Dimension(1200,800));
        this.setResizable(false);
        this.setLocation(200,20);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //Creates the window with title "Chess". Also sets size and makes sure it closes
        
        final BufferedImage boardImage=ImageIO.read(new File("Images\\board.png"));
        //This image is used for the board
        
        Border blackBorder=BorderFactory.createLineBorder(Color.black);
        Font myFont=new Font("Consolas", Font.BOLD, 20);
        /* Creates a plain black border and font which many of my components use. The font is monospaced
         * meaning all the characters and spaces are the same pixel length, which is important so that 
         * the move list aligns properly
         */
            
        gameOverLabel.setFont(myFont);
        gameOverLabel.setSize(new Dimension(500,100));
        gameOver.add(gameOverLabel, SwingConstants.CENTER);
        gameOver.setTitle("Game over!");
        gameOver.setSize(new Dimension(500,100));
        gameOver.setLocationRelativeTo(this);
        //Sets up the dialog displayed when the game ends
        
        JPanel boardPanel=new JPanel(){
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(boardImage,0,0,null);
            }
        }; 
        boardPanel.setBounds(0,0,800,800);
        //Sets up the image of the board as well as a way to draw it
        
        piecePanel.setLayout(new GridLayout(8,8));
        piecePanel.setOpaque(false);
        piecePanel.setBounds(0,0,800,800);
        standardGameSetup();
        /* This panel holds all the pieces in a 8x8 layout
         * Once the panel is created it is populated with pieces in
         * the standard game set up.
         */
        
        JLayeredPane boardLayeredPane=new JLayeredPane();
        boardLayeredPane.setPreferredSize(new Dimension(800,800));
        boardLayeredPane.add(boardPanel,0,0);
        boardLayeredPane.add(piecePanel,1,0);
        //A layered pane is used to display the pieces over the board
        
        statusLabel.setOpaque(true);
        statusLabel.setIconTextGap(5);
        statusLabel.setBackground(Color.WHITE);
        statusLabel.setFont(myFont);
        
        JPanel statusLabelPanel=new JPanel();
        statusLabelPanel.setPreferredSize(new Dimension(300,80));
        statusLabelPanel.setMaximumSize(new Dimension(300,80));
        statusLabelPanel.setMinimumSize(new Dimension(300,80));
        statusLabelPanel.setBackground(Color.WHITE);
        statusLabelPanel.add(statusLabel);
        statusLabelPanel.setBorder(blackBorder);
        /* This label shows whos turn it is as well
         * as any statues such as check, checkmate or the
         * end of a game. For these UI sections, each label or 
         * button is placed into its own JPanel to be sized before being 
         * added to the layout.
         */
        
        moveList.setEditable(false);
        moveList.setFont(myFont);

        JScrollPane scrollBar=new JScrollPane (moveList);
        scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollBar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollBar.setBounds(0,0,300,400);
        scrollBar.setBorder(blackBorder);
        //The move list can get quite long, so a scroll bar is used to browse it
        
        JPanel moveListPanel=new JPanel();
        moveListPanel.setLayout(null);
        moveListPanel.add(scrollBar);
        moveListPanel.setPreferredSize(new Dimension(300,400));
        moveListPanel.setMaximumSize(new Dimension(300,400));
        moveListPanel.setMinimumSize(new Dimension(300,400));
        moveListPanel.setBackground(Color.WHITE);
        moveListPanel.setBorder(blackBorder);
        //An area which shows the past moves in the game
        
        resignButton.setPreferredSize(new Dimension(150,100));
        resignButton.setMaximumSize(new Dimension(150,100));
        resignButton.setMinimumSize(new Dimension(150,100));
        resignButton.setBackground(new Color(235,235,235));
        resignButton.setBorder(blackBorder);
        resignButton.addActionListener(this);
        
        JPanel resignButtonPanel=new JPanel();
        resignButtonPanel.setLayout(new BoxLayout(resignButtonPanel, BoxLayout.PAGE_AXIS));
        resignButtonPanel.add(resignButton);
        resignButtonPanel.setPreferredSize(new Dimension(150,100));
        resignButtonPanel.setMaximumSize(new Dimension(150,100));
        resignButtonPanel.setMinimumSize(new Dimension(150,100));
        //A button that gives the player an option to resign
        
        drawButton.setPreferredSize(new Dimension(150,100));
        drawButton.setMaximumSize(new Dimension(150,100));
        drawButton.setMinimumSize(new Dimension(150,100));
        drawButton.setBackground(new Color(112,133,183));
        drawButton.setBorder(blackBorder);
        drawButton.addActionListener(this);
        
        JPanel drawButtonPanel=new JPanel();
        drawButtonPanel.setLayout(new BoxLayout(drawButtonPanel, BoxLayout.PAGE_AXIS));
        drawButtonPanel.add(drawButton);
        drawButtonPanel.setPreferredSize(new Dimension(150,100));
        drawButtonPanel.setMaximumSize(new Dimension(150,100));
        drawButtonPanel.setMinimumSize(new Dimension(150,100));
        //This button lets one player offer the other one a draw
        
        JPanel buttonPane=new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.add(resignButtonPanel);
        buttonPane.add(Box.createRigidArea(new Dimension(0,5)));
        buttonPane.add(drawButtonPanel);
        //This is used to lay out the draw and resign buttons next to each other
        
        JPanel UIPane=new JPanel();
        UIPane.setLayout(new BoxLayout(UIPane, BoxLayout.PAGE_AXIS));
        UIPane.add(statusLabelPanel);
        UIPane.add(Box.createRigidArea(new Dimension(0,5)));
        UIPane.add(moveListPanel);
        UIPane.add(Box.createRigidArea(new Dimension(0,5)));
        UIPane.add(buttonPane);
        UIPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        //This contains all of the UI except for the board and pieces, and lays them out properly
        
        JPanel container=new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.LINE_AXIS));
        container.add(boardLayeredPane);
        container.add(UIPane);
        //Container holds everything in the board layered pane and the UI pane, which is all components
        
        this.add(container);
        this.pack();
        repaint();
        this.setVisible(true);
        //Finally, the container is added to the frame and it is all set to visible
    }
    
    private static void standardGameSetup(){
        squares[0][0]=new Square(0,0,new Rook(true));
        squares[1][0]=new Square(1,0,new Knight(true));
        squares[2][0]=new Square(2,0,new Bishop(true));
        squares[3][0]=new Square(3,0,new Queen(true));
        squares[4][0]=new Square(4,0,new King(true));
        squares[5][0]=new Square(5,0,new Bishop(true));
        squares[6][0]=new Square(6,0,new Knight(true));
        squares[7][0]=new Square(7,0,new Rook(true));
        for(int i=0;i<8;i++){
            squares[i][1]=new Square(i,1,new Pawn(true));
        }
        //Creates 8 white pawns in a row
        for (int i=2;i<6;i++) {
            for (int j=0;j<8;j++) {
                squares[j][i]=new Square(j,i,null);
            }
        }
        //Fills in all the squares without pieces
        for(int i=0;i<8;i++){
            squares[i][6]=new Square(i,6,new Pawn(false));
        }
        //Creates 8 black pawns in a row
        squares[0][7]=new Square(0,7,new Rook(false));
        squares[1][7]=new Square(1,7,new Knight(false));
        squares[2][7]=new Square(2,7,new Bishop(false));
        squares[3][7]=new Square(3,7,new Queen(false));
        squares[4][7]=new Square(4,7,new King(false));
        squares[5][7]=new Square(5,7,new Bishop(false));
        squares[6][7]=new Square(6,7,new Knight(false));
        squares[7][7]=new Square(7,7,new Rook(false)); 
        //Creating squares and filling them in with pieces in the default chess layout
        
        for(int i=7;i>-1;i--){
            for(int j=0;j<8;j++){
                piecePanel.add(squares[j][i].buttonPanel);
            }
        }
        //All these squares contain JPanels showing the pieces, which are then added to the board
        
        convertXNotation.put(0,"a");
        convertXNotation.put(1,"b");
        convertXNotation.put(2,"c");
        convertXNotation.put(3,"d");
        convertXNotation.put(4,"e");
        convertXNotation.put(5,"f");
        convertXNotation.put(6,"g");
        convertXNotation.put(7,"h");
        
        convertYNotation.put(0,"1");
        convertYNotation.put(1,"2");
        convertYNotation.put(2,"3");
        convertYNotation.put(3,"4");
        convertYNotation.put(4,"5");
        convertYNotation.put(5,"6");
        convertYNotation.put(6,"7");
        convertYNotation.put(7,"8");
        //HashMaps used to convert from x and y values used internally to proper chess notation
    }
    
    public static void switchIcon(){
        if(statusLabel.getIcon().equals(wPawn)){
            statusLabel.setIcon(bPawn);
            if(Game.blackCheckmated){
                statusLabel.setIcon(null);
                whiteWins+=1;
                statusLabel.setText(whiteWins+" - "+blackWins);
                gameOverLabel.setText("White wins by checkmate!");
                gameOver.setVisible(true);
            }else if(Game.blackInCheck){
                statusLabel.setText("Black is in check");
            }else{
                statusLabel.setText("Black's turn");
            }
        }else{
            statusLabel.setIcon(wPawn);
            if(Game.whiteCheckmated){
                statusLabel.setIcon(null);
                blackWins+=1;
                statusLabel.setText(whiteWins+" - "+blackWins);
                gameOverLabel.setText("Black wins by checkmate!");
                gameOver.setVisible(true);
            }else if(Game.whiteInCheck){
                statusLabel.setText("White is in check");
            }else{
                statusLabel.setText("White's turn");
            }
        }
        if(Game.stalemate){
            statusLabel.setIcon(null);
            whiteWins+=0.5;
            blackWins+=0.5;
            statusLabel.setText(whiteWins+" - "+blackWins);
            gameOverLabel.setText("Draw by stalemate!");
            gameOver.setVisible(true);
        }
        /*A win is represented by one point to the winner, and a draw is half a point to both players. Text
         * will also be displayed that signifys the end of the game.
         */
        if(drawOffered){
            drawButton.setText("Accept draw?");
        }else{
            drawButton.setText("Offer draw?");
        }
        //Updates the icon panel with whos turn it is, as well as if they are in check. Also updates the draw button
    }
    
    public static void updateMoveList(){
        lastMoveString="";
        if(lastMove.shortCastle){
            lastMoveString="O-O";
        }else if(lastMove.longCastle){
            lastMoveString="O-O-O";
        }else if(lastMove.getPromotion()){
            lastMoveString=convertXNotation.get(lastMove.getEndX());
            lastMoveString+=convertYNotation.get(lastMove.getEndY());
            lastMoveString+="=";
            switch(lastMove.getEnd().getCurrentPiece().getClass().getSimpleName()){
                case("Queen"): lastMoveString+="Q";
                               break;
                case("Bishop"): lastMoveString+="B";
                                break;
                case("Knight"): lastMoveString+="N";
                                break;
                case("Rook"): lastMoveString+="R";
                              break;
            }
        }else{
            switch(lastMove.getPiece().getClass().getSimpleName()){
                case("King"): lastMoveString="K";
                              break;
                case("Queen"): lastMoveString="Q";
                               break;
                case("Bishop"): lastMoveString="B";
                                break;
                case("Knight"): lastMoveString="N";
                                break;
                case("Rook"): lastMoveString="R";
                              break;
            }
            if(lastMove.getCapture() && lastMove.getPiece().getClass().getSimpleName().equals("Pawn")){
                lastMoveString+=convertXNotation.get(lastMove.getStartX());
                lastMoveString+="x";
            }else if(lastMove.getCapture()){
                lastMoveString+="x";
            }
            lastMoveString+=convertXNotation.get(lastMove.getEndX());
            lastMoveString+=convertYNotation.get(lastMove.getEndY());
        }
        if(lastMove.getColour()){
            nMoves++;
            if(Game.blackCheckmated){
                lastMoveString+="#";
            }else if(Game.blackInCheck){
                lastMoveString+="+";
            }
            for(int i=lastMoveString.length();i<10;i++){
                lastMoveString+=" ";
            }
            //Spaces are added to the end of white moves to make the length of the string always the same
            moveList.append(nMoves+". "+lastMoveString);
        }else{
            if(Game.whiteCheckmated){
                lastMoveString+="#";
            }else if(Game.whiteInCheck){
                lastMoveString+="+";
            }
            moveList.append(lastMoveString+"\n");
        }
        /* The raw information from the program needs to be converted to chess notation to go on the move 
         * list. Chess notation contains information about which piece moved, the square it goes to, and
         * whether the move is a capture or check. Special notation is used for castling and promotions.
         */
    }
    
    public void actionPerformed(ActionEvent e){
        if(Game.gameActive){
            if(e.getSource()==resignButton){
                if(Game.whiteTurn){
                    Game.whiteResigns=true;
                    statusLabel.setIcon(null);
                    blackWins+=1;
                    statusLabel.setText(whiteWins+" - "+blackWins);
                    gameOverLabel.setText("Black wins by resignation!");
                    gameOver.setVisible(true);
                }else{
                    Game.blackResigns=true;
                    statusLabel.setIcon(null);
                    whiteWins+=1;
                    statusLabel.setText(whiteWins+" - "+blackWins);
                    gameOverLabel.setText("White wins by resignation!");
                    gameOver.setVisible(true);
                }
                Game.gameActive=false;
            }else if(e.getSource()==drawButton){
                if(!drawButtonClicked){
                    if(!drawOffered){
                       drawOffered=true; 
                    }else{
                        Game.gameActive=false;
                        statusLabel.setIcon(null);
                        whiteWins+=0.5;
                        blackWins+=0.5;
                        statusLabel.setText(whiteWins+" - "+blackWins);
                        gameOverLabel.setText("Draw by agreement!");
                        gameOver.setVisible(true);
                    }
                    drawButtonClicked=true;
                }
            }
        }
        //The buttons that control resiging and offering a draw to the other player
    }
}