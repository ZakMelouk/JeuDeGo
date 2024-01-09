package player;

import Jeu.Coord;
import Jeu.IPlayer;
import Jeu.Pierre;
import Jeu.Tableau;

import java.util.ArrayList;
import java.util.Random;

public class RandomPlayer implements IPlayer {
    //Couleur couleur;

    @Override
    public Pierre play(Tableau tab, String couleur, String coord){
        ArrayList<Coord> CoordPasOccupées = new ArrayList<>();
        for(int i = 0; i< tab.getTaille(); ++i){
            for(int j = 0; j < tab.getTaille() ; ++j){
                if(!(tab.contientPierre(new Coord(i,j)))){
                    if((tab.liberte(new Pierre(couleur, i, j), new ArrayList<>()) == 0 && tab.getLibertéVoisins(new Pierre(couleur, i, j))==0)){
                        return (new Pierre(couleur,i,j));
                    }
                    if((tab.liberte(new Pierre(couleur, i, j),new ArrayList<>())!=0)){
                        CoordPasOccupées.add(new Coord(i,j));
                    }
                }
            }
        }
        if(CoordPasOccupées.size()==0){
            throw new IllegalArgumentException();
        }
        Random random = new Random();
        int MesCoord = random.nextInt(CoordPasOccupées.size());
        int x = CoordPasOccupées.get(MesCoord).getX();
        int y = CoordPasOccupées.get(MesCoord).getY();
        couleur = couleur.toUpperCase();
        Pierre pierre = new Pierre(couleur,x,y);
        return pierre;
    }

   /* @Override
    public Boolean SonTour(Tableau tab, String couleur) {
        return tab.getTour(couleur);
    }

    */
}
