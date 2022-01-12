import java.awt.Dimension;
import javax.swing.*;
import java.awt.Graphics;

public class Affichage extends JPanel {
    public static final int LONG = 400;
    public static final int LARG = 500;
    public static final int largeur = 15 ;
    public static final int longueur = 100;
    public static final int  centre = 300 ;


    public Affichage() {
        setPreferredSize(new Dimension(LARG, LONG));
    }
    @Override
    public void paint(Graphics g) {
        g.drawOval(40, centre, largeur, longueur);

    }


}
