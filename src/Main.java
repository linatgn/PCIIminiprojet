import javax.swing.*;

public class Main {
    public static void main(String [] args) {

        JFrame test = new JFrame("Test dessin");
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        test.add(new Affichage());
        test.pack();
        test.setVisible(true);
    }


}