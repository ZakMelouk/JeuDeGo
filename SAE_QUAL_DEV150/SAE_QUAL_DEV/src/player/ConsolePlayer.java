package player;

import Jeu.Coord;
import Jeu.IPlayer;
import Jeu.Pierre;
import Jeu.Tableau;

public class ConsolePlayer implements IPlayer {


    @Override
    public Pierre play(Tableau tab, String couleur, String coord) {

        if(coord.length() != 2)
            throw new IllegalArgumentException("invalid color or coordinate");
        Pierre pierre;
        couleur = couleur.toUpperCase();
        coord = coord.toUpperCase();

        int x = coord.charAt(0) - 'A';
        int y = coord.charAt(1) -'1';
        pierre = new Pierre(couleur,x,y);
        return pierre;
    }

   /* @Override
    public Boolean SonTour(Tableau tab, String couleur) {
        return tab.getTour(couleur);
    }

    */
}
