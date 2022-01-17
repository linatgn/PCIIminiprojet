package Controler;

import Vue.Affichage;
import Modele.Etat;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Controler implements MouseListener {

    //Attributs du controleur
    private Etat etat;
    private Affichage affichage;

    //Constructeur
    public Controler(Etat etat, Affichage affichage){
        this.etat = etat;
        this.affichage = affichage;
    }

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e the event to be processed
     *
     * make a jump when the mouse button has been clicked on the panel
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        etat.jump();
        affichage.change();
    }
    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


}