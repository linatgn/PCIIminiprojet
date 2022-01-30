package Vue;

import Modele.Etat;

/**
 * Permet de repaindre toutes les 20 milsec
 */
class ThreadAffichage extends Thread {

    private final int DELAY = 150;
    private Affichage affichage;
    private Etat etat;


    public ThreadAffichage(Affichage affichage, Etat etat) {
        this.etat = etat;
        this.affichage = affichage;
    }


    /**
     * Permet de redessiner au bout d'un certain temps
     */
    @Override
    public void run() {
        //on dessine tant qu'on a pas perdu
        while (etat.testPerdu()) {
            affichage.revalidate();
            affichage.repaint();
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //Affichage de l'ecran de fin de la partie
        affichage.endScreen();
    }
}