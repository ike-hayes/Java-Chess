
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
    public GUI() throws IOException{
        setTitle("Chess");
        this.getContentPane().setPreferredSize(new Dimension(1200,800));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.LINE_AXIS));

        final BufferedImage boardImage=ImageIO.read(new File("Images\\board.png"));

        Border blackBorder=BorderFactory.createLineBorder(Color.black);
        
        JPanel boardPanel=new JPanel(){
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(boardImage,0,0,null);
            }
        };
        boardPanel.setPreferredSize(new Dimension(800,800));
        container.add(boardPanel);
        
        Icon wPawn=new ImageIcon("Images\\wP.png");
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
        resignButton.addActionListener(this);
        
        JPanel resignButtonPanel=new JPanel();
        resignButtonPanel.setBackground(Color.ORANGE);
        resignButtonPanel.setPreferredSize(new Dimension(150,100));
        resignButtonPanel.setMaximumSize(new Dimension(150,100));
        resignButtonPanel.setMinimumSize(new Dimension(150,100));
        
        JPanel buttonPane=new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.add(resignButtonPanel);
        buttonPane.add(Box.createRigidArea(new Dimension(0,5)));
        
        JPanel UIPane=new JPanel();
        UIPane.setLayout(new BoxLayout(UIPane, BoxLayout.PAGE_AXIS));
        UIPane.add(statusLabelPanel);
        UIPane.add(Box.createRigidArea(new Dimension(0,5)));
        UIPane.add(moveListPanel);
        UIPane.add(Box.createRigidArea(new Dimension(0,5)));
        UIPane.add(buttonPane);
        UIPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        container.add(UIPane);
        
        this.add(container);
        this.pack();
        repaint();        
    }
    public void actionPerformed(ActionEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}
}