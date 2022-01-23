package Modele;

public class Voler extends Thread{

    private Etat etat;
    private final int delai = 100;

    public Voler(Etat e){
        this.etat = e;
    }

    public void resetCounter(){ this.interrupt();}

    /**
     * Permet de faire descendre l'ovale
     */
    @Override
    public void run(){
        boolean b = true;
        while(true){
            if(b) etat.moveDown();
            try{
                b = true;
                Thread.sleep(delai);
            } catch (InterruptedException e) {
                b = false;
            }
        }
    }


}