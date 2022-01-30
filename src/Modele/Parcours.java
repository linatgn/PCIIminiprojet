package Modele;

import Vue.Affichage;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Parcours {

    //Une variable Random qui va initialiser les points du parcours
    private final Random rand = new Random();

    /**
     * le tableau des points du parcours
     **/
    public static ArrayList<Point> points = new ArrayList<Point>();


    /*** Constante pour l'avancement de la ligne ***/
    private final int SPEED = 2;

    //Constante pour la distance entre les points
    private final int distance = 120;
    
    //Variable pour le score
    private int score = 0;

    /*** Constructeur ***/
    public Parcours(){
        initParcours();
    }

    /**
     * Permet d'initialiser les positions des points du parcours
     */
    private void initParcours(){
        //Point de depart
        Point p = new Point(90 + (Affichage.WIDTH/20)/2,  + 500 + Etat.height_ovale/2);
        points.add(p);
        int x = p.x;
        //Points au hasard dans la fenetre
        while(x < Etat.max_width){
            //On change le x
            x += distance;
            //On creer un y random
            int y = Etat.min_height + rand.nextInt(Etat.max_height - Etat.min_height);
            points.add(new Point (x, y));
        }
    }

    /**
     * Creer un point au hasard
     * @Param un point (le dernier point de la liste point)
     * @return un point
     */
    private Point randPoint(Point p){
        return new Point(p.x + distance, Etat.min_height + rand.nextInt(Etat.max_height - Etat.min_height));
    }

    /**
     * Change la position des points
     * Creer et enleve les points pour une ligne infini
     * @return les points a afficher
     */
    public Point[] getPoints(){
        //On prend le premier point du parcours
        Point p = points.get(0);
        //On verifie qu'il est toujours dans les limites qu'on a defini
        if(p.x - SPEED < Etat.min_width) {
            //Si ce n'est pas le cas on l'enleve
            points.remove(p);
            //Et on rajoute un point a la fin
            points.add(randPoint(points.get(points.size() - 1)));
        }
        //Le tableau de points a rendre
        Point res[] = new Point[points.size()];
        int a = 0;
        //On parcours tous les points deja bouge
        for(Point po : points) {
            //On les rajoute au tableau qu'on passera a l'affichage
            res[a] = po;
            a = a+1;
        }
        return res;
    }

    /**
     * Getter pour l'ecart entre les X des points
     * @return l'ecart
     */
    public int getDistance(){
        return this.distance;
    }

    /**
     * GETTER pour le score
     * @return le score
     */
    public int getScore(){ return this.score; }

    /**
     * Permet de changer la position des points du parcours
     */
    public void setPosition(){
        score += SPEED;
        for (Point point : points) point.x -= SPEED;
    }
}