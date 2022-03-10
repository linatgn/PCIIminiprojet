import Vue.Affichage;
import Modele.Etat;


public class Main  {

    /** la classe main construit un objet de chaque classe et fait le lien entre le modele, la vue et
     * le controleur puis elle crée un objet jframe dans laquelle elle ajoute la vue
     * @param args
     */
    public static void main(String [] args) {
        Etat etat = new Etat();
        Affichage vue = new Affichage(etat);
    }


}