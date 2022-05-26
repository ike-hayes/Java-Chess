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
public class GUI extends JFrame implements ActionListener, MouseListener{
    Icon wPawn=new ImageIcon("Images\\wP.png");
    Icon wRook=new ImageIcon("Images\\wR.png");
    Icon wKnight=new ImageIcon("Images\\wN.png");
    Icon wBishop=new ImageIcon("Images\\wB.png");
    Icon wKing=new ImageIcon("Images\\wK.png");
    Icon wQueen=new ImageIcon("Images\\wQ.png");
    
    Icon bPawn=new ImageIcon("Images\\bP.png");
    Icon bRook=new ImageIcon("Images\\bR.png");
    Icon bKnight=new ImageIcon("Images\\bN.png");
    Icon bBishop=new ImageIcon("Images\\bB.png");
    Icon bKing=new ImageIcon("Images\\bK.png");
    Icon bQueen=new ImageIcon("Images\\bQ.png");
    public GUI() throws IOException{
        setTitle("Chess");
        this.getContentPane().setPreferredSize(new Dimension(1200,800));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        final BufferedImage boardImage=ImageIO.read(new File("Images\\board.png"));

        Border blackBorder=BorderFactory.createLineBorder(Color.black);
        
        JPanel boardPanel=new JPanel(){
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(boardImage,0,0,null);
            }
        };
        boardPanel.setBounds(0,0,800,800);
        
        JPanel piecePane=new JPanel();
        piecePane.setLayout(null);
        piecePane.setOpaque(false);
        piecePane.setBounds(0,0,800,800);
        piecePane.setPreferredSize(new Dimension(800,800));
        piecePane.add(new PiecePanel(wPawn,100,100));
        piecePane.add(new PiecePanel(wPawn,500,400));
        
        
        JLayeredPane boardLayeredPane=new JLayeredPane();
        boardLayeredPane.setBounds(0,0,800,800);
        boardLayeredPane.add(boardPanel,0,0);
        boardLayeredPane.add(piecePane,1,0);

        JLabel statusLabel=new JLabel("White's turn", wPawn, SwingConstants.LEFT);
        statusLabel.setOpaque(true);
        statusLabel.setIconTextGap(5);
        statusLabel.setBackground(Color.WHITE);
        
        JPanel statusLabelPanel = new JPanel();
        statusLabelPanel.setPreferredSize(new Dimension(300,80));
        statusLabelPanel.setMaximumSize(new Dimension(300,80));
        statusLabelPanel.setMinimumSize(new Dimension(300,80));
        statusLabelPanel.setBackground(Color.WHITE);
        statusLabelPanel.add(statusLabel);
        statusLabelPanel.setBorder(blackBorder);
        
        JTextArea moveList=new JTextArea("testing");
        moveList.setEditable(false);
        
        JPanel moveListPanel=new JPanel();
        moveListPanel.add(moveList, BorderLayout.CENTER);
        moveListPanel.setPreferredSize(new Dimension(300,400));
        moveListPanel.setMaximumSize(new Dimension(300,400));
        moveListPanel.setMinimumSize(new Dimension(300,400));
        moveListPanel.setBackground(Color.WHITE);
        moveListPanel.setBorder(blackBorder);
        
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
        
        JPanel buttonPane=new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.add(resignButtonPanel);
        buttonPane.add(Box.createRigidArea(new Dimension(0,5)));
        buttonPane.add(drawButtonPanel);
        
        JPanel UIPane=new JPanel();
        UIPane.setLayout(new BoxLayout(UIPane, BoxLayout.PAGE_AXIS));
        UIPane.add(statusLabelPanel);
        UIPane.add(Box.createRigidArea(new Dimension(0,5)));
        UIPane.add(moveListPanel);
        UIPane.add(Box.createRigidArea(new Dimension(0,5)));
        UIPane.add(buttonPane);
        UIPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.LINE_AXIS));
        container.add(boardLayeredPane);
        container.add(UIPane);
         
        this.add(container);
        this.pack();
        repaint();
        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}
}