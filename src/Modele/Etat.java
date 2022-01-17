package Modele;
public class Etat {

    /*** Constantes ***/
    private final int jump = 15;

    /*** Variable de la hauteur de l'ovale ***/
    private int hauteur = 0;

    /**
     * Change la longueur de l'ovale
     */
    public void jump() {
        hauteur += jump;

    }
}


