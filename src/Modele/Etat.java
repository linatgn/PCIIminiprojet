package Modele;
import java.awt.*;
import java.util.ArrayList;

import Vue.Affichage;

public class Etat {

    /*** Constantes ***/
    private final int jump = 15;
    private final int  speed = 3;
    public static final int max_height = 500;
    public static final int min_height = 150;
    public static final int height_ovale = 60;
    public static final int min_width = -100;





    /*** Variable de la hauteur de l'ovale ***/
    private int hauteur = 300;

    /*** La thread pour pouvoir faire retomber l'ovale ***/
    private Voler fallDown;

    /*** Le parcours ***/
    private Parcours parcours;


    public Etat() {
        //On commence le thread pour faire tomber l'ovale
        this.fallDown = new Voler(this);
        fallDown.start();

        //Initialisation du parcours
        parcours = new Parcours();


    }

    /**
     * permet de changer la hauteur de l'ovale
     */
    public void jump() {
        hauteur += jump;
        if (hauteur + height_ovale > max_height) hauteur = max_height - height_ovale;

        fallDown.resetCounter();
    }

    /**
     * Permet de faire tomber l'ovale
     */
    public void moveDown() {
        hauteur -=  speed;
        if (hauteur < min_height)
            hauteur = min_height;
    }


    /**
     * Getter pour la hauteur
     *
     * @return retourne la hauteur de l'ovale
     */
    public int getHauteur() {
        return this.hauteur;
    }

    /**
     * Getter pour le parcours
     * @return un tableau de points
     */
    public Point[] getParcours() {
        return parcours.getPoints();
    }

}