package Modele;

import Vue.Affichage;
import java.awt.*;
import java.util.ArrayList;

public class Etat {

    /*** Constantes ***/
    private final int jump = 15;
    private final int speed = 3;
    public static final int max_height = 500;
    public static final int min_height = 150;
    public static final int min_width = -120;
    public static final int max_width = 1000;
    public static final int height_ovale = 60;

    /*** Variable de la hauteur de l'ovale ***/
    private int hauteur = 100;

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
     * permet de changer la hauteur de l'ovale
     */
    public void jump(){
        hauteur = hauteur+jump;
        if(hauteur + height_ovale > max_height) hauteur = max_height - height_ovale;

        fallDown.resetCounter();
    }

    /**
     * Permet de faire tomber l'ovale
     */
    public void moveDown(){
        hauteur =hauteur- speed;
        if(hauteur < min_height)
            hauteur = min_height;
    }

    /**
     * Determine si il y a une collision
     * @return vrai si l'ovale est sorti de la ligne brisée
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
        float y = m * (xOvale) + c;

        //la hauteur de l'ovale
        int Y_OVALE = Affichage.HEIGHT - this.hauteur;

        //On regarde si notre y est entre les dimension de l'ovale
        return (y > Y_OVALE) && (y < Y_OVALE + height_ovale);
    }

    /**
     * Getter pour la hauteur
     * @return retourne la hauteur de l'ovale
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
    public int getScore() {
        return parcours.getScore();}
}