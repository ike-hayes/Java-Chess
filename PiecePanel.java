/**
 * Write a description of class PieceButton here.
 *
 * @author Ike Hayes
 * @version (a version number or a date)
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.border.Border;
public class PiecePanel extends JPanel implements ActionListener{
    final int buttonWidth=100;
    final int buttonHeight=100;
    /**
     * Constructor for objects of class PieceButton
     */
    public PiecePanel(Icon icon,int x,int y){
        this.setBounds(x,y,buttonWidth,buttonHeight);
        this.setBackground(new Color(0,0,0,0));
        JButton pieceButton=new JButton(icon);
        pieceButton.setBounds(x,y,buttonWidth,buttonHeight);
        pieceButton.setOpaque(false);
        pieceButton.setContentAreaFilled(false);
        pieceButton.setBorderPainted(false);
        this.add(pieceButton);
    }
    public void actionPerformed(ActionEvent e){}
}