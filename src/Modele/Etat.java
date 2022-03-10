package Modele;

import Vue.Affichage;
import java.awt.*;
import java.util.ArrayList;

public class Etat {

    /*** Constantes ***/
    private final int jump = 15;
    private final int speed = 3;
    public static final int max_height = 550;
    public static final int min_height = 100;
    public static final int min_width = -120;
    public static final int max_width = 1200;
    public static final int height_ovale = 60;

    /*** Varaible de la hauteur de l'ovale ***/
    private int hauteur = 0;

    /*** La thread pour pouvoir faire retomber l'ovale ***/
    private Voler fallDown;

    /*** Le parcours ***/
    private Parcours parcours;

    public Etat(){
        //On commence le thread pour faire tomber l'ovale
        this.fallDown = new Voler(this);
        fallDown.start();

        //Initialisation du parcours
        parcours = new Parcours();
        //Commencement du thread pour faire avancer le parcours
        (new Avancer(this.parcours, this)).start();
    }

    /**
     * Change the height of the oval with JUMP_HEIGHT
     */
    public void jump(){
        hauteur += jump;
        if(hauteur + height_ovale > max_height) hauteur = max_height - height_ovale;

        fallDown.resetCounter();
    }

    /**
     * Permet de faire tomber l'ovale
     */
    public void moveDown(){
        hauteur -= speed;
        if(hauteur < min_height)
            hauteur = min_height;
    }

    /**
     * Permet de savoir s'il y a collision ou pas
     * @return vrai si c'est le cas
     */
    public boolean testPerdu() {
        //La liste des points du parcours
        ArrayList<Point> point = Parcours.points;

        //L'abscisse de l'ovale
        int xOvale = 90 + (Affichage.WIDTH/20)/2;
        //Les points qui sont avant et apres notres ovale
        Point pp = new Point();
        Point np = new Point();

        //La selection des points qui entourent l'ovale
        for(int i = 1; i < point.size(); i++){
            //Si le point est apres notre ovale
            if(point.get(i).x > xOvale) {
                pp = point.get(i-1);
                np = point.get(i);
                //On s'arrete des qu'on a trouve les deux points
                break;
            }
        }

        //Calcule de la pente
        float m = (np.y - pp.y) / (float)(np.x - pp.x);
        float c = pp.y - m * pp.x;

        //Calcule du y
        float y = m * (90 + (Affichage.WIDTH/20)/2) + c;

        //la hauteur de l'ovale
        int Y_OVALE = Affichage.HEIGHT - this.hauteur;

        //On regarde si notre y est entre les dimension de l'ovale
        return (y > Y_OVALE) && (y < Y_OVALE + height_ovale);
    }

    /**
     * Getter for HAUTEUR
     * @return HAUTEUR (height of the oval)
     */
    public int getHauteur(){
        return this.hauteur;
    }

    /**
     * Getter pour le parcours
     * @return un tableau de points
     */
    public Point[] getParcours() {
        return parcours.getPoints();
    }

    /**
     * Getter pour le score
     * @return le score du parcours
     */
    public int getScore() { return parcours.getScore();}
}
