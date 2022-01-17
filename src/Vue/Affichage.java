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
    public static final int WIDTH = 400;
    public static final int HEIGHT = 500;

  //Contructeur
    public Affichage(Etat etat){


        this.etat = etat;
        //Creation de la fenetre
        frame = new JFrame("Test dessin");
        frame.setLocation(500, 250);

        //Ajout de la fenetre d'affichage
        setPreferredSize(new Dimension(WIDTH, HEIGHT));



    }
    public void change(){
        revalidate();
        repaint();
    }

    /**
     * Used to paint the oval on the screen
     * @param g
     */
    @Override
    public void paint(Graphics g){
        super.paint(g);
        int hauteur = etat.gethauteur();

        int widthOvale = 50;
        int x = 50;

        int heightOvale = 100;
        int y = 300;

        //Selectionne la couleur et dessin
        g.setColor(Color.BLACK);
        g.drawOval(x, HEIGHT - hauteur -heightOvale, widthOvale, heightOvale);

    }

}

