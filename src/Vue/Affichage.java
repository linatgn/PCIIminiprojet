package Vue;

import Controler.Controler;
import Modele.Etat;

import javax.swing.*;
import java.awt.*;

public class Affichage extends JPanel{

    /*** Attributs de l'affichage ***/
    private Etat etat;
    private JFrame frame;

    /*** Constantes de la fentre d'affichage ***/
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    //Contructeur
    public Affichage(Etat etat){
        //Part1.Affichage depend d'un etat
        this.etat = etat;
        //Creation de la fenetre
        frame = new JFrame("Flappy");
        frame.setLocation(500, 250);
        //Ajout de la fenetre d'affichage
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.add(this);

        // On ajoute un mouseListener pour faire bouger l'ovale
        frame.addMouseListener(new Controler(this.etat, this));

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //On commence le thread pour l'affichage
        (new ThreadAffichage(this, this.etat)).start();
    }



    /**
     * Used to paint the oval on the screen
     * @param g
     */
    @Override
    public void paint(Graphics g){
        g.clearRect(0, 0, WIDTH, HEIGHT);
        super.paint(g);

        //Dessin de l'ovale
        int width = getWidth();
        int height = getHeight();

        //La largeur de l'ovale
        int widthOvale = width / 20;
        //Sa position en X
        int xOvale = 90;

        //La hauteur de l'ovalee
        int heightOvale = Etat.height_ovale;
        //Sa posisiton en Y
        int y_ovale = height - etat.getHauteur();

        //Selectionne la couleur et dessin
        g.setColor(Color.BLACK);
        g.drawOval(xOvale, y_ovale, widthOvale, heightOvale);


        //Dessin de la ligne brisee
        g.setColor(Color.RED);
        Point[] points = etat.getParcours();
        for(int i = 1; i < points.length; i++){
            //On trace un lignee entre deux points
            Point previousPoint = points[i-1];
            Point currentPoint = points[i];
            g.drawLine(previousPoint.x, previousPoint.y, currentPoint.x, currentPoint.y);
        }
    }
    public void endScreen() {
        JOptionPane.showMessageDialog(frame, "Score Total : " + etat.getScore(), "Perdu!", JOptionPane.PLAIN_MESSAGE);
        System.exit(0);
    }

}