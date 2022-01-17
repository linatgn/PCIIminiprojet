import Vue.Affichage;
import Modele.Etat;
import Controler.Controler;

import javax.swing.*;

public class Main extends JFrame {

    /** la classe main construit un objet de chaque classe et fait le lien entre le modele, la vue et
     * le controleur puis elle crée un objet jframe dans laquelle elle ajoute la vue
     * @param args
     */
    public static void main(String [] args) {
        JFrame frame = new JFrame();
        Etat etat = new Etat();
        Affichage affichage = new Affichage(etat);
        //Ajout du controleur
        Controler controler = new Controler(etat, affichage);
        affichage.addMouseListener(controler);
        frame.add(affichage);

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}