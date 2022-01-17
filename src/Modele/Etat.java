package Modele;
public class Etat {

    /*** Constantes ***/
    private final int jump = 15;
    public int HEIGHT_OVALE = 60;

    public Etat () {
    }

    /**
     * saut de l'ovale
     */
    public void jump() {
        HEIGHT_OVALE += jump;
    }
    /** on guette la hauteur **/
    public int gethauteur() {
        return HEIGHT_OVALE;
    }
}


