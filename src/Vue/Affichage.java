package Vue;

import Modele.Etat;

import javax.swing.*;
import java.awt.*;

public class Affichage extends JPanel {

    /**
     * Attributs de l'affichage
     **/
    private Etat etat;
    private JFrame frame;

    /**
     * Constantes de la fenetre d'affichage
     **/
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    //Contructeur
    public Affichage(Etat etat) {
        this.etat = etat;
        //Creation de la fenetre
        frame = new JFrame("Test dessin");
        frame.setLocation(500, 250);
        //Ajout de la fenetre d'affichage
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.add(this);

        (new ThreadAffichage(this)).start();
    }


    /**
     * utiliser pour dessiner l'ovale sur l'écran
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        //Dessin de l'ovale
        int width = getWidth();
        int height = getHeight();

        /**On calcule une rapport pour que l'ovale soit toujours
         proportionnelle a la fenetre**/

        float rapportHauteur = height / (float) (Etat.max_height - Etat.min_height);

        int widthOvale = width / 20;
        int xOvale = width / 4;

        int heightOvale = (int) (etat.height_ovale * rapportHauteur);
        int yOvale = (int) (height - (etat.getHauteur() - Etat.min_height) * rapportHauteur - heightOvale);

        //Selectionne la couleur et le dessin
        g.setColor(Color.BLACK);
        g.drawOval(xOvale, yOvale, widthOvale, heightOvale);

        //Dessin de la ligne brisee

        //Dessin de la ligne brisee
        g.setColor(Color.RED);
        Point[] points = etat.getParcours();
        for (int i = 1; i < points.length; i++) {
            //On trace un lignee entre deux points
            Point previousPoint = points[i - 1];
            Point currentPoint = points[i];
            g.drawLine(previousPoint.x, previousPoint.y, currentPoint.x, currentPoint.y);
        }


    }


    /**
     * Permet de repeindre toutes les 20 milsec
     */
    class ThreadAffichage extends Thread {

        private final int delai = 20;
        private Affichage affichage;

        public ThreadAffichage(Affichage affichage) {
            this.affichage = affichage;
        }

        @Override
        public void run() {
            while (true) {
                affichage.repaint();
                try {
                    Thread.sleep(delai);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}





