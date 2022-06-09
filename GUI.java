/**
 * The UI which shows the game to the player
 *
 * @author Ike Hayes
 * @version 12/5/22
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.border.Border;
//Imports all libraries required
public class GUI extends JFrame implements ActionListener{
    Icon wPawn=new ImageIcon("Images\\wP.png");
    Icon bPawn=new ImageIcon("Images\\bP.png");
    //These two icons are used to indicate whose turn it is
    JPanel piecePanel=new JPanel();
    
    static Square[][] squares=new Square[8][8];
    //Setting up panel and array to store the pieces in
    
    public GUI() throws IOException{
        setTitle("Chess");
        this.getContentPane().setPreferredSize(new Dimension(1200,800));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //Creates the window with title "Chess". Also sets size and makes sure it closes

        final BufferedImage boardImage=ImageIO.read(new File("Images\\board.png"));
        //This image is used for the board
        
        Border blackBorder=BorderFactory.createLineBorder(Color.black);
        //Creates a plain black border which many of my components use
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
        
        JLabel statusLabel=new JLabel("White's turn", wPawn, SwingConstants.LEFT);
        statusLabel.setOpaque(true);
        statusLabel.setIconTextGap(5);
        statusLabel.setBackground(Color.WHITE);
        
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
        JTextArea moveList=new JTextArea("testing");
        moveList.setEditable(false);
        
        JPanel moveListPanel=new JPanel();
        moveListPanel.add(moveList, BorderLayout.CENTER);
        moveListPanel.setPreferredSize(new Dimension(300,400));
        moveListPanel.setMaximumSize(new Dimension(300,400));
        moveListPanel.setMinimumSize(new Dimension(300,400));
        moveListPanel.setBackground(Color.WHITE);
        moveListPanel.setBorder(blackBorder);
        //An area which shows the past moves in the game
        
        JButton resignButton=new JButton("Resign?");
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
        
        JButton drawButton=new JButton("Offer Draw?");
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
    private void standardGameSetup(){
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
    }
    public void actionPerformed(ActionEvent e){}
}